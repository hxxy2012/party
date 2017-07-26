package com.gw.zhsq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gw.base.constant.SystemConstant;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.WGYService;

/**
 * 智慧社区微信社区网格员
 * @author hanxu
 *	2015-10-22
 *	http://localhost:8080/Weixin/wfw/wgy/getWGYList.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/wgy")
public class WfwWgyController extends BaseController {
	
	@Resource
	private WGYService wgyService;
	
	//获取网格员列表
	@RequestMapping(value = "/getWGYList")
	public String getWGYList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
			
			Map<String, Object> wgyMap = wgyService.getWGYListMap(requestMap);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> wgyList = (List<Map<String, Object>>) wgyMap.get("result");
			if(wgyList!=null&&wgyList.size()>0){
				for(Map<String,Object> map : wgyList){
					String mark = (String) map.get("remark");
					if(mark.length() > 30){
						mark = mark.substring(0,30)+"...";
					}
					map.put("mark", mark);
				}
			}
			request.setAttribute("wgyList", wgyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wfw/wgy/wgy_list";
	}
	
	//异步取得网格员列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getWGYListAsync")
	@ResponseBody
	public void getWGYListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> newsMap = wgyService.getWGYListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if(newsList!=null&&newsList.size()>0){
				for(Map<String,Object> map : newsList){
					String mark = (String) map.get("remark");
					if(mark.length() > 30){
						mark = mark.substring(0,30)+"...";
					}
					map.put("mark", mark);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map =new HashMap();
			map.put("dataList",newsList);
			map.put("size",(newsList==null)?0:newsList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
	
}
