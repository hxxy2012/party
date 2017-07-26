package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 网格员
 * @author hanxu
 *	2015-11-02
 */
public interface WGYService {
	
	/**
	 * 获取网格员列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
    Map<String, Object> getWGYListMap(HashMap<String, String> requestMap)throws OtherException;
    
}
