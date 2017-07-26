package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.StreetLawGuideClientCall;
import com.gw.zhsq.web.service.StreetLawGuideService;

/**
 * 街道办事指南
 * @author fuyun
 *	2016-04-07
 */
@Service("streetLawGuideService")
public class StreetLawGuideServiceImpl implements StreetLawGuideService {
	
	//获取街道办事指南列表
	@Override
	public Map<String, Object> getStreetLawGuideListMap(HashMap<String, String> requestMap) throws OtherException {
		String pageno = "1",pageSize = "100";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		requestMap.put("page", pageno);//当前页数
		requestMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> streetLawGuideListMap = StreetLawGuideClientCall.getStreetLawGuideListMap(requestMap);
		return streetLawGuideListMap;
	}

	//获取街道办事指南详情
	@Override
	public Map<String, Object> getStreetLawGuideMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> streetLawGuideMap = StreetLawGuideClientCall.getStreetLawGuideMap(requestMap);
		return streetLawGuideMap;
	}

}
