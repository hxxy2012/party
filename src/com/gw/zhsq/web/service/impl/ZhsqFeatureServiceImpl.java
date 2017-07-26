package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.ZhsqFeatureClientCall;
import com.gw.zhsq.web.service.ZhsqFeatureService;

@Service("zhsqFeatureService")
public class ZhsqFeatureServiceImpl implements ZhsqFeatureService {

	//获取社区特色列表
	@Override
	public Map<String, Object> getZhsqFeatureListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		String title = requestMap.get("title");//标题
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("isTop", "1");//是否推荐 0不推荐 1推荐
		paramMap.put("title", title);//标题
		paramMap.put("clientContent", "");//内容
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> featureListMap = null;
		if(StringUtils.isNotBlank(shequId)){
			featureListMap = ZhsqFeatureClientCall.getFeatureListMap(paramMap);
		}
		return featureListMap;
	}

	@Override
	public Map<String, Object> getZhsqFeatureInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> featureInfoMap = ZhsqFeatureClientCall.getFeatureMap(requestMap);
		if(featureInfoMap!=null){
			String clientContent = StringUtil.nvl(featureInfoMap.get("clientContent"));
			clientContent = StringUtil.HtmlText(clientContent);
			featureInfoMap.put("clientContent", clientContent);
		}
		return featureInfoMap;
	}

}
