package com.gw.zhsq.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gw.zhsq.web.service.NoticesInfoService;

/**
 * 公告详情
 * @author fuyun
 * 2015-4-3
 * 请求地址：http://localhost:8080/Weixin/noticesInfo/getNoticesInfo.htm
 */
@Controller
@RequestMapping("/noticesInfo")
public class NoticesInfoController extends BaseController {
	
	@Resource
	private NoticesInfoService noticesInfoService;
	
	//公告详情
	@RequestMapping(value = "/getNoticesInfo")
	public String getNoticesInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			//活动公告
			Map<String, Object> noticesMap = noticesInfoService.getNoticesInfoMap(requestMap);
			System.out.println("noticesMap======>>"+noticesMap);
			//过滤html标签
			if(noticesMap!=null){
				String contentStr =(String) noticesMap.get("content");
//				String newContent = this.HtmlText(contentStr);
				noticesMap.put("newContent", contentStr);
				request.setAttribute("noticesMap", noticesMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "noticesInfo/noticesInfo_zhsq";
	}
	
}
