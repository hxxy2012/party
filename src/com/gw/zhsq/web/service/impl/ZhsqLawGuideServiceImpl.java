package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.ZhsqLawGuideClientCall;
import com.gw.zhsq.web.call.ZhsqLiveClientCall;
import com.gw.zhsq.web.service.ZhsqLawGuideService;

/**
 * 社区办事指南
 * @author fuyun
 *	2016-04-07
 */
@Service("zhsqLawGuideService")
public class ZhsqLawGuideServiceImpl implements ZhsqLawGuideService {
	
	//获取社区办事指南列表
	@Override
	public Map<String, Object> getZhsqLawGuideListMap(HashMap<String, String> requestMap) throws OtherException {
		String pageno = "1",pageSize = "100";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		requestMap.put("page", pageno);//当前页数
		requestMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> streetLawGuideListMap = ZhsqLawGuideClientCall.getZhsqLawGuideListMap(requestMap);
		return streetLawGuideListMap;
	}
	
	
	
	
	public List<Map<String, Object>> getBasLawGuideList(HashMap<String, String> requestMap) throws OtherException
	{
		List<Map<String, Object>> advList = null;
		try {
			advList = ZhsqLawGuideClientCall.getBasLawGuideList(requestMap);
		//	System.out.println("------------List<Map<String, Object>>---------------->"+advList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}
	
	
	
	
	//获取社区办事指南类型列表
		@Override
		public Map<String, Object> getZhsqLawGuideTypeListMap(HashMap<String, String> requestMap) throws OtherException {
//			String pageno = "1",pageSize = "100";
//			if(StringUtils.isNotBlank(requestMap.get("pageno"))){
//				pageno = requestMap.get("pageno");
//			}
//			if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
//				pageSize = requestMap.get("pageSize");
//			}
//			requestMap.put("page", pageno);//当前页数
//			requestMap.put("pageSize", pageSize);//每页条数
			Map<String, Object> streetLawGuideListMap = ZhsqLawGuideClientCall.getZhsqLawGuideTypeListMap(requestMap);
			return streetLawGuideListMap;
		}

	//获取社区办事指南详情
	@Override
	public Map<String, Object> getZhsqLawGuideMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> streetLawGuideMap = ZhsqLawGuideClientCall.getZhsqLawGuideMap(requestMap);
		return streetLawGuideMap;
	}

}
