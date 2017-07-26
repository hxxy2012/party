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
import com.gw.base.exception.OtherException;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.ZhsqQuestionService;

/**
 * 【社区】问卷调查
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/zhsq/question/getZhsqQuestion.htm?shequId=202
 */
@Controller
@RequestMapping("/zhsq/question")
public class ZhsqQuestionController extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private ZhsqQuestionService zhsqQuestionService;
	
	//获取社区问卷调查列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqQuestion")
	public String getZhsqQuestion(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
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
		
		try {
			Map<String, Object> zhsqQuestionMap = zhsqQuestionService.getZhsqQuestionListMap(requestMap);
			List<Map<String,Object>> zhsqQuestionList = (List<Map<String, Object>>) zhsqQuestionMap.get("result");
			if(zhsqQuestionList!=null&&zhsqQuestionList.size()>0){
				for(Map<String,Object> map : zhsqQuestionList){
					String detail = (String) map.get("detail");
					if(detail.length() > 10){
						detail = detail.substring(0,10)+"...";
					}
					map.put("detail", detail);
				}
			}
			request.setAttribute("zhsqQuestionList", zhsqQuestionList);
			
			//获取顶部幻灯片
			 HashMap<String, String> paramMaps = new HashMap<String, String>();
				paramMaps.put("bgLocation", "13");
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
			        	advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_wjdc.jpg");
			        	advMapNew5.put("url", "");
			        	advMapNew5.put("title", "");
			        	advListNew.add(advMapNew5);
			        	Map<String,Object> advMapNew6 = new HashMap<String, Object>();
			        	advMapNew6.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_wjdc.jpg");
			        	advMapNew6.put("url", "");
			        	advMapNew6.put("title", "");
			        	advListNew.add(advMapNew6);
//			          	 Map<String,Object> advMapNew5 = new HashMap<String, Object>();
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
//			        	 advListNew.add(advMapNew4);
			     
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
			
			
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/wfw/zhsqQuestion/zhsqQuestion_list";
	}
	
	
	//获取社区问卷调查题目以及选项
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqQuestionInfo")
	public String getZhsqQuestionInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String temp = requestMap.get("temp");
		String shequId = requestMap.get("shequId");//社区ID
		String jspStr = "";
		try {
			requestMap.remove("shequId");
			Map<String, Object> zhsqQuestionInfoMap = zhsqQuestionService.getZhsqQuestionInfoMap(requestMap);
			request.setAttribute("zhsqQuestionInfoMap", zhsqQuestionInfoMap);
			if(StringUtil.isNotNull(temp)&&temp.equals("1")){
				//获取会员登录信息
				HttpSession sessions = request.getSession();
				Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
				if(memberMap==null){
					request.setAttribute("login_type", "zhsqQuest");
					sessions.setAttribute("login_type", "zhsqQuest");
					request.setAttribute("qs_id", zhsqQuestionInfoMap.get("qs_id"));
					return "login";
//					return "redirect:/member/login.htm";
				}else{
					request.setAttribute("memberMap", memberMap);
					requestMap.put("memberId", StringUtil.nvl(memberMap.get("id")));
//				requestMap.put("memberId", "413343");
					Map<String, Object> resultInfoMap = zhsqQuestionService.getResultInfoMap(requestMap);
					List<Map<String,Object>> resultInfoList = (List<Map<String, Object>>) resultInfoMap.get("data");
					System.out.println("resultInfoList====>>"+resultInfoList);
					if(resultInfoList!=null&&resultInfoList.size()>0){
						request.setAttribute("resultType", "1");//该会员已做过此问卷调查
					}else{
						request.setAttribute("resultType", "0");//该会员没做过此问卷调查
					}
					//问卷详情列表广告
					HashMap<String, String> paramMaps15 = new HashMap<String, String>();
				 	paramMaps15.put("bgLocation", "15");
				 	paramMaps15.put("shequId", shequId);//社区ID
				 	List<Map<String, Object>> advBot = advService.getZhsqAdvList(paramMaps15);
				 	String topImg = "";
					if(advBot!=null&&advBot.size()>0){
						Map<String, Object> map = advBot.get(0);
						Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
						topImg = (String) paramMapss.get("imagePaths");
					}
					request.setAttribute("topImg", topImg);
					jspStr = "/wfw/zhsqQuestion/zhsqQuestion_wenda";
					
					
				}
			}else{
				//问卷调查详情
				HashMap<String, String> paramMaps14 = new HashMap<String, String>();
				paramMaps14.put("bgLocation", "14");
				paramMaps14.put("shequId", shequId);//社区ID
				List<Map<String, Object>> advMid = advService.getZhsqAdvList(paramMaps14);
				String topImg = "";
				if(advMid!=null&&advMid.size()>0){
					Map<String, Object> map = advMid.get(0);
					Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
					topImg = (String) paramMapss.get("imagePaths");
				}
				request.setAttribute("topImg", topImg);
				jspStr = "/wfw/zhsqQuestion/zhsqQuestion_info";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jspStr;
	}
	
	//提交社区问卷调查题目以及选项
	@RequestMapping(value = "/subZhsqQuestion")
	public String subZhsqQuestion(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession session = request.getSession();
		String toPage = "";
		try {
			String token = requestMap.get("token");
			String session_token = StringUtil.nvl(session.getAttribute("token"));
			if(token.equals(session_token)){
				session.removeAttribute("token");
				String qs_id = requestMap.get("qs_id");
				Map<String, Object> zhsqQuestionMap = zhsqQuestionService.subZhsqQuestionMap(requestMap);
				String success = StringUtil.nvl(zhsqQuestionMap.get("success"));
				request.setAttribute("qs_id", qs_id);
				request.setAttribute("success", success);
				if("0".equals(success)){
					toPage = "/wfw/zhsqQuestion/zhsqQuestion_succe";
				}else{
					toPage = "/wfw/zhsqQuestion/zhsqQuestion_erro";
				}
			}else{
				toPage = "/wfw/zhsqQuestion/zhsqQuestion_erro";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toPage;
	}
	
	//获取【社区】会员问卷列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMemberZhsqQuestion")
	public String getMemberZhsqQuestion(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(shequId)){
			shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
		}
		//获取会员登录信息
		String memberId = "";		
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "zhsqQuestion");
			sessions.setAttribute("login_type", "zhsqQuestion");
//			return "maqun/maqun_login";
			return "login";
//			return "redirect:/member/login.htm";
		}else{
			memberId = StringUtil.nvl(memberMap.get("id"));
		}
		try {
			requestMap.put("shequId", shequId);
			requestMap.put("memberId", memberId);
			Map<String, Object> memberZhsqQuestionMap = zhsqQuestionService.getMemberZhsqQuestionListMap(requestMap);
			List<Map<String,Object>> memberZhsqQuestionList = (List<Map<String, Object>>) memberZhsqQuestionMap.get("data");
			
			request.setAttribute("memberZhsqQuestionList", memberZhsqQuestionList);
			request.setAttribute("memberId", memberId);
			
		} catch (OtherException e) {
			e.printStackTrace();
		}
		
		return "/member/zhsqQuestion_member_List";
	}
	
	//获取会员中心社区问卷调查题目以及选项
//	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getZhsqMemberQuestionInfo")
	public String getZhsqMemberQuestionInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
//		String memberId = requestMap.get("memberId");//会员ID
		String jspStr = "";
		try {
			requestMap.remove("shequId");
			Map<String, Object> zhsqQuestionInfoMap = zhsqQuestionService.getZhsqQuestionInfoMap(requestMap);
			request.setAttribute("zhsqQuestionInfoMap", zhsqQuestionInfoMap);
			
			jspStr = "/member/zhsqQuestion_member_wenda";
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jspStr;
	}
	
	
	
	
}
