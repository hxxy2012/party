package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动公告列表
 * @author fuyun
 * 2015-4-3
 */
public interface NoticesInfoService {
	
	/**
	 * 获取活动公告列表
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getNoticesInfoList(HashMap<String,String> requestMap);
	
	/**
	 * 获取活动公告详情
	 * @param requestMap
	 * @return
	 */
    Map<String, Object> getNoticesMap(HashMap<String, String> requestMap);
    
    /**
	 * 获取活动公告详情
	 * @param requestMap
	 * @return
	 */
    Map<String, Object> getNoticesInfoMap(HashMap<String, String> requestMap);
}
