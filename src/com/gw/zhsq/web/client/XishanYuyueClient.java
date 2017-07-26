package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 在线预约
 * 
 * @author lihui 2016-05-18
 */
public class XishanYuyueClient extends BaseClient {
	// 提交预约
	public static ApiResponse subXishanYuyue(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subXishanYuyue";// 提交预约信息地址
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交预约信息失败！");
		}
		return apiResponse;
	}

	// 获取预约信息列表
	public static ApiResponse getXishanYuyueListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanYuyueListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取预约信息列表失败！");
		}
		return apiResponse;
	}

	// 获取预约详情
	public static ApiResponse getXishanYuyueMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanYuyueMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl, requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取预约详情失败！");
		}
		return apiResponse;
	}
}
