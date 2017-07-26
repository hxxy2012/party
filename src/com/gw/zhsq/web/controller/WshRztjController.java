package com.gw.zhsq.web.controller;

import java.util.HashMap;
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
import com.gw.base.constant.SystemConstant;
import com.gw.base.util.PropertiesUtil;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.service.RZTJService;

/**
 * [智慧社区]微生活认证推荐
 * @author hanxu
 *	2015-10-22
 * 请求地址：http://localhost:8080/Weixin/wsh/rztj/init.htm?shequId=202
 */
@Controller
@RequestMapping("/wsh/rztj")
public class WshRztjController extends BaseController {
	
	@Resource
	private RZTJService rztjService;
	
	//初始化认证推荐
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/init")
	public String init(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
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
		
		//获取会员登录信息
		HttpSession sessions = request.getSession();
		Map<String, Object> memberMap = (Map<String, Object>) sessions.getAttribute("memberSession");
		request.setAttribute("memberMap", memberMap);
//		String appid = SystemConstant.APPID;//微信APPID
		String appId = PropertiesUtil.getSetting("weixin_appid_"+shequId);//微信APPID
		String secret = PropertiesUtil.getSetting("weixin_secret_"+shequId);//微信AppSecret(应用密钥)
		String weixin_noncestr = SystemConstant.WEIXIN_NONCESTR;//微信签名随机字符串
		String weixin_timestamp = SystemConstant.WEIXIN_TIMESTAMP;//微信签名时间戳
		String weixin_rztj_url = SystemConstant.WEIXIN_RZTJ_URL+"?shequId="+shequId;//微信签名url地址
		try{
			String signature = "";
			//获取access_token
			String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
			JSONObject access_token_obj = JSONObject.fromObject(SystemConstant.getURLString(access_token_url, null));
			String access_token = StringUtil.nvl(access_token_obj.get("access_token"));
			if(StringUtils.isNotBlank(access_token)){
				//获取jsapi_ticket
		    	String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		    	JSONObject jsapi_ticket_obj = JSONObject.fromObject(SystemConstant.getURLString(jsapi_ticket_url, null));
				String jsapi_ticket = StringUtil.nvl(jsapi_ticket_obj.get("ticket"));
				if(StringUtils.isNotBlank(jsapi_ticket)){
					String string1 = "jsapi_ticket="+jsapi_ticket+"&noncestr="+weixin_noncestr+"&timestamp="+weixin_timestamp+"&url="+weixin_rztj_url;
					System.out.println("string1==>"+string1);
					//生成签名
			    	signature = SHA1.encode(string1);
				}
				
				System.out.println("appId==>"+appId);
				System.out.println("signature==>"+signature);
		    	System.out.println("access_token==>"+access_token);
		    	System.out.println("jsapi_ticket==>"+jsapi_ticket);
		    	System.out.println("weixin_noncestr==>"+weixin_noncestr);
		    	System.out.println("weixin_timestamp==>"+weixin_timestamp);
		    	System.out.println("weixin_rztj_url==>"+weixin_rztj_url);
		    	
		    	request.setAttribute("appId", appId);
		    	request.setAttribute("signature", signature);
		    	request.setAttribute("jsapi_ticket", jsapi_ticket);
		    	request.setAttribute("access_token", access_token);
		    	request.setAttribute("weixin_noncestr", weixin_noncestr);
				request.setAttribute("weixin_timestamp", weixin_timestamp);
				request.setAttribute("weixin_rztj_url", weixin_rztj_url);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(memberMap==null){
			request.setAttribute("login_type", "rztj");
			sessions.setAttribute("login_type", "rztj");
//			return "login";
			return "redirect:/member/login.htm";
		}else{
			return "/wsh/rztj/wsh_rztj";
		}
	}
	
	//新增认证推荐
	@RequestMapping(value = "/subRZTJ")
	public String subRZTJ(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> requestMap = findRequestMap(request);
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
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String name = requestMap.get("name");//联系人姓名
		String phone = requestMap.get("phone");//联系人手机
		String content = requestMap.get("content");//说明
		String memberId = requestMap.get("memberId");//会员ID
		String title = requestMap.get("title");//商品主题
		String address = requestMap.get("address");//详细地址
		String price = requestMap.get("price");//商品价格
		String atAccount = requestMap.get("memberName");//发布人姓名
		//String imagepaths = requestMap.get("imagepaths");//商品图片
		String serviceType = requestMap.get("serviceType");//服务类型(0家政服务1餐饮服务2其他服务)
		
		String imagepaths = "";//图片
		String rztjImg1 = requestMap.get("rztjImg1");//图片
		String rztjImg2 = requestMap.get("rztjImg2");//图片
		String rztjImg3 = requestMap.get("rztjImg3");//图片
		String rztjImg4 = requestMap.get("rztjImg4");//图片
		String rztjImg5 = requestMap.get("rztjImg5");//图片
		String rztjImg6 = requestMap.get("rztjImg6");//图片
		String rztjImg7 = requestMap.get("rztjImg7");//图片
		String rztjImg8 = requestMap.get("rztjImg8");//图片
		String rztjImg9 = requestMap.get("rztjImg9");//图片
		
		if(StringUtils.isNotBlank(rztjImg1)){
			imagepaths = imagepaths+rztjImg1+"_";
		}
		if(StringUtils.isNotBlank(rztjImg2)){
			imagepaths = imagepaths+rztjImg2+"_";
		}
		if(StringUtils.isNotBlank(rztjImg3)){
			imagepaths = imagepaths+rztjImg3+"_";
		}
		if(StringUtils.isNotBlank(rztjImg4)){
			imagepaths = imagepaths+rztjImg4+"_";
		}
		if(StringUtils.isNotBlank(rztjImg5)){
			imagepaths = imagepaths+rztjImg5+"_";
		}
		if(StringUtils.isNotBlank(rztjImg6)){
			imagepaths = imagepaths+rztjImg6+"_";
		}
		if(StringUtils.isNotBlank(rztjImg7)){
			imagepaths = imagepaths+rztjImg7+"_";
		}
		if(StringUtils.isNotBlank(rztjImg8)){
			imagepaths = imagepaths+rztjImg8+"_";
		}
		if(StringUtils.isNotBlank(rztjImg9)){
			imagepaths = imagepaths+rztjImg9+"_";
		}
		
		paramMap.put("shequId", shequId);//社区ID
		paramMap.put("name", name);//反馈人
		paramMap.put("phone", phone);//反馈人手机号
		paramMap.put("content", content);//内容
		paramMap.put("memberId", memberId);//会员ID
		paramMap.put("title", title);//商品主题
		paramMap.put("address", address);//修复状态
		paramMap.put("price", price);//费用标准
		paramMap.put("atAccount", atAccount);//发布人
		paramMap.put("imagepaths", imagepaths);//商品图片
		paramMap.put("serviceType", serviceType);//服务类型(0家政服务1餐饮服务2其他服务)
		
		String toPage = "";
		try {
			System.out.println("paramMap=====subRZTJ====>>"+paramMap);
			Map<String, Object> rztjMap = rztjService.subRZTJMap(paramMap);
			String success = StringUtil.nvl(rztjMap.get("success"));
			if("0".equals(success)){
				toPage = "/wsh/rztj/wsh_rztj_succe";
			}else{
				toPage = "/wsh/rztj/wsh_rztj_erro";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toPage;
	}
	
}
