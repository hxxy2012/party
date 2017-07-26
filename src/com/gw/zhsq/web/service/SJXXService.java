package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;

/**
 * 书记信箱
 * @author hanxu
 *	2015-10-26
 */
public interface SJXXService {
	
	/**
	 * 提交书记信箱
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subSJXXMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取书记信箱列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getSJXXListMap(HashMap<String, String> requestMap)throws OtherException;
	
	/**
	 * 获取书记信箱详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getSJXXInfoMap(HashMap<String, String> requestMap) throws OtherException;
	
	/**
	 * 提交书记信箱评价
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subSatisfactMap(HashMap<String, String> requestMap)throws OtherException;
	
}
