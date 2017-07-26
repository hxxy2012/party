package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 故障报修
 * @author hanxu
 *	2015-11-02
 */
public class GZBXClient extends BaseClient{

	/**
	 * 提交故障报修信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subGZBXMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subGZBX";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交故障报修信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取故障报修列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getGZBXListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getGZBXList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取故障报修列表失败！");
		}
		return apiResponse;
	}
		
	/**
	 * 获取故障报修详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getGZBXMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getGZBXInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取故障报修详情失败！");
		}
		return apiResponse;
	}		
	
	/**
	 * 获取社区信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getShequMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getShequInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区信息失败！");
		}
		return apiResponse;
	}	
	
}
