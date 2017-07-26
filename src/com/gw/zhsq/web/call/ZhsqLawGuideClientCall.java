package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ZhsqLawGuideClient;
import com.gw.zhsq.web.client.ZhsqLiveClient;

/**
 * 社区办事指南
 * @author fuyun
 *	2016-04-07
 */
public class ZhsqLawGuideClientCall extends BaseCall{

	/**
	 * 获取社区办事指南列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getZhsqLawGuideListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqLawGuideClient.getZhsqLawGuideListMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxListMap;
	}
	
	/**
	 * 获取社区办事指南类型列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getZhsqLawGuideTypeListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqLawGuideClient.getZhsqLawGuideTypeListMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxListMap;
	}
	

	public static List<Map<String, Object>> getBasLawGuideList(HashMap<String, String> requestMap) throws OtherException
	{
		List<Map<String, Object>> zhsqLawGuideList = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqLawGuideClient.getZhsqLawGuideList(requestMap);
		//apiResponse.setSuccess();
		if (apiResponse.isSuccess()) {
			zhsqLawGuideList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqLawGuideList;
		
	}
	
	
	/**
	 * 获取社区办事指南详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	
	public static Map<String, Object> getZhsqLawGuideMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqLawGuideClient.getZhsqLawGuideMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxMap;
	}
		
}
