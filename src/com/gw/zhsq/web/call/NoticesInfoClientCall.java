package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.NoticesInfoClient;

/**
 * 获取公告列表
 * @author fuyun 
 * 2015-4-3
 */
public class NoticesInfoClientCall extends BaseCall {

	/**
	 * 获取活动公告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getNoticesInfoistMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> list = null;
		ApiResponse apiResponse = null;
		apiResponse = NoticesInfoClient.getNoticesInfoMap(requestMap);
		if (apiResponse.isSuccess()) {
			list = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return list;
	}
	
	/**
	 * 获取活动公告详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getNoticesInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> list = null;
		ApiResponse apiResponse = null;
		apiResponse = NoticesInfoClient.getNoticesInfoMap(requestMap);
		if (apiResponse.isSuccess()) {
			list = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return list;
	}
	
	/**
	 * 获取活动公告详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getNoticesMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> list = null;
		ApiResponse apiResponse = null;
		apiResponse = NoticesInfoClient.getNoticesMap(requestMap);
		if (apiResponse.isSuccess()) {
			list = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return list;
	}
	
}
