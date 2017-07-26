package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.AdvClient;

/**
 * 获取广告图片
 * @author fuyun
 *	2015-07-22
 */
public class AdvClientCall extends BaseCall{

	/**
	 * 获取幻灯片列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getAdvListMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> advLis = null;
		ApiResponse apiResponse = null;
		apiResponse = AdvClient.getAdvListMap(requestMap);
		if (apiResponse.isSuccess()) {
			advLis = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return advLis;
	}
	
	/**
	 * 获取社区服务顶部幻灯片列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getWfwAdvListMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> advLis = null;
		ApiResponse apiResponse = null;
		apiResponse = AdvClient.getWfwAdvListMap(requestMap);
		if (apiResponse.isSuccess()) {
			advLis = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return advLis;
	}
	
	/**
	 * 获取社区服务横幅广告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getWfwBannerAdvListMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> advLis = null;
		ApiResponse apiResponse = null;
		apiResponse = AdvClient.getWfwBannerAdvListMap(requestMap);
		if (apiResponse.isSuccess()) {
			advLis = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return advLis;
	}
	
	/**
	 * 获取社区广告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getZhsqAdvListMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> advLis = null;
		ApiResponse apiResponse = null;
		apiResponse = AdvClient.getZhsqAdvListMap(requestMap);
		if (apiResponse.isSuccess()) {
			advLis = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return advLis;
	}
	
	/**
	 * 获取街道广告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getStreetAdvListMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> advLis = null;
		ApiResponse apiResponse = null;
		apiResponse = AdvClient.getStreetAdvListMap(requestMap);
		if (apiResponse.isSuccess()) {
			advLis = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return advLis;
	}
	
}
