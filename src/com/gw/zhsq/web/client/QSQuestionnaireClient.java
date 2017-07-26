package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 【马群问卷】街道问卷
 * @author hanxu
 *	2016-6-17
 */
public class QSQuestionnaireClient extends BaseClient{
	
	static String zhsqUrl = PropertiesUtil.getSetting("server_url");

	//获取【马群问卷】街道问卷列表
	public static ApiResponse getQSQuestionnaireListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getQSQuestionnaireListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道问卷列表失败！");
		}
		return apiResponse;
	}
	
	//获取【马群问卷】街道问卷详情
	public static ApiResponse getQSQuestionnaireMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getQSQuestionnaireMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道问卷详情失败！");
		}
		return apiResponse;
	}	
	
	//获取【马群问卷】街道问卷问题列表
	public static ApiResponse getQSQuestionList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getQSQuestionList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道问卷题目列表失败！");
		}
		return apiResponse;
	}
	
	//提交【马群问卷】街道问卷
	public static ApiResponse subQSQuestionnaire(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subQSQuestionnaire";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交问卷失败！");
		}
		return apiResponse;
	}
	
	//获取【马群问卷】街道问卷排行榜
	public static ApiResponse getQSQuestionRankListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getQSQuestionRankListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道问卷排行榜失败！");
		}
		return apiResponse;
	}
	
	//获取【马群问卷】会员问卷列表
	public static ApiResponse getMemberQuestionnaireList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getMemberQuestionnaireList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取会员问卷列表失败！");
		}
		return apiResponse;
	}
	
	//获取【马群问卷】街道问卷单位列表
	public static ApiResponse getQSInfoClassList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getQSInfoClassList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取单位列表失败！");
		}
		return apiResponse;
	}
	
}
