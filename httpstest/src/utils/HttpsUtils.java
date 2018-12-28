/**
 * 
 */
package utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject; 
/**   
 * @ClassName:  HttpsUtils   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018�?9�?1�? 上午10:36:15   
 *      
 */
public class HttpsUtils {
	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 200000;
 
	static {
		// 设置连接�?
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大�?
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
		// Validate connections after 1 sec of inactivity
		connMgr.setValidateAfterInactivity(1000);
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超�?
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
 
		requestConfig = configBuilder.build();
	}
 
	/**
	 * 发�?? GET 请求（HTTP），不带输入数据
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject doGet(String url) {
		return doGet(url, new HashMap<String, Object>());
	}
 
	/**
	 * 发�?? GET 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static JSONObject doGet(String url, Map<String, Object> params) {
		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		apiUrl += param;
		String result = null;
		HttpClient httpClient = null;
		if (apiUrl.startsWith("https")) {
			httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
					.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		} else {
			httpClient = HttpClients.createDefault();
		}
		try {
			HttpGet httpGet = new HttpGet(apiUrl);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = StreamUtil.inputStream2String(instream, "UTF-8");
			}
		} catch (IOException e) {
//			LoggerUtils.error(logger, e, e.getMessage());
//			throw new ServiceException(e.getMessage());
			e.printStackTrace();
		}
		return JSON.parseObject(result);
	}
 
	/**
	 * 发�?? POST 请求（HTTP），不带输入数据
	 * 
	 * @param apiUrl
	 * @return
	 */
	public static JSONObject doPost(String apiUrl) {
		return doPost(apiUrl, new HashMap<String, Object>());
	}
 
	/**
	 * 发�?? POST 请求，K-V形式
	 * 
	 * @param apiUrl API接口URL
	 * @param params 参数map
	 * @return
	 */
	public static JSONObject doPost(String apiUrl, Map<String, Object> params) {
		CloseableHttpClient httpClient = null;
		if (apiUrl.startsWith("https")) {
			httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
					.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		} else {
			httpClient = HttpClients.createDefault();
		}
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		JSONObject rtnJson = new JSONObject();

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
			httpStr = httpStr.replace("\\\"", "'").replace("\"", "");
			rtnJson = JSONObject.parseObject(httpStr);
			
		} catch (Exception e) {
//			LoggerUtils.error(logger, e, e.getMessage());
//			throw new ServiceException(e.getMessage());
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
//					LoggerUtils.error(logger, e, e.getMessage());
//					throw new ServiceException(e.getMessage());
					System.out.println(22);
					e.printStackTrace();
				}
			}
		}
		return rtnJson;
	}
 
	/**
	 * 发�?? POST 请求，JSON形式
	 * 
	 * @param apiUrl
	 * @param json
	 *            json对象
	 * @return
	 */
	public static JSONObject doPost(String apiUrl, Object json) {
		CloseableHttpClient httpClient = null;
		if (apiUrl.startsWith("https")) {
			httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
					.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		} else {
			httpClient = HttpClients.createDefault();
		}
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
 
		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
//			LoggerUtils.error(logger, e, e.getMessage());
//			throw new ServiceException(e.getMessage());
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
//					LoggerUtils.error(logger, e, e.getMessage());
//					throw new ServiceException(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return JSON.parseObject(httpStr);
	}
 
	/**
	 * 创建SSL安全连接
	 * 
	 * @return
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
 
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
 
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});
		} catch (GeneralSecurityException e) {
//			LoggerUtils.error(logger, e, e.getMessage());
//			throw new ServiceException(e.getMessage());
			e.printStackTrace();
		}
		return sslsf;
	}

	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>();
		params.put("addDevice_NotifyMessage", "343");
		doPost("https://129.28.69.163:443/deviceAdded", params);
	}
}
