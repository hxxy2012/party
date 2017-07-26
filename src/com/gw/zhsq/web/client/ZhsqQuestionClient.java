package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 社区问卷调查
 * @author hanxu
 *	2015-11-02
 */
public class ZhsqQuestionClient extends BaseClient{
	
	static String zhsqUrl = PropertiesUtil.getSetting("server_url");

	//提交社区问卷调查信息
	public static ApiResponse subZhsqQuestionMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subZhsqQuestionnaireSurvey";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交社区问卷调查题目以及选项失败！");
		}
		return apiResponse;
	}
	
	//获取社区问卷调查列表
	public static ApiResponse getZhsqQuestionListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqQuestionnaireSurveyListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区问卷调查列表失败！");
		}
		return apiResponse;
	}
	
	//获取社区问卷调查题目以及选项
	public static ApiResponse getZhsqQuestionMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqQuestionnaireSurveyMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区问卷调查题目以及选项失败！");
		}
		return apiResponse;
	}	
	
	//获取会员社区问卷调查题目以及选项
	public static ApiResponse getResultMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqResultMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取会员问卷调查题目以及选项失败！");
		}
		return apiResponse;
	}	
	
	//获取【社区】会员问卷列表
	public static ApiResponse getMemberZhsqQuestionListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getMemberZhsqQuestionListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区会员问卷列表失败！");
		}
		return apiResponse;
	}	
	
}
