package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.JlhdClientCall;
import com.gw.zhsq.web.service.JlhdService;

/**
 * 交流互动逻辑层
 * @author fuyun
 *	2015-11-04
 */
@Service("jlhdService")
public class JlhdServiceImpl implements JlhdService {
	
	//获取交流互动列表
	@Override
	public Map<String, Object> getJlhdListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jlhdListMap = JlhdClientCall.getJlhdListMap(requestMap);
		return jlhdListMap;
	}

	//获取交流互动详情
	@Override
	public Map<String, Object> getJlhdMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> jlhdMap = JlhdClientCall.getJlhdMap(requestMap);
		return jlhdMap;
	}

	//新增交流互动
	@Override
	public Map<String, Object> addJlhd(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> jlhdMap = JlhdClientCall.addJlhd(requestMap);
		return jlhdMap;
	}

	//新增交流互动回复
	@Override
	public Map<String, Object> addHuifu(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> jlhdMap = JlhdClientCall.addHuifu(requestMap);
		return jlhdMap;
	}

}
