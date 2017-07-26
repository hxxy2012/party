package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 书记信箱
 * @author hanxu
 *	2015-10-26
 */
public class SJXXClient extends BaseClient{
	
	/**
	 * 提交书记信箱反馈信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subSJXXMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subSJXX";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交书记信箱反馈信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取书记信箱列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getSJXXListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getSJXXListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取书记信箱列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取书记信箱详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getSJXXMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getSJXXMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取书记信箱详情失败！");
		}
		return apiResponse;
	}	
	
	/**
	 * 提交书记信箱评价
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subSatisfactMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subSatisfact";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交书记信箱评价失败！");
		}
		return apiResponse;
	}
	
}
