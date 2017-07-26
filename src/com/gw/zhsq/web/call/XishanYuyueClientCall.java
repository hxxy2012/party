package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.XishanYuyueClient;

/**
 * 在线预约
 * @author lihui
 *	2016-05-18
 */
public class XishanYuyueClientCall extends BaseCall {
	// 提交预约
	public static Map<String, Object> subXishanYuyue(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanYuyueClient.subXishanYuyue(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return yuyueMap;
	}

	// 获取预约信息列表
	public static Map<String, Object> getXishanYuyueListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanYuyueClient.getXishanYuyueListMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return yuyueListMap;
	}

	// 获取预约详情
	public static Map<String, Object> getXishanYuyueMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanYuyueClient.getXishanYuyueMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return yuyueMap;
	}
}
