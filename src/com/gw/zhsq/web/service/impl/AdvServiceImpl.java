package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gw.zhsq.web.call.AdvClientCall;
import com.gw.zhsq.web.service.AdvService;

/**
 * 幻灯片服务
 * @author hanxu
 *	2016-03-11
 */
@Service("advService")
public class AdvServiceImpl implements AdvService {
	
	/**
	 * 获取幻灯片
	 * @param fuyun
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getAdvList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> advList = null;
		try {
			advList = AdvClientCall.getAdvListMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}
	
	/**
	 * 获取社区服务顶部幻灯片
	 * @param 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getWfwAdvList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> advList = null;
		try {
			advList = AdvClientCall.getWfwAdvListMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}
	
	/**
	 * 获取社区服务横幅广告
	 * @param 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getWfwBannerAdvList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> advList = null;
		try {
			advList = AdvClientCall.getWfwBannerAdvListMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}
	
	/**
	 * 获取社区广告列表
	 * @param 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getZhsqAdvList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> advList = null;
		try {
			advList = AdvClientCall.getZhsqAdvListMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}
	
	/**
	 * 获取街道广告列表
	 * @param 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getStreetAdvList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> advList = null;
		try {
			advList = AdvClientCall.getStreetAdvListMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}
	
	
}
