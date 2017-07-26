package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gw.zhsq.web.call.ZhsqLiveClientCall;
import com.gw.zhsq.web.service.ZhsqLiveService;

/**
 * 社区生活/生活助手
 * @author fuyun
 *	2016-03-11
 */
@Service("zhsqLiveService")
public class ZhsqLiveServiceImpl implements ZhsqLiveService {
	
	/**
	 * 获取社区生活/生活助手列表
	 * @param fuyun
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getZhsqLiveList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> advList = null;
		try {
			advList = ZhsqLiveClientCall.getZhsqLiveList(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advList;
	}

	@Override
	public List<Map<String, Object>> getZhsqList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> zhsqList = null;
		try {
			zhsqList = ZhsqLiveClientCall.getZhsqList(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zhsqList;
	}
	
	
	
}
