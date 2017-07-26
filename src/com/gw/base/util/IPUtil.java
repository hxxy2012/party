package com.gw.base.util;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 获取来访IP帮助类
 * @author hanxu
 *	2015-04-03
 */
public class IPUtil {

	private static Logger logger = Logger.getLogger(IPUtil.class);

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 多级反向代理
		if (null != ip && !"".equals(ip.trim())) {
			StringTokenizer st = new StringTokenizer(ip, ",");
			if (st.countTokens() > 1) {
				return st.nextToken();
			}
		}
		return ip;
	}

	public static String getIpAddress(HttpServletRequest request) {
		String fromIP = request.getHeader("x-forwarded-for");
		logger.info(" get fromIP===" + fromIP);
		if (fromIP == null) {
			fromIP = request.getRemoteAddr();
		} else {
			// StringTokenizer tokenizer = new StringTokenizer(fromIP,
			// ",");
			//			
			// for (int ii = 0; ii < tokenizer.countTokens() - 1; ii++) {
			// tokenizer.nextElement();
			// }
			// fromIP = tokenizer.nextToken().trim();
			// if (fromIP.equals("")) {
			// fromIP = null;
			// }

			String[] ipStr = fromIP.split(",");
			fromIP = ipStr[0];

		}
		logger.info("fromIP==" + fromIP);
		return fromIP;
	}

	public static String getIp(final HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 多级反向代理
		if (null != ip && !"".equals(ip.trim())) {
			StringTokenizer st = new StringTokenizer(ip, ",");
			if (st.countTokens() > 1) {
				return st.nextToken();
			}
		}
		return ip;
	}
}
