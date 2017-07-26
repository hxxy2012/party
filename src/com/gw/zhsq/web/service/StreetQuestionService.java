package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 街道问卷调查
 * @author hanxu
 *	2015-11-02
 */
public interface StreetQuestionService {
	
	/**
	 * 提交街道问卷调查题目以及选项
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subStreetQuestionMap(HashMap<String,String> requestMap) throws OtherException;
	/**
	 * 获取街道问卷调查列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getStreetQuestionListMap(HashMap<String, String> requestMap)throws OtherException;
	/**
	 * 获取街道问卷调查题目以及选项
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getStreetQuestionInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
	 * 获取当前会员的街道问卷调查题目以及选项
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getResultInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
    
}
