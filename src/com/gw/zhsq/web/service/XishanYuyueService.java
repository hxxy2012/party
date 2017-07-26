package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

public interface XishanYuyueService {
	/**
	 * 提交预约信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> subXishanYuyue(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 获取预约信息，列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getXishanYuyueListMap(HashMap<String, String> requestMap)throws OtherException;
	
	/**
	 * 获取详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getXishanYuyueInfoMap(HashMap<String, String> requestMap) throws OtherException;
}
