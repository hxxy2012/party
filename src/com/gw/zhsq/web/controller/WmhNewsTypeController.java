package com.gw.zhsq.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gw.base.constant.SystemConstant;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.NewsTypeService;

/**
 * 智慧社区微信政务分类
 * @author hanxu
 *	2015-04-02
 *	http://localhost:8080/Weixin/wmh/newsType/init.htm
 */
@Controller
@RequestMapping("/wmh/newsType")
public class WmhNewsTypeController extends BaseController {
	
	@Resource
	private NewsTypeService newsTypeService;
	
	//初始化政务分类列表
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			String shequId = requestMap.get("shequId");//社区ID
			HttpSession shequSessions = request.getSession();
			if(StringUtils.isBlank(shequId)){
				shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
				if(StringUtils.isBlank(shequId)){
					shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
				}
			}
			requestMap.put("shequId", shequId);
			request.setAttribute("shequId", shequId);
			shequSessions.setAttribute("shequId", shequId);
			
			List<Map<String, Object>> newsClassList = newsTypeService.getNewsClassList(requestMap);
			request.setAttribute("newsClassList", newsClassList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wmh/sqzw/wmh_news_type";
	}
	
}
