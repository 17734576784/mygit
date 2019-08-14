package com.wo.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * http工具类
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
			// 编码
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset="+ requestCharset);
			conn.setRequestMethod(method);// 提交模式
			conn.setUseCaches(false);
			conn.setReadTimeout(2000000000);//读取超时 单位毫秒
			conn.setDoOutput(true);// 是否输入参数
			conn.setDoInput(true);

	        StringBuffer p = new StringBuffer();
	        // 表单参数与get形式一样
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
		        conn.getOutputStream().write(bypes);// 输入参数
		        
	        }
	        //返回信息
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
				conn.disconnect(); // 中断连接
			}
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect(); // 中断连接
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
	    conn.setConnectTimeout(10000); //连接超时为10秒  
	    conn.setRequestMethod("POST");  
	    conn.setRequestProperty("Content-Type",  
	                "multipart/form-data; boundary=" + boundary);  
	    DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
	    
	    
        //文件参数
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
        //文本参数
        for (int i=0; i<stringParams.size(); i++) {  
            kv = stringParams.get(i);
            String value = kv.getValue();  
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + kv.key  
                    + "\"\r\n");  
            ds.writeBytes("\r\n");  
            ds.writeBytes(encode(value) + "\r\n");  
        }  
        //添加结尾数据  
        ds.writeBytes("--" + boundary + "--" + "\r\n");  
        ds.writeBytes("\r\n");  
        //返回信息
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
	 //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream  
    private static String getContentType(File f) throws Exception {  
          
//      return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型  
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
        return "image/" + it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写  
  
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
		params.put("user.name", "人员2");
		params.put("user.mobileCode", "139028389394");
		System.out.println(HttpUtil.doPost("http://127.0.0.1:8082/pspt/user/user!add.action", params));*/
		/*Map<String,String> params = new HashMap<String,String>();
		params.put("userid", "2");
		params.put("user.name", "人员2");
		params.put("user.mobileCode", "139028389394");
		System.out.println(HttpUtil.doPost("http://127.0.0.1/pspt/user/user!add.action", params));*/
		
		//System.out.println(new String("äººå1".getBytes("iso-8859-1"),"utf-8"));
		
		//文件上传
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
		url = "http://127.0.0.1:8080/manage/listPointAll.json?token=7C8C2A9C78F6F939CC3BCA51181C9359D21A01E3B11D623E7BA69D8E19B7A97E&queryJsonStr={%22station%22:%20%22100001%22}";
		String result = HttpUtil.doGet(url, null);
		System.out.println("result = " + result);
	}
}
