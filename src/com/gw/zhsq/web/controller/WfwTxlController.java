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
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.TXLService;

/**
 * 智慧社区微信社区通讯录
 * @author hanxu
 *	2015-10-22
 *	http://localhost:8080/Weixin/wfw/txl/getTXLList.htm?shequId=202
 */
@Controller
@RequestMapping("/wfw/txl")
public class WfwTxlController extends BaseController {
	@Resource
	private AdvService advService;
	@Resource
	private TXLService txlService;
	
	//获取通讯录列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getTXLList")
	public String getTXLList(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
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
			
			Map<String, Object> txlMap = txlService.getTXLListMap(requestMap);
			List<Map<String,Object>> txlList = (List<Map<String, Object>>) txlMap.get("result");
			if(txlList!=null&&txlList.size()>0){
				for(Map<String,Object> map : txlList){
					String mark = (String) map.get("mark");
					if(mark.length() > 30){
						mark = mark.substring(0,30)+"...";
					}
					map.put("mark", mark);
				}
			}
			//顶部广告
			HashMap<String, String> paramMaps21 = new HashMap<String, String>();
			paramMaps21.put("bgLocation", "21");
			paramMaps21.put("shequId", shequId);//社区ID
			List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps21);
			String topImg = "";
			if(advTop!=null&&advTop.size()>0){
				Map<String, Object> map = advTop.get(0);
				Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
				topImg = (String) paramMapss.get("imagePaths");
			}
			request.setAttribute("topImg", topImg);
			request.setAttribute("txlList", txlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wfw/txl/txl_list";
	}
	
	//异步取得通讯录列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getTXLListAsync")
	@ResponseBody
	public void getTXLListAsync(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList=new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> newsMap = txlService.getTXLListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if(newsList!=null&&newsList.size()>0){
				for(Map<String,Object> map : newsList){
					String mark = (String) map.get("mark");
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
	
	//获取通讯录分类
//	@RequestMapping(value = "/init")
//	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, String> requestMap = findRequestMap(request);
//		HashMap<String, String> paramMap = new HashMap<String, String>();
//		//社区ID
//		String shequId = requestMap.get("shequId");
//		HttpSession shequSessions = request.getSession();
//		if(StringUtils.isBlank(shequId)){
//			shequId = SystemConstant.SHEQU_ID;//默认为金陵驿社区
//		}
//		requestMap.put("shequId", shequId);
//		shequSessions.setAttribute("shequId", shequId);
//		paramMap.put("type", "1");
//		paramMap.put("shequId", shequId);
//		//获取报修列表
//		requestMap.put("memberId", "");
//		Map<String, Object> gzbxMap=null;
//		Map<String, Object> shequMap=null;
//		try {
//			shequMap = gzbxService.getShequInfoMap(paramMap);
//			gzbxMap = gzbxService.getGZBXListMap(requestMap);
//			@SuppressWarnings("unchecked")
//			List<Map<String,Object>> gzbxList = (List<Map<String, Object>>) gzbxMap.get("result");
//			if(gzbxList!=null&&gzbxList.size()>0){
//				for(Map<String,Object> map : gzbxList){
//					String content = (String) map.get("content");
//					if(content.length() > 30){
//						content = content.substring(0,30)+"...";
//					}
//					map.put("content", content);
//				}
//			}
//			request.setAttribute("gzbxList", gzbxList);
//			request.setAttribute("shequMap", shequMap);
//		} catch (OtherException e) {
//			e.printStackTrace();
//		}
//		
//		return "/wfw/gzbx/wfw_gzbx";
//	}
	
	
	//获取通讯录详情
//	@RequestMapping(value = "/getGZBXInfo")
//	public String getGZBXInfo(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
//		HashMap<String, String> requestMap = findRequestMap(request);
//		try {
//			Map<String, Object> gzbxInfoMap = gzbxService.getGZBXInfoMap(requestMap);
//			System.out.println("gzbxInfoMap======>>"+gzbxInfoMap);
//			request.setAttribute("gzbxInfoMap", gzbxInfoMap);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "/wfw/gzbx/wfw_gzbx_info";
//	}
	
}
