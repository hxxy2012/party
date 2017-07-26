package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;

import com.gw.base.exception.OtherException;

/**
 *【锡山经济技术开发区建设城管】微信端接口
 * @author fuyun
 * 2016-04-12
 */
public interface XishanService {

	/**
	 * 获取资讯列表
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getNewsListMap(HashMap<String,String> requestMap)throws OtherException;
	
	/**
	 * 获取资讯详情
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getNewsMap(HashMap<String,String> requestMap)throws OtherException;
	
	/**
	 * 获取项目列表
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getProjectListMap(HashMap<String,String> requestMap)throws OtherException;
	
	/**
	 * 获取项目详情
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getProjectMap(HashMap<String,String> requestMap)throws OtherException;
	
	/**
	 * 获取下载文件列表
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getFileListMap(HashMap<String,String> requestMap)throws OtherException;
	
}
