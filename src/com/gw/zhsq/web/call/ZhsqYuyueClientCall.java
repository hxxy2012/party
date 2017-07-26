package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ZhsqYuyueClient;

/**
 * 社区预约
 * @author lihui
 *	2016-04-21
 */
public class ZhsqYuyueClientCall extends BaseCall {
	
	//提交社区预约
	public static Map<String, Object> subShequYuyue(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqYuyueClient.subShequYuyue(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return yuyueMap;
	}

	//获取社区预约信息列表【会员中心】
	public static Map<String, Object> getShequYuyueListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqYuyueClient.getShequYuyueListMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return yuyueListMap;
	}

	// 获取社区预约详情
	public static Map<String, Object> getShequYuyueMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqYuyueClient.getShequYuyueMap(requestMap);
		if (apiResponse.isSuccess()) {
			yuyueMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return yuyueMap;
	}
}
