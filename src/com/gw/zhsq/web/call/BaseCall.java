package com.gw.zhsq.web.call;

/**
 * 定义Call类，此类负责讲相应的httpclient类的数据解析成Bean
 * @author fuyun
 * 	2015-04-02
 */
public class BaseCall {

	//判断字符串是否是null，如果是null，返回""，不是返回原串
	protected static String filterBlank(Object object) {
		String value = "";
		if (object != null) {
			value = object.toString();
		}
		return value;
	}
	
}
