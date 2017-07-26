package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.SJXXClientCall;
import com.gw.zhsq.web.service.SJXXService;

/**
 * 书记信箱
 * @author hanxu
 *	2015-10-26
 */
@Service("sjxxService")
public class SJXXServiceImpl implements SJXXService {
	
	//提交书记信箱
	@Override
	public Map<String, Object> subSJXXMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> sjxxMap = null;
		if(StringUtils.isNotBlank(requestMap.get("shequId"))){
			sjxxMap = SJXXClientCall.subSJXXMap(requestMap);
		}
		return sjxxMap;
	}

	//获取书记信箱列表
	@Override
	public Map<String, Object> getSJXXListMap(HashMap<String, String> requestMap)throws OtherException {
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
		Map<String, Object> sjxxListMap = SJXXClientCall.getSJXXListMap(paramMap);
		return sjxxListMap;
	}
	
	//书记信箱详情
	@Override
	public Map<String, Object> getSJXXInfoMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String sjxxId = requestMap.get("sjxxId");//书记信箱ID
		paramMap.put("sjxxId", sjxxId);
		Map<String, Object> sjxxInfoMap = SJXXClientCall.getSJXXMap(paramMap);
		if(sjxxInfoMap!=null){
			String content = StringUtil.nvl(sjxxInfoMap.get("content"));
			content = StringUtil.HtmlText(content);
			sjxxInfoMap.put("content", content);
		}
		return sjxxInfoMap;
	}
	
	//提交书记信箱评价
	@Override
	public Map<String, Object> subSatisfactMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> sjxxMap = null;
		if(StringUtils.isNotBlank(requestMap.get("sjxxId"))){
			sjxxMap = SJXXClientCall.subSatisfactMap(requestMap);
		}
		return sjxxMap;
	}

}
