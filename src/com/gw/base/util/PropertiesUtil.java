package com.gw.base.util;

import java.util.ResourceBundle;

/**
 * 读取配置文件
 * @author fuyun
 * 	2015-11-14
 */
public class PropertiesUtil {

	public static String getSetting(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("setting");
		return bundle.getString(key);
	}

}
