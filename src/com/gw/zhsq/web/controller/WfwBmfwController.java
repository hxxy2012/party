package com.gw.zhsq.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gw.zhsq.web.service.NewsService;

/**
 * 智慧社区微信社区便民服务
 * @author hanxu
 *	2015-10-22
 *	http://localhost:8080/Weixin/wfw/bmfw/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/bmfw")
public class WfwBmfwController extends BaseController {
	
	@Resource
	private NewsService newsService;
	
	/**
	 * 初始化天气预报界面
	 * http://localhost:8080/Weixin/wfw/bmfw/tqyb.htm
	 */
	@RequestMapping(value = "/tqyb")
	public String tqyb(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_tqyb";
	}
	
	/**
	 * 初始化快递查询界面
	 * http://localhost:8080/Weixin/wfw/bmfw/kdcx.htm
	 */
	@RequestMapping(value = "/kdcx")
	public String kdcx(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_kdcx";
	}
	
	/**
	 * 初始化违章查询界面
	 * http://localhost:8080/Weixin/wfw/bmfw/wzcx.htm
	 */
	@RequestMapping(value = "/wzcx")
	public String wzcx(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_wzcx";
	}
		
	/**
	 * 初始化身份证查询界面
	 * http://localhost:8080/Weixin/wfw/bmfw/sfzcx.htm
	 */
	@RequestMapping(value = "/sfzcx")
	public String sfzcx(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_sfzcx";
	}
	
	/**
	 * 初始化万年历查询界面
	 * http://localhost:8080/Weixin/wfw/bmfw/wnlcx.htm
	 */
	@RequestMapping(value = "/wnlcx")
	public String wnlcx(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_wnlcx";
	}
	
	/**
	 * 初始化星座运势界面
	 * http://localhost:8080/Weixin/wfw/bmfw/xzys.htm
	 */
	@RequestMapping(value = "/xzys")
	public String xzys(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_xzys";
	}
	
	/**
	 * 初始化占仆算卦界面
	 * http://localhost:8080/Weixin/wfw/bmfw/zpsg.htm
	 */
	@RequestMapping(value = "/zpsg")
	public String zpsg(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		return "/wfw/bmfw/wfw_zpsg";
	}
}
