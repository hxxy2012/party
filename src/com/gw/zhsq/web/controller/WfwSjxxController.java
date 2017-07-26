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
import com.gw.zhsq.web.service.SJXXService;

/**
 * 智慧社区微信书记信箱
 * @author hanxu
 *	2015-10-26
 * http://localhost:8080/Weixin/wfw/sjxx/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/sjxx")
public class WfwSjxxController extends BaseController {
	
	@Resource
	private SJXXService sjxxService;
	@Resource
	private GZBXService gzbxService;
	
	/**
	 * 初始化书记信箱
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String getSJXX(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		HttpSession sessions = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "sjxx");
			sessions.setAttribute("login_type", "sjxx");
//			return "login";
			return "redirect:/member/login.htm?login_type=sjxx";
		}else{
			request.setAttribute("memberMap", memberMap);
			
			//获取社区信息（书记信箱）
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("type", "2");
			paramMap.put("shequId", shequId);
			Map<String, Object> shequMap = null;
			try {
				shequMap = gzbxService.getShequInfoMap(paramMap);
			} catch (OtherException e) {
				e.printStackTrace();
			}
			request.setAttribute("shequMap", shequMap);
		}
		return "/wfw/sjxx/wfw_sjxx";
	}
	
	/**
	 * 新增书记信箱反馈
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/subSJXX")
	public String subSJXX(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		String token = requestMap.get("token");
		String session_token = StringUtil.nvl(session.getAttribute("token"));
		String toPage = "";
		if(token.equals(session_token)){
			session.removeAttribute("token");
			HashMap<String, String> paramMap = new HashMap<String, String>();
			String name2 = requestMap.get("name");//姓名
			String phone = requestMap.get("phone");//手机
			String content2 = requestMap.get("content");//说明
			String memberId = requestMap.get("memberId");//会员ID
			
			paramMap.put("shequId", shequId);//社区ID
			paramMap.put("name", name2);//写信人
			paramMap.put("phone", phone);//写信人手机号
			paramMap.put("content", content2);//说明
			paramMap.put("memberId", memberId);//会员ID
			try {
				Map<String, Object> sjxxMap = sjxxService.subSJXXMap(paramMap);
				String success = StringUtil.nvl(sjxxMap.get("success"));
				if("0".equals(success)){
					toPage = "/wfw/sjxx/wfw_sjxx_succe";
				}else{
					toPage = "/wfw/sjxx/wfw_sjxx_erro";
				}
			} catch (OtherException e) {
				e.printStackTrace();
			}
		}else{
			toPage = "/wfw/sjxx/wfw_sjxx_erro";
		}
		return toPage;
	}
	
	/**
	 * 会员中心获取书记信箱列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSJXXList")
	public String getSJXXList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> sjxxMap = null;
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
				request.setAttribute("login_type", "sjxx");
				sessions.setAttribute("login_type", "sjxx");
//				return "login";
				return "redirect:/member/login.htm";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				sjxxMap = sjxxService.getSJXXListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> sjxxList = (List<Map<String, Object>>) sjxxMap.get("result");
				if(sjxxList!=null&&sjxxList.size()>0){
					for(Map<String,Object> map : sjxxList){
						String content = (String) map.get("content");
						if(content.length() > 10){
							content = content.substring(0,10)+"...";
						}
						map.put("content", content);
					}
				}
				request.setAttribute("sjxxList", sjxxList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/sjxx_list";
	}
	
	/**
	 * 异步取得书记信箱列表
	 * @param model
	 * @param request
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getSJXXListAsync")
	@ResponseBody
	public void getSJXXListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> newsMap = sjxxService.getSJXXListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if(newsList!=null&&newsList.size()>0){
				for(Map<String,Object> map : newsList){
					String content = (String) map.get("content");
					if(content.length() > 10){
						content = content.substring(0,10)+"...";
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
	
	/**
	 * 获取书记信箱详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSJXXInfo")
	public String getSJXXInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> sjxxInfoMap = sjxxService.getSJXXInfoMap(requestMap);
			request.setAttribute("sjxxInfoMap", sjxxInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wfw/sjxx/wfw_sjxx_info";
	}
	
	
	/**
	 * 提交书记信箱评价
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/subSatisfact")
	public String subSatisfact(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String sjxxId = requestMap.get("sjxxId");//书记信箱ID
		String satisfactType = requestMap.get("satisfactType");//满意度（1非常满意2满意3不满意）
		String satisfactContent = requestMap.get("satisfactContent");//满意意见
		
		paramMap.put("sjxxId", sjxxId);
		paramMap.put("satisfactType", satisfactType);
		paramMap.put("satisfactContent", satisfactContent);
		String toPage = "";
		try {
			Map<String, Object> sjxxMap = sjxxService.subSatisfactMap(paramMap);
			String success = StringUtil.nvl(sjxxMap.get("success"));
			request.setAttribute("sjxxId", sjxxId);
			if("0".equals(success)){
				toPage = "/wfw/sjxx/wfw_sjxx_satisfact_succe";
			}else{
				toPage = "/wfw/sjxx/wfw_sjxx_satisfact_erro";
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return toPage;
	}
		
}
