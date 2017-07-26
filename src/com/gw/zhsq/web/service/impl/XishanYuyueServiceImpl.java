package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.XishanYuyueClientCall;
import com.gw.zhsq.web.service.XishanYuyueService;

/**
 * 在线预约
 * @author lihui
 *	2016-05-18
 */
@Service("xishanYuyueService")
public class XishanYuyueServiceImpl implements XishanYuyueService {

	//提交预约信息
	@Override
	public Map<String, Object> subXishanYuyue(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> yuyueMap = XishanYuyueClientCall.subXishanYuyue(requestMap);
		return yuyueMap;
	}

	//获取预约信息列表
	@Override
	public Map<String, Object> getXishanYuyueListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
//		String memberId = requestMap.get("memberId");//会员ID
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
//		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> yuyueListMap = XishanYuyueClientCall.getXishanYuyueListMap(paramMap);
		return yuyueListMap;
	}

	//获取预约信息详情
	@Override
	public Map<String, Object> getXishanYuyueInfoMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String yuyueId = requestMap.get("yuyueId");
		paramMap.put("yuyueId", yuyueId);
		Map<String, Object> yuyueInfoMap = XishanYuyueClientCall.getXishanYuyueMap(paramMap);
		return yuyueInfoMap;
	}

}
