package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.RZTJClientCall;
import com.gw.zhsq.web.service.RZTJService;

/**
 * 认证推荐
 * @author hanxu
 *	2015-11-02
 */
@Service("rztjService")
public class RZTJServiceImpl implements RZTJService {
	
	//提交认证推荐
	@Override
	public Map<String, Object> subRZTJMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> RZTJMap = null;
		if(StringUtils.isNotBlank(requestMap.get("shequId"))){
			RZTJMap = RZTJClientCall.subRZTJMap(requestMap);
		}
		return RZTJMap;
	}
	
	//获取认证推荐列表
	@Override
	public Map<String, Object> getRZTJListMap(HashMap<String, String> requestMap)throws OtherException {
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
		String serviceType = requestMap.get("serviceType");//服务类型(0家政服务1餐饮服务2其他服务)
		paramMap.put("serviceType", serviceType);
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> rztjListMap = RZTJClientCall.getRZTJListMap(paramMap);
		return rztjListMap;
	}
	
	//获取认证推荐详情
	@Override
	public Map<String, Object> getRZTJInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> rztjInfoMap = RZTJClientCall.getRZTJMap(requestMap);
		if(rztjInfoMap!=null){
			String content = StringUtil.nvl(rztjInfoMap.get("content"));
			content = StringUtil.HtmlText(content);
			rztjInfoMap.put("content", content);
		}
		return rztjInfoMap;
	}

}
