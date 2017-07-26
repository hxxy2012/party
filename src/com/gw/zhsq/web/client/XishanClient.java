package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 *【锡山经济技术开发区建设城管】微信端
 * @author fuyun
 * 2016-04-12
 */
public class XishanClient extends BaseClient{

	/**
	 * 获取资讯列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNewsListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanNewsListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			System.out.println("============获取返回数据 returnMsg===========");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取资讯列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取资讯详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getNewsMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanNewsMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取资讯详情失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取项目列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getProjectListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanProjectListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取项目列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取项目详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getProjectMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanProjectMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取项目详情失败！");
		}
		return apiResponse;
	}
		
	/**
	 * 获取下载文件列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getFileListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getXishanFileListMap";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取项目列表失败！");
		}
		return apiResponse;
	}
}
