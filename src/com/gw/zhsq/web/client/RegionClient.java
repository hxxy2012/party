package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 地区服务
 * @author fuyun
 *	2016-03-11
 */
public class RegionClient extends BaseClient{

	/**
	 * 获取省下所有城市
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getOneProCitys(HashMap<String, String> requestMap) {
		String rUrl = "/client/regions/getCityList.htm";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取城市失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取地区信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getRegionInfo(HashMap<String, String> requestMap) {
		String rUrl = "/client/regions/getRegionInfo.htm";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取地区失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取省份信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getProInfo(HashMap<String, String> requestMap) {
		String rUrl = "/client/regions/getProInfo.htm";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取省份失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取城市信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getCityInfo(HashMap<String, String> requestMap) {
		String rUrl = "/client/regions/getCityInfo.htm";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取城市失败！");
		}
		return apiResponse;
	}
}
