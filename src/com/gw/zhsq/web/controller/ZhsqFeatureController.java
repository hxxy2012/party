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
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.ZhsqFeatureService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 社区特色
 * 
 * @author lihui 
 * 2016-04-22
 * 
 * http://localhost:8080/Weixin/zhsq/feature/getZhsqFeatureList.htm?shequId=202
 * 
 */
@Controller
@RequestMapping("/zhsq/feature")
public class ZhsqFeatureController extends BaseController {

	@Resource
	private AdvService advService;
	@Resource
	private ZhsqFeatureService zhsqFeatureService;
	
	// 获取社区特色信息列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqFeatureList")
	public String getFeatureList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> featureMap = null;
		try {
			String shequId = requestMap.get("shequId");// 社区ID
			String title = requestMap.get("title");
			if(title != null && title != ""){
				title.trim();
			}
			HttpSession shequSessions = request.getSession();
			if (StringUtils.isBlank(shequId)) {
				shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
				if (StringUtils.isBlank(shequId)) {
					shequId = SystemConstant.SHEQU_ID;// 默认为金陵驿社区
				}
			}
			requestMap.put("shequId", shequId);
			requestMap.put("title", title);
			request.setAttribute("shequId", shequId);
			request.setAttribute("title", title);
			shequSessions.setAttribute("shequId", shequId);
			featureMap = zhsqFeatureService.getZhsqFeatureListMap(requestMap);
			List<Map<String, Object>> featureList = (List<Map<String, Object>>) featureMap.get("result");
			if (featureList != null && featureList.size() > 0) {
				for (Map<String, Object> map : featureList) {
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
							map2.put("imagePaths", imagePaths);
						}
					}
				}
			}
			request.setAttribute("featureList", featureList);
			
			//顶部广告
			String topImg = "";
			HashMap<String, String> paramMaps20 = new HashMap<String, String>();
			paramMaps20.put("bgLocation", "20");
			paramMaps20.put("shequId", shequId);//社区ID
			List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps20);
			if(advTop!=null&&advTop.size()>0){
				Map<String, Object> map = advTop.get(0);
				Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
				topImg = (String) paramMapss.get("imagePaths");
			}
			request.setAttribute("topImg", topImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wmh/zhsqFeature/zhsqFeature_list";
	}

	// 获取社区特色详情
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqFeatureInfo")
	public String getFeatureInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			HashMap<String, String> paramMaps = new HashMap<String, String>();
			paramMaps.put("featureId", requestMap.get("featureId"));
			Map<String, Object> featureInfoMap = zhsqFeatureService.getZhsqFeatureInfoMap(paramMaps);
			request.setAttribute("featureInfoMap", featureInfoMap);
			Map<String, Object> paramMap = (Map<String, Object>) featureInfoMap.get("paramMap");// 图片集合
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
						//if (imagePaths.indexOf("/base/") > -1) {
						//	imagePaths = imagePaths.replaceAll("/base/", "/small/");
							indexADMapNew.put("img", imagePaths);
						//}
					}
					indexADListNew.add(indexADMapNew);
				}
			}

			if (indexADList != null && indexADList.size() == 1) {
				Map<String, Object> temp = indexADList.get(0);
				String imagePaths = StringUtil.nvl(temp.get("largetPath"));
				if (StringUtils.isNotBlank(imagePaths)) {
					//if (imagePaths.indexOf("/base/") > -1) {
						//imagePaths = imagePaths.replaceAll("/base/", "/small/");
						request.setAttribute("imageUrl", imagePaths);
					//}
				}
			}

			JSONArray indexADJsonArray = JSONArray.fromObject(indexADListNew);
			request.setAttribute("indexADJsonArray", indexADJsonArray);
			request.setAttribute("indexADListNew", indexADListNew);
			request.setAttribute("indexADSize", indexADListNew.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wmh/zhsqFeature/zhsqFeature_info";
	}

	// 异步取得社区特色列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getZhsqFeatureListAsync")
	@ResponseBody
	public void getFeatureListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> featureList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> featureMap = zhsqFeatureService.getZhsqFeatureListMap(requestMap);
			featureList = (List<Map<String, Object>>) featureMap.get("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", featureList);
			map.put("size", (featureList == null) ? 0 : featureList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
