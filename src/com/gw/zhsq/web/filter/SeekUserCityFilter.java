package com.gw.zhsq.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 查找访问者所在城市
 * @author fuyun
 * @version 1.0
 * @created 2015-03-23
 */
public class SeekUserCityFilter implements Filter {
	
	public void init(FilterConfig config) {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException { 
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		RegionService regionMng = (RegionService)SpringContextUtil.getBean("regionsService");
		/* 获取路径关键字ckey,取得用户选择城市*/
//		HttpSession session = httpRequest.getSession();
//		String code = httpRequest.getParameter("code");
//		if(code != null && code.length() > 0) {
//			try {
//				String openid = MemberController.getOpenid(code);
//				if(null != openid && openid.length() > 0) {
//					session.setAttribute("wx_openid", openid);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		System.out.println("=====SeekUserCityFilter.doFilter=====");
//		HttpSession session = httpRequest.getSession();
//		String code = httpRequest.getParameter("code");
//		System.out.println("code=1=>"+code);
//		if((code != null) && (code.length() > 0) && (!(httpRequest.getRequestURI().endsWith("/login.htm")))){
//			try {
//				System.out.println("code=2=>"+code);
//		        String openid = SystemConstant.getOpenid(code);
//		        System.out.println("openid=2=>"+openid);
//		        if ((openid == null) || (openid.length() <= 0))
//		        	session.setAttribute("wx_openid", openid);	
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
	
//	/**
//	 * 获取指定name的cookie
//	 * @param httpRequest
//	 * @param cookiename
//	 * @return
//	 */
//	@SuppressWarnings("unused")
//	private Cookie getCookie(HttpServletRequest httpRequest,String cookiename){
//		Map<String,Cookie> csMap = readCookieMap(httpRequest);
//		if(csMap.containsKey(cookiename)){
//	        Cookie cookie = (Cookie)csMap.get(cookiename);
//	        return cookie;
//	    }else{
//	        return null;
//	    }   
//	}
//	
//	/**
//	 * 将cookie封装到Map里面
//	 * @param request
//	 * @return
//	 */
//	private  Map<String,Cookie> readCookieMap(HttpServletRequest request){  
//	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
//	    Cookie[] cookies = request.getCookies();
//	    if(null!=cookies){
//	        for(Cookie cookie : cookies){
//	            cookieMap.put(cookie.getName(), cookie);
//	        }
//	    }
//	    return cookieMap;
//	}
	
//	/**
//	 * 获取访问者真实地址(过滤代理)
//	 * @param request
//	 * @return
//	 */
//	@SuppressWarnings("unused")
//	private String getRemortIP(HttpServletRequest request) {  
//	    /*if (request.getHeader("x-forwarded-for") == null) {  
//	        return request.getRemoteAddr();  
//	    }  
//	    return request.getHeader("x-forwarded-for");  */
//		
//		String ip = request.getHeader("x-forwarded-for");
//		if(ip ==null){
//			 return request.getRemoteAddr();  
//		}
//		String[] ips = ip.split(",");
//		for(String i:ips){
//			if(i!=null&&(!i.equals("unknown"))){
//				return i;
//			}
//		}
//		return ip;
//	}   
	
//	/**
//	 * 第三方接口:通过ip获取城市信息
//	 * @param weburl
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unused")
//	private String getCityInfo(String weburl) throws Exception{
//	    URL url = new URL(weburl);
//	    // 打开连接，此处只是创建一个实例，并没有真正的连接
//		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//		httpCon.setRequestMethod("GET");
//		httpCon.connect();
//		InputStream in = httpCon.getInputStream();
//		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in,"gb2312")); 
//		StringBuffer sb = new StringBuffer();
//		 String inputLine = null;  
//		while((inputLine=bufferReader.readLine())!=null){
//			sb.append(inputLine);
//		}
//		bufferReader.close();
//		in.close();
//		httpCon.disconnect();
//		return sb.toString();
//	}
}