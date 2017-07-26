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
import org.gw.weixin.SHA1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gw.base.constant.SystemConstant;
import com.gw.base.exception.OtherException;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.GZBXService;
import com.gw.zhsq.web.service.JBTSService;

/**
 * 智慧社区微信意见反馈
 * @author hanxu
 *	2015-10-26
 * http://localhost:8080/Weixin/wfw/jbts/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/jbts")
public class WfwJbtsController extends BaseController {
	
	@Resource
	private JBTSService jbtsService;
	@Resource
	private GZBXService gzbxService;
	
	//初始化意见反馈
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String getJBTS(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		String streetId = requestMap.get("streetId");//街道ID
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_ID;//默认为东台街道
			}
			if("22".equals(streetId)){
				if(StringUtils.isBlank(shequId)){
					shequId = SystemConstant.DONGTAI_SHEQU_ID;
				}
				request.setAttribute("shequId", shequId);
				sessions.setAttribute("shequId", shequId);
			}
		}
		requestMap.put("streetId", streetId);
		request.setAttribute("streetId", streetId);
		sessions.setAttribute("streetId", streetId);
//		//社区ID
//		String shequId = requestMap.get("shequId");
//		HttpSession shequSessions = request.getSession();
//		if(StringUtils.isBlank(shequId)){
//			shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
//		}
//		requestMap.put("shequId", shequId);
//		shequSessions.setAttribute("shequId", shequId);
		
		//获取会员登录信息
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "jbts");
			sessions.setAttribute("login_type", "jbts");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			request.setAttribute("memberMap", memberMap);
			//获取社区信息（意见反馈）
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("type", "3");
			paramMap.put("shequId", shequId);
			Map<String, Object> shequMap = null;
			try {
				shequMap = gzbxService.getShequInfoMap(paramMap);
			} catch (OtherException e) {
				e.printStackTrace();
			}
			request.setAttribute("shequMap", shequMap);
		}
		
		
//		String appid = SystemConstant.APPID;//微信APPID
		String appid = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
		String weixin_jbts_url = SystemConstant.WEIXIN_JBTS_URL+"?shequId="+shequId;//微信签名url地址
		try{
			String signature = "";
			//获取access_token
			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
			JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
			String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
			if(StringUtils.isNotBlank(access_token)){
				//获取jsapi_ticket
		    	String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		    	JSONObject jsapi_ticket_obj = JSONObject.fromObject(SystemConstant.getURLString(jsapi_ticket_url, null));
				String jsapi_ticket = StringUtil.nvl(jsapi_ticket_obj.get("ticket"));
				if(StringUtils.isNotBlank(jsapi_ticket)){
					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_jbts_url;
					System.out.println("string1==>"+string1);
					//生成签名
			    	signature = SHA1.encode(string1);
				}
				
				System.out.println("appid==>"+appid);
		    	System.out.println("access_token==>"+access_token);
		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
		    	System.out.println("signature==>"+signature);
		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
		    	System.out.println("weixin_jbts_url==>"+weixin_jbts_url);
		    	
		    	request.setAttribute("appid", appid);
		    	request.setAttribute("signature", signature);
		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
		    	request.setAttribute("access_token", access_token);
		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
				request.setAttribute("weixin_timestamp", weixin_timestamp);
				request.setAttribute("weixin_jbts_url", weixin_jbts_url);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "/wfw/jbts/wfw_jbts";
	}
	
	//新增意见反馈
	@RequestMapping(value = "/subJBTS")
	public String subJBTS(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String name = requestMap.get("name");//姓名
		String phone = requestMap.get("phone");//手机
		String content = requestMap.get("content");//说明
		String memberId = requestMap.get("memberId");//会员ID
		String atAccount = requestMap.get("memberName");//发布人姓名
		String address = requestMap.get("address");//意见反馈所在地址
		String latitude = requestMap.get("latitude");//纬度
		String longitude = requestMap.get("longitude");//经度
		String imagepaths = "";//图片
		String jbtsImg1 = requestMap.get("jbtsImg1");//图片
		String jbtsImg2 = requestMap.get("jbtsImg2");//图片
		String jbtsImg3 = requestMap.get("jbtsImg3");//图片
		String jbtsImg4 = requestMap.get("jbtsImg4");//图片
		String jbtsImg5 = requestMap.get("jbtsImg5");//图片
		String jbtsImg6 = requestMap.get("jbtsImg6");//图片
		String jbtsImg7 = requestMap.get("jbtsImg7");//图片
		String jbtsImg8 = requestMap.get("jbtsImg8");//图片
		String jbtsImg9 = requestMap.get("jbtsImg9");//图片
		
		if(StringUtils.isNotBlank(jbtsImg1)){
			imagepaths = imagepaths+jbtsImg1+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg2)){
			imagepaths = imagepaths+jbtsImg2+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg3)){
			imagepaths = imagepaths+jbtsImg3+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg4)){
			imagepaths = imagepaths+jbtsImg4+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg5)){
			imagepaths = imagepaths+jbtsImg5+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg6)){
			imagepaths = imagepaths+jbtsImg6+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg7)){
			imagepaths = imagepaths+jbtsImg7+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg8)){
			imagepaths = imagepaths+jbtsImg8+"_";
		}
		if(StringUtils.isNotBlank(jbtsImg9)){
			imagepaths = imagepaths+jbtsImg9+"_";
		}
		
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("name", name);//反馈人
		paramMap.put("phone", phone);//反馈人手机号
		paramMap.put("content", content);//内容
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("atAccount", atAccount);//发布人姓名
		paramMap.put("address", address);//意见反馈所在地址
		paramMap.put("imagepaths", imagepaths);//意见反馈图片
		paramMap.put("latitude", latitude);//纬度
		paramMap.put("longitude", longitude);//经度
		
		String toPage = "";
		String token = requestMap.get("token");
		String session_token = StringUtil.nvl(session.getAttribute("token"));
		if(token.equals(session_token)){
			session.removeAttribute("token");
			try {
				Map<String, Object> jbtsMap = jbtsService.subJBTSMap(paramMap);
				String success = StringUtil.nvl(jbtsMap.get("success"));
				if("0".equals(success)){
					toPage = "/wfw/jbts/wfw_jbts_succe";
				}else{
					toPage = "/wfw/jbts/wfw_jbts_erro";
				}
			} catch (OtherException e) {
				e.printStackTrace();
			}
		}else{
			toPage = "/wfw/jbts/wfw_jbts_erro";
		}
		return toPage;
	}
	
	//获取意见反馈详情
	@RequestMapping(value = "/getJBTSInfo")
	public String getJBTSInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> jbtsInfoMap = jbtsService.getJBTSInfoMap(requestMap);
			request.setAttribute("jbtsInfoMap", jbtsInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wfw/jbts/wfw_jbts_info";
	}
	
	//获取意见反馈列表
	@RequestMapping(value = "/getJBTSList")
	public String getJBTSList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> jbtsMap = null;
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
				request.setAttribute("login_type", "jbtsList");
				sessions.setAttribute("login_type", "jbtsList");
				return "login";
				//return "redirect:/member/login.htm";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				jbtsMap = jbtsService.getJBTSListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> jbtsList = (List<Map<String, Object>>) jbtsMap.get("result");
				if(jbtsList!=null&&jbtsList.size()>0){
					for(Map<String,Object> map : jbtsList){
						String content = (String) map.get("content");
						if(content.length() > 30){
							content = content.substring(0,30)+"...";
						}
						map.put("content", content);
					}
				}
				request.setAttribute("jbtsList", jbtsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/jbts_list";
	}
	
	//异步取得政务列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getJBTSListAsync")
	@ResponseBody
	public void getJBTSListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
		try {
			
			Map<String, Object> newsMap = jbtsService.getJBTSListMap(requestMap);
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
	
	//提交意见反馈评价
	@RequestMapping(value = "/subSatisfact")
	public String subSatisfact(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String jbtsId = requestMap.get("jbtsId");//意见反馈ID
		String satisfactType = requestMap.get("satisfactType");//满意度（1非常满意2满意3不满意）
		String satisfactContent = requestMap.get("satisfactContent");//满意意见
//		String memberId = requestMap.get("memberId");
		
		paramMap.put("jbtsId", jbtsId);
		paramMap.put("satisfactType", satisfactType);
		paramMap.put("satisfactContent", satisfactContent);
//		paramMap.put("memberId", memberId);
		String toPage = "";
		try {
			Map<String, Object> jbtsMap = jbtsService.subSatisfactMap(paramMap);
			String success = StringUtil.nvl(jbtsMap.get("success"));
			request.setAttribute("jbtsId", jbtsId);
			if("0".equals(success)){
				toPage = "/wfw/jbts/wfw_jbts_satisfact_succe";
			}else{
				toPage = "/wfw/jbts/wfw_jbts_satisfact_erro";
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return toPage;
	}
	
}
