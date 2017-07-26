package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.TXLClient;

/**
 * 通讯录
 * @author hanxu
 *	2015-11-02
 */
public class TXLClientCall extends BaseCall{
	
	/**
	 * 获取通讯录列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getTXLListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> txlListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = TXLClient.getTXLListMap(requestMap);
		if (apiResponse.isSuccess()) {
			txlListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return txlListMap;
	}
	
}
