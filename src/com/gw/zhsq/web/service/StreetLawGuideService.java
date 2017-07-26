package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;

/**
 * 街道办事指南
 * @author fuyun
 *	2016-04-07
 */
public interface StreetLawGuideService {

	/**
	 * 获取街道办事指南列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getStreetLawGuideListMap(HashMap<String, String> requestMap)throws OtherException;
	
	/**
	 * 获取街道办事指南详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getStreetLawGuideMap(HashMap<String, String> requestMap)throws OtherException;
	
}
