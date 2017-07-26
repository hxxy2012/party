package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.RegionClient;

/**
 * 地区请求
 * @author fuyun
 *	2015-04-08
 */
public class RegionClientCall extends BaseCall{

	/**
	 * 获取省下所有城市
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static List<Map<String, Object>> getOneProCitys(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> citys = null;
		ApiResponse apiResponse = null;
		apiResponse = RegionClient.getOneProCitys(requestMap);
		if (apiResponse.isSuccess()) {
			citys = apiResponse.getListValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return citys;
	}
	
	/**
	 * 获取地区信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> getRegionInfo(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> region = null;
		ApiResponse apiResponse = null;
		apiResponse = RegionClient.getRegionInfo(requestMap);
		if (apiResponse.isSuccess()) {
			String value = apiResponse.getStringValue();
			region = new HashMap();
			region.put("name", value);
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return region;
	}
	
	/**
	 * 获取省份信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> getProInfo(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> proInfo = null;
		ApiResponse apiResponse = null;
		apiResponse = RegionClient.getProInfo(requestMap);
		if (apiResponse.isSuccess()) {
			String value = apiResponse.getStringValue();
			proInfo = new HashMap();
			proInfo.put("province_id", value);
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return proInfo;
	}
	
	/**
	 * 获取城市信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getCityInfo(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> cityInfo = null;
		ApiResponse apiResponse = null;
		apiResponse = RegionClient.getCityInfo(requestMap);
		if (apiResponse.isSuccess()) {
			String value = apiResponse.getStringValue();
			cityInfo = new HashMap<String, Object>();
			cityInfo.put("city_id", value);
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return cityInfo;
	}
}
