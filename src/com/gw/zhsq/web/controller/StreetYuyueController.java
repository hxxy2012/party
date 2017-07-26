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
import org.apache.commons.lang.StringUtils;
import org.gw.weixin.template.HttpRequestH;
import org.gw.weixin.template.TemplateData;
import org.gw.weixin.template.WxTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gw.base.constant.SystemConstant;
import com.gw.base.exception.OtherException;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.StreetYuyueService;
import net.sf.json.JSONObject;

/**
 * 街道预约
 * 
 * @author lihui 2016-04-21
 *         http://localhost:8080/Weixin/street/yuyue/init.htm?streetId=22
 */
@Controller
@RequestMapping("/street/yuyue")
public class StreetYuyueController extends BaseController {

	@Resource
	private StreetYuyueService streetYuyueService;

	/**
	 * 初始化街道预约
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessions = request.getSession();
		Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if (memberSessionMap == null) {
			request.setAttribute("login_type", "streetYuyue");
			sessions.setAttribute("login_type", "streetYuyue");
			return "redirect:/member/login.htm";
		} else {
			HashMap<String, String> requestMap = findRequestMap(request);
			String shequId = requestMap.get("shequId");// 社区ID
			String streetId = requestMap.get("streetId");// 街道ID
			if (StringUtils.isBlank(streetId)) {
				streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
				if (StringUtils.isBlank(streetId)) {
					streetId = SystemConstant.STREET_ID;// 默认为东台街道
				}
				if ("22".equals(streetId)) {
					if (StringUtils.isBlank(shequId)) {
						shequId = SystemConstant.DONGTAI_SHEQU_ID;
					}
					request.setAttribute("shequId", shequId);
					sessions.setAttribute("shequId", shequId);
				}
			}
			requestMap.put("streetId", streetId);
			request.setAttribute("streetId", streetId);
			sessions.setAttribute("streetId", streetId);
			sessions.setAttribute("dayMapList", afterNDay());
			return "street/streetYuyue/street_yuyue";
		}
	}
	//获取当前日期后5个工作日的日期
	public List<Map<String, Object>> afterNDay() {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		List<Map<String, Object>> dayMapList = new ArrayList<Map<String, Object>>();
		Calendar calendar = Calendar.getInstance();
		DateFormat temp_day = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(new Date());
		for(int i=1;i<8;i++){
			Map<String, Object> dayMap = new HashMap<String, Object>();
			String day = null;
			calendar.add(Calendar.DATE, 1);
			Date d2 = calendar.getTime();
			Calendar calendar_temp = Calendar.getInstance();
			calendar_temp.setTime(d2);
			int w = calendar_temp.get(Calendar.DAY_OF_WEEK)-1;
			if(w == 0 || w == 6){
				continue;
			}else{
				day = temp_day.format(d2);
				dayMap.put("day", day+"("+weekDays[w]+")");
				dayMapList.add(dayMap);
			}
		}
		return dayMapList;
	}

	/**
	 * 提交街道预约
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/yuyuesuccess")
	public String getYuyueinfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String toPage = "street/streetYuyue/street_yuyue_fail";
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession sessions = request.getSession();
		String streetId = (String) sessions.getAttribute("streetId");// 街道ID
		Map<String, Object> memberSessionMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		if (StringUtils.isBlank(streetId)) {
			streetId = SystemConstant.STREET_ID;
			sessions.setAttribute("streetId", streetId);
		}
		String memberId = "";
		if (memberSessionMap == null) {
			request.setAttribute("login_type", "streetYuyue");
			sessions.setAttribute("login_type", "streetYuyue");
			// return "login";
			return "redirect:/member/login.htm";
		} else {
			String weixinAccessToken = "";
			String weixinAppId = PropertiesUtil.getSetting("weixin_jiedao_appid_" + streetId);// 微信APPID
			String weixinSecret = PropertiesUtil.getSetting("weixin_jiedao_secret_" + streetId);// 微信AppSecret(应用密钥)
			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ weixinAppId + "&secret=" + weixinSecret;
			try {
				JSONObject access_token_obj = JSONObject
						.fromObject(SystemConstant.getURLString(access_token_url, null));
				weixinAccessToken = StringUtil.nvl(access_token_obj.get("access_token"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			memberId = StringUtil.nvl(memberSessionMap.get("id"));// 会员ID
			String openId = StringUtil.nvl(sessions.getAttribute("openId"));// 微信openId
			String name = requestMap.get("name");// 姓名
			String phone = requestMap.get("phone");// 手机
			String yuyueDate = requestMap.get("yuyueDate");// 日期
			String yuyueTime = requestMap.get("yuyueTime");// 时间
			String action = requestMap.get("action");// 事项
			String idcard = requestMap.get("idcard");// 身份证
			String testnumber = requestMap.get("testnumber");// 输入验证码
			HashMap<String, String> codeMap = (HashMap<String, String>) sessions.getAttribute("codeMap");
			String sysCode = codeMap.get("code");
			if (sysCode.equals(testnumber)) {
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("memberId", memberId);
				paramMap.put("weixinAppId", weixinAppId);
				paramMap.put("weixinSecret", weixinSecret);
				paramMap.put("weixinAccessToken", weixinAccessToken);
				paramMap.put("openId", openId);
				paramMap.put("streetId", streetId);
				paramMap.put("name", name);
				paramMap.put("phone", phone);
				paramMap.put("yuyueDate", yuyueDate);
				paramMap.put("yuyueTime", yuyueTime);
				paramMap.put("action", action);
				paramMap.put("idcard", idcard);
				try {
					Map<String, Object> yuyueMap = streetYuyueService.subStreetYuyue(paramMap);
					String success = StringUtil.nvl(yuyueMap.get("success"));
					if ("0".equals(success)) {
						request.setAttribute("streetId", streetId);
						toPage = "street/streetYuyue/street_yuyue_success";

						// ========== 预约成功推送消息 start ==========//
						try {
							String template_id = PropertiesUtil
									.getSetting("weixin_jiedao_yuyue_templateid_" + streetId);// 微信APPID
							WxTemplate t = new WxTemplate();
							t.setUrl("");
							t.setTouser(openId);// openId
							t.setTopcolor("#000000");
							// t.setTemplate_id("WUZ99HT8znsLle_Lr73BL3YMlvqcT8-0QZScw5-bXWU");//模板ID
							t.setTemplate_id(template_id);// 模板ID
							Map<String, TemplateData> m = new HashMap<String, TemplateData>();
							TemplateData dept = new TemplateData();
							dept.setColor("#000000");
							dept.setValue("预约人员：" + name);
							m.put("first", dept);
							TemplateData event = new TemplateData();
							event.setColor("#000000");
							event.setValue(phone);
							m.put("keyword1", event);
							TemplateData date = new TemplateData();
							date.setColor("#000000");
							date.setValue(action);// 预约事项
							m.put("keyword2", date);
							TemplateData no = new TemplateData();
							no.setColor("#000000");
							no.setValue(yuyueDate + " " + yuyueTime);// 预约时间
							m.put("keyword3", no);
							TemplateData remark = new TemplateData();
							remark.setColor("#000000");
							remark.setValue("身份证号：" + idcard);
							m.put("remark", remark);
							t.setData(m);
							HttpRequestH.httpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
											+ weixinAccessToken, "POST", JSONObject.fromObject(t).toString());
						} catch (Exception e) {
							e.printStackTrace();
						}
						// ========== 预约成功推送消息 end ==========//
					}
				} catch (OtherException e) {
					e.printStackTrace();
				}
			}
		}
		return toPage;
	}

	/**
	 * 获取街道预约信息列表【会员中心】
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getYuyueList")
	public String getYuyueList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> yuyueMap = null;
		List<Map<String, Object>> yuyueList = new ArrayList<Map<String, Object>>();
		HttpSession session = request.getSession();
		try {
			Map<String, Object> memberSessionMap = (Map<String, Object>) session.getAttribute("memberSession");
			if (memberSessionMap == null) {
				// return "login";
				return "redirect:/member/login.htm";
			} else {
				HashMap<String, String> requestMap = new HashMap<String, String>();
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));// 会员ID
				yuyueMap = streetYuyueService.getStreetYuyueListMap(requestMap);
				yuyueList = (List<Map<String, Object>>) yuyueMap.get("result");
				request.setAttribute("yuyueList", yuyueList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "street/streetYuyue/street_yuyue_list";
	}

	/**
	 * 异步获取预约信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getYuyueListAsync")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getYuyueListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> yuyueList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> yuyueMap = streetYuyueService.getStreetYuyueListMap(requestMap);
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
	 * 获取街道预约详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getYuyueInfo")
	public String getYuyueInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> yuyueInfoMap = streetYuyueService.getStreetYuyueInfoMap(requestMap);
			request.setAttribute("yuyueInfoMap", yuyueInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "street/streetyuyue/street_yuyue_info";
	}

}