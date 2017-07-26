package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.gw.zhsq.web.call.NoticesInfoClientCall;
import com.gw.zhsq.web.service.NoticesInfoService;

/**
 * 社区公告
 * @author fuyun
 *	2015-10-23
 */
@Service("noticesInfoServiceService")
public class NoticesInfoServiceImpl implements NoticesInfoService {

	//获取活动公告列表
	@Override
	public List<Map<String, Object>> getNoticesInfoList(HashMap<String, String> requestMap) {
		List<Map<String, Object>> noticesInfoList = null;
		try {
			noticesInfoList = NoticesInfoClientCall.getNoticesInfoistMap(requestMap);
			//过滤html标签
			if(noticesInfoList!=null&&noticesInfoList.size()>0){
				for (int i = 0; i < noticesInfoList.size(); i++) {
					Map<String,Object> temp= noticesInfoList.get(0);
					String contentStr =(String) temp.get("content");
					temp.put("newContent", contentStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticesInfoList;
	}
	
	//获取活动公告详情
	@Override
	public Map<String, Object> getNoticesInfoMap(HashMap<String, String> requestMap) {
		Map<String, Object> noticesMap = null;
		try {
			noticesMap = NoticesInfoClientCall.getNoticesInfoMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticesMap;
	}
	
	//获取活动公告详情
	@Override
	public Map<String, Object> getNoticesMap(HashMap<String, String> requestMap) {
		Map<String, Object> noticesMap = null;
		try {
			noticesMap = NoticesInfoClientCall.getNoticesMap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticesMap;
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
	      try { 
	    	  String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
	    	  String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
	    	  String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
	    	  String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
		      
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
	      
	          textStr = htmlStr; 
      
	      }catch(Exception e) { 
	      } 
	      return textStr;
	}   
}
