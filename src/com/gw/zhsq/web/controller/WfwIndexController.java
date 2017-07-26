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
import com.gw.zhsq.web.service.NewsTypeService;
import com.gw.zhsq.web.service.NoticesInfoService;
import com.gw.zhsq.web.service.ZhsqLiveService;

/**
 * [智慧社区]微服务首页
 * @author fuyun
 * 2015-10-01
 * 请求地址：http://localhost:8080/Weixin/wfw/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw")
public class WfwIndexController extends BaseController {

	@Resource
	private AdvService advService;
	@Resource
	private NewsTypeService newsTypeService;
	@Resource
	private NoticesInfoService noticesInfoService;
	@Resource
	private ZhsqLiveService zhsqLiveService;
	
	//微服务首页
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
//			if(memberMap==null){
//				request.setAttribute("login_type", "wfwIndex");
//				sessions.setAttribute("login_type", "wfwIndex");
//				return "redirect:/member/login.htm?login_type=wfwIndex";
//			}else{
//				request.setAttribute("memberMap", memberMap);
//				String member_shequId = StringUtil.nvl(memberMap.get("shequId"));
//				System.out.println("memberMap=====>"+memberMap);
//				System.out.println("shequId=====>"+shequId);
//				System.out.println("member_shequId=====>"+member_shequId);
//				if(!member_shequId.equals(shequId)){
//					sessions.removeAttribute("memberSession");
//					sessions.invalidate();
//					request.setAttribute("login_type", "wfwIndex");
//					System.out.println("==========wfwIndex==========");
//					//sessions.setAttribute("login_type", "wfwIndex");
//					return "redirect:/member/login.htm?login_type=wfwIndex&shequId="+shequId;
//				}else{
//			HttpSession sessions = request.getSession();
//			Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
//			if(memberMap==null){
//				request.setAttribute("login_type", "wfwIndex");
//				sessions.setAttribute("login_type", "wfwIndex");
//				return "redirect:/member/login.htm?login_type=wfwIndex";
//			}else{
//				request.setAttribute("memberMap", memberMap);
//				String member_shequId = StringUtil.nvl(memberMap.get("shequId"));
//				System.out.println("memberMap=====>"+memberMap);
//				System.out.println("shequId=====>"+shequId);
//				System.out.println("member_shequId=====>"+member_shequId);
//				if(!member_shequId.equals(shequId)){
//					sessions.removeAttribute("memberSession");
//					sessions.invalidate();
//					request.setAttribute("login_type", "wfwIndex");
//					System.out.println("==========wfwIndex==========");
//					//sessions.setAttribute("login_type", "wfwIndex");
//					return "redirect:/member/login.htm?login_type=wfwIndex&shequId="+shequId;
//				}else{
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
//				        	 Map<String,Object> advMapNew2 = new HashMap<String, Object>();
//				        	 advMapNew2.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_gzbx.jpg");
//				        	 advMapNew2.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/gzbx/init.htm?shequId="+shequId);
//				        	 advMapNew2.put("title", "");
//				        	 advListNew.add(advMapNew2);
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
//				}
//			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wfw_index";
	}
	
}

