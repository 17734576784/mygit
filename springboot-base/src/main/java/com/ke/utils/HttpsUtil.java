package com.ke.utils;


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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
	private static final int HTTPS_PORT = 8443;
//	private static final String HTTPS_URL = "https://192.168.19.9:8443/sts/login.json";
	private static final String KEY_STORE_CLIENT_PATH = "c://client.p12";
	private static final String KEY_STORE_TRUST_PATH = "c://client.truststore";
	private static final String KEY_STORE_PASSWORD = "222222";
	private static final String KEY_STORE_TRUST_PASSWORD = "222222";

//    public static void main(String[] args){
//        String url=HTTPS_URL;
//        Map params=new HashMap();
////        params.put("cmd","test");
////        params.put("data","证书1");
//        
//        JSONObject json = new JSONObject();
//		json.put("userName", "stsadmin");
//		json.put("password", "123456");
//		params.put("queryJsonStr", json.toString());
//		
//        String charset="utf-8";
//        doSSLPost( url, params,  charset);
//    }
	
	public static CloseableHttpClient createDefault() {
		return HttpClientBuilder.create().build();
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
    	 CloseableHttpClient httpClient = createDefault();
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
			sr.register(new Scheme("https", 8443, ssf));

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
