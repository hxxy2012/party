package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.StreetYuyueClient;

/**
 * 街道预约
 * @author lihui
 *	2016-04-21
 */
public class StreetYuyueClientCall extends BaseCall {
	
	//提交街道预约
	public static Map<String, Object> subStreetYuyue(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetYuyueClient.subStreetYuyue(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return yuyueMap;
	}

	//获取街道预约信息列表【会员中心】
	public static Map<String, Object> getStreetYuyueListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetYuyueClient.getStreetYuyueListMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return yuyueListMap;
	}

	// 获取接到预约详情
	public static Map<String, Object> getStreetYuyueMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetYuyueClient.getStreetYuyueMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return yuyueMap;
	}
}
