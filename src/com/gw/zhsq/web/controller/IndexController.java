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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gw.base.constant.SystemConstant;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.NewsService;
import com.gw.zhsq.web.service.NewsTypeService;
import com.gw.zhsq.web.service.NoticesInfoService;
import com.gw.zhsq.web.service.RegionService;

/**
 * 智慧社区微信首页
 * @author fuyun
 * 2015-04-01
 * 请求地址：http://localhost:8080/Weixin/index/getIndexInfo.htm?shequId=202
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Resource
	private AdvService advService;
	@Resource
	private RegionService regionService;
	@Resource
	private NewsTypeService newsTypeService;
	@Resource
	private NoticesInfoService noticesInfoService;
	@Resource
	private NewsService newsService;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(IndexController.class);
	
	//添加微信关注用户
	@RequestMapping(value = "/getIndexInfo")
	public String getIndexInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			//获取城市
//			String proName="江苏",proId="5",cityName="徐州",cityId="87";//默认值江苏5，南京85徐州87
//			HttpSession  sessonContext = request.getSession();
//		    if(sessonContext.getAttribute("userCity")!=null){
//		    	 String usercity = (String)sessonContext.getAttribute("userCity");//格式【省名,省id,市名,市id】
//		    	  proName = usercity.split(",")[0];
//		    	  proId = usercity.split(",")[1];
//		    	  cityName = usercity.split(",")[2];
//		    	  cityId = usercity.split(",")[3];
//		    }
//		    requestMap.put("province", proId);
//		    requestMap.put("city", cityId);
//			//幻灯片
//		    requestMap.put("acid", "444");//微信首页幻灯片
		    
//		    requestMap.put("requestName", "getAds");
			
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
			
		    requestMap.put("province", "110000");
		    requestMap.put("city", "110100");
		    //requestMap.put("adLocation", "");
		    System.out.println("requestMap==index===>>"+requestMap);
			List<Map<String, Object>> adv = advService.getAdvList(requestMap);
			System.out.println("adv===>"+adv);
			request.setAttribute("adv", adv);
			
			List<Map<String,Object>> advListNew = new ArrayList<Map<String,Object>>();
			 Map<String,Object> advMapNew = null;
			 if(adv!=null&&adv.size()>0){
		        	for(int i=0;i<adv.size();i++){
		        		advMapNew = new HashMap<String, Object>();
		        		Map<String,Object> indexMap = adv.get(i);
		        		@SuppressWarnings("unchecked")
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
		        }
			 if(adv!=null&&adv.size()==1){
				 Map<String,Object> temp = adv.get(0);
				 @SuppressWarnings("unchecked")
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
				
			
			//活动公告
			HashMap<String, String> paramMap = new HashMap<String, String>();
			String pageno = "1",pageSize = "10";
			if(StringUtils.isNotBlank(requestMap.get("pageno"))){
				pageno = requestMap.get("pageno");
			}
			if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
				pageSize = requestMap.get("pageSize");
			}
			paramMap.put("shequId", shequId);//社区ID
			paramMap.put("page", pageno);//当前页数
			paramMap.put("pageSize", pageSize);//每页条数  
			paramMap.put("notifyRange", "5");
			
			if(StringUtils.isNotBlank(shequId)){
				Map<String, Object> noticesMap = noticesInfoService.getNoticesMap(paramMap);
				//过滤html标签
				if(noticesMap!=null){
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> noticesInfoList = (List<Map<String, Object>>) noticesMap.get("result");
					if(noticesInfoList!=null&&noticesInfoList.size()>0){
						for (int i = 0; i < noticesInfoList.size(); i++) {
							Map<String,Object> temp= noticesInfoList.get(0);
							String contentStr =(String) temp.get("content");
//							String newContent = this.HtmlText(contentStr);
							temp.put("newContent", contentStr);
							request.setAttribute("noticesMap", temp);
						}
					}
				}
			}	
			
			//获取首页政务分类
			List<Map<String, Object>> newsClassList = newsTypeService.getNewsClassList(requestMap);
			
			//获取首页政务列表
			HashMap<String, String> paramMaps1 = new HashMap<String, String>();
			HashMap<String, String> paramMaps2 = new HashMap<String, String>();
            if(newsClassList!=null&&newsClassList.size()>1){
        		Map<String, Object> classMap1 = newsClassList.get(0);
        		request.setAttribute("classMap1", classMap1);
        		String classId1 = StringUtil.nvl( classMap1.get("classId"));
        		paramMaps1.put("classId", classId1);
        		paramMaps1.put("shequId", shequId);
        		
        		Map<String, Object> newsMap1 = newsService.getNewsListMap(paramMaps1);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> newsList1 = (List<Map<String, Object>>) newsMap1.get("result");
				if(newsList1!=null&&newsList1.size()>0){
					for(Map<String,Object> map : newsList1){
						String clientContent = (String) map.get("clientContent");
						if(clientContent.length() > 30){
							clientContent = clientContent.substring(0,30)+"...";
						}
						map.put("clientContent", clientContent);
					}
				}
				request.setAttribute("newsList1", newsList1);
        		
        		
        		Map<String, Object> classMap2 = newsClassList.get(1);
        		request.setAttribute("classMap2", classMap2);
        		String classId2 = StringUtil.nvl( classMap2.get("classId"));
        		paramMaps2.put("classId", classId2);
        		paramMaps2.put("shequId", shequId);
        		
        		Map<String, Object> newsMap2 = newsService.getNewsListMap(paramMaps2);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> newsList2 = (List<Map<String, Object>>) newsMap2.get("result");
				if(newsList2!=null&&newsList2.size()>0){
					for(Map<String,Object> map : newsList2){
						String clientContent = (String) map.get("clientContent");
						if(clientContent.length() > 30){
							clientContent = clientContent.substring(0,30)+"...";
						}
						map.put("clientContent", clientContent);
					}
				}
				request.setAttribute("newsList2", newsList2);
            } 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "wmh_index";
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/toCitySelPage")
	public String toCitys(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String proId="5",cityId="87",proName="江苏",cityName="徐州市";//默认值江苏5，南京85徐州87
		HttpSession  sessonContext = request.getSession();
	    if(sessonContext.getAttribute("userCity")!=null){
	    	  String usercity = (String)sessonContext.getAttribute("userCity");//格式【省名,省id,市名,市id】
	    	  proName = usercity.split(",")[0];
	    	  proId = usercity.split(",")[1];
	    	  cityName = usercity.split(",")[2];
	    	  cityId =usercity.split(",")[3];
	    }
	    requestMap.put("provice_id",proId);
		List<Map<String,Object>> citys = regionService.getOneProCitys(requestMap);
		Map currentCityInfo = new HashMap();
		currentCityInfo.put("proName", proName);
		currentCityInfo.put("cityName", cityName);
		currentCityInfo.put("cityId", cityId);
		request.setAttribute("citys", citys);
		request.setAttribute("currentCityInfo", currentCityInfo);
		return "citySeletor";
	}
	
	
	//建设中
		@RequestMapping(value = "/construction")
		public String binding(ModelMap model, HttpServletRequest request, HttpServletResponse response){
			return "construction";
		}
	
	
	
}

