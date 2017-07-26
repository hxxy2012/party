package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 意见反馈
 * @author hanxu
 *	2015-10-26
 */
public class JBTSClient extends BaseClient{
	
	/**
	 * 新增意见反馈
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subJBTSMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subJBTS";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交意见反馈信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取意见反馈列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getJBTSListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getJBTSList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取意见反馈列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取意见反馈详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getJBTSMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getJBTSInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取意见反馈详情失败！");
		}
		return apiResponse;
	}		
	
	/**
	 * 提交已经反馈评价
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subSatisfactMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subSatisfactByJBTS";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交意见反馈评价失败！");
		}
		return apiResponse;
	}
	
}
