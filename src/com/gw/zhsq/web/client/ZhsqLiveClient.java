package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 *  获取社区生活/生活助手
 * @author fuyun
 *	2015-07-22
 */
public class ZhsqLiveClient extends BaseClient{

	/**
	 * 获取社区生活/生活助手列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static ApiResponse getZhsqLiveList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqLiveClass";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区生活/生活助手列表失败！");
		}
		return apiResponse;
	}
	
	public static ApiResponse getZhsqList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区失败！");
		}
		return apiResponse;
	}
	
	
}
