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
 * [智慧社区]微生活餐饮服务
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/wsh/cyfw/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wsh/cyfw")
public class WshCyfwController extends BaseController {
	
	@Resource
	private RZTJService rztjService;
	
	//初始化餐饮服务
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
		requestMap.put("serviceType", "1");//服务类型(0家政服务1餐饮服务2其他服务)
		Map<String, Object> cyfwMap=null;
		try {
			cyfwMap = rztjService.getRZTJListMap(requestMap);
			List<Map<String,Object>> cyfwList = (List<Map<String, Object>>) cyfwMap.get("result");
			if(cyfwList!=null&&cyfwList.size()>0){
				for(Map<String,Object> map : cyfwList){
					String title = (String) map.get("title");
					if(title.length() > 10){
						title = title.substring(0,10)+"...";
					}
					map.put("title", title);
				}
			}
			request.setAttribute("cyfwList", cyfwList);
		} catch (OtherException e) {
			e.printStackTrace();
		}
		return "/wsh/cyfw/wsh_cyfw_list";
	}
	
	//获取餐饮服务详情
	@RequestMapping(value = "/getCyfwDetail")
	public String getCyfwDetail(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> cyfwInfoMap = rztjService.getRZTJInfoMap(requestMap);
			request.setAttribute("cyfwInfoMap", cyfwInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wsh/cyfw/wsh_cyfw_detail";
	}
	
}
