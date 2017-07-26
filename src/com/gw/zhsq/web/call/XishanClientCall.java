package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.XishanClient;

/**
 *【锡山经济技术开发区建设城管】微信端
 * @author fuyun
 * 2016-04-12
 */
public class XishanClientCall {

	/**
	 * 获取【锡山经济技术开发区建设城管】资讯列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getNewsListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> newsListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanClient.getNewsListMap(requestMap);
		if (apiResponse.isSuccess()) {
			newsListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsListMap;
	}
	
	/**
	 * 获取【锡山经济技术开发区建设城管】资讯详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getNewsMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> newsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanClient.getNewsMap(requestMap);
		if (apiResponse.isSuccess()) {
			newsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return newsMap;
	}
	
	/**
	 * 获取【锡山经济技术开发区建设城管】项目列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getProjectListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> projectListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanClient.getProjectListMap(requestMap);
		if (apiResponse.isSuccess()) {
			projectListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return projectListMap;
	}
	
	/**
	 * 获取【锡山经济技术开发区建设城管】项目详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getProjectMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> projectMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanClient.getProjectMap(requestMap);
		if (apiResponse.isSuccess()) {
			projectMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return projectMap;
	}
	
	/**
	 * 获取【锡山经济技术开发区建设城管】下载文件列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getFileListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> fileListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = XishanClient.getFileListMap(requestMap);
		if (apiResponse.isSuccess()) {
			fileListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return fileListMap;
	}
}
