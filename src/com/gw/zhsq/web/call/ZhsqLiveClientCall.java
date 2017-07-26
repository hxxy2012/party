package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ZhsqLiveClient;

/**
 * 获取社区生活/生活助手
 * @author fuyun
 *	2015-07-22
 */
public class ZhsqLiveClientCall extends BaseCall{

	/**
	 * 获取幻灯片列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getZhsqLiveList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> zhsqLiveList = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqLiveClient.getZhsqLiveList(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqLiveList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqLiveList;
	}
	
	public static List<Map<String, Object>> getZhsqList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> zhsqLiveList = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqLiveClient.getZhsqList(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqLiveList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqLiveList;
	}
	
	
	
}
