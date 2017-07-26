package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 街道资讯业务层
 * @author hanxu
 *	2015-12-20
 */
public interface StreetNewsService {
	
	/**
	 * 获取下级分类列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	List<Map<String, Object>> getStreetInfoClassList(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getStreetNewsInfoList(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取资讯详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getStreetNewsInfoMap(HashMap<String,String> requestMap) throws OtherException;
}
