package com.gw.base.util;

import java.io.File;
import java.util.HashMap;

/**
 * HttpClient的封装类
 */
public class SimpleHttp {
//	private static int HTTPTIMEOUT = 30000;
//	private static int CONNECTIONTIMEOUT = 5000;
	private static String DEFAULTCODING = "GBK";

	/**
	 * HttpClient连接接口，直接返回数据，编码默认GBK Post请求
	 * 
	 * @param 接口URL
	 * @param NameValuePair参数
	 * @return
	 * @throws Exception
	 */
	public static String invokePostHttp(String url, HashMap<String, String> requestMap)
			throws Exception {
		return invokePostHttp(url, requestMap, DEFAULTCODING);
	}

	/**
	 * HttpClient连接接口，直接返回数据
	 * 
	 * @param 接口URL
	 * @param NameValuePair参数
	 * @param 编码
	 * @return
	 * @throws Exception
	 */
	public static String invokePostHttp(String url, HashMap<String, String> requestMap,
			String coding) throws Exception {
		// return HttpClient3Util.invokePostHttp(url, requestMap, coding);

		// return HttpClient4Util.invokePostHttp(url, requestMap, coding);

		return HttpClient4Util.invokePostHttpWithConn(url, requestMap, coding);
	}

	/**
	 * HttpClient直接连接接口，包含上传文件,编码默认GBK Post请求
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
			File file) throws Exception {

		return invokePostFileHttp(url, requestMap, file, DEFAULTCODING);
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

		return HttpClient4Util.invokePostFileHttp(url, requestMap, file, coding);
	}
}