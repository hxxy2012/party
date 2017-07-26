package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 线下体检
 * @author hanxu
 *	2015-10-26
 */
public interface ZhsqExperienceService {
	
	/**
	 * 获取线下体检会员电子血压列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getMemberBloodPressListMap(HashMap<String, String> requestMap)throws OtherException;
    
	/**
	 * 获取线下体检会员电子体重列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getMemberWeightListMap(HashMap<String, String> requestMap)throws OtherException;
    
    
	/**
	 * 获取线下体检会员电子血氧列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getMemberBloodOxygenListMap(HashMap<String, String> requestMap)throws OtherException;
    
    
	/**
	 * 获取线下体检会员电子血糖列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getMemberBloodSugarListMap(HashMap<String, String> requestMap)throws OtherException;
    
    
    
}
