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
import com.gw.zhsq.web.service.ESSCService;

/**
 * 智慧社区微信社区二手市场
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/wsh/essc/getESSC.htm?shequId=202
 */
@Controller
@RequestMapping("/wsh/essc")
public class WshEsscController extends BaseController {
	
	@Resource
	private ESSCService esscService;
	
	//获取二手市场
	@RequestMapping(value = "/getESSC")
	public String getESSC(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		//获取报修列表
		requestMap.put("memberId", "");
		Map<String, Object> esscMap=null;
		try {
			esscMap = esscService.getESSCListMap(requestMap);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> esscList = (List<Map<String, Object>>) esscMap.get("result");
			if(esscList!=null&&esscList.size()>0){
				for(Map<String,Object> map : esscList){
					String title = (String) map.get("title");
					if(title.length() > 10){
						title = title.substring(0,10)+"...";
					}
					map.put("title", title);
				}
			}
			request.setAttribute("esscList", esscList);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/wsh/essc/wsh_essc_list";
	}
	
	//获取二手市场预约列表
	@RequestMapping(value = "/getESSCYuYue")
	public String getESSCYuYue(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
			request.setAttribute("login_type", "essc");
			sessions.setAttribute("login_type", "essc");
//			return "login";
			return "redirect:/member/login.htm?login_type=essc";
		}else{
			request.setAttribute("memberMap", memberMap);
		
//		String appid = SystemConstant.APPID;//微信APPID
		String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
		String weixin_rztj_url = SystemConstant.WEIXIN_ESSC_YUYUE_URL+"?shequId="+shequId;//微信签名url地址
		try{
			String signature = "";
			//获取access_token
			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
			JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
			String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
			if(StringUtils.isNotBlank(access_token)){
				//获取jsapi_ticket
		    	String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		    	JSONObject jsapi_ticket_obj = JSONObject.fromObject(SystemConstant.getURLString(jsapi_ticket_url, null));
				String jsapi_ticket = StringUtil.nvl(jsapi_ticket_obj.get("ticket"));
				if(StringUtils.isNotBlank(jsapi_ticket)){
					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_rztj_url;
					System.out.println("string1==>"+string1);
					//生成签名
			    	signature = SHA1.encode(string1);
				}
				System.out.println("appId==>"+appId);
				System.out.println("signature==>"+signature);
		    	System.out.println("access_token==>"+access_token);
		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
		    	System.out.println("weixin_rztj_url==>"+weixin_rztj_url);
		    	
		    	request.setAttribute("appId", appId);
		    	request.setAttribute("signature", signature);
		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
		    	request.setAttribute("access_token", access_token);
		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
				request.setAttribute("weixin_timestamp", weixin_timestamp);
				request.setAttribute("weixin_rztj_url", weixin_rztj_url);
			}
			
			
//		String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
//		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
//		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
//		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
//		String weixin_essc_yuyue_url = SystemConstant.WEIXIN_ESSC_YUYUE_URL+"?shequId="+shequId;//微信签名url地址
//		try{
//			String signature = "";
//			//获取access_token
//			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
//			JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
//			String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
//			if(StringUtils.isNotBlank(access_token)){
//				//获取jsapi_ticket
//		    	String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
//		    	JSONObject jsapi_ticket_obj = JSONObject.fromObject(SystemConstant.getURLString(jsapi_ticket_url, null));
//				String jsapi_ticket = StringUtil.nvl(jsapi_ticket_obj.get("ticket"));
//				if(StringUtils.isNotBlank(jsapi_ticket)){
//					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_essc_yuyue_url;
//					System.out.println("string1==>"+string1);
//					//生成签名
//			    	signature = SHA1.encode(string1);
//				}
//		    	System.out.println("access_token==>"+access_token);
//		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
//		    	System.out.println("signature==>"+signature);
//		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
//		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
//		    	System.out.println("weixin_essc_yuyue_url==>"+weixin_essc_yuyue_url);
//		    	
//		    	request.setAttribute("appId", appId);
//		    	request.setAttribute("signature", signature);
//		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
//		    	request.setAttribute("access_token", access_token);
//		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
//				request.setAttribute("weixin_timestamp", weixin_timestamp);
//				request.setAttribute("weixin_essc_yuyue_url", weixin_essc_yuyue_url);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return "/wsh/essc/wsh_essc_fabu";
	}
	
	
	//获取二手市场详情
	@RequestMapping(value = "/getESSCInfo")
	public String getESSCInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> esscInfoMap = esscService.getESSCInfoMap(requestMap);
			request.setAttribute("esscInfoMap", esscInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wsh/essc/wsh_essc_info";
	}
	
	//新增二手市场
	@RequestMapping(value = "/subESSC")
	public String subESSC(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		String name = requestMap.get("name");//联系人姓名
		String phone = requestMap.get("phone");//联系人手机
		String content = requestMap.get("content");//说明
		String memberId = requestMap.get("memberId");//会员ID
		String title = requestMap.get("title");//商品主题
		String address = requestMap.get("address");//详细地址
		String price = requestMap.get("price");//商品价格
		String atAccount = requestMap.get("memberName");//发布人姓名
		String imagepaths = requestMap.get("imagepaths");//商品图片
		
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("name", name);//反馈人
		paramMap.put("phone", phone);//反馈人手机号
		paramMap.put("content", content);//内容
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("title", title);//商品主题
		paramMap.put("address", address);//修复状态
		paramMap.put("price", price);//商品价格
		paramMap.put("atAccount", atAccount);//发布人姓名
		paramMap.put("imagepaths", imagepaths);//商品图片
		
		String toPage = "";
		try {
			Map<String, Object> esscMap = esscService.subESSCMap(paramMap);
			String success = StringUtil.nvl(esscMap.get("success"));
			if("0".equals(success)){
				toPage = "/wsh/essc/wsh_essc_succe";
			}else{
				toPage = "/wsh/essc/wsh_essc_erro";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toPage;
	}
	
	//会员中心获取二手市场列表
	@RequestMapping(value = "/getESSCList")
	public String getESSCList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> esscMap = null;
		HttpSession memberSessions = request.getSession();
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
			Map<String, Object> memberSessionMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "essc");
				sessions.setAttribute("login_type", "essc");
//				return "login";
				return "redirect:/member/login.htm?login_type=essc";
			}else{
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
				esscMap = esscService.getESSCListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> esscList = (List<Map<String, Object>>) esscMap.get("result");
				if(esscList!=null&&esscList.size()>0){
					for(Map<String,Object> map : esscList){
						String title = (String) map.get("title");
						if(title.length() > 10){
							title = title.substring(0,10)+"...";
						}
						map.put("title", title);
					}
				}
				
				request.setAttribute("esscList", esscList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/essc_list";
	}
	
	//异步取得二手市场列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getESSCListAsync")
	@ResponseBody
	public void getESSCListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> newsMap = esscService.getESSCListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if(newsList!=null&&newsList.size()>0){
				for(Map<String,Object> map : newsList){
					String title = (String) map.get("title");
					if(title.length() > 10){
						title = title.substring(0,10)+"...";
					}
					map.put("title", title);
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
