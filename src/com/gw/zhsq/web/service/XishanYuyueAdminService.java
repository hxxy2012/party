package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

public interface XishanYuyueAdminService {
	/**
	 * 获取预约提醒人员列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> getXishanYuyueAdminListMap(HashMap<String, String> requestMap)throws OtherException;
}
