/**
 * 
 */
package utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.*;
/**   
 * @ClassName:  HttpClientUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年9月7日 上午11:44:00   
 *      
 */

public class HttpClientUtil {

    private static final String KEY_STORE_TYPE_JKS = "jks";
    private static final String KEY_STORE_TYPE_P12 = "PKCS12";
    private static final String SCHEME_HTTPS = "https";
    private static final int HTTPS_PORT = 8443;
    private static final String HTTPS_URL = "https://129.28.69.163:8443/";
    private static final String KEY_STORE_CLIENT_PATH = "D:/NB证书/client.p12";
    private static final String KEY_STORE_TRUST_PATH = "D:/NB证书//client.truststore";
    private static final String KEY_STORE_PASSWORD = "1029384756";
    private static final String KEY_STORE_TRUST_PASSWORD = "1029384756";

    @SuppressWarnings("unchecked")
	public static void main(String[] args){
		String url = HTTPS_URL + "test";
		
		JSONObject json = new JSONObject();
		json.put("deviceId","0fa12276-690c-4a0b-b092-6a7b6556960e");
		json.put("serviceId","PhotoService");
		json.put("method","sendphotoonce");
//		
//		json.put("serviceId","AlarmService");
//		json.put("method","openalarm");
//		
//		json.put("serviceId","TimeService");
//		json.put("method","commandUpTimeService");
//		  
//		json.put("serviceId","CameraService");
//		json.put("method","adjustcamera");
		
//		json.put("serviceId","UpdataService");
//		json.put("method","updata");
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("value", 1);
		
//		paramJson.put("openslope", 0);
//		paramJson.put("openmagnetic", 1);
//		
//		paramJson.put("timetype", 2);
//		paramJson.put("time", 13);
		
//		paramJson.put("direction", 1);
//		paramJson.put("value", 10);
//		paramJson.put("verifyCode","869029030051873");
//		paramJson.put("nodeId","869029030051873");
//		paramJson.put("timeout","0");

//		{"deviceId":"8c23b6b4-ea68-48fb-9c2f-90452a81ebb1",
//			"manufacturerId":"XLXX",
//			"manufacturerName":"XLXX",
//			"deviceType":"GasMeter",
//			"model":"XL0001",
//			"protocolType":"CoAP"}
		
//		paramJson.put("deviceId", "98eb6118-a684-4dbb-9894-62ea69c1a335");
//		paramJson.put("manufacturerId", "XLXX");
//		paramJson.put("manufacturerName", "XLXX");
//		paramJson.put("deviceType", "GasMeter");
//		paramJson.put("model", "XL0001");
//		paramJson.put("protocolType", "CoAP");

		json.put("param", paramJson.toString());
        
		Map param = new HashMap();
        param.put("command", json.toString());
//        param.put("deviceInfo", "2222");
//		Map param = new HashMap();
//		param.put("deviceId", "a2a4bfcf-ceb6-48c2-a091-8c28fd27a564");
      
        String charset="utf-8";
		String url1 ="https://129.28.69.163:8443/asynCommand";
//		String url1 ="https://129.28.69.163:8443/test";
//		url1 = "https://129.28.69.163:8443/modifyDeviceInfo";

        doSSLPost(url1, param,  charset);
//        uploadHttpClient(url1,"kafka-0.0.1-SNAPSHOT.jar");
        
    }
    
    /** 
     * 使用HttpClient来上传数据 
     */  
    public static void uploadHttpClient(String url,String fileName){  
        HttpClient client=new DefaultHttpClient();  
        HttpPost httpPost=new HttpPost(url);//通过post传递  
        /**绑定数据  这里需要注意  如果是中文  会出现中文乱码问题 但只要按设置好*/  
        MultipartEntity muit=new MultipartEntity();  
        // 上传 文本， 转换编码为utf-8 其中"text" 为字段名，  
        //Charset.forName(CHARSET)为参数值,可设置为UTF-8，其实就是正常的值转换成utf-8的编码格式  
        // 后边new StringBody(text,Charset.forName(CHARSET))  
   
//        File parent= Environment.getExternalStorageDirectory();//路径  
        File fileupload=new File("D:/",fileName);  
        FileBody fileBody=new FileBody(fileupload);  
        muit.addPart("file",fileBody);  
        httpPost.setEntity(muit);  
        /**发送请求*/  
        try {  
            HttpResponse response=client.execute(httpPost);  
            System.out.println(response.getStatusLine().getStatusCode());
            //判断师傅上传成功  返回200  
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){  
                System.out.println(EntityUtils.toString(response.getEntity()));  
            }  
        } catch (ClientProtocolException e){  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
   
    }  

    private static void doSSLPost(String url, Map<String, String> map, String charset) {
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
            SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, KEY_STORE_PASSWORD, trustStore);
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
}