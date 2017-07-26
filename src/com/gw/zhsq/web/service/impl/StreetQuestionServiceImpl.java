package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.StreetQuestionClientCall;
import com.gw.zhsq.web.service.StreetQuestionService;

@Service("streetQuestionService")
public class StreetQuestionServiceImpl implements StreetQuestionService {
	
	@Override
	public Map<String, Object> subStreetQuestionMap(HashMap<String, String> requestMap)throws OtherException {

		Map<String, Object> streetQuestionMap =  StreetQuestionClientCall.subStreetQuestionMap(requestMap);
		
		return streetQuestionMap;
	}
	
	@Override
	public Map<String, Object> getStreetQuestionListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
//		String memberId = requestMap.get("memberId");//会员ID
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String streetId = requestMap.get("streetId");//街道ID
		paramMap.put("streetId", streetId);
		
//		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		
		System.out.println("paramMap======>>"+paramMap);
		Map<String, Object> streetQuestionListMap = StreetQuestionClientCall.getStreetQuestionListMap(paramMap);
		
		return streetQuestionListMap;
	}
	
	/**
	 * 街道问卷调查题目以及选项
	 * @param 
	 * @return
	 * @throws OtherException 
	 */
	@Override
	public Map<String, Object> getStreetQuestionInfoMap(HashMap<String, String> requestMap) throws OtherException {
		
		Map<String, Object> streetQuestionInfoMap = StreetQuestionClientCall.getStreetQuestionMap(requestMap);
		System.out.println("streetQuestionInfoMap======>>"+streetQuestionInfoMap);
//		if(esscInfoMap!=null){
//			String content = StringUtil.nvl(esscInfoMap.get("content"));
//			content = StringUtil.HtmlText(content);
//			esscInfoMap.put("content", content);
//		}
		
		return streetQuestionInfoMap;
	}
	
	/**
	 * 获取当前会员的街道问卷调查题目以及选项
	 * @param 
	 * @return
	 * @throws OtherException 
	 */
	@Override
	public Map<String, Object> getResultInfoMap(HashMap<String, String> requestMap) throws OtherException {
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String qs_id = requestMap.get("qs_id");
		paramMap.put("member_id", memberId);//会员ID
		paramMap.put("qs_id", qs_id);//问卷调查ID
		Map<String, Object> resultInfoMap = StreetQuestionClientCall.getResultMap(paramMap);
		System.out.println("resultInfoMap======>>"+resultInfoMap.get("data"));
//		if(esscInfoMap!=null){
//			String content = StringUtil.nvl(esscInfoMap.get("content"));
//			content = StringUtil.HtmlText(content);
//			esscInfoMap.put("content", content);
//		}
		
		return resultInfoMap;
	}
	
	

}
