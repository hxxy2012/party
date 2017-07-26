package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地区服务
 * @author fuyun
 *	2016-03-11
 */
public interface RegionService {
	
	/**
	 * 获取省下所有城市
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getOneProCitys(HashMap<String,String> requestMap);
	
	/**
	 * 获取地区信息
	 * @param requestMap
	 * @return
	 */
	public Map<String, Object> getRegionInfo(HashMap<String, String> requestMap);
	
	/**
	 * 获取省份
	 * @param requestMap
	 * @return
	 */
	public  Map<String, Object> getProInfo(HashMap<String, String> requestMap);
	
	/**
	 * 获取城市
	 * @param requestMap
	 * @return
	 */
	public  Map<String, Object> getCityInfo(HashMap<String, String> requestMap);
	
}
