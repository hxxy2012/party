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
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gw.base.constant.SystemConstant;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.GZBXService;

/**
 * 智慧社区微信社区故障报修
 * @author hanxu
 *	2015-10-22
 *	http://localhost:8080/Weixin/wfw/gzbx/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/gzbx")
public class WfwGzbxController extends BaseController {
	
	@Resource
	private GZBXService gzbxService;
	
	//获取故障报修分类
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
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("type", "1");
		paramMap.put("shequId", shequId);
		//获取报修列表
		requestMap.put("memberId", "");
		Map<String, Object> gzbxMap=null;
		Map<String, Object> shequMap=null;
		try {
			shequMap = gzbxService.getShequInfoMap(paramMap);
			gzbxMap = gzbxService.getGZBXListMap(requestMap);
			List<Map<String,Object>> gzbxList = (List<Map<String, Object>>) gzbxMap.get("result");
			if(gzbxList!=null&&gzbxList.size()>0){
				for(Map<String,Object> map : gzbxList){
					String content = (String) map.get("content");
					if(content.length() > 30){
						content = content.substring(0,30)+"...";
					}
					map.put("content", content);
				}
			}
			request.setAttribute("gzbxList", gzbxList);
			request.setAttribute("shequMap", shequMap);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/wfw/gzbx/wfw_gzbx";
	}
	
	//获取故障报修预约列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getGZBXYuYue")
	public String getGZBXYuYue(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		//获取会员登录信息
		HttpSession memberSessions = request.getSession();
		Map<String, Object> memberMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
		request.setAttribute("memberMap", memberMap);
		return "/wfw/gzbx/wfw_gzbx_yuyue";
	}
	
	//获取故障报修详情
	@RequestMapping(value = "/getGZBXInfo")
	public String getGZBXInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
			Map<String, Object> gzbxInfoMap = gzbxService.getGZBXInfoMap(requestMap);
			request.setAttribute("gzbxInfoMap", gzbxInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wfw/gzbx/wfw_gzbx_info";
	}
	
	//新增故障报修
	@RequestMapping(value = "/subGZBX")
	public String subGZBX(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String name = requestMap.get("name");//姓名
		String phone = requestMap.get("phone");//手机
		String content = requestMap.get("content");//说明
		String memberId = requestMap.get("memberId");//会员ID
		String yuyueTime = requestMap.get("yuyueTime");//预约时间
		String address = requestMap.get("address");//详细地址
		
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("name", name);//反馈人
		paramMap.put("phone", phone);//反馈人手机号
		paramMap.put("content", content);//内容
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("yuyueTime", yuyueTime);//预约时间
		paramMap.put("address", address);//修复状态
		paramMap.put("repair", "4");//修复状态
		
		String toPage = "";
		try {
			Map<String, Object> gzbxMap = gzbxService.subGZBXMap(paramMap);
			String success = StringUtil.nvl(gzbxMap.get("success"));
			if("0".equals(success)){
				toPage = "/wfw/gzbx/wfw_gzbx_succe";
			}else{
				toPage = "/wfw/gzbx/wfw_gzbx_erro";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toPage;
	}
	
	
	//获取故障报修列表
	@RequestMapping(value = "/getGZBXList")
	public String getGZBXList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> gzbxMap = null;
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
				request.setAttribute("login_type", "gzbxList");
				sessions.setAttribute("login_type", "gzbxList");
//				return "login";
				return "redirect:/member/login.htm";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				gzbxMap = gzbxService.getGZBXListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> gzbxList = (List<Map<String, Object>>) gzbxMap.get("result");
				if(gzbxList!=null&&gzbxList.size()>0){
					for(Map<String,Object> map : gzbxList){
						String content = (String) map.get("content");
						if(content.length() > 30){
							content = content.substring(0,30)+"...";
						}
						map.put("content", content);
					}
				}
				request.setAttribute("gzbxList", gzbxList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/gzbx_list";
	}
	
	//异步取得故障报修列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getGZBXListAsync")
	@ResponseBody
	public void getGZBXListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> newsMap = gzbxService.getGZBXListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if(newsList!=null&&newsList.size()>0){
				for(Map<String,Object> map : newsList){
					String content = (String) map.get("content");
					if(content.length() > 30){
						content = content.substring(0,30)+"...";
					}
					map.put("content", content);
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

