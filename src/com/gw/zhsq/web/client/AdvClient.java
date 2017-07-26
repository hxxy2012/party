package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 获取广告图片
 * @author fuyun
 *	2015-07-22
 */
public class AdvClient extends BaseClient{

	/**
	 * 获取幻灯片列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static ApiResponse getAdvListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getAds";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			System.out.println("returnMsg==>"+returnMsg);
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取幻灯片列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取社区服务顶部幻灯片列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static ApiResponse getWfwAdvListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getWfwAdsList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			System.out.println("returnMsg==>"+returnMsg);
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区服务顶部幻灯片列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取社区服务横幅广告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static ApiResponse getWfwBannerAdvListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getBannerAdsList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			System.out.println("returnMsg==>"+returnMsg);
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区服务横幅广告列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取社区广告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static ApiResponse getZhsqAdvListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getZhsqAdvList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			System.out.println("returnMsg==>"+returnMsg);
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取社区广告列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取街道广告列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static ApiResponse getStreetAdvListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getStreetAdvList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			System.out.println("returnMsg==>"+returnMsg);
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取街道广告列表失败！");
		}
		return apiResponse;
	}
	
	
}
