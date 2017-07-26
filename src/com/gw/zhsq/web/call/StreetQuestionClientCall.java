package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.StreetQuestionClient;

/**
 * 街道问卷调查
 * @author hanxu
 *	2015-11-02
 */
public class StreetQuestionClientCall extends BaseCall{

	//提交街道问卷调查题目以及选项
	public static Map<String, Object> subStreetQuestionMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> streetQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetQuestionClient.subStreetQuestionMap(requestMap);
		if (apiResponse.isSuccess()) {
			streetQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return streetQuestionMap;
	}
	
	//获取街道问卷调查列表
	public static Map<String, Object> getStreetQuestionListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> streetQuestionListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetQuestionClient.getStreetQuestionListMap(requestMap);
		if (apiResponse.isSuccess()) {
			streetQuestionListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return streetQuestionListMap;
	}
	
	//获取街道问卷调查题目以及选项
	public static Map<String, Object> getStreetQuestionMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> streetQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetQuestionClient.getStreetQuestionMap(requestMap);
		if (apiResponse.isSuccess()) {
			streetQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return streetQuestionMap;
	}	
	
	//获取会员街道问卷调查题目以及选项
	public static Map<String, Object> getResultMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> streetQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = StreetQuestionClient.getResultMap(requestMap);
		if (apiResponse.isSuccess()) {
			streetQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return streetQuestionMap;
	}	
	
	
	
}
