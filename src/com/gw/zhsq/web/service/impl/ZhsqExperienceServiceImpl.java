package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.ZhsqExperienceClientCall;
import com.gw.zhsq.web.service.ZhsqExperienceService;

/**
 * 线下体检
 * @author hanxu
 *	2016-04-18
 */
@Service("zhsqExperienceService")
public class ZhsqExperienceServiceImpl implements ZhsqExperienceService {
	
	//获取线下体检会员电子血压列表
	@Override
	public Map<String, Object> getMemberBloodPressListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String pageno = "1",pageSize = "1000";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> bloodPressListMap = ZhsqExperienceClientCall.getMemberBloodPressListMap(paramMap);
		return bloodPressListMap;
	}
	
	//获取线下体检会员电子体重列表
	@Override
	public Map<String, Object> getMemberWeightListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String pageno = "1",pageSize = "1000";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> weightListMap = ZhsqExperienceClientCall.getMemberWeightListMap(paramMap);
		return weightListMap;
	}
	
	//获取线下体检会员电子血氧列表
	@Override
	public Map<String, Object> getMemberBloodOxygenListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String pageno = "1",pageSize = "1000";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> bloodOxygenListMap = ZhsqExperienceClientCall.getMemberBloodOxygenListMap(paramMap);
		return bloodOxygenListMap;
	}
	
	//获取线下体检会员电子血糖列表
	@Override
	public Map<String, Object> getMemberBloodSugarListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");
		String pageno = "1",pageSize = "1000";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> sugarListMap = ZhsqExperienceClientCall.getMemberBloodSugarListMap(paramMap);
		return sugarListMap;
	}
	
	
}
