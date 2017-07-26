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
import com.gw.zhsq.web.service.NewsTypeService;
import com.gw.zhsq.web.service.NoticesInfoService;
import com.gw.zhsq.web.service.WGYService;
import com.gw.zhsq.web.service.ZhsqLiveService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * [智慧社区]dz首页
 * @author xjj
 * 2017-06-08
 * 请求地址：http://localhost:8081/Weixin/dz/init.htm?shequId=202
 */
@Controller
@RequestMapping("/dz")
public class DzIndexController extends BaseController {

	@Resource
	private AdvService advService;
	@Resource
	private NewsTypeService newsTypeService;
	@Resource
	private NoticesInfoService noticesInfoService;
	@Resource
	private ZhsqLiveService zhsqLiveService;
	@Resource
	private WGYService wgyService;
	
	//定制服务中心首页
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
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
			
			//获取会员登录信息
			Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			if(memberMap!=null){
				String member_shequId = StringUtil.nvl(memberMap.get("shequId"));
				if(!member_shequId.equals(shequId)){
					sessions.removeAttribute("memberSession");
					sessions.invalidate();
				}
			}
					// 获取政务各大分类
					List<Map<String, Object>> newsClassList = null;
					try {
						newsClassList = newsTypeService.getNewsClassList(requestMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
					request.setAttribute("newsClassList", newsClassList);
					
				    requestMap.put("province", "110000");
				    requestMap.put("city", "110100");
				    HashMap<String, String> paramMaps = new HashMap<String, String>();
					paramMaps.put("bgLocation", "4");
					paramMaps.put("shequId", shequId);//社区ID
				    
					List<Map<String, Object>> adv = advService.getZhsqAdvList(paramMaps);
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
						
					//上部横幅广告
					HashMap<String, String> paramMaps1 = new HashMap<String, String>();
					paramMaps1.put("bgLocation", "5");
					paramMaps1.put("shequId", shequId);//社区ID
					List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps1);
					String topImg = "";
					if(advTop!=null&&advTop.size()>0){
						Map<String, Object> map = advTop.get(0);
						Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
						topImg = (String) paramMapss.get("imagePaths");
					}
					request.setAttribute("topImg", topImg);
						
					//中部横幅广告
					HashMap<String, String> paramMaps2 = new HashMap<String, String>();
					paramMaps2.put("bgLocation", "6");
					paramMaps2.put("shequId", shequId);//社区ID
					List<Map<String, Object>> advMid = advService.getZhsqAdvList(paramMaps2);
					String midImg = "";
					if(advMid!=null&&advMid.size()>0){
						Map<String, Object> map = advMid.get(0);
						Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
						midImg = (String) paramMapss.get("imagePaths");
					}
					request.setAttribute("midImg", midImg);
						
					//下部横幅广告
					HashMap<String, String> paramMaps3 = new HashMap<String, String>();
				 	paramMaps3.put("bgLocation", "7");
				 	paramMaps3.put("shequId", shequId);//社区ID
				 	List<Map<String, Object>> advBot = advService.getZhsqAdvList(paramMaps3);
				 	String botImg = "";
					if(advBot!=null&&advBot.size()>0){
						Map<String, Object> map = advBot.get(0);
						Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
						botImg = (String) paramMapss.get("imagePaths");
					}
					request.setAttribute("botImg", botImg);
						
					//活动公告
					Map<String,Object> noticesInfoMap = null;
					try{
						HashMap<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("shequId", shequId);//社区ID
						paramMap.put("page", "1");//当前页数
						paramMap.put("pageSize", "5");//每页条数
						paramMap.put("notifyRange", "5");//通知范围(1客户端2服务端3后台4网站5微信6其它)
						Map<String, Object> noticesMap = noticesInfoService.getNoticesMap(paramMap);
						if(noticesMap!=null){
							List<Map<String, Object>> noticesInfoList = (List<Map<String, Object>>) noticesMap.get("result");
							if(noticesInfoList!=null&&noticesInfoList.size()>0){
								//noticesInfoMap = noticesInfoList.get(0);
								request.setAttribute("noticesList", noticesInfoList);
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					request.setAttribute("noticesMap", noticesInfoMap);
						
					//社区生活
					HashMap<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("shequId", shequId);//社区ID
					paramMap.put("lvLocation", "1");//1社区生活  2生活助手
					List<Map<String, Object>> shequLiveList = zhsqLiveService.getZhsqLiveList(paramMap);
					request.setAttribute("shequLiveList", shequLiveList);
					
					//生活助手
					HashMap<String, String> paramMap2 = new HashMap<String, String>();
					paramMap2.put("shequId", shequId);//社区ID
					paramMap2.put("lvLocation", "2");//1社区生活  2生活助手
					List<Map<String, Object>> shequAdieList = zhsqLiveService.getZhsqLiveList(paramMap2);
					request.setAttribute("shequAdieList", shequAdieList);
					
					//获取社区名称
					String shequName = "社区服务";
					HashMap<String, String> paramMap3 = new HashMap<String, String>();
					paramMap3.put("shequId", shequId);//社区ID
					List<Map<String, Object>> shequList = zhsqLiveService.getZhsqList(paramMap3);
					if(shequList!=null && shequList.size()>0){
						Map<String, Object> shequMap = shequList.get(0);
						shequName = StringUtil.nvl(shequMap.get("dhName"));
					}
					request.setAttribute("shequName", shequName);
					
					System.out.println("++++++++++++++requestMap："+requestMap);
					System.out.println("++++++++++++++paramMaps："+paramMaps);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/dz/wasq_462/index";
	}
	
	
	
	//定制服务中心首页
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/initShzs")
		public String initShzs(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			HashMap<String, String> requestMap = findRequestMap(request);
			try {
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
				
				//获取会员登录信息
				Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
				if(memberMap!=null){
					String member_shequId = StringUtil.nvl(memberMap.get("shequId"));
					if(!member_shequId.equals(shequId)){
						sessions.removeAttribute("memberSession");
						sessions.invalidate();
					}
				}
						// 获取政务各大分类
						List<Map<String, Object>> newsClassList = null;
						try {
							newsClassList = newsTypeService.getNewsClassList(requestMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
						request.setAttribute("newsClassList", newsClassList);
						
					    requestMap.put("province", "110000");
					    requestMap.put("city", "110100");
					    HashMap<String, String> paramMaps = new HashMap<String, String>();
						paramMaps.put("bgLocation", "4");
						paramMaps.put("shequId", shequId);//社区ID
					    
						List<Map<String, Object>> adv = advService.getZhsqAdvList(paramMaps);
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
							
						//上部横幅广告
						HashMap<String, String> paramMaps1 = new HashMap<String, String>();
						paramMaps1.put("bgLocation", "5");
						paramMaps1.put("shequId", shequId);//社区ID
						List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps1);
						String topImg = "";
						if(advTop!=null&&advTop.size()>0){
							Map<String, Object> map = advTop.get(0);
							Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
							topImg = (String) paramMapss.get("imagePaths");
						}
						request.setAttribute("topImg", topImg);
							
						//中部横幅广告
						HashMap<String, String> paramMaps2 = new HashMap<String, String>();
						paramMaps2.put("bgLocation", "6");
						paramMaps2.put("shequId", shequId);//社区ID
						List<Map<String, Object>> advMid = advService.getZhsqAdvList(paramMaps2);
						String midImg = "";
						if(advMid!=null&&advMid.size()>0){
							Map<String, Object> map = advMid.get(0);
							Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
							midImg = (String) paramMapss.get("imagePaths");
						}
						request.setAttribute("midImg", midImg);
							
						//下部横幅广告
						HashMap<String, String> paramMaps3 = new HashMap<String, String>();
					 	paramMaps3.put("bgLocation", "7");
					 	paramMaps3.put("shequId", shequId);//社区ID
					 	List<Map<String, Object>> advBot = advService.getZhsqAdvList(paramMaps3);
					 	String botImg = "";
						if(advBot!=null&&advBot.size()>0){
							Map<String, Object> map = advBot.get(0);
							Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
							botImg = (String) paramMapss.get("imagePaths");
						}
						request.setAttribute("botImg", botImg);
							
						//活动公告
						Map<String,Object> noticesInfoMap = null;
						try{
							HashMap<String, String> paramMap = new HashMap<String, String>();
							paramMap.put("shequId", shequId);//社区ID
							paramMap.put("page", "1");//当前页数
							paramMap.put("pageSize", "5");//每页条数
							paramMap.put("notifyRange", "5");//通知范围(1客户端2服务端3后台4网站5微信6其它)
							Map<String, Object> noticesMap = noticesInfoService.getNoticesMap(paramMap);
							if(noticesMap!=null){
								List<Map<String, Object>> noticesInfoList = (List<Map<String, Object>>) noticesMap.get("result");
								if(noticesInfoList!=null&&noticesInfoList.size()>0){
									//noticesInfoMap = noticesInfoList.get(0);
									request.setAttribute("noticesList", noticesInfoList);
								}
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						request.setAttribute("noticesMap", noticesInfoMap);
							
						//社区生活
						HashMap<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("shequId", shequId);//社区ID
						paramMap.put("lvLocation", "1");//1社区生活  2生活助手
						List<Map<String, Object>> shequLiveList = zhsqLiveService.getZhsqLiveList(paramMap);
						request.setAttribute("shequLiveList", shequLiveList);
						
						//生活助手
						HashMap<String, String> paramMap2 = new HashMap<String, String>();
						paramMap2.put("shequId", shequId);//社区ID
						paramMap2.put("lvLocation", "2");//1社区生活  2生活助手
						List<Map<String, Object>> shequAdieList = zhsqLiveService.getZhsqLiveList(paramMap2);
						request.setAttribute("shequAdieList", shequAdieList);
						
						//获取社区名称
						String shequName = "社区服务";
						HashMap<String, String> paramMap3 = new HashMap<String, String>();
						paramMap3.put("shequId", shequId);//社区ID
						List<Map<String, Object>> shequList = zhsqLiveService.getZhsqList(paramMap3);
						if(shequList!=null && shequList.size()>0){
							Map<String, Object> shequMap = shequList.get(0);
							shequName = StringUtil.nvl(shequMap.get("dhName"));
						}
						request.setAttribute("shequName", shequName);
						
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/dz/wasq_462/shzs";
		}
		
		
		//网格人员
		@RequestMapping(value = "/initWgry")
		public String initWgry(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			return "/dz/wasq_462/wgry";
		}
		//党建
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/initDjjj")
		public String initDjjj(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			HashMap<String, String> requestMap = findRequestMap(request);
			try {
				String shequId = requestMap.get("shequId");//社区ID
				HttpSession sessions = request.getSession();
				if(StringUtils.isBlank(shequId)){
					shequId = StringUtil.nvl(sessions.getAttribute("shequId"));
					if(StringUtils.isBlank(shequId)){
						shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
					}
				}
				
				// 获取政务各大分类
				List<Map<String, Object>> newsClassList = null;
				try {
					newsClassList = newsTypeService.getNewsClassList(requestMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("newsClassList", newsClassList);
				
			    requestMap.put("province", "110000");
			    requestMap.put("city", "110100");
			    HashMap<String, String> paramMaps = new HashMap<String, String>();
				paramMaps.put("bgLocation", "4");
				paramMaps.put("shequId", shequId);//社区ID
				
				
				//获取社区名称
				String shequName = "社区服务";
				HashMap<String, String> paramMap3 = new HashMap<String, String>();
				paramMap3.put("shequId", shequId);//社区ID
				List<Map<String, Object>> shequList = zhsqLiveService.getZhsqList(paramMap3);
				if(shequList!=null && shequList.size()>0){
					Map<String, Object> shequMap = shequList.get(0);
					shequName = StringUtil.nvl(shequMap.get("dhName"));
				}
				request.setAttribute("shequName", shequName);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/dz/wasq_462/djjj";
		}
		
		
		
		//民生民政
		@RequestMapping(value = "/initMsmz")
		public String initMsmz(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			return "/dz/wasq_462/msmz";
		}
		//群众之声
		@RequestMapping(value = "/initQzzs")
		public String initqzzs(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			HashMap<String, String> requestMap = findRequestMap(request);
			try {
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
				
				//获取会员登录信息
				Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
				if(memberMap!=null){
					String member_shequId = StringUtil.nvl(memberMap.get("shequId"));
					if(!member_shequId.equals(shequId)){
						sessions.removeAttribute("memberSession");
						sessions.invalidate();
					}
				}
						// 获取政务各大分类
						List<Map<String, Object>> newsClassList = null;
						try {
							newsClassList = newsTypeService.getNewsClassList(requestMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
						request.setAttribute("newsClassList", newsClassList);
						
					    requestMap.put("province", "110000");
					    requestMap.put("city", "110100");
					    HashMap<String, String> paramMaps = new HashMap<String, String>();
						paramMaps.put("bgLocation", "4");
						paramMaps.put("shequId", shequId);//社区ID
					    
						List<Map<String, Object>> adv = advService.getZhsqAdvList(paramMaps);
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
							
						//上部横幅广告
						HashMap<String, String> paramMaps1 = new HashMap<String, String>();
						paramMaps1.put("bgLocation", "5");
						paramMaps1.put("shequId", shequId);//社区ID
						List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps1);
						String topImg = "";
						if(advTop!=null&&advTop.size()>0){
							Map<String, Object> map = advTop.get(0);
							Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
							topImg = (String) paramMapss.get("imagePaths");
						}
						request.setAttribute("topImg", topImg);
							
						//中部横幅广告
						HashMap<String, String> paramMaps2 = new HashMap<String, String>();
						paramMaps2.put("bgLocation", "6");
						paramMaps2.put("shequId", shequId);//社区ID
						List<Map<String, Object>> advMid = advService.getZhsqAdvList(paramMaps2);
						String midImg = "";
						if(advMid!=null&&advMid.size()>0){
							Map<String, Object> map = advMid.get(0);
							Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
							midImg = (String) paramMapss.get("imagePaths");
						}
						request.setAttribute("midImg", midImg);
							
						//下部横幅广告
						HashMap<String, String> paramMaps3 = new HashMap<String, String>();
					 	paramMaps3.put("bgLocation", "7");
					 	paramMaps3.put("shequId", shequId);//社区ID
					 	List<Map<String, Object>> advBot = advService.getZhsqAdvList(paramMaps3);
					 	String botImg = "";
						if(advBot!=null&&advBot.size()>0){
							Map<String, Object> map = advBot.get(0);
							Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
							botImg = (String) paramMapss.get("imagePaths");
						}
						request.setAttribute("botImg", botImg);
							
						//活动公告
						Map<String,Object> noticesInfoMap = null;
						try{
							HashMap<String, String> paramMap = new HashMap<String, String>();
							paramMap.put("shequId", shequId);//社区ID
							paramMap.put("page", "1");//当前页数
							paramMap.put("pageSize", "5");//每页条数
							paramMap.put("notifyRange", "5");//通知范围(1客户端2服务端3后台4网站5微信6其它)
							Map<String, Object> noticesMap = noticesInfoService.getNoticesMap(paramMap);
							if(noticesMap!=null){
								List<Map<String, Object>> noticesInfoList = (List<Map<String, Object>>) noticesMap.get("result");
								if(noticesInfoList!=null&&noticesInfoList.size()>0){
									//noticesInfoMap = noticesInfoList.get(0);
									request.setAttribute("noticesList", noticesInfoList);
								}
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						request.setAttribute("noticesMap", noticesInfoMap);
							
						//社区生活
						HashMap<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("shequId", shequId);//社区ID
						paramMap.put("lvLocation", "1");//1社区生活  2生活助手
						List<Map<String, Object>> shequLiveList = zhsqLiveService.getZhsqLiveList(paramMap);
						request.setAttribute("shequLiveList", shequLiveList);
						
						//生活助手
						HashMap<String, String> paramMap2 = new HashMap<String, String>();
						paramMap2.put("shequId", shequId);//社区ID
						paramMap2.put("lvLocation", "2");//1社区生活  2生活助手
						List<Map<String, Object>> shequAdieList = zhsqLiveService.getZhsqLiveList(paramMap2);
						request.setAttribute("shequAdieList", shequAdieList);
						
						//获取社区名称
						String shequName = "社区服务";
						HashMap<String, String> paramMap3 = new HashMap<String, String>();
						paramMap3.put("shequId", shequId);//社区ID
						List<Map<String, Object>> shequList = zhsqLiveService.getZhsqList(paramMap3);
						if(shequList!=null && shequList.size()>0){
							Map<String, Object> shequMap = shequList.get(0);
							shequName = StringUtil.nvl(shequMap.get("dhName"));
						}
						request.setAttribute("shequName", shequName);
						
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "/dz/wasq_462/qzzs";
		}
		
		
		
		
		
		
		//获取网格员列表
		@RequestMapping(value = "/getLdzList")
		public String getWGYList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
				Map<String, Object> wgyMap = wgyService.getWGYListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> wgyList = (List<Map<String, Object>>) wgyMap.get("result");
				if(wgyList!=null&&wgyList.size()>0){
					for(Map<String,Object> map : wgyList){
						String mark = (String) map.get("remark");
						if(mark.length() > 30){
							mark = mark.substring(0,30)+"...";
						}
							map.put("mark", mark);
					}
				}
				request.setAttribute("wgyList", wgyList);
				System.out.println("wgyList:"+wgyList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/dz/wasq_462/ldz_list";
		}
		
		//获取网格员列表
				@RequestMapping(value = "/getSgList")
				public String getSgList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
						Map<String, Object> wgyMap = wgyService.getWGYListMap(requestMap);
						@SuppressWarnings("unchecked")
						List<Map<String,Object>> wgyList = (List<Map<String, Object>>) wgyMap.get("result");
						if(wgyList!=null&&wgyList.size()>0){
							for(Map<String,Object> map : wgyList){
								String mark = (String) map.get("remark");
								if(mark.length() > 30){
									mark = mark.substring(0,30)+"...";
								}
									map.put("mark", mark);
							}
						}
						request.setAttribute("wgyList", wgyList);
						System.out.println("wgyList:"+wgyList);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "/dz/wasq_462/sg_list";
				}
		
		
		
		//异步取得网格员列表
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value = "/getLdzListAsync")
		@ResponseBody
		public void getWGYListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
			HashMap<String, String> requestMap = findRequestMap(request);
			List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
			try {
				Map<String, Object> newsMap = wgyService.getWGYListMap(requestMap);
				newsList = (List<Map<String, Object>>) newsMap.get("result");
				if(newsList!=null&&newsList.size()>0){
					for(Map<String,Object> map : newsList){
						String mark = (String) map.get("remark");
						if(mark.length() > 30){
							mark = mark.substring(0,30)+"...";
						}
						map.put("mark", mark);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			PrintWriter out;
			try {
				response.setContentType("text/json;charset=UTF-8");
				Map map =new HashMap();
				map.put("dataList",newsList);
				map.put("size",(newsList==null)?0:newsList.size());
				JSONObject obj = JSONObject.fromObject(map);
				out = response.getWriter();
				out.write(obj.toString());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	
}

