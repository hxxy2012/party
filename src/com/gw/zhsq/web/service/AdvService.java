package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 幻灯片服务
 * @author fuyun
 *	2016-03-11
 */
public interface AdvService {
	
	/**
	 * 获取社区广告列表
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getZhsqAdvList(HashMap<String, String> requestMap);
	/**
	 * 获取幻灯片
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getAdvList(HashMap<String,String> requestMap);
	/**
	 * 获取社区服务顶部幻灯片
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getWfwAdvList(HashMap<String, String> requestMap);
	/**
	 * 获取社区服务横幅广告
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getWfwBannerAdvList(HashMap<String, String> requestMap);
	/**
	 * 获取街道广告列表
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getStreetAdvList(HashMap<String, String> requestMap);
	
	
}
