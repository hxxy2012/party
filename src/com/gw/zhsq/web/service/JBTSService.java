package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 意见反馈
 * @author hanxu
 *	2015-10-26
 */
public interface JBTSService {
	
	/**
	 * 提交意见反馈
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subJBTSMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取意见反馈列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getJBTSListMap(HashMap<String, String> requestMap)throws OtherException;
    
    /**
	 * 获取意见反馈详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getJBTSInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
    /**
   	 * 提交已经反馈评价
   	 * @param requestMap
   	 * @return
   	 * @throws OtherException
   	 */
    Map<String, Object> subSatisfactMap(HashMap<String, String> requestMap)throws OtherException;
    
}
