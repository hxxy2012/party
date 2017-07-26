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
import com.gw.zhsq.web.service.RZTJService;

/**
 * [智慧社区]微生活家政服务
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/wsh/jzfw/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wsh/jzfw")
public class WshJzfwController extends BaseController {
	
	@Resource
	private RZTJService rztjService;
	
	//初始化家政服务
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
		
		requestMap.put("memberId", "");//会员ID
		requestMap.put("serviceType", "0");//服务类型(0家政服务1餐饮服务2其他服务)
		Map<String, Object> jzfwMap=null;
		try {
			jzfwMap = rztjService.getRZTJListMap(requestMap);
			List<Map<String,Object>> jzfwList = (List<Map<String, Object>>) jzfwMap.get("result");
			if(jzfwList!=null&&jzfwList.size()>0){
				for(Map<String,Object> map : jzfwList){
					String title = (String) map.get("title");
					if(title.length() > 10){
						title = title.substring(0,10)+"...";
					}
					map.put("title", title);
				}
			}
			request.setAttribute("jzfwList", jzfwList);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/wsh/jzfw/wsh_jzfw_list";
	}
	
	//获取家政服务详情
	@RequestMapping(value = "/getJzfwDetail")
	public String getJzfwDetail(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> jzfwInfoMap = rztjService.getRZTJInfoMap(requestMap);
			request.setAttribute("jzfwInfoMap", jzfwInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wsh/jzfw/wsh_jzfw_detail";
	}
		
}
