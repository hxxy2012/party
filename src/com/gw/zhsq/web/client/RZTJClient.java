package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 认证推荐
 * @author hanxu
 *	2015-11-02
 */
public class RZTJClient extends BaseClient{

	/**
	 * 提交认证推荐信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subRZTJMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subRZTJ";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交认证推荐信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取认证推荐列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getRZTJListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getRZTJList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取认证推荐列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取认证推荐详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getRZTJMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getRZTJInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取认证推荐详情失败！");
		}
		return apiResponse;
	}			
	
}
