package com.gw.zhsq.web.controller;

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
import com.gw.base.constant.SystemConstant;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.JlhdService;

/**
 * 智慧社区微信交流互动
 * @author hanxu
 *	2015-10-26
 * http://localhost:8080/Weixin/wfw/jlhd/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/jlhd")
public class WfwJlhdController extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private JlhdService jlhdService;
	
	//初始化交流互动
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
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		shequSessions.setAttribute("shequId", shequId);
		
		//获取交流互动列表
		List<Map<String, Object>> jlhdList = null;
		try{
			requestMap.put("page", "1");//当前页数
			requestMap.put("pageSize", "10");//当前条数
			Map<String, Object> jlhdListMap = jlhdService.getJlhdListMap(requestMap);
			if(jlhdListMap!=null && jlhdListMap.get("result")!=null){
				//顶部广告
				HashMap<String, String> paramMaps12 = new HashMap<String, String>();
				paramMaps12.put("bgLocation", "12");
				paramMaps12.put("shequId", shequId);//社区ID
				List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps12);
				String topImg = "";
				if(advTop!=null&&advTop.size()>0){
					Map<String, Object> map = advTop.get(0);
					Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
					topImg = (String) paramMapss.get("imagePaths");
				}
				request.setAttribute("topImg", topImg);
				jlhdList = (List<Map<String, Object>>) jlhdListMap.get("result");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("jlhdList", jlhdList);
		return "/wfw/jlhd/wfw_jlhd_list";
	}
	
	//初始化详情
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/info")
	public String info(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		//获取会员登录信息
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "jlhdDetail");
			sessions.setAttribute("login_type", "jlhdDetail");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			//获取交流互动详情
			Map<String, Object> jlhdMap = null;
			try{
				jlhdMap = jlhdService.getJlhdMap(requestMap);
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("jlhdMap", jlhdMap);
		}
		return "/wfw/jlhd/wfw_jlhd_info";
	}
	
	//初始化提问
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tiwen")
	public String tiwen(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		//获取会员登录信息
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "jlhdTiwen");
			sessions.setAttribute("login_type", "jlhdTiwen");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			return "/wfw/jlhd/wfw_jlhd_tw";
		}
	}
		
	//新增提问
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addTiwen")
	public String addTiwen(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		//判断会员是否登录，未登录跳转至登录界面
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "jlhdAddTiwen");
			sessions.setAttribute("login_type", "jlhdAddTiwen");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			//提交在线交流
			Map<String, Object> jlhdMap = null;
			try{
				String token = requestMap.get("token");
				String session_token = StringUtil.nvl(sessions.getAttribute("token"));
				if(token.equals(session_token)){
					sessions.removeAttribute("token");
					requestMap.remove("token");
					requestMap.put("acId", "1");//咨询分类（交流互动）
					requestMap.put("atStatus", "0");//咨询状态（0新增1已回复2已关闭）
					requestMap.put("source", "weixin");//来源（微信）
					requestMap.put("isPublic", "1");//是否公开(0不公开1公开)
					requestMap.put("atAccount", StringUtil.nvl(memberMap.get("id")));
					jlhdMap = jlhdService.addJlhd(requestMap);
					return "/wfw/jlhd/wfw_jlhd_succe";
				}else{
					return "/wfw/jlhd/wfw_jlhd_erro";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("jlhdMap", jlhdMap);
		}
		return "/wfw/jlhd/wfw_jlhd_tw";
	}
	
	//新增咨询回复
	@SuppressWarnings({ "unchecked"})
	@RequestMapping(value = "/addHuifu")
	public String addHuifu(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		HttpSession session = request.getSession();
		if(StringUtils.isBlank(shequId)){
			shequId = StringUtil.nvl(session.getAttribute("shequId"));
			if(StringUtils.isBlank(shequId)){
				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
			}
		}
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		session.setAttribute("shequId", shequId);
		
		//判断会员是否登录，未登录跳转至登录界面
		Map<String, Object> memberMap = (Map<String, Object>) session.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "jlhdHuifu");
			session.setAttribute("login_type", "jlhdHuifu");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			//提交咨询回复
			Map<String, Object> jlhdMap = null;
			try{
				String token = requestMap.get("token");
				String session_token = StringUtil.nvl(session.getAttribute("token"));
				if(token.equals(session_token)){
					session.removeAttribute("token");
					requestMap.put("atAccount", StringUtil.nvl(memberMap.get("id")));
					requestMap.remove("token");
					jlhdMap = jlhdService.addHuifu(requestMap);
					return "/wfw/jlhd/wfw_jlhd_succe";
				}else{
					return "/wfw/jlhd/wfw_jlhd_erro";
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("jlhdMap", jlhdMap);
		}
		return "/wfw/jlhd/wfw_jlhd_info";
	}
	
	//初始化交流互动
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getJLHDList")
	public String getJLHDList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		Map<String, Object> memberSessionMap = (Map<String, Object>) shequSessions.getAttribute("memberSession");
		
		//获取交流互动列表
		List<Map<String, Object>> jlhdList = null;
		try{
			requestMap.put("page", "1");//当前页数
			requestMap.put("pageSize", "10");//当前条数
			requestMap.put("jlhdType", "2");//列表类型(1,微服务交流互动列表  2,个人中心我的咨询列表)
//			requestMap.put("atAccount", StringUtil.nvl(memberSessionMap.get("account")));
			requestMap.put("atAccount", StringUtil.nvl(memberSessionMap.get("id")));
			Map<String, Object> jlhdListMap = jlhdService.getJlhdListMap(requestMap);
			if(jlhdListMap!=null && jlhdListMap.get("result")!=null){
				jlhdList = (List<Map<String, Object>>) jlhdListMap.get("result");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("jlhdList", jlhdList);
		return "/member/jlhd_list";
	}
	
	//初始化详情
	@RequestMapping(value = "/memberInfo")
	public String memberInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		//获取交流互动详情
		Map<String, Object> jlhdMap = null;
		try{
			jlhdMap = jlhdService.getJlhdMap(requestMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("jlhdMap", jlhdMap);
		return "/member/jlhd_info";
	}
		
}
