package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 社区办事指南
 * 
 * @author fuyun 2016-04-07
 */
public class ZhsqLawGuideClient extends BaseClient {

	/**
	 * 获取社区办事指南列表
	 * 
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getZhsqLawGuideListMap(
			HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqLawGuideListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,
					requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区办事指南列表失败！");
		}
		return apiResponse;
	}

	/**
	 * 获取社区办事指南类型列表
	 * 
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getZhsqLawGuideTypeListMap(
			HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqLawGuideTypeListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,
					requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区办事指南类型列表失败！");
		}
		return apiResponse;
	}

	public static ApiResponse getZhsqLawGuideList(
			HashMap<String, String> requestMap) {

		String rUrl = "/SheQu.gw?requestName=getBasLawGuide";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,
					requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区办事指南类型列表失败！");
		}
		return apiResponse;
	}

	/**
	 * 获取社区办事指南详情
	 * 
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getZhsqLawGuideMap(
			HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqLawGuideMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,
					requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区办事指南详情失败！");
		}
		return apiResponse;
	}

}
