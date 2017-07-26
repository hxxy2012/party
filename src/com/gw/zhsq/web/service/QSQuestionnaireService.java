package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;

/**
 * 【马群问卷】街道问卷
 * @author hanxu
 *	2016-6-17
 */
public interface QSQuestionnaireService {
	
	/**
	 * 获取【马群问卷】街道问卷列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getQSQuestionnaireListMap(HashMap<String, String> requestMap)throws OtherException;
    
	/**
	 * 获取【马群问卷】街道问卷详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getQSQuestionnaireInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
	 * 获取【马群问卷】街道问卷问题列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    List<Map<String, Object>> getQSQuestionList(HashMap<String, String> requestMap)throws OtherException;
    
    /**
   	 * 提交【马群问卷】街道问卷
   	 * @param requestMap
   	 * @return
   	 * @throws OtherException
   	 */
   	Map<String, Object> subQSQuestionnaire(HashMap<String,String> requestMap) throws OtherException;
    
    /**
	 * 获取【马群问卷】街道问卷排行榜
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
   	List<Map<String, Object>> getQSQuestionRankListMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
     * 获取【马群问卷】会员问卷列表
     * @param requestMap
     * @return
     * @throws OtherException
     */
    List<Map<String, Object>> getMemberQuestionnaireListMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
	 * 获取【马群问卷】街道问卷单位列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    List<Map<String, Object>> getQSInfoClassList(HashMap<String, String> requestMap)throws OtherException;
}
