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
 * [智慧社区]微生活其它服务
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/wsh/qtfw/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wsh/qtfw")
public class WshQtfwController extends BaseController {
	
	@Resource
	private RZTJService rztjService;
	
	//初始化其它服务
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
		requestMap.put("serviceType", "2");//服务类型(0家政服务1餐饮服务2其他服务)
		Map<String, Object> qtfwMap=null;
		try {
			qtfwMap = rztjService.getRZTJListMap(requestMap);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> qtfwList = (List<Map<String, Object>>) qtfwMap.get("result");
			if(qtfwList!=null&&qtfwList.size()>0){
				for(Map<String,Object> map : qtfwList){
					String title = (String) map.get("title");
					if(title.length() > 10){
						title = title.substring(0,10)+"...";
					}
					map.put("title", title);
				}
			}
			request.setAttribute("qtfwList", qtfwList);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/wsh/qtfw/wsh_qtfw_list";
	}
	
	//获取其它服务详情
	@RequestMapping(value = "/getQtfwDetail")
	public String getQtfwDetail(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> qtfwInfoMap = rztjService.getRZTJInfoMap(requestMap);
			request.setAttribute("qtfwInfoMap", qtfwInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wsh/qtfw/wsh_qtfw_detail";
	}
		
}
