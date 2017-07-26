package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.JlhdClient;

/**
 * 交流互动
 * @author fuyun
 *	2015-11-04
 */
public class JlhdClientCall extends BaseCall{

	/**
	 * 获取交流互动列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getJlhdListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jlhdListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JlhdClient.getJlhdListMap(requestMap);
		if (apiResponse.isSuccess()) {
			jlhdListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jlhdListMap;
	}
	
	/**
	 * 获取交流互动详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getJlhdMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jlhdListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JlhdClient.getJlhdMap(requestMap);
		if (apiResponse.isSuccess()) {
			jlhdListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jlhdListMap;
	}
	
	/**
	 * 新增交流互动
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> addJlhd(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jlhdListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JlhdClient.addJlhd(requestMap);
		if (apiResponse.isSuccess()) {
			jlhdListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jlhdListMap;
	}
	
	/**
	 * 新增交流互动回复
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> addHuifu(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jlhdListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = JlhdClient.addHuifu(requestMap);
		if (apiResponse.isSuccess()) {
			jlhdListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jlhdListMap;
	}
	
}
