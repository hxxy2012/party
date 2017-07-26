package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.XishanClientCall;
import com.gw.zhsq.web.service.XishanService;

/**
 *【锡山经济技术开发区建设城管】微信端接口
 * @author fuyun
 * 2016-04-12
 */
@Service("xishanService")
public class XishanServiceImpl implements XishanService {

	//获取资讯列表
	@Override
	public Map<String, Object> getNewsListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String classId = requestMap.get("classId");//政务父ID
		if(classId==null || "".equals(classId)){
			classId = "";//政务分类ID
		}
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String title = requestMap.get("title");//政务标题
		paramMap.put("classId", classId);//政务分类ID
		paramMap.put("title", title);//标题
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> newsListMap = XishanClientCall.getNewsListMap(paramMap);
		return newsListMap;
	}

	//获取资讯详情
	@Override
	public Map<String, Object> getNewsMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> newsInfoMap = XishanClientCall.getNewsMap(requestMap);
		if(newsInfoMap!=null){
			String clientContent = StringUtil.nvl(newsInfoMap.get("clientContent"));
			clientContent = StringUtil.HtmlText(clientContent);
			newsInfoMap.put("clientContent", clientContent);
		}
		return newsInfoMap;
	}

	//获取项目列表
	@Override
	public Map<String, Object> getProjectListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String projectType = requestMap.get("projectType");//工程类型（1施工许可证2道口开设）
		if(projectType==null || "".equals(projectType)){
			projectType = "1";
		}
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String projectName = requestMap.get("projectName");//工程名称
		paramMap.put("projectType", projectType);
		paramMap.put("projectName", projectName);
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> projectListMap = XishanClientCall.getProjectListMap(paramMap);
		return projectListMap;
	}

	//获取项目详情
	@Override
	public Map<String, Object> getProjectMap(HashMap<String, String> requestMap)throws OtherException {
		Map<String, Object> newsInfoMap = XishanClientCall.getProjectMap(requestMap);
		if(newsInfoMap!=null){
			String licenceRemark = StringUtil.nvl(newsInfoMap.get("licenceRemark"));
			licenceRemark = StringUtil.HtmlText(licenceRemark);
			newsInfoMap.put("licenceRemark", licenceRemark);
		}
		return newsInfoMap;
	}

	//获取下载文件列表
	@Override
	public Map<String, Object> getFileListMap(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> fileListMap = XishanClientCall.getFileListMap(paramMap);
		return fileListMap;
	}
}
