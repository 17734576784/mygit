/**   
* @Title: MyHttpRequest.java 
* @Package utils 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��11��28�� ����4:04:52 
* @version V1.0   
*/
package utils;

/** 
* @ClassName: MyHttpRequest 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2018��11��28�� ����4:04:52 
*  
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author xuwujing
 * @Data 2016-6-13 ����11:57:52
 * @Description  http���󹤾���
 */
public class MyHttpRequest {

    /**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����Map�������������Ӧ���� {"name1":"value1","name2":"value2"}����ʽ��
     * @param charset         
     *             ���ͺͽ��յĸ�ʽ
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, Map<String,Object> map,String charset){
          StringBuffer sb=new StringBuffer();
          //�����������
          if(map!=null&&map.size()>0){
              Iterator it=map.entrySet().iterator(); //���������
              while(it.hasNext()){
                 Map.Entry  er= (Entry) it.next();
                 sb.append(er.getKey());
                 sb.append("=");
                 sb.append(er.getValue());
                 sb.append("&");
             }
          }
       return  sendGet(url,sb.toString(), charset);
    }


    /**
     * ��ָ��URL����POST����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����Map�������������Ӧ���� {"name1":"value1","name2":"value2"}����ʽ��
     * @param charset         
     *             ���ͺͽ��յĸ�ʽ
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, Map<String,Object> map,String charset){
          StringBuffer sb=new StringBuffer();
          //�����������
          if(map!=null&&map.size()>0){
                for (Entry<String, Object> e : map.entrySet()) {  
                    sb.append(e.getKey());  
                    sb.append("=");  
                    sb.append(e.getValue());  
                    sb.append("&");  
                }  
          }
       return  sendPost(url,sb.toString(),charset);
    }


    /**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @param charset         
     *             ���ͺͽ��յĸ�ʽ
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, String param,String charset) {
        String result = "";
        String line;
        StringBuffer sb=new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ��������� ���������ʽ
            conn.setRequestProperty("contentType", charset); 
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //���ó�ʱʱ��
            conn.setConnectTimeout(60);
            conn.setReadTimeout(60);
            // ����ʵ�ʵ�����
            conn.connect();
            // ���� BufferedReader����������ȡURL����Ӧ,���ý��ո�ʽ
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @param charset         
     *             ���ͺͽ��յĸ�ʽ       
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, String param,String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        StringBuffer sb=new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮������� 
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ��������� ���������ʽ
            conn.setRequestProperty("contentType", charset);  
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //���ó�ʱʱ��
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(600000);
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ    ���ý��ո�ʽ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            System.out.println("���� POST��������쳣!"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }  
    public static void main(String[] args) {
        String getUrl="http://129.28.69.163:11028/Enterprise_EnnGas/enngas/message/nbNotifyAction!checkExistOrders.action";
        String postUrl="http://129.28.69.163:11028/Enterprise_EnnGas/enngas/message/nbNotifyAction!checkExistOrders.action";
        
        String url = "http:127.0.0.1:8080/test";
        Map<String, Object> paramJson = new HashMap<>();
		paramJson.put("version", "1.001.0018112103110000");
		paramJson.put("deviceId", "81a8a1e9-0870-48e1-ad7a-57ad16e5b0d1");

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("param", paramJson);
//        System.out.println("Get����2:"+MyHttpRequest.sendGet(getUrl, paramMap,"utf-8"));
        System.out.println("Post����2:"+MyHttpRequest.sendPost(url, paramMap,"utf-8"));
    }
}