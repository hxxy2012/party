package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

public class StreetNewsClient extends BaseClient {

	/**
	 * 获取下级资讯分类列表
	 * 
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getStreetInfoClassList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetInfoClass";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道资讯分类列表失败！");
		}
		return apiResponse;
	}

	/**
	 * 获取街道资讯列表
	 * 
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getStreetNewsListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetNewsInfoList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取政务列表失败！");
		}
		return apiResponse;
	}

	/**
	 * 获取政务详情
	 * 
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getStreetNewsMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetNewsInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取政务详情失败！");
		}
		return apiResponse;
	}
}
