package com.gw.zhsq.web.client;

import java.util.HashMap;

import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 街道问卷调查
 * @author hanxu
 *	2015-11-02
 */
public class StreetQuestionClient extends BaseClient{
	
	static String zhsqUrl = PropertiesUtil.getSetting("server_url");

	//提交街道问卷调查信息
	public static ApiResponse subStreetQuestionMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subWmspQuestionnaireSurvey";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("提交街道问卷调查题目以及选项失败！");
		}
		return apiResponse;
	}
	
	//获取街道问卷调查列表
	public static ApiResponse getStreetQuestionListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getWmspQuestionnaireSurveyListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道问卷调查列表失败！");
		}
		return apiResponse;
	}
	
	//获取街道问卷调查题目以及选项
	public static ApiResponse getStreetQuestionMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getWmspQuestionnaireSurveyMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道问卷调查题目以及选项失败！");
		}
		return apiResponse;
	}	
	
	//获取会员街道问卷调查题目以及选项
	public static ApiResponse getResultMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getResultMap";
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
		
	
}
