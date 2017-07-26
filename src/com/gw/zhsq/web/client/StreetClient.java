package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 街道
 * @author lihui
 * 2016-04-27
 */
public class StreetClient extends BaseClient{
	
	/**
	 * 获取某街道下社区列表信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getSheQuListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getSheQuListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取某街道下社区列表失败！");
		}
		return apiResponse;
	}
	
}
