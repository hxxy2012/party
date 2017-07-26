package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.GZBXClient;

/**
 * 故障报修
 * @author hanxu
 *	2015-11-02
 */
public class GZBXClientCall extends BaseCall{

	//提交故障报修信息
	public static Map<String, Object> subGZBXMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> gzbxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = GZBXClient.subGZBXMap(requestMap);
		if (apiResponse.isSuccess()) {
			gzbxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return gzbxMap;
	}
	
	//获取故障报修列表
	public static Map<String, Object> getGZBXListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> gzbxListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = GZBXClient.getGZBXListMap(requestMap);
		if (apiResponse.isSuccess()) {
			gzbxListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return gzbxListMap;
	}
	
	//获取故障报修详情
	public static Map<String, Object> getGZBXMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> gzbxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = GZBXClient.getGZBXMap(requestMap);
		if (apiResponse.isSuccess()) {
			gzbxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return gzbxMap;
	}
	
	//获取社区信息
	public static Map<String, Object> getShequMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> shequMap = null;
		ApiResponse apiResponse = null;
		apiResponse = GZBXClient.getShequMap(requestMap);
		if (apiResponse.isSuccess()) {
			shequMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return shequMap;
	}
	
	
	
}
