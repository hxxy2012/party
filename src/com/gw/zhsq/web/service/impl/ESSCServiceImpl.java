package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.ESSCClientCall;
import com.gw.zhsq.web.service.ESSCService;

/**
 * 二手市场
 * @author hanxu
 *	2015-11-02
 */
@Service("esscService")
public class ESSCServiceImpl implements ESSCService {
	
	//提交二手市场
	@Override
	public Map<String, Object> subESSCMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> ESSCMap = null;
		if(StringUtils.isNotBlank(requestMap.get("shequId"))){
			ESSCMap = ESSCClientCall.subESSCMap(requestMap);
		}
		return ESSCMap;
	}
	
	//获取二手市场列表
	@Override
	public Map<String, Object> getESSCListMap(HashMap<String, String> requestMap)throws OtherException {
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
		Map<String, Object> esscListMap = ESSCClientCall.getESSCListMap(paramMap);
		return esscListMap;
	}
	
	//获取二手市场详情
	@Override
	public Map<String, Object> getESSCInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> esscInfoMap = ESSCClientCall.getESSCMap(requestMap);
		if(esscInfoMap!=null){
			String content = StringUtil.nvl(esscInfoMap.get("content"));
			content = StringUtil.HtmlText(content);
			esscInfoMap.put("content", content);
		}
		return esscInfoMap;
	}

}
