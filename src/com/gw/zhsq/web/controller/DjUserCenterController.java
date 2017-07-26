package com.gw.zhsq.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.DjUserCenterService;

/**
 * 用户中心
 * @author mars
 *  http://localhost:8080/party/djUser/updatePhone?id=6d523fb007fd43df8e55536d57231e24&mobile=1234556
 */
@Controller
@RequestMapping(value = "/djUser")
public class DjUserCenterController extends BaseController{
	
	@Autowired
	DjUserCenterService djUserCenterService;
	
	@RequestMapping(value = "/updatePhone")
	public String updatePhone(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("id", StringUtil.nvl(request.getAttribute("id")));
		paramsMap.put("mobile", StringUtil.nvl(request.getAttribute("mobile")));
		try{
			Map<String, Object> userCenterMap = djUserCenterService.updatePhone(paramsMap);
			request.setAttribute("userCenterMap", userCenterMap);
			return "";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
	}
}
