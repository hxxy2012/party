package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 二手市场
 * @author hanxu
 *	2015-11-02
 */
public interface ESSCService {
	
	/**
	 * 提交二手市场
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subESSCMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取二手市场列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getESSCListMap(HashMap<String, String> requestMap)throws OtherException;
    
	/**
	 * 获取二手市场详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getESSCInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
}
