package com.gw.zhsq.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gw.base.constant.SystemConstant;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.ZhsqLawGuideService;

import edu.emory.mathcs.backport.java.util.LinkedList;

/**
 * 社区办事指南
 * @author fuyun
 *	2016-04-20
 *	http://localhost:8080/Weixin/zhsq/lawGuide/init.htm
 */
@Controller
@RequestMapping("/zhsq/lawGuide")
public class ZhsqLawGuideController extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private ZhsqLawGuideService zhsqLawGuideService;
	
	/**
	 * 初始化办事指南
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		HttpSession shequSessions = request.getSession();
		if(StringUtils.isBlank(shequId)){
			shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
			if(StringUtils.isBlank(shequId)){
				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
			}
		}
		System.out.println("=======shequIdshequIdshequId====>>"+shequId);
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		shequSessions.setAttribute("shequId", shequId);
		
		
//		String type =  requestMap.get("type");
		Map<String, Object> zhsqLawGuideListMap = null;
		try {
			
			
			
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("shequId", shequId);
			List<Map<String, Object>> bbasLawGuideList = zhsqLawGuideService.getBasLawGuideList(paramMap);
			List<Map<String, Object>> basLawGuideList=new LinkedList();
			for(Map<String, Object> map:bbasLawGuideList){
				if(Integer.valueOf(map.get("classId").toString())!=0)
					basLawGuideList.add(map);			
			}
			request.setAttribute("basLawGuideList", basLawGuideList);
			
			
			
			
//			HashMap<String, String> hashMap = new HashMap<String,String>();
//			hashMap.put("shequId", shequId);
//			zhsqLawGuideListMap = zhsqLawGuideService.getZhsqLawGuideTypeListMap(hashMap);
//			List<Map<String,Object>> zhsqLawGuideList = (List<Map<String, Object>>) zhsqLawGuideListMap.get("data");
//			List<Map<String,Object>> zhsqLawGuideListTemp = new ArrayList<Map<String,Object>>();
//			
//			String sort[]={"10","3","1","5","2","11","12","13","7","15"};
//			for(int i=0;i<sort.length;i++){
//				for(Map<String,Object> map : zhsqLawGuideList){
//					if(sort[i].equals(map.get("type"))){
//						zhsqLawGuideListTemp.add(map);
//						break;
//					}
//				}
//				
//			}
			
//			System.out.println("zhsqLawGuideList====lawGuide====>>"+zhsqLawGuideList);
////			request.setAttribute("zhsqLawGuideList", zhsqLawGuideList);
//			request.setAttribute("zhsqLawGuideList", zhsqLawGuideListTemp);
			request.setAttribute("zhsqLawGuideListMap", zhsqLawGuideListMap);
			
			//获取顶部幻灯片
//			 HashMap<String, String> paramMaps = new HashMap<String, String>();
//				paramMaps.put("bgLocation", "17");
//				paramMaps.put("shequId", shequId);//社区ID
//			    
//				List<Map<String, Object>> adv = advService.getZhsqAdvList(paramMaps);
//				request.setAttribute("adv", adv);
//				
//				List<Map<String,Object>> advListNew = new ArrayList<Map<String,Object>>();
//				 Map<String,Object> advMapNew = null;
//				 if(adv!=null&&adv.size()>0){
//			        	for(int i=0;i<adv.size();i++){
//			        		advMapNew = new HashMap<String, Object>();
//			        		Map<String,Object> indexMap = adv.get(i);
//							Map<String,Object> paramMap = (Map<String, Object>) indexMap.get("paramMap");
//			        		String imagePaths = "";
//			        		if(paramMap!=null){
//			        			imagePaths = StringUtil.nvl(paramMap.get("imagePaths"));
//			        		}
//			        		String url = StringUtil.nvl(indexMap.get("adUrl"));
//			        		if(StringUtils.isNotBlank(url)){
//			        			advMapNew.put("url", url);
//		        			}else{
//		        				advMapNew.put("url", "#");
//		        			}
//			        		advMapNew.put("title", "");
//			        			if(StringUtils.isNotBlank(imagePaths)){
//			        				advMapNew.put("img", imagePaths);
//			        			}
//			        			advListNew.add(advMapNew);
//			        	}
//			        }
//				 else{
//						 Map<String,Object> advMapNew5 = new HashMap<String, Object>();
//				        	advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_bszn.jpg");
//				        	advMapNew5.put("url", "");
//				        	advMapNew5.put("title", "");
//				        	advListNew.add(advMapNew5);
//				        	Map<String,Object> advMapNew6 = new HashMap<String, Object>();
//				        	advMapNew6.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_bszn.jpg");
//				        	advMapNew6.put("url", "");
//				        	advMapNew6.put("title", "");
//				        	advListNew.add(advMapNew6);
//			          	/* Map<String,Object> advMapNew5 = new HashMap<String, Object>();
//			        	 advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_fw.jpg");
//			        	 advMapNew5.put("url", "");
//			        	 advMapNew5.put("title", "");
//			        	 advListNew.add(advMapNew5);
//			        	 Map<String,Object> advMapNew1 = new HashMap<String, Object>();
//			        	 advMapNew1.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jbts.jpg");
//			        	 advMapNew1.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jbts/init.htm?shequId="+shequId);
//			        	 advMapNew1.put("title", "");
//			        	 advListNew.add(advMapNew1);
////			        	 Map<String,Object> advMapNew2 = new HashMap<String, Object>();
////			        	 advMapNew2.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_gzbx.jpg");
////			        	 advMapNew2.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/gzbx/init.htm?shequId="+shequId);
////			        	 advMapNew2.put("title", "");
////			        	 advListNew.add(advMapNew2);
//			        	 Map<String,Object> advMapNew3 = new HashMap<String, Object>();
//			        	 advMapNew3.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jlhd.jpg");
//			        	 advMapNew3.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jlhd/init.htm?shequId="+shequId);
//			        	 advMapNew3.put("title", "");
//			        	 advListNew.add(advMapNew3);
//			        	 Map<String,Object> advMapNew4 = new HashMap<String, Object>();
//			        	 advMapNew4.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_sjxx.jpg");
//			        	 advMapNew4.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/sjxx/init.htm?shequId="+shequId);
//			        	 advMapNew4.put("title", "");
//			        	 advListNew.add(advMapNew4);*/
//			     
//			        }
//				 if(adv!=null&&adv.size()==1){
//					 Map<String,Object> temp = adv.get(0);
//					Map<String,Object> map = (Map<String, Object>) temp.get("paramMap");
//		        		String imagePaths = "";
//		        		if(map!=null){
//		        			imagePaths = StringUtil.nvl(map.get("imagePaths"));
//		        		}
//					 request.setAttribute("imageUrl", imagePaths);
//					 System.out.println("============imagePaths================" + imagePaths);
//				 }
//			        JSONArray indexADJsonArray = JSONArray.fromObject(advListNew); 
//			    	request.setAttribute("indexADJsonArray", indexADJsonArray);
//					request.setAttribute("indexADListNew", advListNew);
//					request.setAttribute("indexADSize", advListNew.size());
//					System.out.println("=============advListNew================" + advListNew);
//					System.out.println("==========advListNew.size()=============" + advListNew.size());
//					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "wfw/zhsqLawGuide/zhsq_lawGuide_index";
	}
	
	/**
	 * 获取办事指南列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqLawGuideListMap")
	public String getZhsqLawGuideListMap(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=======================1111111=");
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(shequId)){
			shequId = StringUtil.nvl(sessions.getAttribute("shequId"));
			if(StringUtils.isBlank(shequId)){
				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
			}
		}
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		sessions.setAttribute("shequId", shequId);
		
		String type =  requestMap.get("type");
		Map<String, Object> zhsqLawGuideListMap = null;
		try {
			HashMap<String, String> hashMap = new HashMap<String,String>();
			hashMap.put("title", "");
			hashMap.put("type", type);
			hashMap.put("shequId", shequId);
			zhsqLawGuideListMap = zhsqLawGuideService.getZhsqLawGuideListMap(hashMap);
			List<Map<String,Object>> zhsqLawGuideList = (List<Map<String, Object>>) zhsqLawGuideListMap.get("result");
			request.setAttribute("zhsqLawGuideList", zhsqLawGuideList);
			request.setAttribute("zhsqLawGuideListMap", zhsqLawGuideListMap);
			
			//顶部广告
			HashMap<String, String> paramMaps18 = new HashMap<String, String>();
			paramMaps18.put("bgLocation", "18");
			paramMaps18.put("shequId", shequId);//社区ID
			List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps18);
			String topImg = "";
			if(advTop!=null&&advTop.size()>0){
				Map<String, Object> map = advTop.get(0);
				Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
				topImg = (String) paramMapss.get("imagePaths");
			}
			request.setAttribute("topImg", topImg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wfw/zhsqLawGuide/zhsq_lawGuide_list";
	}
	
	/**
	 * 获取办事指南详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqLawGuideMap")
	public String getZhsqLawGuideMap(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===============1111111111");
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		HttpSession shequSessions = request.getSession();
		if(StringUtils.isBlank(shequId)){
			shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
			if(StringUtils.isBlank(shequId)){
				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
			}
		}
		System.out.println("=============shequId1213=============>>"+shequId);
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		shequSessions.setAttribute("shequId", shequId);
		String lawGuideId =  requestMap.get("lawGuideId");
		Map<String, Object> zhsqLawGuideMap = null;
		try {
			HashMap<String, String> hashMap = new HashMap<String,String>();
			hashMap.put("lawGuideId", lawGuideId);
			zhsqLawGuideMap = zhsqLawGuideService.getZhsqLawGuideMap(hashMap);
			request.setAttribute("zhsqLawGuideMap", zhsqLawGuideMap);
			
			//顶部广告
			HashMap<String, String> paramMaps19 = new HashMap<String, String>();
			paramMaps19.put("bgLocation", "19");
			paramMaps19.put("shequId", shequId);//社区ID
			List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps19);
			String topImg = "";
			if(advTop!=null&&advTop.size()>0){
				Map<String, Object> map = advTop.get(0);
				Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
				topImg = (String) paramMapss.get("imagePaths");
			}
			request.setAttribute("topImg", topImg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wfw/zhsqLawGuide/zhsq_lawGuide_detail";
	}
}
