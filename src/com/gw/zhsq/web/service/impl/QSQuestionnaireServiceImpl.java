package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.QSQuestionnaireClientCall;
import com.gw.zhsq.web.service.QSQuestionnaireService;

/**
 * 【马群问卷】街道问卷
 * @author hanxu
 *	2016-6-17
 */
@Service("qsQuestionnaireService")
public class QSQuestionnaireServiceImpl implements QSQuestionnaireService {
	
	//获取【马群问卷】街道问卷列表
	@Override
	public Map<String, Object> getQSQuestionnaireListMap(HashMap<String, String> requestMap)throws OtherException {
		String streetId = requestMap.get("streetId");//街道ID
		String pageno = "1",pageSize = "100";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("streetId", streetId);
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> qsQuestionListMap = QSQuestionnaireClientCall.getQSQuestionnaireListMap(paramMap);
		return qsQuestionListMap;
	}
	
	//获取【马群问卷】街道问卷详情
	@Override
	public Map<String, Object> getQSQuestionnaireInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionInfoMap = QSQuestionnaireClientCall.getQSQuestionnaireMap(requestMap);
		return zhsqQuestionInfoMap;
	}
		
	//获取【马群问卷】街道问卷问题列表
	@Override
	public List<Map<String, Object>> getQSQuestionList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> qsQuestionList = QSQuestionnaireClientCall.getQSQuestionList(requestMap);
		return qsQuestionList;
	}
	
	//提交【马群问卷】街道问卷
	@Override
	public Map<String, Object> subQSQuestionnaire(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> qsQuestionnaireMap =  QSQuestionnaireClientCall.subQSQuestionnaire(requestMap);
		return qsQuestionnaireMap;
	}
	
	//获取【马群问卷】街道问卷排行榜
	@Override
	public List<Map<String, Object>> getQSQuestionRankListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String qsId = requestMap.get("qsId");
		String streetId = requestMap.get("streetId");
		paramMap.put("qsId", qsId);//问卷ID
		paramMap.put("streetId", streetId);//街道ID
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		List<Map<String, Object>> resultInfoMap = QSQuestionnaireClientCall.getQSQuestionRankListMap(paramMap);
		return resultInfoMap;
	}

	//获取【马群问卷】会员问卷列表
	@Override
	public List<Map<String, Object>> getMemberQuestionnaireListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String streetId = requestMap.get("streetId");
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("streetId", streetId);//街道ID
		List<Map<String, Object>> resultList = QSQuestionnaireClientCall.getMemberQuestionnaireList(paramMap);
		return resultList;
	}

	//获取【马群问卷】街道问卷单位列表
	@Override
	public List<Map<String, Object>> getQSInfoClassList(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String streetId = requestMap.get("streetId");//街道ID
		String supClassId = requestMap.get("supClassId");//上级ID
		paramMap.put("streetId", streetId);
		if(StringUtils.isNotBlank(supClassId)){
			paramMap.put("supClassId", supClassId);
		}
		List<Map<String, Object>> resultList = QSQuestionnaireClientCall.getQSInfoClassList(paramMap);
		return resultList;
	}
	
}
