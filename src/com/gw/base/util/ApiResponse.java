package com.gw.base.util;

import java.util.List;
import java.util.Map;

/**
 * 接口访问结果的载体
 * @author fuyun
 * 	2015-04-02
 */

public class ApiResponse {
	/**
	 * 是否成功
	 */
	private boolean success = false;
	/**
	 * 错误码
	 */
	private String errorcode;
	/**
	 * 错误信息
	 */
	private String errormsg = "";
	/**
	 * 总行数，分页用
	 */
	private int totalcount;
	/**
	 * 列表,忽视大小写的Map结构
	 */
	private List<Map<String,Object>> listValue = null;
	/**
	 * 接口得到一行数据，封装成Map
	 */
	private Map<String, Object> hashMapValue = null;
	/**
	 * 得到单个值，默认成String
	 */
	private String stringValue;

	/**
	 * 得到单个值，默认成int
	 */
	private int intValue;

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	// Getters and Setters.
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String error) {
		this.errormsg = error;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public Map<String, Object> getHashMapValue() {
		return hashMapValue;
	}

	public List<Map<String,Object>> getListValue() {
		return listValue;
	}

	public void setListValue(List<Map<String,Object>> listValue) {
		this.listValue = listValue;
	}

	public void setHashMapValue(Map<String, Object> hashMapValue) {
		this.hashMapValue = hashMapValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	
}
