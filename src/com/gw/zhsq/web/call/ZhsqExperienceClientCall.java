package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ZhsqExperienceClient;

/**
 * 线下体检
 * @author hanxu
 *	2015-10-26
 */
public class ZhsqExperienceClientCall extends BaseCall{

	
	/**
	 * 获取线下体检会员电子血压列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getMemberBloodPressListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberBloodPressListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqExperienceClient.getMemberBloodPressListMap(requestMap);
		if (apiResponse.isSuccess()) {
			memberBloodPressListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberBloodPressListMap;
	}
	
	/**
	 * 获取线下体检会员电子体重列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getMemberWeightListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberWeightListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqExperienceClient.getMemberWeightListMap(requestMap);
		if (apiResponse.isSuccess()) {
			memberWeightListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberWeightListMap;
	}
	
	/**
	 * 获取线下体检会员电子血氧列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getMemberBloodOxygenListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberBloodOxygenListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqExperienceClient.getMemberBloodOxygenListMap(requestMap);
		if (apiResponse.isSuccess()) {
			memberBloodOxygenListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberBloodOxygenListMap;
	}
	
	/**
	 * 获取线下体检会员电子血糖列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getMemberBloodSugarListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberBloodSugarListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqExperienceClient.getMemberBloodSugarListMap(requestMap);
		if (apiResponse.isSuccess()) {
			memberBloodSugarListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberBloodSugarListMap;
	}
	
	
}
