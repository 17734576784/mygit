package utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * http宸ュ叿绫�
 */
public class HttpUtil {
	private static String boundary = "--------httppost123";

	public static String doPost(String url, Map<String, String> params) {
		return request(url, "POST", "UTF-8", "UTF-8", params);
	}

	public static String doPost(String url, String requestCharset,
			String responseCharset, Map<String, String> params) {
		return request(url, "POST", requestCharset, responseCharset, params);
	}

	public static String doGet(String url, Map<String, String> params) {
		return request(url, "GET", "UTF-8", "UTF-8", params);
	}

	public static String doGet(String url, String requestCharset,
			String responseCharset, Map<String, String> params) {
		return request(url, "GET", requestCharset, responseCharset, params);
	}

	public static String request(String url, String method,
			String requestCharset, String responseCharset,
			Map<String, String> params) {
		HttpURLConnection conn = null;
		try {
			
			URL openurl = new URL(url);
			conn = (HttpURLConnection) openurl.openConnection();
			// 缂栫爜
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset="+ requestCharset);
			conn.setRequestMethod(method);// 鎻愪氦妯″紡
			conn.setUseCaches(false);
			conn.setReadTimeout(20000000);//璇诲彇瓒呮椂 鍗曚綅姣
			conn.setDoOutput(true);// 鏄惁杈撳叆鍙傛暟
			conn.setDoInput(true);

	        StringBuffer p = new StringBuffer();
	        // 琛ㄥ崟鍙傛暟涓巊et褰㈠紡涓�鏍�
	        if(params != null){
	        	String key=null;
		        Iterator<String> it = params.keySet().iterator();
		        while(it.hasNext()){
		        	key = it.next();
		        	p.append(key).append("=").append(params.get(key)).append("&");
		        }
		        p.append("wap=1");
		        byte[] bypes = p.toString().getBytes();
		        conn.setRequestProperty("Content-Length",String.valueOf(bypes.length)); 
		        conn.getOutputStream().write(bypes);// 杈撳叆鍙傛暟
		        
	        }
	        //杩斿洖淇℃伅
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), responseCharset));
			StringBuffer buf = new StringBuffer();
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				buf.append(temp);
			}
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				conn.disconnect(); // 涓柇杩炴帴
			}
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect(); // 涓柇杩炴帴
			}
		}
	}
	/**
	 * 
	 * @param url
	 * @param stringParams
	 * @param fileParams
	 * @return
	 * @throws Exception
	 */
	public static String upload(String url,List<KeyValue> stringParams,List<KeyValue> fileParams) throws Exception{
		HttpURLConnection    conn = (HttpURLConnection) new URL(url).openConnection();  
	    conn.setDoOutput(true);  
	    conn.setUseCaches(false);  
	    conn.setConnectTimeout(10000); //杩炴帴瓒呮椂涓�10绉�  
	    conn.setRequestMethod("POST");  
	    conn.setRequestProperty("Content-Type",  
	                "multipart/form-data; boundary=" + boundary);  
	    DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
	    
	    
        //鏂囦欢鍙傛暟
        KeyValue kv;
        byte[] buf = new byte[10240];
        for (int i=0; i<fileParams.size(); i++) {  
            kv = fileParams.get(i);  
            File value = new File(kv.getValue());  
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + kv.getKey()  
                    + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");  
            ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");  
            ds.writeBytes("\r\n");
            FileInputStream fis = new FileInputStream(value);
            int len = -1;
            while((len = fis.read(buf)) != -1){
            	ds.write(buf,0,len);
            }
            ds.writeBytes("\r\n");
            ds.flush();
            fis.close();
        }  
        //鏂囨湰鍙傛暟
        for (int i=0; i<stringParams.size(); i++) {  
            kv = stringParams.get(i);
            String value = kv.getValue();  
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + kv.key  
                    + "\"\r\n");  
            ds.writeBytes("\r\n");  
            ds.writeBytes(encode(value) + "\r\n");  
        }  
        //娣诲姞缁撳熬鏁版嵁  
        ds.writeBytes("--" + boundary + "--" + "\r\n");  
        ds.writeBytes("\r\n");  
        //杩斿洖淇℃伅
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		StringBuffer sbuf = new StringBuffer();
		String temp = null;
		while((temp = reader.readLine()) != null){
			sbuf.append(temp);
		}
		conn.disconnect();  
		return sbuf.toString();
	}
	 private static String encode(String value) throws Exception{  
	        return URLEncoder.encode(value, "UTF-8");  
	    } 
	 //鑾峰彇鏂囦欢鐨勪笂浼犵被鍨嬶紝鍥剧墖鏍煎紡涓篿mage/png,image/jpg绛夈�傞潪鍥剧墖涓篴pplication/octet-stream  
    private static String getContentType(File f) throws Exception {  
          
//      return "application/octet-stream";  // 姝よ涓嶅啀缁嗗垎鏄惁涓哄浘鐗囷紝鍏ㄩ儴浣滀负application/octet-stream 绫诲瀷  
        ImageInputStream imagein = ImageIO.createImageInputStream(f);  
        if (imagein == null) {  
            return "application/octet-stream";  
        }  
        Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);  
        if (!it.hasNext()) {  
            imagein.close();  
            return "application/octet-stream";  
        }  
        imagein.close();  
        return "image/" + it.next().getFormatName().toLowerCase();//灏咶ormatName杩斿洖鐨勫�艰浆鎹㈡垚灏忓啓锛岄粯璁や负澶у啓  
  
    }  
	public static class KeyValue{
		private String key;
		private String value;
		
		public KeyValue(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
		
	public static void main(String[] args) throws Exception{
		/*Map<String,String> params = new HashMap<String,String>();
		params.put("userid", "2");
		params.put("user.name", "浜哄憳2");
		params.put("user.mobileCode", "139028389394");
		System.out.println(HttpUtil.doPost("http://127.0.0.1:8082/pspt/user/user!add.action", params));*/
		/*Map<String,String> params = new HashMap<String,String>();
		params.put("userid", "2");
		params.put("user.name", "浜哄憳2");
		params.put("user.mobileCode", "139028389394");
		System.out.println(HttpUtil.doPost("http://127.0.0.1/pspt/user/user!add.action", params));*/
		
		//System.out.println(new String("盲潞潞氓聭聵1".getBytes("iso-8859-1"),"utf-8"));
		
		//鏂囦欢涓婁紶
		/*List<KeyValue> stringParams = new ArrayList<KeyValue>();
		List<KeyValue> fileParams = new ArrayList<KeyValue>();
		 stringParams.add(new KeyValue("carid","2"));
		 stringParams.add(new KeyValue("userid","2"));
		 stringParams.add(new KeyValue("goodsid","2"));
		 stringParams.add(new KeyValue("pictype","2"));
		 fileParams.add(new KeyValue("pic","D:\\pieChart.png"));
		 fileParams.add(new KeyValue("pic","D:\\QRCode.png"));
		 String str = upload("http://127.0.0.1:8082/pspt/driver/driver!upload.action",stringParams,fileParams);
		 System.out.println(str);*/
		String url = "http://59.49.18.116:8008/sxwwpt_wai/inquire/illegalAction!carInquire.action?type=1&hpzl=02&csjcKey=140000&vioViolation.hphm=A708V1&clsbdm=096513&authCode=4869&authCode2=4869&pagination.currentPage=1";
		url = "http://129.28.69.163:11028/Enterprise_EnnGas/enngas/message/nbNotifyAction!checkExistOrders.action";
//		url ="http://127.0.0.1:8080/test";
		url = "http://129.28.69.163:11028/Enterprise_EnnGas/enngas/message/nbNotifyAction!notifyUploadResult.action";
		url = "http://127.0.0.1:8082/login";
//		url ="http://127.0.0.1:8080/ShiroWeb/doadd.html";


		Map<String, Object> paramJson = new HashMap<>();
		paramJson.put("version", "1.001.0018112103110000");
		paramJson.put("deviceId", "81a8a1e9-0870-48e1-ad7a-57ad16e5b0d1");
		paramJson.put("status", "0");

		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put("param", paramJson.toString());
		paramMap.put("username", "admin");
		paramMap.put("password", "123456");
		paramMap.put("token", "123456789");

		url ="http://127.0.0.1:8080/login.json";
		url ="http://127.0.0.1:8080/list.json";
//		url ="http://127.0.0.1:8080/error.json";

		String result = HttpUtil.doPost(url, paramMap);
		System.out.println("result = " + result);
	}
}
