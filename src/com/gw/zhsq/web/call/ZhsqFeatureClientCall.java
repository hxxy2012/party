package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ZhsqFeatureClient;

/**
 * 社区特色
 * 
 * @author lihui 2016-04-22
 */
public class ZhsqFeatureClientCall extends BaseCall {
	/**
	 * 获取社区特色列表
	 * 
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getFeatureListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> featureList = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqFeatureClient.getZhsqFeatureListMap(requestMap);
		if (apiResponse.isSuccess()) {
			featureList = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return featureList;
	}

	/**
	 * 获取社区特色详情
	 * 
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getFeatureMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> featureMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqFeatureClient.getZhsqFeatureInfoMap(requestMap);
		if (apiResponse.isSuccess()) {
			featureMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return featureMap;
	}
}
