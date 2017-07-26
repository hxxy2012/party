package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.WGYClient;

/**
 * 网格员
 * @author hanxu
 *	2015-11-02
 */
public class WGYClientCall extends BaseCall{
	
	/**
	 * 获取网格员列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getWGYListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> wgyListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = WGYClient.getWGYListMap(requestMap);
		if (apiResponse.isSuccess()) {
			wgyListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return wgyListMap;
	}
	
}
