package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

public interface ZhsqFeatureService {
	/**
	 * 获取社区特色信息，列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getZhsqFeatureListMap(HashMap<String, String> requestMap)throws OtherException;
	
	/**
	 * 获取社区特色详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getZhsqFeatureInfoMap(HashMap<String, String> requestMap) throws OtherException;
}
