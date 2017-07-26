package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 社区问卷调查
 * @author hanxu
 *	2015-11-02
 */
public interface ZhsqQuestionService {
	
	/**
	 * 提交社区问卷调查题目以及选项
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subZhsqQuestionMap(HashMap<String,String> requestMap) throws OtherException;
	/**
	 * 获取社区问卷调查列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getZhsqQuestionListMap(HashMap<String, String> requestMap)throws OtherException;
	/**
	 * 获取社区问卷调查题目以及选项
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getZhsqQuestionInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
	 * 获取当前会员的社区问卷调查题目以及选项
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getResultInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
     * 获取【社区】会员问卷列表
     * @param requestMap
     * @return
     * @throws OtherException
     */
    Map<String, Object> getMemberZhsqQuestionListMap(HashMap<String, String> requestMap) throws OtherException;
}
