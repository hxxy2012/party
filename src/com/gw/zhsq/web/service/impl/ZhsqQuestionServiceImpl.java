package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.ZhsqQuestionClientCall;
import com.gw.zhsq.web.service.ZhsqQuestionService;

/**
 * 【社区】问卷调查
 * @author hanxu
 *	2015-10-22
 */
@Service("zhsqQuestionService")
public class ZhsqQuestionServiceImpl implements ZhsqQuestionService {
	
	//获取社区问卷调查详情
	@Override
	public Map<String, Object> subZhsqQuestionMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> zhsqQuestionMap =  ZhsqQuestionClientCall.subZhsqQuestionMap(requestMap);
		return zhsqQuestionMap;
	}
	
	//获取社区问卷调查列表
	@Override
	public Map<String, Object> getZhsqQuestionListMap(HashMap<String, String> requestMap)throws OtherException {
		String shequId = requestMap.get("shequId");//街道ID
		String pageno = "1",pageSize = "100";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("shequId", shequId);
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> zhsqQuestionListMap = ZhsqQuestionClientCall.getZhsqQuestionListMap(paramMap);
		return zhsqQuestionListMap;
	}
	
	//街道问卷调查题目以及选项
	@Override
	public Map<String, Object> getZhsqQuestionInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> zhsqQuestionInfoMap = ZhsqQuestionClientCall.getZhsqQuestionMap(requestMap);
		return zhsqQuestionInfoMap;
	}
	
	//获取当前会员的街道问卷调查题目以及选项
	@Override
	public Map<String, Object> getResultInfoMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String qs_id = requestMap.get("qs_id");
		paramMap.put("member_id", memberId);//会员ID
		paramMap.put("qs_id", qs_id);//问卷调查ID
		Map<String, Object> resultInfoMap = ZhsqQuestionClientCall.getResultMap(paramMap);
		return resultInfoMap;
	}

	//获取【社区】会员问卷列表
	@Override
	public Map<String, Object> getMemberZhsqQuestionListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String shequId = requestMap.get("shequId");
		paramMap.put("member_id", memberId);//会员ID
		paramMap.put("shequId", shequId);//社区ID
		System.out.println("paramMap====weixinServerce=====>>"+paramMap);
		Map<String, Object> zhsqQuestionInfoMap = ZhsqQuestionClientCall.getMemberZhsqQuestionListMap(paramMap);
		return zhsqQuestionInfoMap;
	}
	
	
	
}
