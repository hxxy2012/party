package com.gw.zhsq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
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
import com.gw.base.constant.SystemConstant;
import com.gw.base.exception.OtherException;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.SendCodeUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.MemberService;
import com.gw.zhsq.web.service.NoticesInfoService;

/**
 * 个人中心控制层
 * @author fuyun
 *	2015-04-02 13:33
 *	http://localhost:8080/Weixin/member/memberLogin.htm
 */
@Controller
@RequestMapping("/member_copy")
public class MemberControllerCopy extends BaseController {
	
	@Resource
	private AdvService advService;
	@Resource
	private MemberService memberService;
	@Resource
	private NoticesInfoService noticesInfoService;
	
	//根据本地存储localStorage获取会员基本信息
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkMemberState")
	public void checkMemberState(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String member_account = request.getParameter("member_account");//会员帐号
		HttpSession memberSessions = request.getSession();
		Map<String, Object> memberMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
		if(memberMap==null && StringUtils.isNotBlank(member_account)){
			HashMap<String, String> requestMap = new HashMap<String,String>();
			requestMap.put("account", member_account);
			try {
				memberMap = memberService.getMemberMap(requestMap);
				memberSessions.setAttribute("memberSession",memberMap);
			} catch (OtherException e) {
				e.printStackTrace();
			}
		}
		ajaxJson("1",response);
	}
	
	//初始化更改个人头像
	@RequestMapping(value = "/initPatientHead")
	public String initPatientHead(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
    	return "member/initMemberHead";
	}
		
	//图片下载
	@RequestMapping(value = "/downloadImage")
	public void downloadImage(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String media_id = request.getParameter("media_id");
		String access_token = request.getParameter("access_token");
		String DOWNLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id;
		String url = String.format(DOWNLOAD_MEDIA, access_token, media_id);
		ajaxJson(url,response);
	}
	
	//根据经伟度获取地址
	@SuppressWarnings("unchecked")
	@RequestMapping({"/getAddress"})
	public void getAddress(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String latitude = request.getParameter("latitude");//纬度
		String longitude = request.getParameter("longitude");//经度
		Object content=null;
		try {
			//百度地图坐标的坐标进行转化
			String coords = longitude+","+latitude;
			String coordsObj = SystemConstant.getURLString("http://api.map.baidu.com/geoconv/v1/?output=json&from=1&to=5&ak="+SystemConstant.BAIDU_KEY+"&coords="+coords, null);
			JSONObject coordsJsonObj = JSONObject.fromObject(coordsObj);
			if("0".equals(coordsJsonObj.get("status").toString())){
				 List<Map<String,Object>> list = (List<Map<String,Object>>) coordsJsonObj.get("result");
				 if(list!=null){
					 Map<String,Object> hashMap = list.get(0);
					 longitude = StringUtil.nvl(hashMap.get("x"));//经度
					 latitude = StringUtil.nvl(hashMap.get("y"));//纬度
				 }
			 }
			//根据经纬度获取地址信息
			String location = latitude+","+longitude;
			content = SystemConstant.getURLString("http://api.map.baidu.com/geocoder/v2/?output=json&ak="+SystemConstant.BAIDU_KEY+"&location="+location, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    JSONObject obj = JSONObject.fromObject(content);
	    PrintWriter out;
	    try {
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//微信快捷登录
	@SuppressWarnings("unchecked")
	@RequestMapping({"/login"})
	public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> requestMap = findRequestMap(request);
		String login_type = requestMap.get("login_type");//上一页面标识
		String shequId = requestMap.get("shequId");//社区ID
		String streetId = requestMap.get("streetId");//街道ID
		HttpSession sessions = request.getSession();
		if(StringUtils.isBlank(shequId)){
			shequId = StringUtil.nvl(sessions.getAttribute("shequId"));
			if(StringUtils.isBlank(shequId)){
				if(StringUtils.isBlank(streetId)){
					streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
					if("22".equals(streetId)){
						//东台街道
						shequId = SystemConstant.DONGTAI_SHEQU_ID;
					}else if("61".equals(streetId)){
						//南京港湾
						shequId = SystemConstant.STREET_MAQUN_ID;
					}else if("81".equals(streetId)){
						//智慧马群
						shequId = SystemConstant.DONGTAI_SHEQU_MAQUN_ID;
					}else if("101".equals(streetId)){
						//建筑防灾
						shequId = SystemConstant.DONGTAI_SHEQU_JZFZ_ID;
					}
				}
				shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
			}
		}
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		sessions.setAttribute("shequId", shequId);
		
		String url="";
		HttpSession memberSessions = request.getSession();
		Map<String, Object> memberMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
		if(memberMap!=null){
			memberSessions.setAttribute("memberSession", memberMap);
			request.setAttribute("memberMap", memberMap);
			//哪儿来回哪儿去
			if("sjxx".equals(login_type)){
				//书记信箱
				return "redirect:/wfw/sjxx/init.htm?shequId="+shequId;
			}else if("jbts".equals(login_type)){
				//意见反馈
				return "redirect:/wfw/jbts/init.htm?shequId="+shequId;
			}else if("rztj".equals(login_type)){
				//认证推荐
				return "redirect:/wsh/rztj/init.htm?shequId="+shequId;
			}else if("essc".equals(login_type)){
				//二手市场
				return "redirect:/wsh/essc/getESSCList.htm?shequId="+shequId;
			}else if("jbtsList".equals(login_type)){
				//意见反馈列表
				return "redirect:/wfw/jbts/getJBTSList.htm?shequId="+shequId;
			}else if("gzbxList".equals(login_type)){
				//故障报修列表
				return "redirect:/wfw/gzbx/getGZBXList.htm?shequId="+shequId;
			}else if("jlhdDetail".equals(login_type) || "jlhdHuifu".equals(login_type)){
				//交流互动列表
				return "redirect:/wfw/jlhd/getJLHDList.htm?shequId="+shequId;
			}else if("jlhdTiwen".equals(login_type) || "jlhdAddTiwen".equals(login_type)){
				//交流互动提问
				return "redirect:/wfw/jlhd/tiwen.htm?shequId="+shequId;
			}else{
//				return "member/baseMemberInfo?shequId="+shequId;
				return "member/baseMemberInfo";
			}
		}else{
			try{
				String code = request.getParameter("code");
				if (code == null) {
					//进入授权界面进行授权
					//url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + SystemConstant.APPID;
					url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + PropertiesUtil.getSetting("weixin_appid_"+shequId);
					url = url + "&redirect_uri="+SystemConstant.REDIRECT_URI;
					url = url + "&response_type=code&scope=snsapi_userinfo#wechat_redirect";
					System.out.println("url==>"+url);
					response.sendRedirect(url);
					return null;
				}
				//获取openid
				String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + PropertiesUtil.getSetting("weixin_appid_"+shequId) + "&secret=" + PropertiesUtil.getSetting("weixin_secret_"+shequId) + "&code=" + code + "&grant_type=authorization_code";
				Object content = SystemConstant.getURLString(openIdUrl, null);
			  	JSONObject obj = JSONObject.fromObject(content);
			  	String openid = (String)obj.get("openid");//openid
			  	
//			  	WxTemplate t = new WxTemplate();  
//		        t.setUrl("");  
//		        t.setTouser(openid);//openId  
//		        t.setTopcolor("#000000");  
//		        t.setTemplate_id("WUZ99HT8znsLle_Lr73BL3YMlvqcT8-0QZScw5-bXWU");//模板ID
//		        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
//		        TemplateData dept = new TemplateData();  
//		        dept.setColor("#000000");  
//		        dept.setValue("***福田区民政局***");  
//		        m.put("dept", dept);  
//		        TemplateData event = new TemplateData();  
//		        event.setColor("#000000");  
//		        event.setValue("***结婚登记***");  
//		        m.put("event", event);  
//		        TemplateData date = new TemplateData();  
//		        date.setColor("blue");  
//		        date.setValue("***2013年11月22日15时30分***");  
//		        m.put("date", date);  
//		        TemplateData no = new TemplateData();  
//		        no.setColor("blue");  
//		        no.setValue("***20131122003***");  
//		        m.put("no", no); 
//		        TemplateData remark = new TemplateData();  
//		        remark.setColor("blue");  
//		        remark.setValue("***请携带相关证件。***");  
//		        m.put("remark", remark);
//		        t.setData(m); 
//				
//				String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
//				String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
//				//获取access_token
//				String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
//				JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
//				String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
//				System.out.println("access_token===sjxx===>"+access_token);
//				//String template_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
//				//JSONObject template_obj = JSONObject.fromObject(SystemConstant.getURLString(template_url, null));
//				JSONObject jsonobj = HttpRequestH.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token, "POST",JSONObject.fromObject(t).toString());
//				System.out.println("jsonobj===xjxx===>"+jsonobj);
				
				//查看某openid是否已注册
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				paramsMap.put("account", openid);
				paramsMap.put("password", SystemConstant.MEMBER_PASSWORD);
				try{
					try{
						memberMap = memberService.memberLogin(paramsMap);
					}catch(Exception e){
					}
					if(memberMap!=null){
						memberSessions.setAttribute("memberSession", memberMap);
						request.setAttribute("memberMap", memberMap);
						request.setAttribute("weixinLogin", "1");
						return "member/baseMemberInfo";
					}else{
						String atoken = (String)obj.get("access_token");//atoken
						content = SystemConstant.getURLString("https://api.weixin.qq.com/sns/userinfo?access_token=" + atoken + "&openid=" + openid + "&lang=zh_CN", null);
					  	obj = JSONObject.fromObject(content);
//						  	StringBuffer html = new StringBuffer("<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;\"/></head><body>");
//						  	html.append("<img src=\"" + obj.get("headimgurl") + "\" width=\"80px\" height=\"80px\"><br>");
//						  	html.append(obj.get("nickname") + "&nbsp;");
//						  	String sex = obj.getString("sex");
//						  	if ("1".equals(sex)){
//					    		html.append("男&nbsp;"); 
//						  	}else if ("2".equals(sex)){
//						  		html.append("女&nbsp;");
//						  	}
//						  	html.append(obj.get("city") + "&nbsp;");
//						  	html.append("<br></body></html>");
//						  	System.out.println("html==>"+html.toString());
//						  	response.getWriter().write(html.toString());
					  	
					  	String nickname = StringUtil.nvl(obj.get("nickname"));//昵称
					  	String headimgurl = StringUtil.nvl(obj.get("headimgurl"));//头像
					  	String city = StringUtil.nvl(obj.get("city"));//城市
					  	String gender = "";//性别
					  	String sex = obj.getString("sex");
					  	if ("1".equals(sex)){
					  		gender="0";
					  	}else if ("2".equals(sex)){
					  		gender="1";
					  	}
						
						HashMap<String, String> paramsRegisterMap = new HashMap<String, String>();
						paramsRegisterMap.put("account", openid);//微信登录默认帐号为openid
						paramsRegisterMap.put("password", SystemConstant.MEMBER_PASSWORD);//默认密码123456
						paramsRegisterMap.put("phone", "");//手机号码
						paramsRegisterMap.put("gender", gender);//性别 ：0 男、1 女
						paramsRegisterMap.put("name", nickname);//昵称
						paramsRegisterMap.put("imagepaths", headimgurl);//头像
						paramsRegisterMap.put("city", city);//城市
						paramsRegisterMap.put("source", "weixin");//注册来源  "android、ios、pc、weixin、other"
						paramsRegisterMap.put("shequId", shequId);//社区ID
						paramsRegisterMap.put("thirdParty", "1");//第三方快捷登录，0普通登录1微信快捷登录2QQ快捷登录
						
						memberMap = memberService.memberRegister(paramsRegisterMap);
						if(memberMap!=null){
							memberSessions.setAttribute("memberSession", memberMap);
							request.setAttribute("memberMap", memberMap);
							request.setAttribute("weixinLogin", "1");
							return "member/baseMemberInfo";
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}catch (Exception e) {
				e.printStackTrace();
				try {
					response.getWriter().write(e.getMessage());
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return "register";
	}
	
	//会员登录
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/memberLogin")
	public String memberLogin(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession memberSessions = request.getSession();
		try {
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
			
			Map<String, Object> memberSessionMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
			String login_name = requestMap.get("login_name");//用户名
			String login_type = requestMap.get("login_type");//上一页面标识
			String login_password = requestMap.get("login_password");//密码
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			
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
				memberSessions.setAttribute("memberSession", memberMap);
				
//				 // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..  
//				 System.out.println("cookie.member_id====="+StringUtil.nvl(memberMap.get("id"))); 
//				Cookie oItem = new Cookie("member_id", StringUtil.nvl(memberMap.get("id")));
//				oItem.setDomain("weixin.gangwaninfo.com");  //请用自己的域 
//				oItem.setPath("/");  
//				 response.addCookie(oItem);  
				 
				System.out.println("memberMap===memberLogin====>>"+memberMap);
				if(memberMap!=null){
					request.setAttribute("memberMap", memberMap);
					//哪儿来回哪儿去
					if("sjxx".equals(login_type)){
						//书记信箱
						return "redirect:/wfw/sjxx/init.htm?shequId="+shequId;
					}else if("jbts".equals(login_type)){
						//意见反馈
						return "redirect:/wfw/jbts/init.htm?shequId="+shequId;
					}else if("rztj".equals(login_type)){
						//认证推荐
						return "redirect:/wsh/rztj/init.htm?shequId="+shequId;
//						return "redirect:/wsh/rztj/init.htm";
					}else if("essc".equals(login_type)){
						//二手市场
						return "redirect:/wsh/essc/getESSCList.htm?shequId="+shequId;
					}else if("jbtsList".equals(login_type)){
						//意见反馈列表
						return "redirect:/wfw/jbts/getJBTSList.htm?shequId="+shequId;
					}else if("gzbxList".equals(login_type)){
						//故障报修列表
						return "redirect:/wfw/gzbx/getGZBXList.htm?shequId="+shequId;
					}else if("jlhdDetail".equals(login_type) || "jlhdHuifu".equals(login_type)){
						return "redirect:/wfw/jlhd/getJLHDList.htm?shequId="+shequId;
					}else if("jlhdTiwen".equals(login_type) || "jlhdAddTiwen".equals(login_type)){
						return "redirect:/wfw/jlhd/tiwen.htm?shequId="+shequId;
					}else{
//						return "member/baseMemberInfo?shequId="+shequId;
						return "member/baseMemberInfo";
					}
				}
			}
			request.setAttribute("login_type", "");
//			return "login";
			return "redirect:/member/login.htm";
		} catch (OtherException e) {
			e.printStackTrace();
			request.setAttribute("error_code", e.getErrorCode());
			request.setAttribute("error_msg", e.getErrorMsg());
			request.setAttribute("login_type", "");
//			return "login";
			return "redirect:/member/login.htm";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_code", "9999");
			request.setAttribute("error_msg", "会员登录失败，请重新登录");
			request.setAttribute("login_type", "");
//			return "login";
			return "redirect:/member/login.htm";
		}
	}
	
	//会员 注册
	@RequestMapping(value = "/memberRegister")
	public String memberRegister(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession memberSessions = request.getSession();
		try {
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
			
			String license  = request.getParameter("license");//阅读协议
			String account = requestMap.get("registerMobile");//用户名/手机号码
			String password = requestMap.get("registerPassword");//登录密码
			String phone = requestMap.get("registerMobile");//用户名/手机号码
			if("agree".equals(license)){
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
					return "member/baseMemberInfo";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register";
	}
	
	//找回密码
	@RequestMapping(value = "/findPassword")
	public String findPassword(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "find_password";
	}
	
	//重设密码
	@RequestMapping(value = "/resPassword")
	public String resPassword(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String mobile = requestMap.get("mobile");
		request.setAttribute("mobile", mobile);
		return "res_password";
	}
	//重置密码
	@RequestMapping(value = "/saveResPassword")
	public void saveResPassword(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String result = "0"; 
		try {
			HashMap<String, String> paramsMap = new HashMap<String, String>();
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
	
	//服务协议
	@RequestMapping(value = "/agreement")
	public String agreement(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "agreements";
	}

	//验证手机验证码
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkMobileCode")
	public void checkMobileCode(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String result = "0";
		try {
			String regCode =  request.getParameter("regCode");
			HashMap<String,String> m =  (HashMap<String,String>) request.getSession().getAttribute("codeMap");
			String sendTime ="";
			if(m!=null){
				sendTime = (String)m.get("sendTime");
			}
			
			if(!sendTime.equals("")){
				if( System.currentTimeMillis() -new Long(sendTime)  < 300000 ){
					
					String code = (String) m.get("code");
					if(regCode == code||regCode.equals(code)){
						result = "1";
					}else{
						result = "0";
					}
				}else{
					result = "2";
					request.getSession().removeAttribute("codeMap");
				}
			}else{
				result = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ajaxJson(result,response);
	}	
	
	/**
	 * 注册验证手机号
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkMobile")
	public void checkMobile(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String result = "0";
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("mobile", requestMap.get("registerMobile"));
			
			Map<String, Object> memberMap = memberService.getMemberListByMobile(paramsMap);
			if(memberMap!=null&&"0".equals(memberMap.get("success"))){
				result = "1";
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		
		ajaxJson(result,response);
	}
	/**
	 * 获取手机验证码
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveCode")
	public void saveCode(ModelMap model,HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		String sendTime = request.getParameter("sendTime");
		String mobile = request.getParameter("mobile");
		String forType = request.getParameter("forType");
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("code", code);
		map.put("sendTime", sendTime);
		map.put("mobile", mobile);
		request.getSession().setAttribute("codeMap", map);
		
		String result ="";
		try {
			result = SendCodeUtil.sendCode(code, mobile,Integer.valueOf(forType));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ajaxJson(result,response);
	}	
	/**
	 * 登入验证用户名和密码匹配
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkLogin")
	public void checkLogin(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String result = "0";
		HashMap<String, String> requestMap = findRequestMap(request);
		String login_name = requestMap.get("login_name");//用户名
		String login_password = requestMap.get("login_password");//密码
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", login_name);
		map.put("password", login_password);
		try {
			Map<String, Object> memberMap = memberService.memberLogin(map);
			if(memberMap!=null){
				result = "1";
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		
		ajaxJson(result,response);
	}
	
	
	//获取会员基本信息
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/baseMemberInfo")
	public String baseMemberInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		Map<String, Object> memberMap = (Map<String, Object>) shequSessions.getAttribute("memberSession");
		if(memberMap==null){
			request.setAttribute("login_type", "");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			request.setAttribute("memberMap", memberMap);
			return "member/baseMemberInfo";
		}
	}
	
	//获取会员个人信息
	@SuppressWarnings({ "unchecked"})
	@RequestMapping(value = "/memberInfo")
	public String memberInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, String> requestMap = findRequestMap(request);
		HashMap<String, String> requestMap = new HashMap<String, String>();
		HttpSession memberSessions = request.getSession();
		Map<String, Object> memberMap = null;
		try {
			Map<String, Object> memberSessionMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
			if(memberSessionMap==null){
				request.setAttribute("login_type", "");
//				return "login";
				return "redirect:/member/login.htm";
			}else{
				requestMap.put("account", StringUtil.nvl(memberSessionMap.get("account")));
				memberMap =  memberService.getMemberMap(requestMap);
				request.setAttribute("member_id", StringUtil.nvl(memberSessionMap.get("member_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("memberMap=====memberInfo=====>>"+memberMap.toString());
		request.setAttribute("memberMap", memberMap);
		return "member/memberInfo";
	}
	
	//保存个人信息
	@RequestMapping(value = "/saveMemberInfo")
	public void saveMemberInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String result = "0"; 
		try {
			int temp = memberService.saveMemberInfo(requestMap);
			if(temp==0){
				result = "1";
			}else if(temp==2){
				result = "2";//该手机号已被注册
			}
		} catch (OtherException e) {
			e.printStackTrace();
		}
		ajaxJson(result,response);
	}
	
	//初始化更改个人头像微信demo
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/initWeixinDemo")
	public String initWeixinDemo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
		
		String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
		String weixin_member_url = SystemConstant.WEIXIN_MEMBER_URL;//微信签名url地址
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
					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_member_url;
					System.out.println("string1==>"+string1);
					//生成签名
			    	signature = SHA1.encode(string1);
				}
		    	System.out.println("access_token==>"+access_token);
		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
		    	System.out.println("signature==>"+signature);
		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
		    	System.out.println("weixin_member_url==>"+weixin_member_url);
		    	
		    	request.setAttribute("appId", appId);
		    	request.setAttribute("signature", signature);
		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
		    	request.setAttribute("access_token", access_token);
		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
				request.setAttribute("weixin_timestamp", weixin_timestamp);
				request.setAttribute("weixin_member_url", weixin_member_url);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		HttpSession memberSessions = request.getSession();
		Map<String, Object> memberSessionMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
		request.setAttribute("memberMap", memberSessionMap);
		return "member/weixinDemo";
	}
		
	//更改个人头像 id;imagepaths;account;
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modifyPatientHead")
	public String modifyPatientHead(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		String thumbnailpaths =  request.getParameter("thumbnailpaths");
		HttpSession memberSessions = request.getSession();
		Map<String, Object> memberSessionMap = (Map<String, Object>) memberSessions.getAttribute("memberSession");
		try {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("id", StringUtil.nvl(memberSessionMap.get("id")));
			hashMap.put("account", StringUtil.nvl(memberSessionMap.get("account")));
			hashMap.put("isWeixin", "1");//是否微信
			hashMap.put("imagepaths", thumbnailpaths);
			String image = memberService.modifyPatientHead(hashMap);
			memberSessionMap.put("image", image);
			memberSessions.setAttribute("memberSession", memberSessionMap);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		request.setAttribute("memberMap", memberSessionMap);
		return "member/baseMemberInfo";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="toResetPassword")
	public String toResetPassword(HttpServletRequest request,HttpServletResponse responset){
		Map<String, Object> memberInfo = (Map<String, Object>) request.getSession().getAttribute("memberSession");
		if(memberInfo==null){
			request.setAttribute("login_type", "");
//			return "login";
			return "redirect:/member/login.htm";
		}
		request.setAttribute("memberId", memberInfo.get("member_id"));
		request.setAttribute("phone", memberInfo.get("phone"));
		request.setAttribute("account", memberInfo.get("account"));
		return "member/resetPassword";
	}
	
	@RequestMapping(value="resetPassword")
	public String resetPassword(HttpServletRequest request,HttpServletResponse response){
		Object memberInfo = request.getSession().getAttribute("memberSession");
		if(memberInfo==null){
			request.setAttribute("login_type", "");
//			return "login";
			return "redirect:/member/login.htm";
		}
		HashMap<String, String> requestMap = findRequestMap(request);
		
		Map<String, Object> resetPasswordInfo=new HashMap<String, Object>();
		try {
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("mobile", requestMap.get("phone"));
			paramsMap.put("password", requestMap.get("newPassword"));
			paramsMap.put("account", requestMap.get("account"));
			paramsMap.put("oldPassword", requestMap.get("oldPassword"));
			
			resetPasswordInfo = memberService.resetPassword(paramsMap);
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
	
	@RequestMapping(value="/exit")
	public String exit(HttpServletRequest request,HttpServletResponse responset){
		Object memberInfo = request.getSession().getAttribute("memberSession");
		if(memberInfo!=null){
			request.getSession().removeAttribute("memberSession");
			request.getSession().invalidate();//移出所有
		}
		return "redirect:/member/memberLogin.htm";
	}
	
    
    public static String getIp(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }  
		String[] ips = ip.split(",");
		for(String i:ips){
			if(i!=null&&(!i.equals("unknown"))){
				return i;
			}
		}
		return ip; 
    } 
	
//	public static Map<String, String> getPrepayMap(String prepay_id) throws Exception {
//		prepay_id = "prepay_id=" + prepay_id;
//		Map<String, String> params = new HashMap<String, String>();
//    	params.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
//    	params.put("nonceStr", String.valueOf(System.currentTimeMillis()));
//		params.put("package", prepay_id);
//		params.put("appId", SystemConstant.APPID);
//		params.put("signType", "MD5");
//		List<String> keys = new ArrayList<String>(params.keySet());
//    	Collections.sort(keys);
//    	String str = "";
//    	for(int i = 0; i < keys.size(); i++) {
//    		if(i > 0) {
//    			str += "&";
//    		}
//    		str = str + keys.get(i) + "=" + params.get(keys.get(i));
//    	}
//    	str += "&key=" + SystemConstant.PAYKEY;
//    	params.put("paySign", GetMD5Code(str).toUpperCase()); 	
//		return params;
//	}
	
//    public static Map<String, String> getPrepayMap(String openid, String spbill_create_ip, String out_trade_no, String total_fee, String body) throws Exception {
//    	return getPrepayMap(getPrepayId(openid, spbill_create_ip, out_trade_no, total_fee, body));
//    }
//    
//    public static String getPrepayId(String openid, String spbill_create_ip, String out_trade_no, String total_fee, String body) throws Exception {
//    	Map<String, String> params = new HashMap<String, String>();
//    	params.put("appid", SystemConstant.APPID);
//    	params.put("mch_id", SystemConstant.MCHID);
//    	params.put("nonce_str", String.valueOf(System.currentTimeMillis()));
//    	params.put("body", body);
//    	params.put("out_trade_no", out_trade_no);
//    	params.put("total_fee", total_fee);
//    	params.put("spbill_create_ip", spbill_create_ip);
//    	params.put("notify_url", SystemConstant.NOTIFY_URL);
//    	params.put("trade_type", "JSAPI");
//    	params.put("openid", openid);
//    	List<String> keys = new ArrayList<String>(params.keySet());
//    	Collections.sort(keys);
//    	String str = "";
//    	for(int i = 0; i < keys.size(); i++) {
//    		if(i > 0) {
//    			str += "&";
//    		}
//    		str = str + keys.get(i) + "=" + params.get(keys.get(i));
//    	}
//    	str += "&key=" + SystemConstant.PAYKEY;
//    	params.put("sign", GetMD5Code(str).toUpperCase());
//    	keys.add("sign");
//    	Document document = DocumentHelper.createDocument();
//    	Element xml = document.addElement("xml");
//    	for(int i = 0; i < keys.size(); i++) {
//    		xml.addElement((String) keys.get(i)).setText((String) params.get(keys.get(i)));
//    	}
//    	String ret = SystemConstant.getURLString("https://api.mch.weixin.qq.com/pay/unifiedorder", document.asXML());
//    	document = DocumentHelper.parseText(ret);
//    	xml = document.getRootElement(); 
//    	return xml.elementText("prepay_id");
//    } 

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    public static String GetMD5Code(String strObj) throws Exception { 
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToHex(md.digest(strObj.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    } 
    
    
    
//    public  InputStream getInputStream(String mediaId) {
//        InputStream is = null;
//        String accessToken = "nAVnm7J5WFp--3Sa3AL3RCRn_UV7ca6UupQgo2S46ptZf7ToyRaB8jO7JWDJnCa1Kr_AsQjNFaMSZU49j2YU7pMkXivLgBRqBl4-ABs74OsFKSgAEAYDL";
//        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
//                + accessToken + "&media_id=" + mediaId;
//        try {
//            URL urlGet = new URL(url);
//            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
//            http.setRequestMethod("GET"); // 必须是get方式请求
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            http.setDoOutput(true);
//            http.setDoInput(true);
//            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
//            http.connect();
//            // 获取文件转化为byte流
//            is = http.getInputStream();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return is;
//    }
    
//    /**
//     * 获取下载图片信息（jpg）
//     * @param mediaId
//     * @throws Exception
//     */
//    public  void saveImageToDisk(String mediaId) throws Exception {
//        InputStream inputStream = getInputStream(mediaId);
//        byte[] data = new byte[1024];
//        int len = 0;
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream("test1.jpg");
//            while ((len = inputStream.read(data)) != -1) {
//                fileOutputStream.write(data, 0, len);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (fileOutputStream != null) {
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    
}
