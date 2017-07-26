package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 获取资讯
 * @author hanxu
 *	2015-04-02
 */
public class NewsClient extends BaseClient{
	
	/**
	 * 获取某资讯分类下的资讯列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNewsListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getNewsInfoList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取政务列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取政务详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNewsMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getNewsInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取政务详情失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取最新资讯列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNewestNewsList(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getNewestNewsList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取最新资讯列表失败！");
		}
		return apiResponse;
	}
	
}
