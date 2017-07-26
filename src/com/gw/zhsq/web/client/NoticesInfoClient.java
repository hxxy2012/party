package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 活动公告
 * @author fuyun
 *	2015-10-23
 */
public class NoticesInfoClient extends BaseClient{

	/**
	 * 获取活动公告列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNoticesMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getNoticeInfoList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取公告失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取活动公告详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNoticesInfoMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getNoticeInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取公告详情失败！");
		}
		return apiResponse;
	}
	
}
