package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.RZTJClient;

/**
 * 认证推荐
 * @author hanxu
 *	2015-11-02
 */
public class RZTJClientCall extends BaseCall{

	/**
	 * 提交认证推荐信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> subRZTJMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> rztjMap = null;
		ApiResponse apiResponse = null;
		apiResponse = RZTJClient.subRZTJMap(requestMap);
		if (apiResponse.isSuccess()) {
			rztjMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return rztjMap;
	}
	
	/**
	 * 获取认证推荐列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getRZTJListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> rztjListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = RZTJClient.getRZTJListMap(requestMap);
		if (apiResponse.isSuccess()) {
			rztjListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return rztjListMap;
	}
	
	/**
	 * 获取认证推荐详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getRZTJMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> rztjMap = null;
		ApiResponse apiResponse = null;
		apiResponse = RZTJClient.getRZTJMap(requestMap);
		if (apiResponse.isSuccess()) {
			rztjMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return rztjMap;
	}	
	
}
