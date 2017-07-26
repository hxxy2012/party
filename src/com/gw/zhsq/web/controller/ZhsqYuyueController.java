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
import com.gw.zhsq.web.service.AdvService;
import com.gw.zhsq.web.service.ZhsqYuyueService;

import net.sf.json.JSONObject;

/**
 * 社区预约
 * 
 * @author lihui 2016-04-21
 * http://localhost:8080/Weixin/zhsq/yuyue/init.htm?shequId=321
 */
@Controller
@RequestMapping("/zhsq/yuyue")
public class ZhsqYuyueController extends BaseController {

	@Resource
	private AdvService advService;
	@Resource
	private ZhsqYuyueService zhsqYuyueService;

	/**
	 * 初始化社区预约
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
			request.setAttribute("login_type", "zhsqYuyue");
			sessions.setAttribute("login_type", "zhsqYuyue");
			return "redirect:/member/login.htm";
		} else {
			HashMap<String, String> requestMap = findRequestMap(request);
			String shequId = requestMap.get("shequId");// 社区ID
			if (StringUtils.isBlank(shequId)) {
				shequId = StringUtil.nvl(sessions.getAttribute("shequId"));
				
				//顶部广告
				HashMap<String, String> paramMaps19 = new HashMap<String, String>();
				paramMaps19.put("bgLocation", "19");
				paramMaps19.put("shequId", shequId);//社区ID
				List<Map<String, Object>> advTop = advService.getZhsqAdvList(paramMaps19);
				String topImg = "";
				if(advTop!=null&&advTop.size()>0){
					Map<String, Object> map = advTop.get(0);
					Map<String, Object> paramMapss = (Map<String, Object>) map.get("paramMap");
					topImg = (String) paramMapss.get("imagePaths");
				}
				request.setAttribute("topImg", topImg);
				
				if (StringUtils.isBlank(shequId)) {
					shequId = SystemConstant.DONGTAI_SHEQU_ID;// 东台默认东园社区ID
				}
			}
			requestMap.put("shequId", shequId);
			request.setAttribute("shequId", shequId);
			sessions.setAttribute("dayMapList", afterNDay());
			return "wfw/zhsqYuyue/zhsq_yuyue";
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
	 * 提交社区预约
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/yuyuesuccess")
	public String getYuyueinfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String toPage = "wfw/zhsqYuyue/zhsq_yuyue_fail";
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession session = request.getSession();
		String shequId = (String) session.getAttribute("shequId");// 社区ID
		Map<String, Object> memberSessionMap = (Map<String, Object>) session.getAttribute("memberSession");
		if (StringUtils.isBlank(shequId)) {
			shequId = SystemConstant.DONGTAI_SHEQU_ID;
			session.setAttribute("shequId", shequId);
		}
		String memberId = "";
		if (memberSessionMap == null) {
			request.setAttribute("login_type", "zhsqYuyue");
			session.setAttribute("login_type", "zhsqYuyue");
			// return "login";
			return "redirect:/member/login.htm";
		} else {
			String token = requestMap.get("token");
			String session_token = StringUtil.nvl(session.getAttribute("token"));
			if(token.equals(session_token)){
				session.removeAttribute("token");
				String weixinAccessToken = "";
				String weixinAppId = PropertiesUtil.getSetting("weixin_appid_" + shequId);// 微信APPID
				String weixinSecret = PropertiesUtil.getSetting("weixin_appid_" + shequId);// 微信AppSecret(应用密钥)
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
				String openId = StringUtil.nvl(session.getAttribute("openId"));// 微信openId
				String name = requestMap.get("name");// 姓名
				String phone = requestMap.get("phone");// 手机
				String yuyueDate = requestMap.get("yuyueDate");// 日期
				String yuyueTime = requestMap.get("yuyueTime");// 时间
				String action = requestMap.get("action");// 事项
				String idcard = requestMap.get("idcard");// 身份证
				String testnumber = requestMap.get("testnumber");// 输入验证码
				HashMap<String, String> codeMap = (HashMap<String, String>) session.getAttribute("codeMap");
				String sysCode = codeMap.get("code");
				if (sysCode.equals(testnumber)) {
					HashMap<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("memberId", memberId);
					paramMap.put("weixinAppId", weixinAppId);
					paramMap.put("weixinSecret", weixinSecret);
					paramMap.put("weixinAccessToken", weixinAccessToken);
					paramMap.put("openId", openId);
					paramMap.put("shequId", shequId);
					paramMap.put("name", name);
					paramMap.put("phone", phone);
					paramMap.put("yuyueDate", yuyueDate);
					paramMap.put("yuyueTime", yuyueTime);
					paramMap.put("action", action);
					paramMap.put("idcard", idcard);
					
//					try {
//						Map<String, Object> yuyueMap = zhsqYuyueService.subShequYuyue(paramMap);
//						String success = StringUtil.nvl(yuyueMap.get("success"));
//						if ("0".equals(success)) {
//							request.setAttribute("shequId", shequId);
//							toPage = "wfw/zhsqYuyue/zhsq_yuyue_success";
//						}
//					} catch (OtherException e) {
//						e.printStackTrace();
//					}

					try {
						Map<String, Object> yuyueMap = zhsqYuyueService.subShequYuyue(paramMap);
						String success = StringUtil.nvl(yuyueMap.get("success"));
						if ("0".equals(success)) {
							request.setAttribute("shequId", shequId);
							toPage = "wfw/zhsqYuyue/zhsq_yuyue_success";

							// ========== 预约成功推送消息 start ==========//
							try {
								String template_id = PropertiesUtil
										.getSetting("weixin_jiedao_yuyue_templateid_" + shequId);// 微信APPID
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
			}else{
				toPage = "wfw/zhsqYuyue/zhsq_yuyue_fail";
			}
		}
		return toPage;
	}

	/**
	 * 获取社区预约信息列表【会员中心】
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * http://localhost:8080/Weixin/zhsq/yuyue/getShequYuyueList.htm?shequId=321
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getShequYuyueList")
	public String getShequYuyueList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
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
				String shequId = requestMap.get("shequId");// 社区ID
				requestMap.put("shequId", shequId);
				requestMap.put("memberId", StringUtil.nvl(memberSessionMap.get("id")));// 会员ID
				yuyueMap = zhsqYuyueService.getShequYuyueListMap(requestMap);
				yuyueList = (List<Map<String, Object>>) yuyueMap.get("result");
				request.setAttribute("yuyueList", yuyueList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wfw/zhsqYuyue/zhsq_yuyue_list";
	}

	/**
	 * 异步获取预约信息
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getShequYuyueListAsync")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getShequYuyueListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> yuyueList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> yuyueMap = zhsqYuyueService.getShequYuyueListMap(requestMap);
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
	 * 获取社区预约详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getShequYuyueInfo")
	public String getShequYuyueInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		try {
			Map<String, Object> yuyueInfoMap = zhsqYuyueService.getShequYuyueInfoMap(requestMap);
			request.setAttribute("yuyueInfoMap", yuyueInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wfw/zhsqYuyue/zhsq_yuyue_info";
	}

}