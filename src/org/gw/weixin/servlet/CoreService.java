package org.gw.weixin.servlet;

import java.util.Date; 
import java.util.HashMap;
import java.util.Map; 
import javax.servlet.http.HttpServletRequest; 
import org.gw.weixin.MessageUtil;
import org.gw.weixin.TextMessage;
import com.gw.base.constant.SystemConstant;
import com.gw.zhsq.web.service.impl.MemberServiceImpl;
 
/** 
 * 核心服务类 
 *  
 * @author liufeng 
 * @date 2013-05-20 
 */ 
public class CoreService { 
	
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */ 
    public static HashMap<String,Object> processRequest(HttpServletRequest request) { 
System.out.println("==========CoreService.processRequest==========");
    HashMap<String,Object> respMap = new HashMap<String,Object>();  
    respMap.put("code", "1");
	String respMessage = null; 
        try { 
            // 默认返回的文本消息内容 
            String respContent = "请求处理异常，请稍候尝试！"; 
 
            // xml请求解析 
            Map<String, String> requestMap = MessageUtil.parseXml(request); 
System.out.println("requestMap=="+requestMap);
            // 发送方帐号（open_id） 
            String fromUserName = requestMap.get("FromUserName"); 
            // 公众帐号 
            String toUserName = requestMap.get("ToUserName"); 
            // 消息类型 
            String msgType = requestMap.get("MsgType");
            //事件编号
            String eventKey = requestMap.get("EventKey");
 
System.out.println("fromUserName=="+fromUserName);
System.out.println("toUserName=="+toUserName);
System.out.println("msgType=="+msgType);
            
            // 回复文本消息 
            TextMessage textMessage = new TextMessage(); 
            textMessage.setToUserName(fromUserName); 
            textMessage.setFromUserName(toUserName); 
            textMessage.setCreateTime(new Date().getTime()); 
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
            //textMessage.setFuncFlag(0); 
 
            // 文本消息 
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { 
                respContent = "您发送的是文本消息！"; 
            } 
            // 图片消息 
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { 
                respContent = "您发送的是图片消息！"; 
            } 
            // 地理位置消息 
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { 
                respContent = "您发送的是地理位置消息！"; 
            } 
            // 链接消息 
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { 
                respContent = "您发送的是链接消息！"; 
            } 
            // 音频消息 
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { 
                respContent = "您发送的是音频消息！"; 
            } 
            // 事件推送 
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { 
                // 事件类型 
                String eventType = requestMap.get("Event");
                System.out.println("eventType=="+eventType);
                // 订阅 
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { 
                    respContent = "欢迎关注！"; 
                } 
                // 取消订阅 
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { 
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息 
                } 
                // 自定义菜单点击事件 
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) { 
                    // TODO 自定义菜单权没有开放，暂不处理该类消息 
                	System.out.println("=====自定义菜单点击事件 =====");
                }
                // 自定义菜单点击事件 
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) { 
                    // TODO 扫描带参数的二维码点击事件 
                	System.out.println("=====扫描带参数的二维码点击事件 =====");
                	respContent = "您可以<a href='http://weixin.gangwaninfo.com/Weixin/member/login.htm?shequId="+eventKey+"'>完善个人信息</a>。用户open_id:"+fromUserName+",社区ID:"+eventKey;
                	respMap.put("code", "2");
                	//TODO 判断用户是否注册，没有注册
                	//查看某openid是否已注册
                	try{
                		MemberServiceImpl memberService = new MemberServiceImpl();
	                	HashMap<String, String> paramsMap = new HashMap<String, String>();
	    				paramsMap.put("openId", fromUserName);
	    				paramsMap.put("password", SystemConstant.MEMBER_PASSWORD);
	    				Map<String, Object> memberMap = null;
	    				try{
							memberMap = memberService.memberLogin(paramsMap);
						}catch(Exception e){
						}
						if(memberMap==null){
							HashMap<String, String> paramsRegisterMap = new HashMap<String, String>();
							paramsRegisterMap.put("account", "");//帐号为空
							paramsRegisterMap.put("openId", fromUserName);//微信登录默认帐号为openid
							paramsRegisterMap.put("password", SystemConstant.MEMBER_PASSWORD);//默认密码123456
							paramsRegisterMap.put("phone", "");//手机号码
							paramsRegisterMap.put("gender", "0");//性别 ：0 男、1 女
							paramsRegisterMap.put("name", "");//昵称
							paramsRegisterMap.put("imagepaths", "");//头像
							paramsRegisterMap.put("city", "");//城市
							paramsRegisterMap.put("source", "weixin");//注册来源  "android、ios、pc、weixin、other"
							paramsRegisterMap.put("shequId", eventKey);//社区ID
							paramsRegisterMap.put("thirdParty", "1");//第三方快捷登录，0普通登录1微信快捷登录2QQ快捷登录
							System.out.println("paramsRegisterMap=="+paramsRegisterMap);
							memberMap = memberService.memberRegister(paramsRegisterMap);
						}
						respMap.put("shequId", eventKey);
						respMap.put("openId", fromUserName);
						respMap.put("memberMap", memberMap);
						System.out.println("memberMap=="+memberMap);
					}catch(Exception e){
						e.printStackTrace();
					}
                }
            } 
 
            textMessage.setContent(respContent); 
            respMessage = MessageUtil.textMessageToXml(textMessage); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        respMap.put("respMessage", respMessage);
        return respMap; 
    } 
} 
