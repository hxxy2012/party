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
 * [智慧社区]微生活首页
 * @author fuyun
 * 2015-10-01
 * 请求地址：http://localhost:8080/Weixin/wsh/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wsh")
public class WshIndexController extends BaseController {

	@Resource
	private RZTJService rztjService;
	
	//微生活首页
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
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
				
			//推荐家政、推荐餐饮
			HashMap<String, String> paramMapRZ = new HashMap<String, String>();
			paramMapRZ.put("serviceType", "");
			paramMapRZ.put("memberId", "");
			paramMapRZ.put("shequId", shequId);
			Map<String, Object> fwMap=null;
			try {
				fwMap = rztjService.getRZTJListMap(paramMapRZ);
				List<Map<String,Object>> fwList = (List<Map<String, Object>>) fwMap.get("result");
				if(fwList!=null&&fwList.size()>0){
					for(Map<String,Object> map : fwList){
						String title = (String) map.get("title");
						if(title.length() > 10){
							title = title.substring(0,10)+"...";
						}
						map.put("title", title);
					}
				}
				request.setAttribute("fwList", fwList);
			} catch (OtherException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wsh_index";
	}
	
}

