package com.gw.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.gw.base.constant.SystemConstant;

/**
 * 1xinxi.cn HTTP接口 发送短信
 * @author fuyun
 *	2015-9-23
 *	http://sms.1xinxi.cn/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&sign=签名&stime=发送时间&type=pt&extno=自定义扩展码
 */
public class SendCodeUtil {
	
	public static String sendCode(String code,String mobile,int type) throws IOException{
		String content ="";
		//type类型：1.注册会员验证   2.找回密码验证
		if(type==1){
			content = "【智慧社区】您好，您的注册验证码为"+code+"，请完成验证，如非本人操作，请忽略本短信。";
		}else if(type==2){
			content = "【智慧社区】您好，您的找回密码验证码为"+code+"，请完成验证，如非本人操作，请忽略本短信。";
		}else if(type==3){
			content = "【智慧社区】您好，您的绑定手机号验证码为"+code+"，请完成验证，如非本人操作，请忽略本短信。";
		}else if(type==4){
			content = "【智慧社区】您好，您的提交预约验证码为"+code+"，请完成验证，如非本人操作，请忽略本短信。";
		}
		System.out.println("短信内容："+content);
		String result="";
		StringBuffer sb = new StringBuffer(SystemConstant.url);
		sb.append("name="+SystemConstant.name);
		sb.append("&pwd="+SystemConstant.pwd);
		sb.append("&mobile="+mobile);
		sb.append("&content="+URLEncoder.encode(content,"UTF-8"));
		sb.append("&stime=");
		sb.append("&type=pt&extno=");
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		InputStream is =url.openStream();
		String returnStr = SendCodeUtil.convertStreamToString(is);
		result = returnStr.substring(0,1);
		is.close();
		return result;
	}
	
	/**
	 * 向工作人员发送短信
	 * @param code
	 * @param mobile
	 * @param type
	 * @return
	 * @throws IOException
	 * admin_phone, yuyueDate, yuyueTime, action,
	 */
	public static String sendYuyueCode(String mobile, String phone, String companyName, String yuyueDate, String yuyueTime, String action,String code, int type) throws IOException{
		String content ="";
		if(type==5){
			content = "【建设城管】您好，您的提交预约验证码为"+code+"，请完成验证，如非本人操作，请忽略本短信。";
		}else if(type==6){
			content = "【建设城管】手机号:"+phone+" 的用户("+companyName+")预约    "+yuyueDate+"  "+yuyueTime
					+"  "+action+" 事项, 请适时处理。";
		}
		System.out.println("短信内容："+content);
		String result="";
		StringBuffer sb = new StringBuffer(SystemConstant.url);
		sb.append("name="+SystemConstant.nameXishan);
		sb.append("&pwd="+SystemConstant.pwdXishan);
		sb.append("&mobile="+mobile);
		sb.append("&content="+URLEncoder.encode(content,"UTF-8"));
		sb.append("&stime=");
		sb.append("&type=pt&extno=");
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		InputStream is =url.openStream();
		String returnStr = SendCodeUtil.convertStreamToString(is);
		result = returnStr.substring(0,1);
		is.close();
		return result;
	}
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
	
}
