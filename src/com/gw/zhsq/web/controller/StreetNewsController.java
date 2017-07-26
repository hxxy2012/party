package com.gw.zhsq.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gw.base.constant.SystemConstant;

import com.gw.base.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gw.zhsq.web.service.StreetNewsService;


/**
 * 街道资讯
 * 
 * @author fuyun 2016-07-19
 */
@Controller
@RequestMapping("/streetNews")
public class StreetNewsController extends BaseController {

	@Resource
	private StreetNewsService streetNewsService;

	/**
	 * 初始化街道
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String streetId = request.getParameter("streetId");// 街道ID
		if (StringUtils.isBlank(streetId)) {
			streetId = SystemConstant.STREET_ID;
		}
		HttpSession sessions = request.getSession();
		sessions.setAttribute("streetId", streetId);
		return "maqun/street_news_list";
	}

	/**
	 * 获取街道资讯列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getStreetNewsList")
	public String getStreetNewsInfoList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession sessions = request.getSession();
		String className = requestMap.get("className");// 分类名称
		String classId = requestMap.get("classId");// 政务父ID
		String title = requestMap.get("title");// 政务标题
		
		if (title != null && title != "") {
			title.trim();
		}
		request.setAttribute("className", className);
		request.setAttribute("classId", classId);
		request.setAttribute("title", title);

		Map<String, Object> newsMap = null;
		try {
			String streetId = request.getParameter("streetId");// 街道ID
			if (StringUtils.isBlank(streetId)) {
				streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
				if (StringUtils.isBlank(streetId)) {
					streetId = SystemConstant.STREET_MAQUN_ID;// 默认为马群街道
				}
			}
			requestMap.put("streetId", streetId);
			request.setAttribute("streetId", streetId);
			
			System.out.println("streetId======>"+streetId);
			System.out.println("classId======>"+classId);
			
			newsMap = streetNewsService.getStreetNewsInfoList(requestMap);
			List<Map<String, Object>> newsList = (List<Map<String, Object>>) newsMap.get("result");
			if (newsList != null && newsList.size() > 0) {
				for (Map<String, Object> map : newsList) {
					String clientContent = (String) map.get("clientContent");
					clientContent = StringUtil.HtmlText(clientContent);
					if (clientContent.length() > 30) {
						clientContent = clientContent.substring(0, 30) + "...";
					}
					map.put("clientContent", clientContent);

					if (map.get("paramMap") != null) {
						Map<String, Object> map2 = (Map<String, Object>) map.get("paramMap");
						if (map2.get("imagePaths") != null) {
							String imagePaths = StringUtil.nvl(map2.get("imagePaths"));
							if (imagePaths.indexOf("/base/") > -1) {
								imagePaths = imagePaths.replaceAll("/base/", "/mobile/");
								map2.put("imagePaths", imagePaths);
							}
						}
					}
				}
			}
			request.setAttribute("newsList", newsList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/street/streetNews/street_news_list";
	}

	/**
	 * 获取街道资讯详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getStreetNewsInfo")
	public String getStreetNewsInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String className = requestMap.get("className");
		try {
			HashMap<String, String> paramMaps = new HashMap<String, String>();
			paramMaps.put("newsId", requestMap.get("newsId"));
			Map<String, Object> newsInfoMap = streetNewsService.getStreetNewsInfoMap(paramMaps);
			request.setAttribute("newsInfoMap", newsInfoMap);
			request.setAttribute("className", className);
			Map<String, Object> paramMap = (Map<String, Object>) newsInfoMap.get("paramMap");// 图片集合
			// 获取资讯幻灯片图片
			List<Map<String, Object>> indexADList = (List<Map<String, Object>>) paramMap.get("imagePaths");
			List<Map<String, Object>> indexADListNew = new ArrayList<Map<String, Object>>();
			Map<String, Object> indexADMapNew = null;
			if (indexADList != null && indexADList.size() > 0) {
				for (int i = 0; i < indexADList.size(); i++) {
					indexADMapNew = new HashMap<String, Object>();
					Map<String, Object> indexMap = indexADList.get(i);
					String imagePaths = StringUtil.nvl(indexMap.get("largetPath"));
					indexADMapNew.put("url", "");
					indexADMapNew.put("title", "");
					if (StringUtils.isNotBlank(imagePaths)) {
						if (imagePaths.indexOf("/base/") > -1) {
							imagePaths = imagePaths.replaceAll("/base/", "/small/");
							indexADMapNew.put("img", imagePaths);
						}
					}
					indexADListNew.add(indexADMapNew);
				}
			}

			if (indexADList != null && indexADList.size() == 1) {
				Map<String, Object> temp = indexADList.get(0);
				String imagePaths = StringUtil.nvl(temp.get("largetPath"));
				if (StringUtils.isNotBlank(imagePaths)) {
					if (imagePaths.indexOf("/base/") > -1) {
						imagePaths = imagePaths.replaceAll("/base/", "/small/");
						request.setAttribute("imageUrl", imagePaths);
					}
				}
			}
			JSONArray indexADJsonArray = JSONArray.fromObject(indexADListNew);
			request.setAttribute("indexADJsonArray", indexADJsonArray);
			request.setAttribute("indexADListNew", indexADListNew);
			request.setAttribute("indexADSize", indexADListNew.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/street/streetNews/street_news_info";
	}

	// 异步取得政务列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getStreetNewsListAsync")
	@ResponseBody
	public void getStreetNewsListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> newsMap = streetNewsService.getStreetNewsInfoList(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if (newsList != null && newsList.size() > 0) {
				for (Map<String, Object> map : newsList) {
					String clientContent = (String) map.get("clientContent");
					if (clientContent.length() > 30) {
						clientContent = clientContent.substring(0, 30) + "...";
					}
					map.put("clientContent", clientContent);

					if (map.get("paramMap") != null) {
						Map<String, Object> map2 = (Map<String, Object>) map.get("paramMap");
						if (map2.get("imagePaths") != null) {
							String imagePaths = StringUtil.nvl(map2.get("imagePaths"));
							if (imagePaths.indexOf("/base/") > -1) {
								imagePaths = imagePaths.replaceAll("/base/", "/mobile/");
								map2.put("imagePaths", imagePaths);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", newsList);
			map.put("size", (newsList == null) ? 0 : newsList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 获取街道政务分类
	@RequestMapping(value = "/getStreetInfoClass")
	public String getStreetInfoClass(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			String streetId = requestMap.get("streetId");// 社区ID
			HttpSession shequSessions = request.getSession();
			if (StringUtils.isBlank(streetId)) {
				streetId = StringUtil.nvl(shequSessions.getAttribute("streetId"));
				if (StringUtils.isBlank(streetId)) {
					streetId = SystemConstant.STREET_MAQUN_ID;// 默认为马群街道
				}
			}
			requestMap.put("streetId", streetId);
			request.setAttribute("streetId", streetId);
			shequSessions.setAttribute("streetId", streetId);

			// 获取政务各大分类
			List<Map<String, Object>> streetInfoClassList = null;
			try {
				streetInfoClassList = streetNewsService.getStreetInfoClassList(requestMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("streetInfoClassList", streetInfoClassList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "street/streetNews/streetInfoClass_index";
	}
	
}
