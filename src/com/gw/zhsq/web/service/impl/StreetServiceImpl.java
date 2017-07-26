package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.StreetClientCall;
import com.gw.zhsq.web.service.StreetService;

/**
 * 街道
 * @author lihui
 *	2016-04-20
 */
@Service("streetService")
public class StreetServiceImpl implements StreetService {
	
	//获取某街道下社区列表信息
	@Override
	public Map<String, Object> getSheQuListMap(HashMap<String, String> requestMap) throws OtherException {
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String streetId = requestMap.get("streetId");//街道ID
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("streetId", streetId);//街道ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> sheQuListMap = null;
		if(StringUtils.isNotBlank(streetId)){
			sheQuListMap = StreetClientCall.getSheQuListMap(paramMap);
		}
		return sheQuListMap;
	}

}
