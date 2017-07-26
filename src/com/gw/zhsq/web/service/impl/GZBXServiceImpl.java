package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.GZBXClientCall;
import com.gw.zhsq.web.service.GZBXService;

/**
 * 故障报修
 * @author hanxu
 *	2015-11-02
 */
@Service("gzbxService")
public class GZBXServiceImpl implements GZBXService {
	
	//提交故障报修
	@Override
	public Map<String, Object> subGZBXMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> GZBXMap = null;
		if(StringUtils.isNotBlank(requestMap.get("shequId"))){
			GZBXMap = GZBXClientCall.subGZBXMap(requestMap);
		}
		return GZBXMap;
	}
	
	//获取故障报修列表
	@Override
	public Map<String, Object> getGZBXListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");//会员ID
		String pageno = "1",pageSize = "10";
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
		Map<String, Object> gzbxListMap = GZBXClientCall.getGZBXListMap(paramMap);
		return gzbxListMap;
	}
	
	//获取故障报修详情
	@Override
	public Map<String, Object> getGZBXInfoMap(HashMap<String, String> requestMap) throws OtherException {
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String gzbxId = requestMap.get("gzbxId");
		if(StringUtils.isNotBlank(gzbxId)){
			paramMap.put("gzbxId", gzbxId);
		}
		Map<String, Object> gzbxInfoMap = GZBXClientCall.getGZBXMap(paramMap);
		if(gzbxInfoMap!=null){
			String content = StringUtil.nvl(gzbxInfoMap.get("content"));
			content = StringUtil.HtmlText(content);
			gzbxInfoMap.put("content", content);
		}
		return gzbxInfoMap;
	}
	
	//获取社区信息
	@Override
	public Map<String, Object> getShequInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> shequInfoMap = GZBXClientCall.getShequMap(requestMap);
		if(shequInfoMap!=null){
			String detail = StringUtil.nvl(shequInfoMap.get("detail"));
			detail = StringUtil.HtmlText(detail);
			shequInfoMap.put("detail", detail);
		}
		return shequInfoMap;
	}

}
