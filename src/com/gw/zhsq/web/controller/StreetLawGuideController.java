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
import com.gw.zhsq.web.service.StreetLawGuideService;

/**
 * 街道办事指南
 * @author fuyun
 *	2016-04-20
 *	http://localhost:8080/Weixin/street/lawGuide/init.htm
 */
@Controller
@RequestMapping("/street/lawGuide")
public class StreetLawGuideController extends BaseController {
	
	@Resource
	private StreetLawGuideService streetLawGuideService;
	
	/**
	 * 初始化办事指南
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_ID;//默认为东台街道
			}
			if("22".equals(streetId)){
				if(StringUtils.isBlank(shequId)){
					shequId = SystemConstant.DONGTAI_SHEQU_ID;
				}
				request.setAttribute("shequId", shequId);
				sessions.setAttribute("shequId", shequId);
			}
		}
		requestMap.put("streetId", streetId);
		request.setAttribute("streetId", streetId);
		sessions.setAttribute("streetId", streetId);
		return "street/streetLawGuide/street_lawGuide_index";
	}
	
	/**
	 * 获取办事指南列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getStreetLawGuideListMap")
	public String getStreetLawGuideListMap(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String type =  requestMap.get("type");
		HttpSession sessions = request.getSession();
		String streetId = (String) sessions.getAttribute("streetId");
		Map<String, Object> streetLawGuideListMap = null;
		try {
			HashMap<String, String> hashMap = new HashMap<String,String>();
			hashMap.put("title", "");
			hashMap.put("type", type);
			hashMap.put("streetId", streetId);
			streetLawGuideListMap = streetLawGuideService.getStreetLawGuideListMap(hashMap);
			List<Map<String,Object>> streetLawGuideList = (List<Map<String, Object>>) streetLawGuideListMap.get("result");
			request.setAttribute("streetLawGuideList", streetLawGuideList);
			request.setAttribute("streetLawGuideListMap", streetLawGuideListMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "street/streetLawGuide/street_lawGuide_list";
	}
	
	/**
	 * 获取办事指南详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getStreetLawGuideMap")
	public String getStreetLawGuideMap(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String lawGuideId =  requestMap.get("lawGuideId");
		Map<String, Object> streetLawGuideMap = null;
		try {
			HashMap<String, String> hashMap = new HashMap<String,String>();
			hashMap.put("lawGuideId", lawGuideId);
			streetLawGuideMap = streetLawGuideService.getStreetLawGuideMap(hashMap);
			request.setAttribute("streetLawGuideMap", streetLawGuideMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "street/streetLawGuide/street_lawGuide_detail";
	}
}
