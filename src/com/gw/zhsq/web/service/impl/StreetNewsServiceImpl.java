package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;

import com.gw.base.util.StringUtil;

import com.gw.zhsq.web.call.StreetNewsClientCall;

import com.gw.zhsq.web.service.StreetNewsService;

/**
 * 街道资讯业务
 * @author hanxu
 *	2015-10-12
 */
@Service("streetNewsService")
public class StreetNewsServiceImpl implements StreetNewsService {
	
	//获取下级分类列表
	@Override
	public List<Map<String, Object>> getStreetInfoClassList(HashMap<String, String> requestMap) throws OtherException {
		String supClassId = requestMap.get("supClassId");//资讯类信息父ID
		String streetId = requestMap.get("streetId");
		if(supClassId==null || "".equals(supClassId)){
			supClassId = "1";//一级资讯类信息父ID
		}
		HashMap<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("supClassId", supClassId);//资讯类信息父ID
		searchMap.put("streetId", streetId);//街道ID
		List<Map<String, Object>> classList = StreetNewsClientCall.getStreetInfoClassList(searchMap);
		return classList;
	}

	@Override
	public Map<String, Object> getStreetNewsInfoList(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String classId = requestMap.get("classId");//政务父ID
		if(classId==null || "".equals(classId)){
			classId = "";//政务分类ID
			paramMap.put("isTop", "1");//是否推荐 0不推荐 1推荐
		}else{
			paramMap.put("isTop", "");//是否推荐 0不推荐 1推荐
		}
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String streetId = requestMap.get("streetId");//社区ID
		String title = requestMap.get("title");//政务标题
		paramMap.put("streetId", streetId);//社区ID
		paramMap.put("classId", classId);//政务分类ID
		paramMap.put("title", title);//标题
	
		paramMap.put("clientContent", "");//内容
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> newsListMap = null;
		if(StringUtils.isNotBlank(streetId)){
			 newsListMap = StreetNewsClientCall.getStreetNewsListMap(paramMap);
		}
		return newsListMap;
	}

	@Override
	public Map<String, Object> getStreetNewsInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> newsInfoMap = StreetNewsClientCall.getStreetNewsMap(requestMap);
		if(newsInfoMap!=null){
			String clientContent = StringUtil.nvl(newsInfoMap.get("clientContent"));
			clientContent = StringUtil.HtmlText(clientContent);
			newsInfoMap.put("clientContent", clientContent);
		}
		return newsInfoMap;
	}

}



