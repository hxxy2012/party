package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 通讯录
 * @author hanxu
 *	2015-11-02
 */
public interface TXLService {
	
	/**
	 * 获取通讯录列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getTXLListMap(HashMap<String, String> requestMap)throws OtherException;
    
}
