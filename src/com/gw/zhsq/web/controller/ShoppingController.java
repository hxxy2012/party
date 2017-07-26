package com.gw.zhsq.web.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * [智慧社区]商城
 * @author hanxu
 *	2015-10-22  weixin.gangwaninfo.com
 * 请求地址：http://localhost:8080/Weixin/shopping/init.htm
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingController extends BaseController{

	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		
		//获取会员登录信息
		HttpSession sessions = request.getSession();
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		request.setAttribute("memberMap", memberMap);

//System.out.println("memberMap=====shopping=====>>"+memberMap.toString());
		if(memberMap==null){
			request.setAttribute("login_type", "shopping");
			sessions.setAttribute("login_type", "shopping");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			//同时调用商城登录 http://120.26.207.193:18087/shopping/shopping_login.htm
//			String userName = (String) memberMap.get("account");
			String openId = (String) memberMap.get("openId");
			String userId = (String) memberMap.get("userId");
			
			return "redirect:http://shopping.gangwaninfo.com/shopping/wap/index.htm?openId="+openId+"&userId="+userId;
		}
		
		
//		return "redirect:http://192.168.1.43:8083/shopping/zhsq_login.htm?userName=test&password=e10adc3949ba59abbe56e057f20f883e";
//		return "redirect:http://192.168.1.43:8083/shopping/index.htm?openId=9A6383AD4B58E8B1ACF65DC68E0B3B68";
//		return "redirect:http://192.168.1.43:8083/shopping/wap/index.htm?openId=9A6383AD4B58E8B1ACF65DC68E0B3B68&userId=32777";


	}
	
	
}
