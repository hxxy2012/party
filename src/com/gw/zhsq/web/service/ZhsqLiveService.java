package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社区生活/生活助手
 * @author fuyun
 *	2016-03-11
 */
public interface ZhsqLiveService {
	
	/**
	 * 获取社区生活/生活助手列表
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getZhsqLiveList(HashMap<String,String> requestMap);

	/**
	 * 获取社区列表
	 * @param requestMap
	 * @return
	 */
	List<Map<String, Object>> getZhsqList(HashMap<String,String> requestMap);
	
}
