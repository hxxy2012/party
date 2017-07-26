package com.gw.zhsq.web.service.impl;

import java.util.HashMap;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.XishanYuyueAdminClientCall;
import com.gw.zhsq.web.service.XishanYuyueAdminService;


/**
 * 获取预约提醒人员列表
 * @author lihui
 *
 * 2016年6月1日
 */
@Service("xishanYuyueAdminService")
public class XishanYuyueAdminServiceImpl implements XishanYuyueAdminService {

	@Override
	public Map<String, Object> getXishanYuyueAdminListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String pageno = "1",pageSize = "100";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> yuyueAdminListMap = XishanYuyueAdminClientCall.getXishanYuyueAdminListMap(paramMap);
		return yuyueAdminListMap;
	}

}
