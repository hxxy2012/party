package com.gw.base.exception;

/**
 * 自定义异常
 * @author fuyun
 *	2015-11-14
 */
public class OtherException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorCode; //错误编码
	private String errorMsg;  //错误信息
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public OtherException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
