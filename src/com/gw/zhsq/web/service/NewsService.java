package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 * 资讯
 * @author hanxu
 *	2015-10-13
 */
public interface NewsService {
	
	/**
	 * 获取资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getNewsListMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取政务列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	List<Map<String, Object>> getNewsList(HashMap<String, String> requestMap) throws OtherException;
	
	/**
	 * 获取政务详情
	 * @param requestMap
	 * @return
	 */
	public Map<String, Object> getNewsInfoMap(HashMap<String, String> requestMap) throws OtherException;
	
	/**
	 * 获取最新资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	List<Map<String, Object>> getNewestNewsList(HashMap<String, String> requestMap) throws OtherException;
	
}
