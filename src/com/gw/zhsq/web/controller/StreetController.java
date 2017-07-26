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
import com.gw.zhsq.web.service.StreetService;

/**
 * 街道
 * @author lihui
 *	2016-04-20
 *	http://localhost:8080/Weixin/street/init.htm
 */
@Controller
@RequestMapping("/street")
public class StreetController extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private StreetService streetService;
	
	/**
	 * 初始化街道
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String streetId = request.getParameter("streetId");//街道ID
		if(StringUtils.isBlank(streetId)){
			streetId = SystemConstant.STREET_ID;
		}
		HttpSession sessions = request.getSession();
		sessions.setAttribute("streetId", streetId);
		if(StringUtils.isNotBlank(streetId)){
			//22东台镇41昌江区民政81马群街道
			return "street/streetIndex/street_index_"+streetId;
		}else{
			return "street/street_index";
		}
	}
	
	/**
	 * 获取某街道下社区列表信息
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getSheQuList")
	public String getSheQuList(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> sheQuMap = null;
		try {
			String streetId = requestMap.get("streetId");// 街道ID
			HttpSession shequSessions = request.getSession();
			if (StringUtils.isBlank(streetId)) {
				streetId = StringUtil.nvl(shequSessions.getAttribute("streetId"));
				if (StringUtils.isBlank(streetId)) {
					streetId = SystemConstant.STREET_ID;// 默认为东台
				}
			}
			requestMap.put("streetId", streetId);
			request.setAttribute("streetId", streetId);
			shequSessions.setAttribute("streetId", streetId);
			sheQuMap = streetService.getSheQuListMap(requestMap);
			// 获取幻灯片
			
		    HashMap<String, String> paramMaps = new HashMap<String, String>();
			paramMaps.put("bgLocation", "2");
			paramMaps.put("streetId", streetId);//社区ID
		    
			List<Map<String, Object>> adv = advService.getStreetAdvList(paramMaps);
			request.setAttribute("adv", adv);
			
			Map<String,Object> advMapNew = null;
			List<Map<String,Object>> advListNew = new ArrayList<Map<String,Object>>();
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
	        }else if(streetId=="22"){
	          	 Map<String,Object> advMapNew5 = new HashMap<String, Object>();
	        	 advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_fw.jpg");
	        	 advMapNew5.put("url", "");
	        	 advMapNew5.put("title", "");
	        	 advListNew.add(advMapNew5);
	        	/* Map<String,Object> advMapNew1 = new HashMap<String, Object>();
	        	 advMapNew1.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jbts.jpg");
	        	 advMapNew1.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jbts/init.htm?shequId="+streetId);
	        	 advMapNew1.put("title", "");
	        	 advListNew.add(advMapNew1);
//		        	 Map<String,Object> advMapNew2 = new HashMap<String, Object>();
//		        	 advMapNew2.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_gzbx.jpg");
//		        	 advMapNew2.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/gzbx/init.htm?shequId="+shequId);
//		        	 advMapNew2.put("title", "");
//		        	 advListNew.add(advMapNew2);
	        	 Map<String,Object> advMapNew3 = new HashMap<String, Object>();
	        	 advMapNew3.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jlhd.jpg");
	        	 advMapNew3.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jlhd/init.htm?shequId="+streetId);
	        	 advMapNew3.put("title", "");
	        	 advListNew.add(advMapNew3);
	        	 Map<String,Object> advMapNew4 = new HashMap<String, Object>();
	        	 advMapNew4.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_sjxx.jpg");
	        	 advMapNew4.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/sjxx/init.htm?shequId="+streetId);
	        	 advMapNew4.put("title", "");
	        	 advListNew.add(advMapNew4);*/
	        }else{
	        	 Map<String,Object> advMapNew5 = new HashMap<String, Object>();
	        	 advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_changjiangqu.jpg");
	        	 advMapNew5.put("url", "");
	        	 advMapNew5.put("title", "");
	        	 advListNew.add(advMapNew5);
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
			List<Map<String, Object>> sheQuList = (List<Map<String, Object>>) sheQuMap.get("result");
			request.setAttribute("sheQuList", sheQuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/street/street_index";
	}
		
}
