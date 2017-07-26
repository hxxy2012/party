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
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.StreetQuestionService;

/**
 * 智慧社区微信社区街道问卷调查
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/street/streetQuestion/getStreetQuestion.htm?shequId=202
 */
@Controller
@RequestMapping("/street/streetQuestion")
public class WfwStreetQuestionSurveyController extends BaseController {
	
	@Resource
	private StreetQuestionService streetQuestionService;
	
	//获取街道问卷调查
	@RequestMapping(value = "/getStreetQuestion")
	public String getStreetQuestion(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");//社区ID
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
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
		
		
		//获取会员登录信息
//		HttpSession memberSessions = request.getSession();
//		@SuppressWarnings("unchecked")
//		Map<String, Object> memberMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
		//获取街道问卷调查列表
		requestMap.put("memberId", "");
		Map<String, Object> streetQuestionMap=null;
		try {
			System.out.println("requestMap======>>"+requestMap);
			streetQuestionMap = streetQuestionService.getStreetQuestionListMap(requestMap);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> streetQuestionList = (List<Map<String, Object>>) streetQuestionMap.get("result");
			if(streetQuestionList!=null&&streetQuestionList.size()>0){
				for(Map<String,Object> map : streetQuestionList){
					String detail = (String) map.get("detail");
					if(detail.length() > 10){
						detail = detail.substring(0,10)+"...";
					}
					map.put("detail", detail);
				}
			}
			request.setAttribute("streetQuestionList", streetQuestionList);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/street/streetQuestion/streetQuestion_list";
	}
	
	
	//获取二手市场预约列表
//	@RequestMapping(value = "/getESSCYuYue")
//	public String getESSCYuYue(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, String> requestMap = findRequestMap(request);
//		String shequId = requestMap.get("shequId");//社区ID
//		HttpSession shequSessions = request.getSession();
//		if(StringUtils.isBlank(shequId)){
//			shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
//			if(StringUtils.isBlank(shequId)){
//				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
//			}
//		}
//		requestMap.put("shequId", shequId);
//		request.setAttribute("shequId", shequId);
//		shequSessions.setAttribute("shequId", shequId);
//		
//		//获取会员登录信息
//		HttpSession memberSessions = request.getSession();
//		@SuppressWarnings("unchecked")
//		Map<String, Object> memberMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
//		request.setAttribute("memberMap", memberMap);
//		
////		String appid = SystemConstant.APPID;//微信APPID
//		String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
//		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
//		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
//		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
//		String weixin_rztj_url = SystemConstant.WEIXIN_ESSC_YUYUE_URL+"?shequId="+shequId;//微信签名url地址
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
//					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_rztj_url;
//					System.out.println("string1==>"+string1);
//					//生成签名
//			    	signature = SHA1.encode(string1);
//				}
//				System.out.println("appId==>"+appId);
//				System.out.println("signature==>"+signature);
//		    	System.out.println("access_token==>"+access_token);
//		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
//		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
//		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
//		    	System.out.println("weixin_rztj_url==>"+weixin_rztj_url);
//		    	
//		    	request.setAttribute("appId", appId);
//		    	request.setAttribute("signature", signature);
//		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
//		    	request.setAttribute("access_token", access_token);
//		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
//				request.setAttribute("weixin_timestamp", weixin_timestamp);
//				request.setAttribute("weixin_rztj_url", weixin_rztj_url);
//			}
//			
//			
////		String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
////		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
////		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
////		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
////		String weixin_essc_yuyue_url = SystemConstant.WEIXIN_ESSC_YUYUE_URL+"?shequId="+shequId;//微信签名url地址
////		try{
////			String signature = "";
////			//获取access_token
////			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
////			JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
////			String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
////			if(StringUtils.isNotBlank(access_token)){
////				//获取jsapi_ticket
////		    	String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
////		    	JSONObject jsapi_ticket_obj = JSONObject.fromObject(SystemConstant.getURLString(jsapi_ticket_url, null));
////				String jsapi_ticket = StringUtil.nvl(jsapi_ticket_obj.get("ticket"));
////				if(StringUtils.isNotBlank(jsapi_ticket)){
////					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_essc_yuyue_url;
////					System.out.println("string1==>"+string1);
////					//生成签名
////			    	signature = SHA1.encode(string1);
////				}
////		    	System.out.println("access_token==>"+access_token);
////		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
////		    	System.out.println("signature==>"+signature);
////		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
////		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
////		    	System.out.println("weixin_essc_yuyue_url==>"+weixin_essc_yuyue_url);
////		    	
////		    	request.setAttribute("appId", appId);
////		    	request.setAttribute("signature", signature);
////		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
////		    	request.setAttribute("access_token", access_token);
////		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
////				request.setAttribute("weixin_timestamp", weixin_timestamp);
////				request.setAttribute("weixin_essc_yuyue_url", weixin_essc_yuyue_url);
////			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return "/wsh/essc/wsh_essc_fabu";
//	}
//	
//	
	//获取街道问卷调查题目以及选项
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getStreetQuestionInfo")
	public String getStreetQuestionInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
//		String shequId = requestMap.get("shequId");//社区ID
//		HttpSession shequSessions = request.getSession();
//		if(StringUtils.isBlank(shequId)){
//			shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
//			if(StringUtils.isBlank(shequId)){
//				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
//			}
//		}
//		requestMap.put("shequId", shequId);
//		request.setAttribute("shequId", shequId);
//		shequSessions.setAttribute("shequId", shequId);
		
		String temp = requestMap.get("temp");
		String jspStr = "";
		try {
			requestMap.remove("shequId");
			System.out.println("requestMap===getStreetQuestionInfo===="+requestMap);
			Map<String, Object> streetQuestionInfoMap = streetQuestionService.getStreetQuestionInfoMap(requestMap);
			request.setAttribute("streetQuestionInfoMap", streetQuestionInfoMap);
			if(StringUtil.isNotNull(temp)&&temp.equals("1")){
//				String success = requestMap.get("success");
//				request.setAttribute("success", success);
				//获取会员登录信息
				HttpSession sessions = request.getSession();
				Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
				if(memberMap==null){
					request.setAttribute("login_type", "streetQuest");
					sessions.setAttribute("login_type", "streetQuest");
					request.setAttribute("qs_id", streetQuestionInfoMap.get("qs_id"));
//					return "login";
					return "redirect:/member/login.htm";
				}else{
					request.setAttribute("memberMap", memberMap);
//					BigDecimal memberId =  (BigDecimal) memberMap.get("id");
					requestMap.put("memberId", memberMap.get("id").toString());
					Map<String, Object> resultInfoMap = streetQuestionService.getResultInfoMap(requestMap);
					
					List<Map<String,Object>> resultInfoList = (List<Map<String, Object>>) resultInfoMap.get("data");
					System.out.println("resultInfoList====>>"+resultInfoList);
					if(resultInfoList!=null&&resultInfoList.size()>0){
						request.setAttribute("resultType", "1");//该会员已做过此问卷调查
					}else{
						request.setAttribute("resultType", "0");//该会员没做过此问卷调查
					}
					
					jspStr = "/street/streetQuestion/streetQuestion_wenda";
				}
				
			}else{
				jspStr = "/street/streetQuestion/streetQuestion_info";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jspStr;
	}
	
	//提交街道问卷调查题目以及选项
	@RequestMapping(value = "/subStreetQuestion")
	public String subStreetQuestion(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String toPage = "";
		try {
			System.out.println("requestMap====subStreetQuestion=====>>>"+requestMap);
			String qs_id = requestMap.get("qs_id");
			Map<String, Object> streetQuestionMap = streetQuestionService.subStreetQuestionMap(requestMap);
			String success = StringUtil.nvl(streetQuestionMap.get("success"));
			request.setAttribute("qs_id", qs_id);
			request.setAttribute("success", success);
			if("0".equals(success)){
				toPage = "/street/streetQuestion/streetQuestion_succe";
			}else{
				toPage = "/street/streetQuestion/streetQuestion_erro";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toPage;
	}
//	
//	//会员中心获取二手市场列表
//	@RequestMapping(value = "/getESSCList")
//	public String getESSCList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, String> requestMap = findRequestMap(request);
//		Map<String, Object> esscMap = null;
//		HttpSession memberSessions = request.getSession();
//		try {
//			String shequId = requestMap.get("shequId");//社区ID
//			HttpSession shequSessions = request.getSession();
//			if(StringUtils.isBlank(shequId)){
//				shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
//				if(StringUtils.isBlank(shequId)){
//					shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
//				}
//			}
//			requestMap.put("shequId", shequId);
//			request.setAttribute("shequId", shequId);
//			shequSessions.setAttribute("shequId", shequId);
//			
//			@SuppressWarnings("unchecked")
//			Map<String, Object> memberSessionMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
//			if(memberSessionMap==null){
//				request.setAttribute("login_type", "essc");
//				return "login";
//				return "redirect:/member/login.htm";
//			}else{
//				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));
//				esscMap = esscService.getESSCListMap(requestMap);
//				@SuppressWarnings("unchecked")
//				List<Map<String,Object>> esscList = (List<Map<String, Object>>) esscMap.get("result");
//				if(esscList!=null&&esscList.size()>0){
//					for(Map<String,Object> map : esscList){
//						String title = (String) map.get("title");
//						if(title.length() > 10){
//							title = title.substring(0,10)+"...";
//						}
//						map.put("title", title);
//					}
//				}
//				
//				request.setAttribute("esscList", esscList);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "/member/essc_list";
//	}
//	
//	//异步取得二手市场列表
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/getESSCListAsync")
//	@ResponseBody
//	public void getESSCListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, String> requestMap = findRequestMap(request);
//		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
//		try {
//			Map<String, Object> newsMap = esscService.getESSCListMap(requestMap);
//			newsList = (List<Map<String, Object>>) newsMap.get("result");
//			if(newsList!=null&&newsList.size()>0){
//				for(Map<String,Object> map : newsList){
//					String title = (String) map.get("title");
//					if(title.length() > 10){
//						title = title.substring(0,10)+"...";
//					}
//					map.put("title", title);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		PrintWriter out;
//		try {
//			response.setContentType("text/json;charset=UTF-8");
//			Map map =new HashMap();
//			map.put("dataList",newsList);
//			map.put("size",(newsList==null)?0:newsList.size());
//			JSONObject obj = JSONObject.fromObject(map);
//			out = response.getWriter();
//			out.write(obj.toString());
//			out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}		
	
	
}
