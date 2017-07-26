package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.QSQuestionnaireClient;

/**
 * 【马群问卷】街道问卷
 * @author hanxu
 *	2016-6-17
 */
public class QSQuestionnaireClientCall extends BaseCall{

	//获取【马群问卷】街道问卷列表
	public static Map<String, Object> getQSQuestionnaireListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> qsQuestionListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.getQSQuestionnaireListMap(requestMap);
		if (apiResponse.isSuccess()) {
			qsQuestionListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return qsQuestionListMap;
	}
	
	//获取【马群问卷】街道问卷详情
	public static Map<String, Object> getQSQuestionnaireMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.getQSQuestionnaireMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}
	
	//获取【马群问卷】街道问卷问题列表
	public static List<Map<String, Object>> getQSQuestionList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> qsQuestionList = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.getQSQuestionList(requestMap);
		if (apiResponse.isSuccess()) {
			qsQuestionList = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return qsQuestionList;
	}
		
	//提交【马群问卷】街道问卷
	public static Map<String, Object> subQSQuestionnaire(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.subQSQuestionnaire(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}
	
	//获取【马群问卷】街道问卷排行榜
	public static List<Map<String, Object>> getQSQuestionRankListMap(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.getQSQuestionRankListMap(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}	
	
	//获取【马群问卷】会员问卷列表
	public static List<Map<String, Object>> getMemberQuestionnaireList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.getMemberQuestionnaireList(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}
	
	//获取【马群问卷】街道问卷单位列表
	public static List<Map<String, Object>> getQSInfoClassList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> zhsqQuestionMap = null;
		ApiResponse apiResponse = null;
		apiResponse = QSQuestionnaireClient.getQSInfoClassList(requestMap);
		if (apiResponse.isSuccess()) {
			zhsqQuestionMap = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return zhsqQuestionMap;
	}
	
}
