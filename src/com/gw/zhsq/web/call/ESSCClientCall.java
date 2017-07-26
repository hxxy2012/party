package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ESSCClient;

/**
 * 二手市场
 * @author hanxu
 *	2015-11-02
 */
public class ESSCClientCall extends BaseCall{

	//提交二手市场信息
	public static Map<String, Object> subESSCMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> esscMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ESSCClient.subESSCMap(requestMap);
		if (apiResponse.isSuccess()) {
			esscMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return esscMap;
	}
	
	//获取二手市场列表
	public static Map<String, Object> getESSCListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> esscListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ESSCClient.getESSCListMap(requestMap);
		if (apiResponse.isSuccess()) {
			esscListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return esscListMap;
	}
	
	//获取二手市场详情
	public static Map<String, Object> getESSCMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> esscMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ESSCClient.getESSCMap(requestMap);
		if (apiResponse.isSuccess()) {
			esscMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return esscMap;
	}	
	
	
	
}
