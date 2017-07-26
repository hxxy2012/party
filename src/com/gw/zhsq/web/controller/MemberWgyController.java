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
import org.gw.weixin.SHA1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gw.base.constant.SystemConstant;
import com.gw.base.exception.OtherException;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.MemberWgyService;

/**
 * 网格员个人中心控制层
 * 
 * @author fuyun 2015-04-02 13:33
 *         http://localhost:8080/Weixin/member/wgy/wgyLogin.htm
 */
@Controller
@RequestMapping("/member/wgy")
public class MemberWgyController extends BaseController {

	@Resource
	private MemberWgyService memberWgyService;

	// 初始化网格员登录
	@RequestMapping({ "/init" })
	public String init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String shequId = requestMap.get("shequId");// 社区ID
		String streetId = requestMap.get("streetId");// 街道ID
		HttpSession sessions = request.getSession();
		if (StringUtils.isBlank(shequId)) {
			if (StringUtils.isBlank(streetId)) {
				streetId = StringUtil.nvl(sessions.getAttribute("streetId"));
				if (StringUtils.isBlank(streetId)) {
					streetId = SystemConstant.STREET_ID;// 默认为东台街道
				}
				if ("22".equals(streetId)) {
					if (StringUtils.isBlank(shequId)) {
						shequId = SystemConstant.DONGTAI_SHEQU_ID;
					}
				}
			}
			requestMap.put("streetId", streetId);
			request.setAttribute("streetId", streetId);
			sessions.setAttribute("streetId", streetId);

			if (StringUtils.isBlank(shequId)) {
				shequId = StringUtil.nvl(sessions.getAttribute("shequId"));
				if (StringUtils.isBlank(shequId)) {
					shequId = SystemConstant.SHEQU_ID;// 默认为金陵驿社区
				}
			}
		}
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		sessions.setAttribute("shequId", shequId);
		return "login_wgy";
	}

	// 网格员登录
	@SuppressWarnings("unchecked")
	@RequestMapping({ "/wgyLogin" })
	public String wgyLogin(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HttpSession sessions = request.getSession();
		try {
			Map<String, Object> memberWgySessionMap = (Map<String, Object>) sessions.getAttribute("memberWgySession");
			String login_name = requestMap.get("login_name");// 用户名
			String login_password = requestMap.get("login_password");// 密码
			String shequId = requestMap.get("shequId");// 社区ID
			HashMap<String, String> paramsMap = new HashMap<String, String>();

			if (StringUtils.isBlank(shequId)) {
				shequId = SystemConstant.SHEQU_ID;// 默认为金陵驿社区
			}
			paramsMap.put("dhId", shequId);
			requestMap.put("shequId", shequId);
			request.setAttribute("shequId", shequId);
			
			if (StringUtils.isBlank(login_name)) {
				if (memberWgySessionMap != null) {
					login_name = StringUtil.nvl(memberWgySessionMap.get("account"));
					paramsMap.put("account", StringUtil.nvl(memberWgySessionMap.get("account")));
				}
			} else {
				paramsMap.put("account", login_name);
			}
			if (StringUtils.isBlank(login_password)) {
				if (memberWgySessionMap != null) {
					login_password = StringUtil.nvl(memberWgySessionMap.get("password"));
					paramsMap.put("password", StringUtil.nvl(memberWgySessionMap.get("password")));
				}
			} else {
				paramsMap.put("password", login_password);
			}
			if (StringUtils.isBlank(login_name) || StringUtils.isBlank(login_password)) {
				request.setAttribute("memberWgyMap", memberWgySessionMap);
			} else {
				Map<String, Object> memberWgyMap = memberWgyService.memberLogin(paramsMap);
				sessions.setAttribute("memberWgySession", memberWgyMap);
				if (memberWgyMap != null) {
					request.setAttribute("memberWgyMap", memberWgyMap);
					return "memberWgy/member_wgy_index";
				}
			}
			return "login_wgy";
		} catch (OtherException e) {
			// e.printStackTrace();
			request.setAttribute("error_code", e.getErrorCode());
			request.setAttribute("error_msg", e.getErrorMsg());
			return "login_wgy";
		} catch (Exception e) {
			// e.printStackTrace();
			request.setAttribute("error_code", "9999");
			request.setAttribute("error_msg", "会员登录失败，请重新登录");
			request.setAttribute("login_type", "");
			sessions.setAttribute("login_type", "");
			return "login_wgy";
		}
	}

	/**
	 * 登入验证用户名和密码匹配
	 * 
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkLogin")
	public void checkLogin(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String result = "0";
		HashMap<String, String> requestMap = findRequestMap(request);
		String login_name = requestMap.get("login_name");// 用户名
		String login_password = requestMap.get("login_password");// 密码
		String shequId = requestMap.get("shequId");// 社区ID
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", login_name);
		if (StringUtils.isBlank(login_password)) {
			login_password = SystemConstant.MEMBER_PASSWORD;
		}
		map.put("password", login_password);
		if (StringUtils.isBlank(shequId)) {
			shequId = SystemConstant.SHEQU_ID;// 默认为金陵驿社区
		}
		map.put("dhId", shequId);
		try {
			Map<String, Object> memberMap = memberWgyService.memberLogin(map);
			if (memberMap != null) {
				result = "1";
			}
		} catch (OtherException e) {
			// e.printStackTrace();
		}
		ajaxJson(result, response);
	}

	// 网格员首页
	@RequestMapping({ "/index" })
	public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "/memberWgy/member_wgy_index";
	}

	// 网格员意见反馈
	@RequestMapping({ "/getYJFKList" })
	public String getYJFKList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, String> requestMap = findRequestMap(request);
		Map<String, Object> yjfkMap = null;
		HttpSession memberSessions = request.getSession();
		try {
			String shequId = requestMap.get("shequId");// 社区ID
			String resolve = requestMap.get("resolve");// 状态类型 0未处理 1已处理 2无需处理
			HttpSession shequSessions = request.getSession();
			/*
			 * if(StringUtils.isBlank(shequId)){ shequId =
			 * StringUtil.nvl(shequSessions.getAttribute("shequId"));
			 * if(StringUtils.isBlank(shequId)){ shequId =
			 * SystemConstant.SHEQU_ID;//默认为金陵驿社区 } }
			 */
			requestMap.put("shequId", shequId);
			request.setAttribute("shequId", shequId);
			shequSessions.setAttribute("shequId", shequId);
			request.setAttribute("resolve", resolve);
			requestMap.put("resolve", resolve);
			@SuppressWarnings("unchecked")
			Map<String, Object> memberWgySessionMap = (Map<String, Object>) memberSessions
					.getAttribute("memberWgySession");
			if (memberWgySessionMap == null) {
				// request.setAttribute("login_type", "jbtsList");
				return "login_wgy";
			} else {
				// requestMap.put("memberId",
				// StringUtil.nvl(memberSessionMap.get("id")));
				yjfkMap = memberWgyService.getYJFKListMap(requestMap);
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> yjfkList = (List<Map<String, Object>>) yjfkMap.get("result");
				if (yjfkList != null && yjfkList.size() > 0) {
					for (Map<String, Object> map : yjfkList) {
						String content = (String) map.get("content");
						if (content.length() > 30) {
							content = content.substring(0, 30) + "...";
						}
						map.put("content", content);
					}
				}
				request.setAttribute("yjfkList", yjfkList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "memberWgy/member_wgy_yjfk";
	}

	// 异步取得网格员意见反馈列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getJBTSListAsync")
	@ResponseBody
	public void getYJFKListAsync(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
		try {

			Map<String, Object> newsMap = memberWgyService.getYJFKListMap(requestMap);
			newsList = (List<Map<String, Object>>) newsMap.get("result");
			if (newsList != null && newsList.size() > 0) {
				for (Map<String, Object> map : newsList) {
					String content = (String) map.get("content");
					if (content.length() > 30) {
						content = content.substring(0, 30) + "...";
					}
					map.put("content", content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out;
		try {
			response.setContentType("text/json;charset=UTF-8");
			Map map = new HashMap();
			map.put("dataList", newsList);
			map.put("size", (newsList == null) ? 0 : newsList.size());
			JSONObject obj = JSONObject.fromObject(map);
			out = response.getWriter();
			out.write(obj.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取网格员意见反馈详情
	@RequestMapping(value = "/getYJFKInfo")
	public String getYJFKInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		String toPage = "";
		try {
			Map<String, Object> yjfkInfoMap = memberWgyService.getYJFKInfoMap(requestMap);
			request.setAttribute("yjfkInfoMap", yjfkInfoMap);
			String resolve = StringUtil.nvl(yjfkInfoMap.get("resolve"));
			if ("1".equals(resolve)) {
				toPage = "memberWgy/member_wgy_yjfk_end";
			} else if ("0".equals(resolve)) {
				toPage = "memberWgy/member_wgy_yjfk_start";
			} else {
				toPage = "memberWgy/member_wgy_yjfk_no";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toPage;
	}

	// 网格员意见反馈 【未处理】
	@RequestMapping({ "/start" })
	public String start(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "memberWgy/member_wgy_yjfk_start";
	}

	// 网格员意见反馈 【处理中】
	@SuppressWarnings("unchecked")
	@RequestMapping({ "/underway" })
	public String underway(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession memberSessions = request.getSession();
		HashMap<String, String> requestMap = findRequestMap(request);
		String jbtsId = requestMap.get("jbtsId");// 意见反馈ID
		String shequId = requestMap.get("shequId");// 社区ID
		HttpSession shequSessions = request.getSession();
		if (StringUtils.isBlank(shequId)) {
			shequId = StringUtil.nvl(shequSessions.getAttribute("shequId"));
			if (StringUtils.isBlank(shequId)) {
				shequId = SystemConstant.SHEQU_ID;// 默认为金陵驿社区
			}
		}
		requestMap.put("shequId", shequId);
		request.setAttribute("shequId", shequId);
		shequSessions.setAttribute("shequId", shequId);
		// String streetId = requestMap.get("streetId");//街道ID
		// if(StringUtils.isBlank(streetId)){
		// streetId = StringUtil.nvl(memberSessions.getAttribute("streetId"));
		// if(StringUtils.isBlank(streetId)){
		// streetId = SystemConstant.STREET_ID;//默认为东台街道
		// }
		// if(StringUtils.isNotBlank(shequId)){
		//
		// }else if("22".equals(streetId)){
		// if(StringUtils.isBlank(shequId)){
		// shequId = SystemConstant.DONGTAI_SHEQU_ID;
		// }
		// }
		// request.setAttribute("shequId", shequId);
		// memberSessions.setAttribute("shequId", shequId);
		// }
		// requestMap.put("streetId", streetId);
		// request.setAttribute("streetId", streetId);
		// memberSessions.setAttribute("streetId", streetId);
		Map<String, Object> memberWgySessionMap = (Map<String, Object>) memberSessions.getAttribute("memberWgySession");
		try {
			HashMap<String, String> parMap = new HashMap<String, String>();
			parMap.put("jbtsId", jbtsId);
			Map<String, Object> yjfkInfoMap = memberWgyService.getYJFKInfoMap(parMap);
			request.setAttribute("yjfkInfoMap", yjfkInfoMap);
			request.setAttribute("memberWgyMap", memberWgySessionMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String appid = PropertiesUtil.getSetting("weixin_appid_" + shequId);// 微信APPID
		String secret = PropertiesUtil.getSetting("weixin_secret_" + shequId);// 微信AppSecret(应用密钥)
		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;// 微信签名随机字符串
		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;// 微信签名时间戳
		String weixin_jbts_url = SystemConstant.WEIXIN_UNDERWAY_JBTS_URL + "?jbtsId=" + jbtsId;// 微信签名url地址
		try {
			String signature = "";
			// 获取access_token
			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ appid + "&secret=" + secret;
			JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
			String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
			if (StringUtils.isNotBlank(access_token)) {
				// 获取jsapi_ticket
				String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
						+ access_token + "&type=jsapi";
				JSONObject jsapi_ticket_obj = JSONObject
						.fromObject(SystemConstant.getURLString(jsapi_ticket_url, null));
				String jsapi_ticket = StringUtil.nvl(jsapi_ticket_obj.get("ticket"));
				if (StringUtils.isNotBlank(jsapi_ticket)) {
					String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + weixin_noncestr + "&timestamp="
							+ weixin_timestamp + "&url=" + weixin_jbts_url;
					System.out.println("string1==>" + string1);
					// 生成签名
					signature = SHA1.encode(string1);
				}

				System.out.println("appid==>" + appid);
				System.out.println("access_token==>" + access_token);
				System.out.println("jsapi_ticket==>" + jsapi_ticket);
				System.out.println("signature==>" + signature);
				System.out.println("weixin_noncestr==>" + weixin_noncestr);
				System.out.println("weixin_timestamp==>" + weixin_timestamp);
				System.out.println("weixin_jbts_url==>" + weixin_jbts_url);

				request.setAttribute("appid", appid);
				request.setAttribute("signature", signature);
				request.setAttribute("jsapi_ticket", jsapi_ticket);
				request.setAttribute("access_token", access_token);
				request.setAttribute("weixin_noncestr", weixin_noncestr);
				request.setAttribute("weixin_timestamp", weixin_timestamp);
				request.setAttribute("weixin_jbts_url", weixin_jbts_url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "memberWgy/member_wgy_yjfk_underway";
	}

	// 网格员处理意见反馈
	@RequestMapping(value = "/subYJFK")
	public String subYJFK(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
		HashMap<String, String> paramMap = new HashMap<String, String>();

		String staffName = requestMap.get("staffName");// 处理说明
		String resolve = requestMap.get("resolve");// 处理状态
		String solution = requestMap.get("solution");// 处理说明
		String jbtsId = requestMap.get("jbtsId");
		String shequId = requestMap.get("shequId");

		String imagepaths = "";// 图片
		String jbtsImg1 = requestMap.get("jbtsImg1");// 图片
		String jbtsImg2 = requestMap.get("jbtsImg2");// 图片
		String jbtsImg3 = requestMap.get("jbtsImg3");// 图片
		String jbtsImg4 = requestMap.get("jbtsImg4");// 图片
		String jbtsImg5 = requestMap.get("jbtsImg5");// 图片
		String jbtsImg6 = requestMap.get("jbtsImg6");// 图片
		String jbtsImg7 = requestMap.get("jbtsImg7");// 图片
		String jbtsImg8 = requestMap.get("jbtsImg8");// 图片
		String jbtsImg9 = requestMap.get("jbtsImg9");// 图片

		if (StringUtils.isNotBlank(jbtsImg1)) {
			imagepaths = imagepaths + jbtsImg1 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg2)) {
			imagepaths = imagepaths + jbtsImg2 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg3)) {
			imagepaths = imagepaths + jbtsImg3 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg4)) {
			imagepaths = imagepaths + jbtsImg4 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg5)) {
			imagepaths = imagepaths + jbtsImg5 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg6)) {
			imagepaths = imagepaths + jbtsImg6 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg7)) {
			imagepaths = imagepaths + jbtsImg7 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg8)) {
			imagepaths = imagepaths + jbtsImg8 + "_";
		}
		if (StringUtils.isNotBlank(jbtsImg9)) {
			imagepaths = imagepaths + jbtsImg9 + "_";
		}

		paramMap.put("resolve", resolve);
		paramMap.put("solution", solution);
		paramMap.put("staffName", staffName);
		paramMap.put("jbtsId", jbtsId);
		paramMap.put("imagepaths", imagepaths);
		String toPage = "";
		try {
			Map<String, Object> jbtsMap = memberWgyService.subYJFKMap(paramMap);
			request.setAttribute("shequId", shequId);
			String success = StringUtil.nvl(jbtsMap.get("success"));
			if ("0".equals(success)) {
				toPage = "/memberWgy/member_wgy_succe";
			} else {
				toPage = "/memberWgy/member_wgy_erro";
			}

		} catch (OtherException e) {
			e.printStackTrace();
		}

		return toPage;
	}

	// 网格员意见反馈 【已处理】
	@RequestMapping({ "/end" })
	public String end(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "memberWgy/member_wgy_yjfk_end";
	}
}
