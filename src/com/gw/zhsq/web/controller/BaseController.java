package com.gw.zhsq.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Controller基类
 * @author fuyun 
 * 2015-02-05
 */
public class BaseController {
	
	// 日志时需要忽略的字段
	private static final HashSet<String> paramsToOmit = new HashSet<String>(); 
	static {
		paramsToOmit.add("loginPassword");
		paramsToOmit.add("loginPasswordChk");
		paramsToOmit.add("password");
		paramsToOmit.add("oldPassword");
		paramsToOmit.add("newPassword");
	}

	public static final String DATA = "data";
	public static final String ERROR = "error";
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String ERRORMSG = "errormsg";
	public static final String ERRORCODE = "errorcode";
	
	/**
	 * 将request里面访问的所有参数转成HasMap
	 * @param request
	 * @return HashMap<String, String>
	 */
	@SuppressWarnings("rawtypes")
	protected HashMap<String, String> findRequestMap(HttpServletRequest request) {
		HashMap<String, String> requestMap = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			requestMap.put(name, valueStr);
		}
		return requestMap;
	}
	
	
	// 输出map正确消息
		public Map<String, Object> successResult(Object data) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ERRORCODE, "0000");
			map.put(ERRORMSG, "");
			map.put(DATA, data);
			return map;
		}

		// 输出JSON错误消息，返回null
		public String ajaxJsonErrorMessage(String message, HttpServletResponse response) {
			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put(STATUS, ERROR);
			jsonMap.put(MESSAGE, message);
			JSONObject jsonObject = JSONObject.fromObject(jsonMap);
			return ajax(jsonObject.toString(), "text/html", response);
		}
		
		// 输出map正确消息
		public Map<String, Object> successResult(String errorCode, String errorMsg, Object data) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ERRORCODE, errorCode);
			map.put(ERRORMSG, errorMsg);
			map.put(DATA, data);
			return map;
		}

		// 输出map错误消息
		public Map<String, Object> errorResult(String errorCode, String errorMsg) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(ERRORCODE, errorCode);
			map.put(ERRORMSG, errorMsg);
			return map;
		}
			
		// AJAX输出文本，返回null
		public String ajaxText(String text, HttpServletResponse response) {
			return ajax(text, "text/plain", response);
		}
		// AJAX输出HTML，返回null
		public String ajaxHtml(String html, HttpServletResponse response) {
			return ajax(html, "text/html", response);
		}
		// 根据字符串输出JSON，返回null
		public String ajaxJson(String jsonString, HttpServletResponse response) {
			return ajax(jsonString, "text/html", response);
		}
		// 根据Map输出JSON，返回null
		public String ajaxJson(Map<String,Object> jsonMap, HttpServletResponse response) {
			JSONObject jsonObject = JSONObject.fromObject(jsonMap);
			return ajax(jsonObject.toString(), "text/html", response);
		}
		// 根据HashMap输出JSON，返回null
		public String ajaxJson(HashMap<String,String> jsonMap, HttpServletResponse response) {
			JSONObject jsonObject = JSONObject.fromObject(jsonMap);
			return ajax(jsonObject.toString(), "text/html", response);
		}
		// 根据List输出JSON，返回null
		public String ajaxJson(List<HashMap<String, String>> list, HttpServletResponse response) {
			JSONArray jsonArray = JSONArray.fromObject(list);
			return ajax(jsonArray.toString(), "text/html", response);
		}
		// 根据List输出JSON，返回null
		public String ajaxJsonListObject(List<Map<String, Object>> list,
				HttpServletResponse response) {
			JSONArray jsonArray = JSONArray.fromObject(list);
			return ajax(jsonArray.toString(), "text/html", response);
		}
		
		// AJAX输出，返回null
		public String ajax(String content, String type, HttpServletResponse response) {
			try {
				response.setContentType(type + ";charset=UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().write(content);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		/**
		 * 得到请求参数
		 * @param request
		 */
		@SuppressWarnings("rawtypes")
		protected String getParams(HttpServletRequest request) {
			StringBuilder sb = new StringBuilder();
			Map params = request.getParameterMap();
			for (Object key : params.keySet()) {
				Object[] objs = null;
				if (null != params.get(key))
					objs = (Object[]) params.get(key);

				sb.append(key).append(":");
				if (objs != null) {
					// 密码等不能写入日志！！
					if (paramsToOmit.contains(key)) {
						sb.append("*****/");
					} else {
						for (Object object : objs) {
							sb.append(object).append("/");
						}
					}
				}
				sb.append(",");
			}
			return sb.toString();
		}
		
		
		/**
		 * 将requet里面的值放进 Attribute里面
		 * @param request
		 * @return HashMap<String, String>
		 */
		@SuppressWarnings("rawtypes")
		protected void setAttribute(HashMap<String, String> requestParams,
				HttpServletRequest request) {
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String values = requestParams.get(name);
				request.setAttribute(name, values);
			}

		}	
	
}