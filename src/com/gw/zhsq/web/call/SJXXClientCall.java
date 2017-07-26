package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.SJXXClient;

/**
 * 书记信箱
 * @author hanxu
 *	2015-10-26
 */
public class SJXXClientCall extends BaseCall{

	/**
	 * 提交书记信箱反馈信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> subSJXXMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = SJXXClient.subSJXXMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxMap;
	}
	
	/**
	 * 获取书记信箱列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getSJXXListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = SJXXClient.getSJXXListMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxListMap;
	}
	
	/**
	 * 获取书记信箱详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getSJXXMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = SJXXClient.getSJXXMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxMap;
	}
	
	/**
	 * 提交书记信箱评价
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> subSatisfactMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> sjxxMap = null;
		ApiResponse apiResponse = null;
		apiResponse = SJXXClient.subSatisfactMap(requestMap);
		if (apiResponse.isSuccess()) {
			sjxxMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return sjxxMap;
	}
	
}
