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
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.NewsService;
import com.gw.zhsq.web.service.NewsTypeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * [智慧社区]微门户首页
 * 
 * @author fuyun 2015-10-01
 *         请求地址：http://localhost:8080/Weixin/wmh/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wmh")
public class WmhIndexController extends BaseController {
	@Resource
	private AdvService advService;
	@Resource
	private NewsService newsService;
	@Resource
	private NewsTypeService newsTypeService;

	// 微门户首页new
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			String shequId = requestMap.get("shequId");// 社区ID
			HttpSession shequSessions = request.getSession();
			if (StringUtils.isBlank(shequId)) {
				shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
				if (StringUtils.isBlank(shequId)) {
					shequId = SystemConstant.SHEQU_ID;// 默认为金陵驿社区
				}
			}
			requestMap.put("shequId", shequId);
			request.setAttribute("shequId", shequId);
			shequSessions.setAttribute("shequId", shequId);

			//获取顶部幻灯片
			 HashMap<String, String> paramMaps = new HashMap<String, String>();
				paramMaps.put("bgLocation", "11");
				paramMaps.put("shequId", shequId);//社区ID
			    
				List<Map<String, Object>> adv = advService.getZhsqAdvList(paramMaps);
				request.setAttribute("adv", adv);
				
				List<Map<String,Object>> advListNew = new ArrayList<Map<String,Object>>();
				 Map<String,Object> advMapNew = null;
				 if(adv!=null&&adv.size()>0){
			        	for(int i=0;i<adv.size();i++){
			        		advMapNew = new HashMap<String, Object>();
			        		Map<String,Object> indexMap = adv.get(i);
							Map<String,Object> paramMap = (Map<String, Object>) indexMap.get("paramMap");
			        		String imagePaths = "";
			        		if(paramMap!=null){
			        			imagePaths = StringUtil.nvl(paramMap.get("imagePaths"));
			        		}
			        		String url = StringUtil.nvl(indexMap.get("adUrl"));
			        		if(StringUtils.isNotBlank(url)){
			        			advMapNew.put("url", url);
		        			}else{
		        				advMapNew.put("url", "#");
		        			}
			        		advMapNew.put("title", "");
			        			if(StringUtils.isNotBlank(imagePaths)){
			        				advMapNew.put("img", imagePaths);
			        			}
			        			advListNew.add(advMapNew);
			        	}
			        }else{
			          	 Map<String,Object> advMapNew5 = new HashMap<String, Object>();
			        	 advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_fw.jpg");
			        	 advMapNew5.put("url", "");
			        	 advMapNew5.put("title", "");
			        	 advListNew.add(advMapNew5);
			        	 Map<String,Object> advMapNew1 = new HashMap<String, Object>();
			        	 advMapNew1.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jbts.jpg");
			        	 advMapNew1.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jbts/init.htm?shequId="+shequId);
			        	 advMapNew1.put("title", "");
			        	 advListNew.add(advMapNew1);
//			        	 Map<String,Object> advMapNew2 = new HashMap<String, Object>();
//			        	 advMapNew2.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_gzbx.jpg");
//			        	 advMapNew2.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/gzbx/init.htm?shequId="+shequId);
//			        	 advMapNew2.put("title", "");
//			        	 advListNew.add(advMapNew2);
			        	 Map<String,Object> advMapNew3 = new HashMap<String, Object>();
			        	 advMapNew3.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jlhd.jpg");
			        	 advMapNew3.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jlhd/init.htm?shequId="+shequId);
			        	 advMapNew3.put("title", "");
			        	 advListNew.add(advMapNew3);
			        	 Map<String,Object> advMapNew4 = new HashMap<String, Object>();
			        	 advMapNew4.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_sjxx.jpg");
			        	 advMapNew4.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/sjxx/init.htm?shequId="+shequId);
			        	 advMapNew4.put("title", "");
			        	 advListNew.add(advMapNew4);
			     
			        }
				 if(adv!=null&&adv.size()==1){
					 Map<String,Object> temp = adv.get(0);
					Map<String,Object> map = (Map<String, Object>) temp.get("paramMap");
		        		String imagePaths = "";
		        		if(map!=null){
		        			imagePaths = StringUtil.nvl(map.get("imagePaths"));
		        		}
					 request.setAttribute("imageUrl", imagePaths);
				 }
			        JSONArray indexADJsonArray = JSONArray.fromObject(advListNew); 
			    	request.setAttribute("indexADJsonArray", indexADJsonArray);
					request.setAttribute("indexADListNew", advListNew);
					request.setAttribute("indexADSize", advListNew.size());
			
			// 获取政务各大分类
			List<Map<String, Object>> newsClassList = null;
			try {
				newsClassList = newsTypeService.getNewsClassList(requestMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("newsClassList", newsClassList);

			// 获取首页最新资讯
			Map<String, Object> newsMap = newsService.getNewsListMap(requestMap);
			List<Map<String, Object>> newsList = (List<Map<String, Object>>) newsMap.get("result");
			System.out.println("newsList======class======>>" + newsList.toString());
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
			request.setAttribute("newsList", newsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wmh_index";
	}

	// 异步取得政务列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getNewsListAsync")
	@ResponseBody
	public void getNewsListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		try {

			Map<String, Object> newsMap = newsService.getNewsListMap(requestMap);
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
	// //微门户首页
	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/initOld")
	// public String initOld(ModelMap model,HttpServletRequest request,
	// HttpServletResponse response) {
	// HashMap<String, String> requestMap = findRequestMap(request);
	// try {
	// //社区ID
	// String shequId = requestMap.get("shequId");
	// HttpSession shequSessions = request.getSession();
	// if(StringUtils.isBlank(shequId)){
	// shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
	// }
	// requestMap.put("shequId", shequId);
	// shequSessions.setAttribute("shequId", shequId);
	//
	// //活动公告
	// Map<String,Object> noticesInfoMap = null;
	// try{
	// HashMap<String, String> paramMap = new HashMap<String, String>();
	// paramMap.put("shequId", shequId);//社区ID
	// paramMap.put("page", "1");//当前页数
	// paramMap.put("pageSize", "1");//每页条数
	// paramMap.put("notifyRange", "5");//通知范围(1客户端2服务端3后台4网站5微信6其它)
	// Map<String, Object> noticesMap =
	// noticesInfoService.getNoticesMap(paramMap);
	// if(noticesMap!=null){
	// List<Map<String, Object>> noticesInfoList = (List<Map<String, Object>>)
	// noticesMap.get("result");
	// if(noticesInfoList!=null&&noticesInfoList.size()>0){
	// noticesInfoMap = noticesInfoList.get(0);
	// }
	// }
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// request.setAttribute("noticesMap", noticesInfoMap);
	//
	// //获取社区活动
	// List<Map<String,Object>> newsList1 = null;
	// try{
	// HashMap<String, String> paramMaps1 = new HashMap<String, String>();
	// paramMaps1.put("classId", SystemConstant.WMH_SQHD);//社区政务下社区活动资讯分类ID
	// paramMaps1.put("shequId", shequId);//社区ID
	// Map<String, Object> newsMap1 = newsService.getNewsListMap(paramMaps1);
	// if(newsMap1!=null && newsMap1.get("result")!=null){
	// newsList1 = (List<Map<String, Object>>) newsMap1.get("result");
	// }
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// request.setAttribute("newsList1", newsList1);
	//
	// //获取社区风采
	// List<Map<String,Object>> newsList2 = null;
	// try{
	// HashMap<String, String> paramMaps2 = new HashMap<String, String>();
	// paramMaps2.put("classId", SystemConstant.WMH_SQFC);//社区政务下社区风采资讯分类ID
	// paramMaps2.put("shequId", shequId);//社区ID
	// Map<String, Object> newsMap2 = newsService.getNewsListMap(paramMaps2);
	// if(newsMap2!=null && newsMap2.get("result")!=null){
	// newsList2 = (List<Map<String, Object>>) newsMap2.get("result");
	// }
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	// request.setAttribute("newsList2", newsList2);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "wmh_index";
	// }

}
