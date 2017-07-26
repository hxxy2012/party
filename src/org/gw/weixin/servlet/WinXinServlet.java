package org.gw.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.gw.weixin.MySecurity;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;

/**
 * 处理微信服务器请求的Servlet URL地址：http://xxx/weixin/dealwith.do
 * 注意：官方文档限制使用80端口哦！
 * @author fuyun
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class WinXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//TOKEN 是你在微信平台开发模式中设置的哦
	public static final String TOKEN = PropertiesUtil.getSetting("token");//微信公众号中的Token(令牌)

	/**
	 * 处理微信服务器验证
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串

		System.out.println("===WinXinServlet.doGet===");
		System.out.println("signature==>"+signature);
		System.out.println("timestamp==>"+timestamp);
		System.out.println("nonce==>"+nonce);
		System.out.println("echostr==>"+echostr);
		
		// 重写totring方法，得到三个参数的拼接字符串
		List<String> list = new ArrayList<String>(3) {
		private static final long serialVersionUID = 2621444383666420433L;
			public String toString() {
				return this.get(0) + this.get(1) + this.get(2);
			}
		};
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);// 排序
		String tmpStr = new MySecurity().encode(list.toString(),
				MySecurity.SHA_1);// SHA-1加密
		Writer out = response.getWriter();
		if (signature.equals(tmpStr)) {
			out.write(echostr);// 请求验证成功，返回随机码
		} else {
			out.write("");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
	 */
	@SuppressWarnings("unchecked")
	protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("==========doPost==========");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 调用核心业务类接收消息、处理消息 
		HashMap<String,Object> respMap = CoreService.processRequest(request); 
		String code = StringUtil.nvl(respMap.get("code"));
		String respMessage = StringUtil.nvl(respMap.get("respMessage"));
		if("2".equals(code)){
			String shequId = StringUtil.nvl(respMap.get("shequId"));
			Map<String, Object> memberMap = (Map<String, Object>) respMap.get("memberMap");
			if(memberMap!=null){
				HttpSession session = request.getSession();
				session.setAttribute("memberSession", memberMap);
				request.setAttribute("memberMap", memberMap);
				request.setAttribute("weixinLogin", "1");
			}
			// 响应消息 
	        PrintWriter out = response.getWriter(); 
	        out.print(respMessage); 
	        out.close();
			System.out.println("======>"+request.getContextPath()+"/member/login.htm?shequId="+shequId);
			//response.sendRedirect(request.getContextPath()+"/member/memberInfo.htm?shequId="+shequId);
			response.sendRedirect("http://weixin.gangwaninfo.com/Weixin/member/login.htm?shequId="+shequId);
			//request.getRequestDispatcher("/member/memberInfo.htm?shequId="+shequId).forward(request, response);
			//request.getRequestDispatcher("http://weixin.gangwaninfo.com/Weixin/member/memberInfo.htm?shequId="+shequId).forward(request,response);
		}else{
			// 响应消息 
	        PrintWriter out = response.getWriter(); 
	        out.print(respMessage); 
	        out.close();
		}
	}

}
