package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.ZhsqQuestionClient;

/**
 * 社区问卷调查
 * @author hanxu
 *	2015-11-02
 */
public class ZhsqQuestionClientCall extends BaseCall{

	//提交社区问卷调查题目以及选项
	public static Map<String, Object> subZhsqQuestionMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqQuestionClient.subZhsqQuestionMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}
	
	//获取社区问卷调查列表
	public static Map<String, Object> getZhsqQuestionListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqQuestionClient.getZhsqQuestionListMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return zhsqQuestionListMap;
	}
	
	//获取社区问卷调查题目以及选项
	public static Map<String, Object> getZhsqQuestionMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqQuestionClient.getZhsqQuestionMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}	
	
	//获取会员社区问卷调查题目以及选项
	public static Map<String, Object> getResultMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqQuestionClient.getResultMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}	
	
	//获取【社区】会员问卷列表
	public static Map<String, Object> getMemberZhsqQuestionListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = ZhsqQuestionClient.getMemberZhsqQuestionListMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),
					apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}	
	
}
