package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

public interface ZhsqYuyueService {
	/**
	 * 提交预约信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subShequYuyue(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取预约信息，列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getShequYuyueListMap(HashMap<String, String> requestMap)throws OtherException;
	
	/**
	 * 获取详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getShequYuyueInfoMap(HashMap<String, String> requestMap) throws OtherException;
}
