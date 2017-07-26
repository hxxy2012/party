package com.gw.zhsq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gw.base.constant.SystemConstant;
import com.gw.base.exception.OtherException;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.QSQuestionnaireService;

/**
 * 【马群问卷】问卷
 * @author fuyun
 *	2016-06-17
 * 请求地址：http://localhost:8080/Weixin/qsQuestionnaire/init.htm?streetId=22
 */
@Controller
@RequestMapping("/qsQuestionnaire")
public class QSQuestionnaireController extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private QSQuestionnaireService qsQuestionnaireService;
	
	//初始化【马群问卷】街道问卷列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
			}
		}
		requestMap.put("streetId", streetId);
		request.setAttribute("streetId", streetId);
		sessions.setAttribute("streetId", streetId);
		
		//获取会员登录信息
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		try {
			if(memberMap==null){
				request.setAttribute("login_type", "qsQuestionnaire");
				sessions.setAttribute("login_type", "qsQuestionnaire");
				return "maqun/maqun_login";
			}
			Map<String, Object> qsQuestionnaireMap = qsQuestionnaireService.getQSQuestionnaireListMap(requestMap);
			List<Map<String,Object>> qsQuestionnaireList = (List<Map<String, Object>>) qsQuestionnaireMap.get("result");
			if(qsQuestionnaireList!=null&&qsQuestionnaireList.size()>0){
				for(Map<String,Object> map : qsQuestionnaireList){
					String detail = (String) map.get("detail");
					if(detail.length() > 10){
						detail = detail.substring(0,10)+"...";
					}
					map.put("detail", detail);
				}
			}
			// 获取幻灯片
		
		    HashMap<String, String> paramMaps = new HashMap<String, String>();
			paramMaps.put("bgLocation", "4");
			paramMaps.put("streetId", streetId);//社区ID
		    
			List<Map<String, Object>> adv = advService.getStreetAdvList(paramMaps);
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
	        	 advMapNew5.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/banner_lxyz.jpg");
	        	 advMapNew5.put("url", "");
	        	 advMapNew5.put("title", "");
	        	 advListNew.add(advMapNew5);
	        	/* Map<String,Object> advMapNew1 = new HashMap<String, Object>();
	        	 advMapNew1.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jbts.jpg");
	        	 advMapNew1.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jbts/init.htm?shequId="+streetId);
	        	 advMapNew1.put("title", "");
	        	 advListNew.add(advMapNew1);
//		        	 Map<String,Object> advMapNew2 = new HashMap<String, Object>();
//		        	 advMapNew2.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_gzbx.jpg");
//		        	 advMapNew2.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/gzbx/init.htm?shequId="+shequId);
//		        	 advMapNew2.put("title", "");
//		        	 advListNew.add(advMapNew2);
	        	 Map<String,Object> advMapNew3 = new HashMap<String, Object>();
	        	 advMapNew3.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_jlhd.jpg");
	        	 advMapNew3.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/jlhd/init.htm?shequId="+streetId);
	        	 advMapNew3.put("title", "");
	        	 advListNew.add(advMapNew3);
	        	 Map<String,Object> advMapNew4 = new HashMap<String, Object>();
	        	 advMapNew4.put("img", PropertiesUtil.getSetting("pic_url")+"/uploadimages/201512/mobile/ban_sjxx.jpg");
	        	 advMapNew4.put("url", PropertiesUtil.getSetting("project_url")+"/Weixin/wfw/sjxx/init.htm?shequId="+streetId);
	        	 advMapNew4.put("title", "");
	        	 advListNew.add(advMapNew4);*/
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
			request.setAttribute("qsQuestionnaireList", qsQuestionnaireList);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/street/qsQuestionnaire/qsQuestionnaire_list";
	}
	
	//初始化【马群问卷】街道问卷详情
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/initQSQuestionnaireInfo")
	public String initQSQuestionnaireInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> qsQuestionnaireMap = null;
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
			}
		}
		requestMap.put("streetId", streetId);
		request.setAttribute("streetId", streetId);
		sessions.setAttribute("streetId", streetId);
		
		String qs_id = requestMap.get("qs_id");//问卷ID
		List<Map<String,Object>> qsInfoClassList = null;
		//获取会员登录信息
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		try {
			if(memberMap==null){
				request.setAttribute("login_type", "qsQuestionnaire");
				sessions.setAttribute("login_type", "qsQuestionnaire");
				return "maqun/maqun_login";
			}
			HashMap<String,String> hashMap = new HashMap<String,String>();
			hashMap.put("qs_id", qs_id);
			qsQuestionnaireMap = qsQuestionnaireService.getQSQuestionnaireInfoMap(hashMap);
			
			//获取街道问卷一级单位
			try {
				//顶部广告
				HashMap<String, String> paramMaps12 = new HashMap<String, String>();
				paramMaps12.put("bgLocation", "12");
				paramMaps12.put("streetId", streetId);//社区ID
				List<Map<String, Object>> advTop = advService.getStreetAdvList(paramMaps12);
				String topImg = "";
				if(advTop!=null&&advTop.size()>0){
					Map<String, Object> map = advTop.get(0);
					Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
					topImg = (String) paramMapss.get("imagePaths");
				}
				request.setAttribute("topImg", topImg);
				qsInfoClassList = qsQuestionnaireService.getQSInfoClassList(requestMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		System.out.println("qsInfoClassList===>"+qsInfoClassList);
		request.setAttribute("qs_id", qs_id);
		request.setAttribute("memberMap", memberMap);
		request.setAttribute("qsInfoClassList", qsInfoClassList);
		request.setAttribute("qsQuestionnaireMap", qsQuestionnaireMap);
		return "/street/qsQuestionnaire/qsQuestionnaire_info";
	}
	
	//初始化【马群问卷】街道问卷随机问题列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getQSQuestionList")
	public String getQSQuestionList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> qsQuestionList = new ArrayList<Map<String, Object>>();
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		String qsId = requestMap.get("qs_id");//问卷ID
		String questionCount = requestMap.get("questionCount");//随机题数
		String name = requestMap.get("name");//姓名
		String phone = requestMap.get("phone");//手机号码
		String unit = requestMap.get("unit");//单位
		String nextUnit = requestMap.get("nextUnit");//下级单位
		//获取会员登录信息
		HttpSession sessions = request.getSession();
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		try {
			if(memberMap==null){
				request.setAttribute("login_type", "qsQuestionnaire");
				sessions.setAttribute("login_type", "qsQuestionnaire");
				return "maqun/maqun_login";
			}
			HashMap<String,String> hashMap = new HashMap<String,String>();
			hashMap.put("qsId", qsId);
			hashMap.put("questionCount", questionCount);
			//顶部广告
			HashMap<String, String> paramMaps11 = new HashMap<String, String>();
			paramMaps11.put("bgLocation", "11");
			paramMaps11.put("streetId", streetId);//社区ID
			List<Map<String, Object>> advTop = advService.getStreetAdvList(paramMaps11);
			String topImg = "";
			if(advTop!=null&&advTop.size()>0){
				Map<String, Object> map = advTop.get(0);
				Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
				topImg = (String) paramMapss.get("imagePaths");
			}
			request.setAttribute("topImg", topImg);
			qsQuestionList = qsQuestionnaireService.getQSQuestionList(hashMap);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		request.setAttribute("qsId", qsId);
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("unit", unit);
		request.setAttribute("nextUnit", nextUnit);
		request.setAttribute("qsQuestionList", qsQuestionList);
		request.setAttribute("qsQuestion_totle", qsQuestionList.size());
		return "/street/qsQuestionnaire/qsQuestionnaire_start";
	}
	
	//初始化【马群问卷】街道问卷随机问题列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/subQSQuestion")
	public String subQSQuestion(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> memberQuestionMap = null;
		HashMap<String, String> requestMap = findRequestMap(request);
		String qsId = requestMap.get("qsId");
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession session = request.getSession();
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(session.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
			}
		}
		String memberId = "11111";
		//获取会员登录信息
		Map<String, Object> memberMap = (Map<String, Object>) session.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "qsQuestionnaire");
			session.setAttribute("login_type", "qsQuestionnaire");
			return "maqun/maqun_login";
		}else{
			memberId = StringUtil.nvl(memberMap.get("id"));
		}
		request.setAttribute("qsQuestMap", requestMap);
		
		String token = requestMap.get("token");
		String session_token = StringUtil.nvl(session.getAttribute("token"));
		if(token.equals(session_token)){
			session.removeAttribute("token");
			double grade = 0.00;
			String correct_totle = requestMap.get("correct_totle");//正确答案数量
			String qsQuestion_totle = requestMap.get("qsQuestion_totle");//总问题数量
			if(StringUtils.isNotBlank(qsQuestion_totle) && StringUtils.isNotBlank(correct_totle)){
				grade = Double.valueOf(correct_totle)/Double.valueOf(qsQuestion_totle)*100;
				if(grade>100){
					grade=100.00;
				}
			}
			DecimalFormat df = new DecimalFormat("######0.00");   
			HashMap<String,String> hashMap = new HashMap<String,String>();
			hashMap.put("unit", requestMap.get("unit"));
			hashMap.put("nextUnit", requestMap.get("nextUnit"));
			hashMap.put("qsId", qsId);
			hashMap.put("name", requestMap.get("name"));
			hashMap.put("phone", requestMap.get("phone"));
			hashMap.put("startTime", requestMap.get("startTime"));
			hashMap.put("endTime", requestMap.get("endTime"));
			hashMap.put("totalTime", requestMap.get("totalTime"));
			hashMap.put("grade", StringUtil.nvl(df.format(grade)));
			hashMap.put("memberId", memberId);
			hashMap.put("streetId", streetId);
			if("101".equals(streetId)){
				hashMap.put("shequId", SystemConstant.DONGTAI_SHEQU_JZFZ_ID);
			}else if("81".equals(streetId)){
				hashMap.put("shequId", SystemConstant.DONGTAI_SHEQU_MAQUN_ID);
			}else{
				hashMap.put("shequId", SystemConstant.DONGTAI_SHEQU_MAQUN_ID);
			}
			try {
				memberQuestionMap = qsQuestionnaireService.subQSQuestionnaire(hashMap);
				//提交问卷将姓名更新为会员名称
				String sessionMemberId = StringUtil.nvl(memberMap.get("id"));
				if(sessionMemberId.equals(memberId)){
					memberMap.put("name", requestMap.get("name"));
				}
			} catch (OtherException e) {
				e.printStackTrace();
			}
			request.setAttribute("qsId", qsId);
			request.setAttribute("memberQuestionMap", memberQuestionMap);
			return "/street/qsQuestionnaire/qsQuestionnaire_end";
		}else{
			HttpSession sessions = request.getSession();
			if(StringUtils.isBlank(streetId)){
				streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
				if(StringUtils.isBlank(streetId)){
					streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
				}
			}
			requestMap.put("streetId", streetId);
			request.setAttribute("streetId", streetId);
			sessions.setAttribute("streetId", streetId);
			
			//获取会员登录信息
			try {
				Map<String, Object> qsQuestionnaireMap = qsQuestionnaireService.getQSQuestionnaireListMap(requestMap);
				List<Map<String,Object>> qsQuestionnaireList = (List<Map<String, Object>>) qsQuestionnaireMap.get("result");
				if(qsQuestionnaireList!=null&&qsQuestionnaireList.size()>0){
					for(Map<String,Object> map : qsQuestionnaireList){
						String detail = (String) map.get("detail");
						if(detail.length() > 10){
							detail = detail.substring(0,10)+"...";
						}
						map.put("detail", detail);
					}
				}
				request.setAttribute("qsQuestionnaireList", qsQuestionnaireList);
			} catch (OtherException e) {
				e.printStackTrace();
			}
			return "/street/qsQuestionnaire/qsQuestionnaire_list";
		}
	}
	
	//初始化【马群问卷】街道问卷排行榜
	@RequestMapping(value = "/qsQuestionRankList")
	public String qsQuestionRankList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
			}
		}
		String qsId = requestMap.get("qsId");//问卷ID
		List<Map<String,Object>> qsMemberQuestionnaireList = null;
		try {
			requestMap.put("streetId", streetId);
			qsMemberQuestionnaireList = qsQuestionnaireService.getQSQuestionRankListMap(requestMap);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		request.setAttribute("qsMemberQuestionnaireList", qsMemberQuestionnaireList);
		request.setAttribute("qsId", qsId);
		return "/street/qsQuestionnaire/qsQuestionnaire_rankList";
	}
	
	//获取【马群问卷】会员问卷列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMemberQuestionnaireList")
	public String getMemberQuestionnaireList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(streetId)){
			streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
		}
		//获取会员登录信息
		String memberId = "";		
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "qsQuestionnaire");
			sessions.setAttribute("login_type", "qsQuestionnaire");
			return "maqun/maqun_login";
		}else{
			memberId = StringUtil.nvl(memberMap.get("id"));
		}
		List<Map<String,Object>> qsMemberQuestionnaireList = null;
		try {
			requestMap.put("streetId", streetId);
			requestMap.put("memberId", memberId);
			qsMemberQuestionnaireList = qsQuestionnaireService.getMemberQuestionnaireListMap(requestMap);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		request.setAttribute("qsMemberQuestionnaireList", qsMemberQuestionnaireList);
		return "/street/qsQuestionnaire/qsQuestionnaire_member_List";
	}
	
	//选择社区问卷单位
	@RequestMapping(value="/changeQSInfoClass")
	public String changeQSInfoClass(HttpServletRequest request,HttpServletResponse response){
		HttpSession sessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String streetId = requestMap.get("streetId");//街道ID
		if(StringUtils.isBlank(streetId)){
			streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
			if(StringUtils.isBlank(streetId)){
				streetId = SystemConstant.STREET_MAQUN_ID;//默认为马群街道
			}
		}
		requestMap.put("streetId", streetId);
		request.setAttribute("streetId", streetId);
		sessions.setAttribute("streetId", streetId);
		
		Map<String, Object> resetPasswordInfo = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> qsInfoClassList = qsQuestionnaireService.getQSInfoClassList(requestMap);
			resetPasswordInfo.put("success","0");
			resetPasswordInfo.put("qsInfoClassList",qsInfoClassList);
		}catch(OtherException e) {
			e.printStackTrace();
			resetPasswordInfo.put("success",e.getErrorCode());
			resetPasswordInfo.put("msg",e.getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			resetPasswordInfo.put("success","-1");
			resetPasswordInfo.put("msg","异常,密码修改失败");
		}
		response.setContentType("text/json;charset=UTF-8");
		JSONObject obj = JSONObject.fromObject(resetPasswordInfo);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
