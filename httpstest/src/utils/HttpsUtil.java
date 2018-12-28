package utils;


/**
 * @author dbr
 *
 */
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author dbr
 *HttpClient 实现证书验证https
 */
public class HttpsUtil {

	private static final String KEY_STORE_TYPE_JKS = "jks";
	private static final String KEY_STORE_TYPE_P12 = "PKCS12";
	private static final String SCHEME_HTTPS = "https";
	private static final int HTTPS_PORT = 443;
	private static final String HTTPS_URL = "https://129.28.69.163:443/modifyDeviceInfo";
	private static final String KEY_STORE_CLIENT_PATH = "c://client.p12";
	private static final String KEY_STORE_TRUST_PATH = "c://client.truststore";
	private static final String KEY_STORE_PASSWORD = "1029384756";
	private static final String KEY_STORE_TRUST_PASSWORD = "1029384756";

    public static void main(String[] args){
		String url = HTTPS_URL;
		Map<String,String> params = new HashMap<String,String>();
//        params.put("cmd","test");
//        params.put("data","证书1");
        
        JSONObject json = new JSONObject();
		json.put("deviceId", "2274dc6a-d5a3-4c81-ad20-5437e90dc944");
		json.put("manufacturerId", "XLXX");
		json.put("manufacturerName", "XLXX");
		json.put("deviceType", "GasMeter");
		json.put("model", "XL0001");
		json.put("protocolType", "CoAP");

		params.put("deviceInfo", json.toString());
		
        String charset="utf-8";
        doSSLPostByCer( url, params,  charset);
    }

    /** +++++++++++++++ FUNCTION DESCRIPTION ++++++++++++++++++
    * <p>
    * <p>NAME        : 	doSSLPostByCer
    * <p>DESCRIPTION : 	HttpClient 实现证书验证https
    * <p>INPUT       : 
    * <p>OUTPUT      : 
    * <p>RETURN      : void
    * <p>
    *-----------------------------------------------------------*/
    @SuppressWarnings("unchecked")
	public static void doSSLPostByCer(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = new DefaultHttpClient();
		String result = null;
        try {
			KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
			KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
			InputStream ksIn = new FileInputStream(KEY_STORE_CLIENT_PATH);
			InputStream tsIn = new FileInputStream(new File(KEY_STORE_TRUST_PATH));
			try {
				keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());
				trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());
			} finally {
				try {
					ksIn.close();
				} catch (Exception ignore) {
				}
				try {
					tsIn.close();
				} catch (Exception ignore) {
				}
			}
			SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore,	KEY_STORE_PASSWORD, trustStore);
			Scheme sch = new Scheme(SCHEME_HTTPS, HTTPS_PORT, socketFactory);
			httpClient.getConnectionManager().getSchemeRegistry().register(sch);
			HttpPost httpPost = new HttpPost(url);
			
            //设置参数
			if (map != null) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				Iterator iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
					list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(list, charset));
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
            System.out.println("result={" + result + "}");
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
    
    /** +++++++++++++++ FUNCTION DESCRIPTION ++++++++++++++++++
	* <p>
	* <p>NAME        : 	
	* <p>DESCRIPTION : 	httpclient忽略证书实现https
	* <p>INPUT       : 
	* <p>OUTPUT      : 
	* <p>RETURN      : void
	* <p>
	*-----------------------------------------------------------*/
	@SuppressWarnings({ "unchecked" })
	public static void doSSLPost(String url, Map<String, String> map, String charset) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			SSLContext ctx = SSLContext.getInstance("SSL");
			//创建TrustManager
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);

			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", HTTPS_PORT, ssf));

			HttpPost httpPost = new HttpPost(url);
			// 设置参数
			if (map != null) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				Iterator iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
					list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(list, charset));
			}
			String result = "";
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			System.out.println("result={" + result + "}");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
