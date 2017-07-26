package com.gw.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import com.gw.base.exception.OtherException;


/**
 * 字符串帮助类
 * @author fuyun
 * 2015-11-14
 */
public class StringUtil {

	/**
	 * 如果为空，那么给空字符串，否则传转换后的字符串 非String型也会转成String型，如果对格式有要求的慎用，比如float型格式化
	 * 
	 * @param object
	 * @return 处理后的字符串
	 */
	public static String nvl(Object object) {
		String retString = "";
		if (object != null) {
			retString = object.toString().trim();
		}
		return retString;
	}
	
	//生成随机数字和字母,  
    public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();  
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    } 

	/**
	 * MD5加密
	 * 
	 * @param plainText
	 * @return String
	 */
	public static String tomd5(String plainText) throws OtherException {
		String str = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new OtherException("9997", "加密失败");
		}
		return str;
	}

	/**
	 * 随机生成验证码
	 * 
	 * @return String
	 */
	public static String createCaptcha() {
		String captcha = "";
		int length = new Random().nextInt(4) + 2; // 获取随机长度
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				captcha += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				captcha += String.valueOf(random.nextInt(10));
			}
		}
		return captcha;
	}

	/**
	 * 获取4位随机数
	 * @return
	 */
	public static String create4Captcha() {
		String captcha = StringUtil.nvl((new Random()).nextInt(9999)+1000);
		return captcha;
	}
	
	/**
	 * 获取UUID
	 * 
	 * @return String
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase().replaceAll("-", "");
	}

	/**
	 * java比较时分秒大小，先转string,判断是前面比后面大，返回true
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compTime(String s1, String s2) {
		if (s1.indexOf(":") < 0 || s1.indexOf(":") < 0) {
			// "格式不正确";
		} else {
			String[] array1 = s1.split(":");
			int total1 = Integer.valueOf(array1[0]) * 3600
					+ Integer.valueOf(array1[1]) * 60
					+ Integer.valueOf(array1[2]);
			String[] array2 = s2.split(":");
			int total2 = Integer.valueOf(array2[0]) * 3600
					+ Integer.valueOf(array2[1]) * 60
					+ Integer.valueOf(array2[2]);
			return total1 - total2 > 0 ? true : false;
		}
		return false;

	}

	/**
	 * 判断字符串是否为空或是否为""
	 * 
	 * @param valueString
	 * @return 不为空返回ture 为空返回false
	 */
	public static boolean isNotNull(String valueString) {
		if (!"".equals(valueString) && null != valueString) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取商户编号
	 * 
	 * @param valueString
	 * @return
	 */
	public static String getBusinessNo() {
		StringBuffer bh = new StringBuffer("SH-BH-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		bh.append(sdf.format(new Date()));
		bh.append(generateRandomFilename(4));
		return bh.toString();
	}
	
	/**
	 * 获取订单编号
	 * 
	 * @param valueString
	 * @return
	 */
	public static String getOrderNo() {
		StringBuffer bh = new StringBuffer("DD");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		bh.append(sdf.format(new Date()));
		bh.append(generateRandomFilename(5));
		return bh.toString();
	}
	
	
	/**
	 * 获取会员编号
	 * 
	 * @param valueString
	 * @return
	 */
	public static String getMemberNo() {
		StringBuffer bh = new StringBuffer("HY-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		bh.append(sdf.format(new Date()));
		bh.append(generateRandomFilename(4));
		return bh.toString();
	}
	/**
	 * 获取流水编号
	 * 
	 * @param valueString
	 * @return
	 */
	public static String getLiuShuiNo() {
		StringBuffer bh = new StringBuffer("");
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		bh.append(sdf.format(new Date()));
		bh.append(generateRandomFilename(4));
		return bh.toString();
	}

	/**
	 * weishu  :  随机数的位数  不足这个位数的话  补零
	 */
	public static String generateRandomFilename(int weishu) {
		String fourRandom = "";
		int randomNum = (int) (Math.random() * 10000);
		fourRandom = randomNum + "";
		int randLength = fourRandom.length();
		if (randLength < weishu) {
			for (int i = 1; i <= weishu - randLength; i++)
				fourRandom = fourRandom + "0";
		}
		StringBuilder sb = new StringBuilder("");
		sb.append(fourRandom);
		return sb.toString();
	}
	
	//处理html标签
	public static String HtmlText(String inputString) { 
	      String htmlStr = inputString; //含html标签的字符串 
	      String textStr =""; 
	      java.util.regex.Pattern p_script; 
	      java.util.regex.Matcher m_script; 
	      java.util.regex.Pattern p_style; 
	      java.util.regex.Matcher m_style; 
	      java.util.regex.Pattern p_html; 
	      java.util.regex.Matcher m_html; 
	      java.util.regex.Pattern p_space; 
	      java.util.regex.Matcher m_space; 
	     // java.util.regex.Pattern p_nbsp; 
	     // java.util.regex.Matcher m_nbsp; 
	      try { 
	       String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
	       String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
	       String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
	       String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	     //  String regEx_nbsp = "&nbsp;";//定义空格回车换行符
	      
	          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	          m_script = p_script.matcher(htmlStr); 
	          htmlStr = m_script.replaceAll(""); //过滤script标签 
	
	          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	          m_style = p_style.matcher(htmlStr); 
	          htmlStr = m_style.replaceAll(""); //过滤style标签 
	      
	          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	          m_html = p_html.matcher(htmlStr); 
	          htmlStr = m_html.replaceAll(""); //过滤html标签 
	          
	          p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
	          m_space = p_space.matcher(htmlStr);  
	          htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
	          
	        //  p_nbsp = Pattern.compile(regEx_nbsp, Pattern.CASE_INSENSITIVE);  
	        //  m_nbsp = p_nbsp.matcher(htmlStr);  
	        //  htmlStr = m_space.replaceAll(""); // 过滤&nbsp标签  
	      
	       textStr = htmlStr; 
	      
	      }catch(Exception e) { 
	      } 
	      return textStr;
  } 
	

}
