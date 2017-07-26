package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.StreetLawGuideClient;

/**
 * 街道办事指南
 * @author fuyun
 *	2016-04-07
 */
public class StreetLawGuideClientCall extends BaseCall{

	/**
	 * 获取街道办事指南列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getStreetLawGuideListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetLawGuideClient.getStreetLawGuideListMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxListMap;
	}
	
	/**
	 * 获取街道办事指南详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getStreetLawGuideMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetLawGuideClient.getStreetLawGuideMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxMap;
	}
		
}
