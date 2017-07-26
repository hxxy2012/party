package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.ZhsqYuyueClientCall;
import com.gw.zhsq.web.service.ZhsqYuyueService;

/**
 * 街道预约
 * @author lihui
 *	2016-04-21
 */
@Service("zhsqYuyueService")
public class ZhsqYuyueServiceImpl implements ZhsqYuyueService {

	//提交街道预约
	@Override
	public Map<String, Object> subShequYuyue(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = ZhsqYuyueClientCall.subShequYuyue(requestMap);
		return yuyueMap;
		
	}

	//获取街道预约信息列表【会员中心】
	@Override
	public Map<String, Object> getShequYuyueListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String memberId = requestMap.get("memberId");//会员ID
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> yuyueListMap = ZhsqYuyueClientCall.getShequYuyueListMap(paramMap);
		return yuyueListMap;
	}

	//获取街道预约详情
	@Override
	public Map<String, Object> getShequYuyueInfoMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String yuyueId = requestMap.get("yuyueId");//书记信箱ID
		paramMap.put("yuyueId", yuyueId);
		Map<String, Object> yuyueInfoMap = ZhsqYuyueClientCall.getShequYuyueMap(paramMap);
		return yuyueInfoMap;
	}

}
