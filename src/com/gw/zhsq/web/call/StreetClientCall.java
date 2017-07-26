package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.StreetClient;

/**
 * 街道
 * @author lihui
 * 2016-04-27
 */
public class StreetClientCall extends BaseCall{
	
	/**
	 * 获取某街道下社区列表信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getSheQuListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> shequList = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetClient.getSheQuListMap(requestMap);
		if (apiResponse.isSuccess()) {
			shequList = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(), apiResponse.getErrormsg());
		}
		return shequList;
	}
}
