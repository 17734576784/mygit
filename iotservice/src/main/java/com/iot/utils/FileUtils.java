/**
 * 
 */
package com.iot.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSONObject;
import com.iot.logger.LogName;
import com.iot.logger.LoggerUtils;

/**
 * @ClassName: FileUtils
 * @Description:文件工具类
 * @author: dbr
 * @date: 2018年9月12日 下午3:47:11
 * 
 */
public class FileUtils {
	/**
	 * 上传文件
	 * 
	 * @param url
	 * @param filePath
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static boolean upload(String url, String filePath, String date,String deviceId,String time) throws ClientProtocolException, IOException {
		boolean flag = false;
		LoggerUtils.Logger(LogName.CALLBACK).info("上传图片：deviceId : " + deviceId + " ,filePath:" + filePath);
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse httpResponse = null;
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000)
					.build();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));

			File file = new File(filePath);

			// 当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
			multipartEntityBuilder.addBinaryBody("file", file);
			JSONObject dataInfo = new JSONObject();
			dataInfo.put("deviceId", deviceId);
			dataInfo.put("date", date);
			dataInfo.put("time", time);


			multipartEntityBuilder.addTextBody("param", dataInfo.toJSONString());
			HttpEntity httpEntity = multipartEntityBuilder.build();
			httpPost.setEntity(httpEntity);

			httpResponse = httpClient.execute(httpPost);
			HttpEntity responseEntity = httpResponse.getEntity();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
				StringBuffer buffer = new StringBuffer();
				String str = "";
				while (!StringUtil.strIsNullOrEmpty(str = reader.readLine())) {
					buffer.append(str);
				}

				flag = true;
			}

			httpClient.close();
			if (httpResponse != null) {
				httpResponse.close();
			}
		} catch (Exception e) {
			flag = false;
			LoggerUtils.Logger(LogName.CALLBACK).error("上传图片异常:" + filePath);
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 文件转为二进制数组
	 * @param file
	 * @return
	 */
    public static byte[] fileToBinArray(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return bytes;
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
    }

	/**
	 * 文件转为二进制字符串
	 * @param file
	 * @return
	 */
    public static String fileToBinStr(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            System.out.println(bytes);
            System.out.println(bytes.length);
            return new String(bytes,"ISO-8859-1");
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin String 出错",ex);
        }
    }


	/**
	 * 二进制字符串转文件
	 * @param bin
	 * @param fileName
	 * @param parentPath
	 * @return
	 */
    public static File binToFile(String bin,String fileName,String parentPath){
        try {
            File fout = new File(parentPath,fileName);
            fout.createNewFile();
            byte[] bytes1 = bin.getBytes("ISO-8859-1");
            FileCopyUtils.copy(bytes1,fout);

            //FileOutputStream outs = new FileOutputStream(fout);
            //outs.write(bytes1);
            //outs.flush();
            //outs.close();

            return fout;
        }catch (Exception ex){
            throw new RuntimeException("transform bin into File 出错",ex);
        }
    }

    /**
     * 文件转为二进制数组
     * 等价于fileToBin
     * @param file
     * @return
     */
    public static byte[] getFileToByte(File file) {
        byte[] by = new byte[(int) file.length()];
        try {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
        return by;
    }
    
	public static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
            for (int i = 0; i < values.length; i++) {
                length_byte += values[i].length;
            }
            byte[] all_byte = new byte[length_byte];
            int countLength = 0;
            for (int i = 0; i < values.length; i++) {
                byte[] b = values[i];
                System.arraycopy(b, 0, all_byte, countLength, b.length);
                countLength += b.length;
            }
            return all_byte;
        }
    
    public static void main(String[] args){
        File file = new File("D://新建文本文档.txt");
//        String fileName = file.getName();
//        binToFile(fileToBinStr(file),fileName,"D://测试byte");
//        getFileToByte(file);
        
        byte[] fileByte = fileToBinArray(file);
        System.out.println(fileByte.length);
        int fileLength = fileByte.length;
        int length = 16;
        int packNum = fileLength/length;
        System.out.println(packNum);
        
        Map<String,Object> fileMap = new LinkedHashMap<String,Object>(packNum);
        for (int i = 0; i <= (packNum); i++) {
        	int blen = (int) ((i + 1) * length > fileLength ? fileLength : (i + 1) * length);
			byte[] b = Arrays.copyOfRange(fileByte, length * i, blen);
			fileMap.put(String.valueOf(i), b);
		}
        
        byte[] all_byte = new byte[fileLength];
        Iterator<Entry<String, Object>> it = fileMap.entrySet().iterator();
		int countLength = 0;
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			byte[] b = (byte[]) entry.getValue();
			System.arraycopy(b, 0, all_byte, countLength, b.length);
			countLength += b.length;
			System.out.println("countLength: "+countLength);
		}
		CommFunc.byte2image(all_byte, "D://dbr.txt");
    }
}
