package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gw.zhsq.web.call.RegionClientCall;
import com.gw.zhsq.web.service.RegionService;

/**
 * 地区服务
 * @author fuyun
 *	2016-03-11
 */
@Service("regionService")
public class RegionServiceImpl implements RegionService {

	//获取省下所有城市
	@Override
	public List<Map<String, Object>> getOneProCitys(HashMap<String, String> requestMap) {
		List<Map<String, Object>> cityList = null;
		try {
			cityList = RegionClientCall.getOneProCitys(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}

	//获取地区信息
	@Override
	public Map<String, Object> getRegionInfo(HashMap<String, String> requestMap) {
		Map<String, Object> cityInfo = null;
		try {
			cityInfo = RegionClientCall.getRegionInfo(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityInfo;
	}

	//获取省份信息
	@Override
	public Map<String, Object> getProInfo(HashMap<String, String> requestMap) {
		Map<String, Object> cityInfo = null;
		try {
			cityInfo = RegionClientCall.getProInfo(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityInfo;
	}

	//获取城市信息
	@Override
	public Map<String, Object> getCityInfo(HashMap<String, String> requestMap) {
		Map<String, Object> cityInfo = null;
		try {
			cityInfo = RegionClientCall.getCityInfo(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityInfo;
	}

}
