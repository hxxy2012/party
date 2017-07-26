package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.NewsClientCall;
import com.gw.zhsq.web.service.NewsService;

/**
 * 资讯
 * @author hanxu
 *	2015-10-13
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {
	
	//获取资讯列表
	@Override
	public Map<String, Object> getNewsListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String classId = requestMap.get("classId");//政务父ID
		if(classId==null || "".equals(classId)){
			classId = "";//政务分类ID
			paramMap.put("isTop", "1");//是否推荐 0不推荐 1推荐
		}else{
			paramMap.put("isTop", "");//是否推荐 0不推荐 1推荐
		}
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		String title = requestMap.get("title");//政务标题
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("classId", classId);//政务分类ID
		paramMap.put("title", title);//标题
	
		paramMap.put("clientContent", "");//内容
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> newsListMap = null;
		if(StringUtils.isNotBlank(shequId)){
			 newsListMap = NewsClientCall.getNewsListMap(paramMap);
		}
		return newsListMap;
	}
	
	//获取政务列表
	@Override
	public List<Map<String, Object>> getNewsList(HashMap<String, String> requestMap) throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		String pageno = "1",pageSize = "10";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String classId = requestMap.get("classId");//父ID
		if(classId==null || "".equals(classId)){
			classId = "1";//分类ID
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("classId", classId);//分类ID
		paramMap.put("page", pageno);  
		paramMap.put("pageSize", pageSize);
		List<Map<String, Object>> newsList = null;
		if(StringUtils.isNotBlank(shequId)){
			newsList = NewsClientCall.getNewsList(paramMap);
		}
		return newsList;
	}
	
	//获取政务详情
	@Override
	public Map<String, Object> getNewsInfoMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> newsInfoMap = NewsClientCall.getNewsMap(requestMap);
		if(newsInfoMap!=null){
			String clientContent = StringUtil.nvl(newsInfoMap.get("clientContent"));
			clientContent = StringUtil.HtmlText(clientContent);
			newsInfoMap.put("clientContent", clientContent);
		}
		return newsInfoMap;
	}

	//获取最新资讯列表
	@Override
	public List<Map<String, Object>> getNewestNewsList(HashMap<String, String> requestMap) throws OtherException {
		List<Map<String, Object>> newsList = NewsClientCall.getNewestNewsList(requestMap);
		return newsList;
	}

}
