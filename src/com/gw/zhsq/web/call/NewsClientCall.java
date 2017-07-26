package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.NewsClient;

/**
 * 获取资讯
 * @author hanxu
 *	2015-04-02
 */
public class NewsClientCall extends BaseCall{

	/**
	 * 获取某资讯分类下的资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getNewsListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> getGoodsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = NewsClient.getNewsListMap(requestMap);
		if (apiResponse.isSuccess()) {
			getGoodsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return getGoodsMap;
	}
	
	/**
	 * 获取政务列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getNewsList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> newsList = null;
		ApiResponse apiResponse = null;
		apiResponse = NewsClient.getNewsListMap(requestMap);
		if (apiResponse.isSuccess()) {
			newsList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsList;
	}
	
	/**
	 * 获取政务详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getNewsMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> newsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = NewsClient.getNewsMap(requestMap);
		if (apiResponse.isSuccess()) {
			newsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsMap;
	}
	
	/**
	 * 获取最新资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getNewestNewsList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> newsList = null;
		ApiResponse apiResponse = null;
		apiResponse = NewsClient.getNewestNewsList(requestMap);
		if (apiResponse.isSuccess()) {
			newsList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsList;
	}
	
}
