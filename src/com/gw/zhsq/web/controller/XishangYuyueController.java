package com.gw.zhsq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gw.base.exception.OtherException;
import com.gw.base.util.SendCodeUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.XishanYuyueAdminService;
import com.gw.zhsq.web.service.XishanYuyueService;

import net.sf.json.JSONObject;

/**
 * 【锡山经济技术开发区建设城管】在线预约
 * 
 * @author lihui 
 * 2016-05-18
 * 请求地址：http://localhost:8080/Weixin/xishan/yuyue/init.htm
 */
@Controller
@RequestMapping("/xishan/yuyue")
public class XishangYuyueController extends BaseController {

	@Resource
	private XishanYuyueService xishanYuyueService;
	@Resource
	private XishanYuyueAdminService xishanYuyueAdminService;

	/**
	 * 初始化在线预约
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/init")
	public String init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		sessions.setAttribute("dayMapList", afterNDay());
		return "xishan/yuyue/xishan_yuyue";
	}

	// 获取当前日期后5个工作日的日期
	public List<Map<String, Object>> afterNDay() {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		List<Map<String, Object>> dayMapList = new ArrayList<Map<String, Object>>();
		Calendar calendar = Calendar.getInstance();
		DateFormat temp_day = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(new Date());
		for (int i = 1; i < 8; i++) {
			Map<String, Object> dayMap = new HashMap<String, Object>();
			String day = null;
			calendar.add(Calendar.DATE, 1);
			Date d2 = calendar.getTime();
			Calendar calendar_temp = Calendar.getInstance();
			calendar_temp.setTime(d2);
			int w = calendar_temp.get(Calendar.DAY_OF_WEEK) - 1;
			if (w == 0 || w == 6) {
				continue;
			} else {
				day = temp_day.format(d2);
				dayMap.put("day", day + "(" + weekDays[w] + ")");
				dayMapList.add(dayMap);
			}
		}
		return dayMapList;
	}

	/**
	 * 提交在线预约
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/yuyuesuccess")
	public String getYuyueinfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String toPage = "xishan/yuyue/xishan_yuyue_fail";
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession sessions = request.getSession();
		Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");

		String memberId = "";
		String companyName = requestMap.get("companyName");// 预约单位
		String name = requestMap.get("name");// 姓名
		String phone = requestMap.get("phone");// 手机
		String yuyueDate = requestMap.get("yuyueDate");// 日期
		String yuyueTime = requestMap.get("yuyueTime");// 时间
		String action = requestMap.get("action");// 预约事项类型(施工许可、道口开设)
		String idcard = requestMap.get("idcard");// 身份证
		String testnumber = requestMap.get("testnumber");// 输入验证码
		HashMap<String, String> codeMap = (HashMap<String, String>) sessions.getAttribute("codeMap");
		String sysCode = codeMap.get("code");
		if (sysCode.equals(testnumber)) {
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("companyName", companyName);
			paramMap.put("name", name);
			paramMap.put("phone", phone);
			paramMap.put("yuyueDate", yuyueDate);
			paramMap.put("yuyueTime", yuyueTime);
			paramMap.put("action", action);
			paramMap.put("idcard", idcard);

			try {
				Map<String, Object> yuyueMap = xishanYuyueService.subXishanYuyue(paramMap);
				String success = StringUtil.nvl(yuyueMap.get("success"));
				Map<String, Object> adminMap = null;
				List<Map<String, Object>> adminList = new ArrayList<Map<String, Object>>();
				if ("0".equals(success)) {
					/**
					 * 短信提醒
					 * 获取系统中预约提醒人员的手机号，并发送短信
					 * XishanYuyueAdminService
					 */
					adminMap = xishanYuyueAdminService.getXishanYuyueAdminListMap(requestMap);
					adminList = (List<Map<String, Object>>) adminMap.get("result");
					for (int i = 0; i < adminList.size(); i++) {
						String admin_phone = (String) adminList.get(i).get("phone");
						System.out.println("预约提醒人员========>" + admin_phone);
						//发送短信提醒
						String result ="";
						int type = 6;
						try {
							result = SendCodeUtil.sendYuyueCode(admin_phone, phone, companyName, yuyueDate, yuyueTime, action, "", type);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if("0".equals(result)){
							System.out.println("发送短信提醒成功。");
						}else{
							System.out.println("发送短信提醒失败。");
						}
					}
					toPage = "xishan/yuyue/xishan_yuyue_success";
				}
			} catch (OtherException e) {
				e.printStackTrace();
			}
		}
		return toPage;
	}

	/**
	 * 获取预约信息列表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return http://localhost:8080/Weixin/xishan/yuyue/getXishanYuyueList.htm
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/getXishanYuyueList")
	public String getXishanYuyueList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> yuyueMap = null;
		List<Map<String, Object>> yuyueList = new ArrayList<Map<String, Object>>();
		HttpSession session = request.getSession();
		try {
			HashMap<String, String> requestMap = new HashMap<String, String>();
			yuyueMap = xishanYuyueService.getXishanYuyueListMap(requestMap);
			yuyueList = (List<Map<String, Object>>) yuyueMap.get("result");
			request.setAttribute("yuyueList", yuyueList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xishan/yuyue/xishan_yuyue_list";
	}

	/**
	 * 异步获取预约信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getXishanYuyueListAsync")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getXishanYuyueListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> yuyueList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> yuyueMap = xishanYuyueService.getXishanYuyueListMap(requestMap);
			yuyueList = (List<Map<String, Object>>) yuyueMap.get("result");

		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", yuyueList);
			map.put("size", (yuyueList == null) ? 0 : yuyueList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取预约详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getXishanYuyueInfo")
	public String getXishanYuyueInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> yuyueInfoMap = xishanYuyueService.getXishanYuyueInfoMap(requestMap);
			request.setAttribute("yuyueInfoMap", yuyueInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xishan/yuyue/xishan_yuyue_info";
	}

}
