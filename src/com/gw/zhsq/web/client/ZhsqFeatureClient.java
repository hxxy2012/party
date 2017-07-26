package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 社区特色
 * @author lihui
 * 2016-04-22
 */
public class ZhsqFeatureClient extends BaseClient{
	/**
	 * 获取社区特色列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getZhsqFeatureListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqFeatureListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区特色列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取社区特色详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getZhsqFeatureInfoMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqFeatureInfoMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区特色详情失败！");
		}
		return apiResponse;
	}
}
