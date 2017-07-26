package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 线下体检
 * @author hanxu
 *	2015-10-26
 */
public class ZhsqExperienceClient extends BaseClient{
	
	/**
	 * 获取线下体检会员电子血压列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getMemberBloodPressListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheBei.gw?requestName=getMemberBloodPressList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取会员电子血压列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取线下体检会员电子体重列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getMemberWeightListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheBei.gw?requestName=getMemberWeightList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取会员电子体重列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取线下体检会员电子血氧列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getMemberBloodOxygenListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheBei.gw?requestName=getMemberBloodOxygenList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取会员电子血氧列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取线下体检会员电子血糖列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getMemberBloodSugarListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheBei.gw?requestName=getMemberBloodSugarList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取会员电子血糖列表失败！");
		}
		return apiResponse;
	}
	
	
	
}
