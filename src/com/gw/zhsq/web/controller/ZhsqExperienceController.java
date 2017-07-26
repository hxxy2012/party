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
import com.gw.zhsq.web.service.ZhsqExperienceService;

/**
 * 智慧社区微信线下体检
 * @author hanxu
 *	2015-10-26
 * http://localhost:8080/Weixin/zhsq/experience/init.htm?shequId=202
 */
@Controller
@RequestMapping("/zhsq/experience")
public class ZhsqExperienceController extends BaseController {
	
	@Resource
	private ZhsqExperienceService zhsqExperienceService;
	
	
	//获取【线下体检】列表
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
			
			@SuppressWarnings("unchecked")
			Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "experience");
				sessions.setAttribute("login_type", "experience");
//				return "login";
				//return "redirect:/member/login.htm";
				return "redirect:/member/login.htm?login_type=experience";
				
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				//获取最新血压数据
				Map<String, Object> bloodPressMap = zhsqExperienceService.getMemberBloodPressListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> bloodPressList = (List<Map<String, Object>>) bloodPressMap.get("result");
				if(bloodPressList!=null&&bloodPressList.size()>0){
					Map<String,Object> bloodPressMap1 = bloodPressList.get(0);
					request.setAttribute("bloodPressMap", bloodPressMap1);
				}
				//获取最新体重数据
				Map<String, Object> weightMap = zhsqExperienceService.getMemberWeightListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> weightList = (List<Map<String, Object>>) weightMap.get("result");
				if(weightList!=null&&weightList.size()>0){
					Map<String,Object> weightMap1 = weightList.get(0);
					request.setAttribute("weightMap", weightMap1);
				}
				
				//获取最新血氧数据
				Map<String, Object> bloodOxygenMap = zhsqExperienceService.getMemberBloodOxygenListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> bloodOxygenList = (List<Map<String, Object>>) bloodOxygenMap.get("result");
				if(bloodOxygenList!=null&&bloodOxygenList.size()>0){
					Map<String,Object> bloodOxygenMap1 = bloodOxygenList.get(0);
					request.setAttribute("bloodOxygenMap", bloodOxygenMap1);
				}
				//获取最新血糖数据
				Map<String, Object> bloodSugarMap = zhsqExperienceService.getMemberBloodSugarListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> bloodSugarList = (List<Map<String, Object>>) bloodSugarMap.get("result");
				if(bloodSugarList!=null&&bloodSugarList.size()>0){
					Map<String,Object> bloodSugarMap1 = bloodSugarList.get(0);
					request.setAttribute("bloodSugarMap", bloodSugarMap1);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/experience/experience_list";
	}
	
	//获取【线下体检】会员电子血压列表
	@RequestMapping(value = "/getMemberBloodPressList")
	public String getMemberBloodPressList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> bloodPressMap = null;
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
			
			@SuppressWarnings("unchecked")
			Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "bloodPressList");
				sessions.setAttribute("login_type", "bloodPressList");
//				return "login";
				return "redirect:/member/login.htm?login_type=bloodPressList";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				bloodPressMap = zhsqExperienceService.getMemberBloodPressListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> bloodPressList = (List<Map<String, Object>>) bloodPressMap.get("result");
				if(bloodPressList!=null&&bloodPressList.size()>0){
					for(Map<String,Object> map : bloodPressList){
						String mark = (String) map.get("mark");
						if(mark.length() > 30){
							mark = mark.substring(0,30)+"...";
						}
						map.put("mark", mark);
					}
				}
				request.setAttribute("bloodPressList", bloodPressList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/experience/bloodPress_list";
	}
	
	//获取【线下体检】会员电子体重列表
	@RequestMapping(value = "/getMemberWeightList")
	public String getMemberWeightList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> weightMap = null;
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
			
			@SuppressWarnings("unchecked")
			Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "weightList");
				sessions.setAttribute("login_type", "weightList");
//				return "login";
				return "redirect:/member/login.htm?login_type=weightList";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				weightMap = zhsqExperienceService.getMemberWeightListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> weightList = (List<Map<String, Object>>) weightMap.get("result");
				if(weightList!=null&&weightList.size()>0){
					for(Map<String,Object> map : weightList){
						String mark = (String) map.get("mark");
						if(mark.length() > 30){
							mark = mark.substring(0,30)+"...";
						}
						map.put("mark", mark);
					}
				}
				request.setAttribute("weightList", weightList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/experience/weight_list";
	}
	
	//获取【线下体检】会员电子血氧列表
	@RequestMapping(value = "/getMemberBloodOxygenList")
	public String getMemberBloodOxygenList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> bloodOxygenMap = null;
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
			
			@SuppressWarnings("unchecked")
			Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "bloodOxygenList");
				sessions.setAttribute("login_type", "bloodOxygenList");
//				return "login";
				return "redirect:/member/login.htm?login_type=bloodOxygenList";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				bloodOxygenMap = zhsqExperienceService.getMemberBloodOxygenListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> bloodOxygenList = (List<Map<String, Object>>) bloodOxygenMap.get("result");
				if(bloodOxygenList!=null&&bloodOxygenList.size()>0){
					for(Map<String,Object> map : bloodOxygenList){
						String mark = (String) map.get("mark");
						if(mark.length() > 30){
							mark = mark.substring(0,30)+"...";
						}
						map.put("mark", mark);
					}
				}
				request.setAttribute("bloodOxygenList", bloodOxygenList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/experience/bloodOxygen_list";
	}
	
	//获取【线下体检】会员电子血糖列表
	@RequestMapping(value = "/getMemberBloodSugarList")
	public String getMemberBloodSugarList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> bloodSugarMap = null;
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
			
			@SuppressWarnings("unchecked")
			Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "bloodSugarList");
				sessions.setAttribute("login_type", "bloodSugarList");
//				return "login";
				return "redirect:/member/login.htm?login_type=bloodSugarList";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				bloodSugarMap = zhsqExperienceService.getMemberBloodSugarListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> bloodSugarList = (List<Map<String, Object>>) bloodSugarMap.get("result");
				if(bloodSugarList!=null&&bloodSugarList.size()>0){
					for(Map<String,Object> map : bloodSugarList){
						String mark = (String) map.get("mark");
						if(mark.length() > 30){
							mark = mark.substring(0,30)+"...";
						}
						map.put("mark", mark);
					}
				}
				request.setAttribute("bloodSugarList", bloodSugarList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/experience/bloodSugar_list";
	}

	
	
	
}
