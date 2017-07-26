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
import com.gw.zhsq.web.service.MemberService;
import com.gw.zhsq.web.service.NewsService;
import com.gw.zhsq.web.service.NewsTypeService;
import com.gw.zhsq.web.service.NoticesInfoService;

/**
 * 智慧马群相关业务
 * @author fuyun
 *	2016-06-28
 *	http://localhost:8080/Weixin/maqun/init.htm
 */
@Controller
@RequestMapping("/maqun")
public class MaqunController extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private NewsService newsService;
	@Resource
	private MemberService memberService;
	@Resource
	private NewsTypeService newsTypeService;
	@Resource
	private NoticesInfoService noticesInfoService;
	
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = request.getParameter("shequId");//社区ID
		String shequName = request.getParameter("shequName");//社区名称
		if(StringUtils.isBlank(shequId)){
			shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;
		}
		HttpSession sessions = request.getSession();
		sessions.setAttribute("shequId", shequId);
		request.setAttribute("shequId", shequId);
		request.setAttribute("shequName", shequName);
		
		// 获取政务各大分类
		List<Map<String, Object>> newsClassList = null;
		try {
			newsClassList = newsTypeService.getNewsClassList(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("newsClassList", newsClassList);
		
		requestMap.remove("shequName");
		requestMap.put("province", "110000");
	    requestMap.put("city", "110100");
		List<Map<String, Object>> adv = advService.getAdvList(requestMap);
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
//	        	 Map<String,Object> advMapNew2 = new HashMap<String, Object>();
//	        	 advMapNew2.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_gzbx.jpg");
//	        	 advMapNew2.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/gzbx/init.htm?shequId="+shequId);
//	        	 advMapNew2.put("title", "");
//	        	 advListNew.add(advMapNew2);
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
		Map<String,Object> noticesInfoMap = null;
		try{
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("shequId", shequId);//社区ID
			paramMap.put("page", "1");//当前页数
			paramMap.put("pageSize", "5");//每页条数
			paramMap.put("notifyRange", "5");//通知范围(1客户端2服务端3后台4网站5微信6其它)
			Map<String, Object> noticesMap = noticesInfoService.getNoticesMap(paramMap);
			if(noticesMap!=null){
				@SuppressWarnings("unchecked")
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
		
		return "maqun/shequ_index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getNewestNewsList")
	public String getNewestNewsList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = request.getParameter("shequId");//社区ID
		String shequName = request.getParameter("shequName");//社区名称
		if(StringUtils.isBlank(shequId)){
			shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;
		}
		HttpSession sessions = request.getSession();
		sessions.setAttribute("shequId", shequId);
		request.setAttribute("shequId", shequId);
		request.setAttribute("shequName", shequName);
		
		// 获取首页最新资讯
		List<Map<String, Object>> newsList = null;
		try {
			Map<String, Object> newsMap = newsService.getNewsListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
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
		} catch (OtherException e) {
			e.printStackTrace();
		}
		request.setAttribute("newsList", newsList);
		return "maqun/maqun_newest_news_list";
	}
	
	//初始化会员中心
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/baseMemberInfo")
	public String baseMemberInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;
			}
		}
		request.setAttribute("streetId", streetId);
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "qsQuestionnaire");
			sessions.setAttribute("login_type", "qsQuestionnaire");
			return "maqun/maqun_login";
		}else{
			request.setAttribute("memberMap", memberMap);
			return "maqun/maqun_baseMemberInfo";
		}
	}
	
	//会员登录
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/memberLogin")
	public String memberLogin(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		String login_type = requestMap.get("login_type");
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;
			}
		}
		request.setAttribute("streetId", streetId);
		
		String shequId = requestMap.get("shequId");//社区ID
		if(StringUtils.isBlank(shequId)){
			shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;//社区ID
		}
		request.setAttribute("shequId", shequId);
		sessions.setAttribute("shequId", shequId);
		
		if(StringUtils.isBlank(login_type)){
			login_type = StringUtil.nvl(sessions.getAttribute("login_type"));
		}
		try {
			Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			String login_name = requestMap.get("login_name");//用户名
			String login_password = requestMap.get("login_password");//密码
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("shequId", shequId);
			if(StringUtils.isBlank(login_name)){
				if(memberSessionMap!=null){
					login_name =  StringUtil.nvl(memberSessionMap.get("account"));
					paramsMap.put("account", StringUtil.nvl(memberSessionMap.get("account")));
				}
			}else{
				paramsMap.put("account", login_name);
			}
			if(StringUtils.isBlank(login_password)){
				if(memberSessionMap!=null){
					login_password =  StringUtil.nvl(memberSessionMap.get("password"));
					paramsMap.put("password", StringUtil.nvl(memberSessionMap.get("password")));
				}
			}else{
				paramsMap.put("password", login_password);
			}
			
			if(StringUtils.isBlank(login_name)||StringUtils.isBlank(login_password)){
				request.setAttribute("memberMap", memberSessionMap);
			}else{
				Map<String, Object> memberMap = memberService.memberLogin(paramsMap);
				sessions.setAttribute("memberSession", memberMap);
				if(memberMap!=null){
					request.setAttribute("memberMap", memberMap);
					if("qsQuestionnaire".equals(login_type)){
						return "redirect:/qsQuestionnaire/init.htm?streetId="+streetId;
					}else{
						return "maqun/maqun_baseMemberInfo";
					}
				}else{
					request.setAttribute("login_type", "qsQuestionnaire");
					sessions.setAttribute("login_type", "qsQuestionnaire");
					return "maqun/maqun_login";
				}
			}
			request.setAttribute("login_type", "qsQuestionnaire");
			sessions.setAttribute("login_type", "qsQuestionnaire");
			return "maqun/maqun_login";
		} catch (OtherException e) {
			e.printStackTrace();
			request.setAttribute("error_code", e.getErrorCode());
			request.setAttribute("error_msg", e.getErrorMsg());
			request.setAttribute("login_type", "qsQuestionnaire");
			sessions.setAttribute("login_type", "qsQuestionnaire");
			return "maqun/maqun_login";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_code", "9999");
			request.setAttribute("error_msg", "会员登录失败，请重新登录");
			request.setAttribute("login_type", "qsQuestionnaire");
			sessions.setAttribute("login_type", "qsQuestionnaire");
			return "maqun/maqun_login";
		}
	}
		
	//会员注册
	@RequestMapping(value = "/memberRegister")
	public String memberRegister(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		String shequId = requestMap.get("shequId");//社区ID
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;
			}
		}
		request.setAttribute("streetId", streetId);
		String login_type = requestMap.get("login_type");
		if(StringUtils.isBlank(login_type)){
			login_type = StringUtil.nvl(sessions.getAttribute("login_type"));
		}
		HttpSession memberSessions = request.getSession();
		try {
			String license  = request.getParameter("license");//阅读协议
			String account = requestMap.get("registerMobile");//用户名/手机号码
			String password = requestMap.get("registerPassword");//登录密码
			String phone = requestMap.get("registerMobile");//用户名/手机号码
			if("agree".equals(license)){
				if(StringUtils.isBlank(shequId)){
					shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;//社区ID
					if("101".equals(streetId)){
						shequId = SystemConstant.DONGTAI_SHEQU_JZFZ_ID;
					}
				}
				HashMap<String, String> paramsRegisterMap = new HashMap<String, String>();
				paramsRegisterMap.put("account", account);
				paramsRegisterMap.put("password", password);
				paramsRegisterMap.put("phone", phone);
				paramsRegisterMap.put("name", "");//昵称
				paramsRegisterMap.put("imagepaths", "");//头像
				paramsRegisterMap.put("gender", "");//性别 ：0 男、1 女
				paramsRegisterMap.put("city", "");//城市
				paramsRegisterMap.put("source", "weixin");//注册来源  "android、ios、pc、weixin、other"
				paramsRegisterMap.put("shequId", shequId);//社区ID
				paramsRegisterMap.put("thirdParty", "0");//第三方快捷登录，0普通登录1微信快捷登录2QQ快捷登录
				
				Map<String, Object> memberRegisterMap = memberService.memberRegister(paramsRegisterMap);
				if(memberRegisterMap!=null){
					memberSessions.setAttribute("memberSession", memberRegisterMap);
					request.setAttribute("memberMap", memberRegisterMap);
					if("qsQuestionnaire".equals(login_type)){
						return "redirect:/qsQuestionnaire/init.htm?streetId="+streetId;
					}else{
						return "maqun/maqun_baseMemberInfo";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "maqun/maqun_register";
	}

	//找回密码
	@RequestMapping(value = "/findPassword")
	public String findPassword(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		if(StringUtils.isBlank(shequId)){
			shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;//社区ID
		}
		request.setAttribute("shequId", shequId);
		sessions.setAttribute("shequId", shequId);
		return "maqun/maqun_find_password";
	}
		
	//重设密码
	@RequestMapping(value = "/resPassword")
	public String resPassword(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String mobile = requestMap.get("mobile");
		request.setAttribute("mobile", mobile);
		return "maqun/maqun_res_password";
	}
	
	//注册验证手机号
	@RequestMapping(value = "/checkMobile")
	public void checkMobile(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String result = "0";
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;
			}
		}
		try {
			String shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;//社区ID
			if("101".equals(streetId)){
				shequId = SystemConstant.DONGTAI_SHEQU_JZFZ_ID;
			}
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("mobile", requestMap.get("registerMobile"));
			paramsMap.put("shequId", shequId);
			
			Map<String, Object> memberMap = memberService.getMemberListByMobile(paramsMap);
			if(memberMap!=null&&"0".equals(memberMap.get("success"))){
				result = "1";
			}
		} catch (OtherException e) {
			//e.printStackTrace();
		}
		ajaxJson(result,response);
	}
	
	//重置密码
	@RequestMapping(value = "/saveResPassword")
	public void saveResPassword(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String result = "0"; 
		HttpSession sessions = request.getSession();
		String streetId = requestMap.get("streetId");
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;
			}
		}
		String shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;//社区ID
		if("101".equals(streetId)){
			shequId = SystemConstant.DONGTAI_SHEQU_JZFZ_ID;
		}
		try {
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("shequId", shequId);
			paramsMap.put("mobile", requestMap.get("mobile"));
			paramsMap.put("password", requestMap.get("newPassword"));
			paramsMap.put("account", requestMap.get("account"));
			paramsMap.put("oldPassword", requestMap.get("oldPassword"));
			int temp = memberService.resPassword(paramsMap);
			if(temp==1){
				result = "1";
			}else if(temp==2){
				result = "2";
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		ajaxJson(result,response);
	}
	
}
