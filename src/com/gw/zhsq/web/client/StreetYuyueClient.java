package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 街道预约
 * @author lihui
 *	2016-04-21
 */
public class StreetYuyueClient extends BaseClient{

	//提交街道预约
	public static ApiResponse subStreetYuyue(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subStreetYuyue";//提交预约信息地址
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交街道预约信息失败！");
		}
		return apiResponse;
	}
	
	//获取街道预约信息列表【会员中心】
	public static ApiResponse getStreetYuyueListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetYuyueListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道预约信息列表失败！");
		}
		return apiResponse;
	}
	
	//获取预约详情
	public static ApiResponse getStreetYuyueMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetYuyueMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道预约详情失败！");
		}
		return apiResponse;
	}	
	
}
