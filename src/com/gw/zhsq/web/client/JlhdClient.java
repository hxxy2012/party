package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 交流互动
 * @author fuyun
 *	2015-11-04
 */
public class JlhdClient extends BaseClient{

	/**
	 * 获取某社区交流互动列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getJlhdListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getAdvistoryTopics";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取交流互动列表信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取某社区交流互动详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getJlhdMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getAdvistoryTopicInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取交流互动详情信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 新增交流互动
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse addJlhd(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=addAdvistoryTopic";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("新增交流互动失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 新增交流互动回复
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse addHuifu(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=addAdvistoryReply";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("新增交流互动回复失败！");
		}
		return apiResponse;
	}
		
}
