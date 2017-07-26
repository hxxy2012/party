package com.gw.base.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HttpClient4.0的封装类
 */
public class HttpClient4Util {

	private static Logger logger = Logger.getLogger(HttpClient4Util.class);
	
	/**
	 * HttpClient直接连接接口，直接返回数据
	 * @param 接口URL
	 * @param NameValuePair参数
	 * @param 编码
	 * @return
	 * @throws Exception
	 */
	public static String invokePostHttp(String url, HashMap<String, String> requestMap,
			String coding) throws Exception {

		String returnMsg = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {

			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 先迭代HashMap
			Iterator<String> it = requestMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				nvps.add(new BasicNameValuePair(key, requestMap.get(key)));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, coding));
//			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + coding);
			httpPost.addHeader("Accept-Language", "zh-cn");
			// httpPost.addHeader("Accept-Encoding", "gzip, deflate");

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				returnMsg = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}
			httpPost.abort();
		} catch (Exception e) {
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return returnMsg;
	}

	/**
	 * HttpClient运用连接池连接接口，直接返回数据
	 * 
	 * @param 接口URL
	 * @param NameValuePair参数
	 * @param 编码
	 * @return
	 * @throws Exception
	 */
	public static String invokePostHttpWithConn(String url, HashMap<String, String> requestMap,
			String coding) throws Exception {

		String returnMsg = "";
		HttpClient httpClient = Http4ConnectionManager.getHttpClient();
		// http.socket.timeout
//		httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
		InputStream in = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			System.out.println("http4访问的url=" + url);
			System.out.println("http4访问的requestMap=" + requestMap);
			//logger.info("http4访问的url=" + url);
			//logger.info("http4访问的requestMap=" + requestMap);

			// 先迭代HashMap
			Iterator<String> it = requestMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				nvps.add(new BasicNameValuePair(key, requestMap.get(key)));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, coding));
//			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + coding);
			httpPost.addHeader("Accept-Language", "zh-cn");
			// httpPost.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			// httpPost.addHeader("Accept-Encoding", "gzip, deflate");

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				in = httpEntity.getContent();
				returnMsg = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}

			logger.info("http4返回的returnMsg=" + returnMsg);
			System.out.println("http4返回的returnMsg=" + returnMsg);
 
			httpPost.abort();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnMsg;
	}

	/**
	 * HttpClient直接连接接口，包含上传文件
	 * 
	 * @param url
	 *            接口的URL
	 * @param requestMap
	 *            字符串的请求参数
	 * @param file
	 *            上传的文件
	 * @param coding
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static String invokePostFileHttp(String url, HashMap<String, String> requestMap,
			File file, String coding) throws Exception {

		String returnMsg = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {

			HttpPost httpPost = new HttpPost(url);
			// 对请求的表单域进行填充
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("file", new FileBody(file));

			// 先迭代HashMap
			Iterator<String> it = requestMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				reqEntity
						.addPart(key, new StringBody(requestMap.get(key), Charset.forName(coding)));
			}

			httpPost.setEntity(reqEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				returnMsg = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}
			httpPost.abort();
		} catch (Exception e) {
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return returnMsg;
	}
}