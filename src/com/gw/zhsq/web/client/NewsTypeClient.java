package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

public class NewsTypeClient extends BaseClient{
	
	/**
	 * 获取下级资讯分类列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNewsClassList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getInfoClass";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取资讯分类列表失败！");
		}
		return apiResponse;
	}
	
}
