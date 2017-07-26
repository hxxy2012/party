package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.JBTSClient;

/**
 * 意见反馈
 * @author hanxu
 *	2015-10-26
 */
public class JBTSClientCall extends BaseCall{

	/**
	 * 新增意见反馈
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> subJBTSMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JBTSClient.subJBTSMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsMap;
	}
	
	/**
	 * 获取意见反馈列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getJBTSListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JBTSClient.getJBTSListMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsListMap;
	}
	
	/**
	 * 获取意见反馈详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getJBTSMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JBTSClient.getJBTSMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsMap;
	}	
	
	/**
	 * 提交意见反馈评价
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> subSatisfactMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JBTSClient.subSatisfactMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsMap;
	}
	
}
