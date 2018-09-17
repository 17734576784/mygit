/**
 * 
 */
package com.iot.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.alibaba.fastjson.JSONObject;

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
	public static boolean upload(String url, String filePath, String date,String deviceId) throws ClientProtocolException, IOException {
		boolean flag = false;
		Log4jUtils.getInfo().info("上传图片：" + filePath);
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

//				System.out.println(buffer.toString());
				flag = true;
			}

			httpClient.close();
			if (httpResponse != null) {
				httpResponse.close();
			}
		} catch (Exception e) {
			flag = false;
			Log4jUtils.getInfo().error("上传图片异常:" + filePath);
			e.printStackTrace();
		}

		return flag;
	}
}
