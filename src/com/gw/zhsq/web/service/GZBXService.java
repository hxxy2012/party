package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 故障报修
 * @author hanxu
 *	2015-11-02
 */
public interface GZBXService {
	
	/**
	 * 提交故障报修
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subGZBXMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取故障报修列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getGZBXListMap(HashMap<String, String> requestMap)throws OtherException;
    
	/**
	 * 获取故障报修详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getGZBXInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
	/**
	 * 获取社区信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getShequInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
}
