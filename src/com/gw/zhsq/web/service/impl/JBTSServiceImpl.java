package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.JBTSClientCall;
import com.gw.zhsq.web.service.JBTSService;

/**
 * 意见反馈
 * @author hanxu
 *	2016-04-18
 */
@Service("jbtsService")
public class JBTSServiceImpl implements JBTSService {
	
	//提交意见反馈
	@Override
	public Map<String, Object> subJBTSMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> JBTSMap = null;
		if(StringUtils.isNotBlank(requestMap.get("shequId"))){
			JBTSMap = JBTSClientCall.subJBTSMap(requestMap);
		}
		return JBTSMap;
	}
	
	//获取意见反馈列表
	@Override
	public Map<String, Object> getJBTSListMap(HashMap<String, String> requestMap)throws OtherException {
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
		Map<String, Object> jbtsListMap = JBTSClientCall.getJBTSListMap(paramMap);
		return jbtsListMap;
	}
	
	//获取意见反馈详情
	@Override
	public Map<String, Object> getJBTSInfoMap(HashMap<String, String> requestMap) throws OtherException {
		
		Map<String, Object> jbtsInfoMap = JBTSClientCall.getJBTSMap(requestMap);
		if(jbtsInfoMap!=null){
			String content = StringUtil.nvl(jbtsInfoMap.get("content"));
			content = StringUtil.HtmlText(content);
			jbtsInfoMap.put("content", content);
		}
		return jbtsInfoMap;
	}
	
	//提交意见反馈评价
	@Override
	public Map<String, Object> subSatisfactMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> jbtsMap = null;
		if(StringUtils.isNotBlank(requestMap.get("jbtsId"))){
			jbtsMap = JBTSClientCall.subSatisfactMap(requestMap);
		}
		return jbtsMap;
	}
	
}
