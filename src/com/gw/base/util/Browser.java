package com.gw.base.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;

public class Browser {
	
	static Logger logger = Logger.getLogger(Browser.class);
	private static int connectionTimeOut = 5000;
	private static int readTimeOut = 5000;
 
	/**
	 * POST提交方式
	 * @param url 提交地址url
	 * @param param 提交参数，用Map封装 key 为参数名 value 为参数值
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @return String
	 */
	public static String post(String url, Map<String, String> param, String sendCharset, String receiveCharset){
		try {
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeOut);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeOut);
			HttpPost post = new HttpPost(url);
			post.addHeader("encode", "utf-8");
			// 创建表单参数列表
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			// 循环map放入参数
			if (param != null) {
				Set<String> setKey = param.keySet();
				for (String key : setKey) {
					qparams.add(new BasicNameValuePair(key, param.get(key)));
				}
			}
			// 填充表单
			post.setEntity(new UrlEncodedFormEntity(qparams, sendCharset));

			HttpResponse response2 = client.execute(post);
			int statusCode = response2.getStatusLine().getStatusCode();
			System.out.println("statusCode:" + statusCode);
			StringBuffer buffer = new StringBuffer();
			String temp = null;
			if (statusCode == 200) {
				HttpEntity entity2 = response2.getEntity();
				BufferedReader reader2 = new BufferedReader(receiveCharset != null ? new InputStreamReader(entity2.getContent(), receiveCharset) : new InputStreamReader(entity2.getContent()));
				while ((temp = reader2.readLine()) != null) {
					buffer.append(temp + "\r\n");
				}
				reader2.close();
				return buffer.toString().trim();
			} else if (statusCode == 302) {// 302表示重定向
				Header[] hs = response2.getHeaders("Location");
				if (hs.length > 0) {
					String url1 = hs[0].getValue();
					post.abort();
					return get(url1);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		return null;

	}
	
	public static String post(String url, Map<String, String> param) throws ClientProtocolException, IOException {
		return post(url, param, "UTF-8", "UTF-8");
	}
	
	public static String post(String url, byte[] data, String receiveCharset){
		try {
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeOut);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeOut);
			HttpPost post = new HttpPost(url);
			post.addHeader("encode", "utf-8");
			
			if (data != null){
				HttpEntity eh = new ByteArrayEntity(data);

				post.setEntity(eh);

			}
			
			HttpResponse response2 = client.execute(post);
			int statusCode = response2.getStatusLine().getStatusCode();
			System.out.println("statusCode:" + statusCode);
			StringBuffer buffer = new StringBuffer();
			String temp = null;
			if (statusCode == 200) {
				HttpEntity entity2 = response2.getEntity();
				BufferedReader reader2 = new BufferedReader(receiveCharset != null ? new InputStreamReader(entity2.getContent(), receiveCharset) : new InputStreamReader(entity2.getContent()));
				while ((temp = reader2.readLine()) != null) {
					buffer.append(temp + "\r\n");
				}
				reader2.close();
				return buffer.toString().trim();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;

	}
	
	public static byte[] postByte(String url){
		try { 
			URL u = new URL(url);
			 HttpURLConnection con = (HttpURLConnection) u.openConnection();
			 con.setRequestMethod("GET");  
			 InputStream inStream = con.getInputStream();  
			 
			 ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
		        //创建一个Buffer字符串  
		        byte[] buffer = new byte[1024];  
		        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
		        int len = 0;  
		        //使用一个输入流从buffer里把数据读取出来  
		        while( (len=inStream.read(buffer)) != -1 ){  
		            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
		            outStream.write(buffer, 0, len);  
		        }  
		        inStream.close();
			return outStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return null;

	}

	
	/**
	 * Get提交方式
	 * @param url 提交地址url后面直接带提交参数
	 * @return String
	 */
	public static String get(String url) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeOut);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeOut);
		HttpGet get = new HttpGet(url);

		try {

			HttpResponse response = client.execute(get);	 
			HttpEntity entity = response.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp + "\r\n");
			}
			get.abort();
			reader.close();
			return buffer.toString().trim();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		}
	
	

	public static int getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public static void setConnectionTimeOut(int connectionTimeOut) {
		Browser.connectionTimeOut = connectionTimeOut;
	}

	public static int getReadTimeOut() {
		return readTimeOut;
	}

	public static void setReadTimeOut(int readTimeOut) {
		Browser.readTimeOut = readTimeOut;
	}
	
	
}
