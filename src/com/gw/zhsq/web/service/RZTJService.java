package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 认证推荐
 * @author hanxu
 *	2015-11-02
 */
public interface RZTJService {
	
	/**
	 * 提交认证推荐
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subRZTJMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取认证推荐列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getRZTJListMap(HashMap<String, String> requestMap)throws OtherException;
    
	/**
	 * 获取认证推荐详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getRZTJInfoMap(HashMap<String, String> requestMap) throws OtherException;
    
}
