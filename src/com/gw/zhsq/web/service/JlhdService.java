package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;

/**
 * 交流互动
 * @author fuyun
 *	2015-11-04
 */
public interface JlhdService {
	
	/**
	 * 获取交流互动列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getJlhdListMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取交流互动详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getJlhdMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 新增交流互动
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> addJlhd(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 新增交流互动回复
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> addHuifu(HashMap<String,String> requestMap) throws OtherException;
	
}
