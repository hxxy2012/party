package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 街道
 * @author lihui
 *	2016-04-20
 */
public interface StreetService {
	
	/**
	 * 获取某街道下社区列表信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getSheQuListMap(HashMap<String, String> requestMap)throws OtherException;
	
}
