package com.gw.base.constant;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import com.gw.base.util.PropertiesUtil;

/**
 * 系统常量
 * @author fuyun
 *	2015-10-25
 */
public class SystemConstant {

	public static String WMH_SQHD = "1";//社区活动ID
	public static String WMH_SQFC = "2";//社区风采ID
	public static String SHEQU_ID = "202";//默认社区ID
	public static String STREET_ID = "22";//默认东台街道ID
	public static String STREET_MAQUN_ID = "81";//默认智慧马群街道ID
	public static String DONGTAI_SHEQU_ID = "321";//东台默认东园社区ID
	public static String DONGTAI_SHEQU_MAQUN_ID = "381";//智慧马群默认社区ID
	public static String DONGTAI_SHEQU_JZFZ_ID = "401";//建筑防灾默认社区ID
	public static String MEMBER_PASSWORD = "123456";//默认密码
	
	//***** *****//
	public static String WEIXIN_NONCESTR = "Zhi2015Hui11She13Qu";//微信签名随机字符串
	public static String WEIXIN_TIMESTAMP = "1420774989";//微信签名时间戳
	public static String WEIXIN_MEMBER_URL = "http://weixin.gangwaninfo.com/Weixin/member/initWeixinDemo.htm";//微信签名url地址，更换会员头像
	public static String WEIXIN_JBTS_URL = "http://weixin.gangwaninfo.com/Weixin/wfw/jbts/init.htm";//微信签名url地址，意见反馈
	public static String WEIXIN_UNDERWAY_JBTS_URL = "http://weixin.gangwaninfo.com/Weixin/member/wgy/underway.htm";//微信签名url地址，处理意见反馈
	public static String WEIXIN_ESSC_YUYUE_URL = "http://weixin.gangwaninfo.com/Weixin/wsh/essc/getESSCYuYue.htm";//微信签名url地址，二手市场
	public static String WEIXIN_RZTJ_URL = "http://weixin.gangwaninfo.com/Weixin/wsh/rztj/init.htm";//微信签名url地址，认证推荐
	
//	public static final String APPID = PropertiesUtil.getSetting("weixin_appid_202");//微信APPID
//	public static final String SECRET = PropertiesUtil.getSetting("weixin_secret_202");//微信AppSecret(应用密钥)
	public static final String PAYKEY = PropertiesUtil.getSetting("paykey");//支付API密钥
	public static final String MCHID = PropertiesUtil.getSetting("mchId");//
	public static final String NOTIFY_URL = PropertiesUtil.getSetting("notifyUrl");//
	public static final String REDIRECT_URI = PropertiesUtil.getSetting("redirect_uri");//
	public static final String ACCESS_TOKEN = PropertiesUtil.getSetting("access_token");//access_token
	public static final String BAIDU_KEY = PropertiesUtil.getSetting("baidu_key");//access_token
	
	//***** 发送短信基本参数 *****//
	public static String url = PropertiesUtil.getSetting("smsUrl");//短信平台地址
	public static String name = PropertiesUtil.getSetting("smsAccount");//短信平台账号
	public static String pwd = PropertiesUtil.getSetting("smsPassword");//短信平台密码
	//**** 锡山 ****//
	public static String nameXishan = PropertiesUtil.getSetting("smsAccountXishan");//短信平台账号
	public static String pwdXishan = PropertiesUtil.getSetting("smsPasswordXishan");//短信平台密码
	
//	public static String getOpenid(String code) throws Exception {
//		Object content = getURLString("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + SystemConstant.APPID + "&secret=" + SystemConstant.SECRET + "&code=" + code + "&grant_type=authorization_code", null);
//	    JSONObject obj = JSONObject.fromObject(content);
//	    return ((String)obj.get("openid"));
//	}
	
	public static String getURLString(String url, Object msg) throws Exception { 
		byte[]b = new byte[1024];
		StringBuffer buffer = new StringBuffer();
		URLConnection conn = new URL(url).openConnection();
        // 设置通用的请求属性
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent",
//                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//        conn.setRequestProperty("Accept-Charset", "UTF-8");
//        conn.setRequestProperty("Charset", "UTF-8");
        // 发送POST请求必须设置如下两行
		if(null != msg)	{
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        OutputStream stream = conn.getOutputStream();
	        stream.write(msg.toString().getBytes("utf-8"));
	        stream.flush();
		}
		InputStream stream = conn.getInputStream();
		int len = 0;
		while((len = stream.read(b)) > 0) {
			buffer.append(new String(b, 0, len));
		}
		return buffer.toString();
	} 
}
