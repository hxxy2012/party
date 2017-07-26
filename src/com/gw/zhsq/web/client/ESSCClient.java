package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 二手市场
 * @author hanxu
 *	2015-11-02
 */
public class ESSCClient extends BaseClient{
	
	/**
	 * 提交二手市场信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subESSCMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subESSC";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交二手市场信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取二手市场列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getESSCListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getESSCList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取二手市场列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取二手市场详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getESSCMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getESSCInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取二手市场详情失败！");
		}
		return apiResponse;
	}			
	
}
