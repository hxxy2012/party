package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gw.base.exception.OtherException;

/**
 * 社区办事指南
 * @author fuyun
 *	2016-05-06
 */
public interface ZhsqLawGuideService {

	/**
	 * 获取社区办事指南列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getZhsqLawGuideListMap(HashMap<String, String> requestMap)throws OtherException;
	/**
	 * 获取社区办事指南类型列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getZhsqLawGuideTypeListMap(HashMap<String, String> requestMap) throws OtherException;
	
	
	List<Map<String, Object>> getBasLawGuideList(HashMap<String, String> requestMap) throws OtherException;
	/**
	 * 获取社区办事指南详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getZhsqLawGuideMap(HashMap<String, String> requestMap)throws OtherException;
	
}
