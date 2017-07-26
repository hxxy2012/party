package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 街道办事指南
 * @author fuyun
 *	2016-04-07
 */
public class StreetLawGuideClient extends BaseClient{
	
	/**
	 * 获取街道办事指南列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getStreetLawGuideListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetLawGuideListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道办事指南列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取街道办事指南详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getStreetLawGuideMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetLawGuideMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道办事指南详情失败！");
		}
		return apiResponse;
	}
		
}
