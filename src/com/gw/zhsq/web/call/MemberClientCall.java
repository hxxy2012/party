package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.MemberClient;

/**
 * 获取个人信息
 * @author fuyun
 *	2015-04-03
 */
public class MemberClientCall extends BaseCall{

	/**
	 * 会员登录
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> memberLogin(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberLoginMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.memberLogin(requestMap);
		if (apiResponse.isSuccess()) {
			memberLoginMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberLoginMap;
	}
	
	/**
	 * 会员注册
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> memberRegister(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberRegisterMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.memberRegister(requestMap);
		if (apiResponse.isSuccess()) {
			memberRegisterMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberRegisterMap;
	}
	
	/**
	 * 会员手机号验证
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getMemberListByMobile(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> list = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.getMemberListByMobile(requestMap);
		if (apiResponse.isSuccess()) {
			list = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return list;
	}
	
	/**
	 * 验证手机号和openid
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> checkMobileOpenid(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> list = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.checkMobileOpenid(requestMap);
		if (apiResponse.isSuccess()) {
			list = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return list;
	}
	
	/**
	 * 重设密码
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static String resPassword(HashMap<String, String> requestMap) throws OtherException {
		String temp = "0";
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.resPassword(requestMap);
		if (apiResponse.isSuccess()) {
			temp = apiResponse.getStringValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return temp;
	}
	
	/**
	 * 获取个人详细信息列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getMemberMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> MemberMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.getMemberMap(requestMap);
		if (apiResponse.isSuccess()) {
			MemberMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return MemberMap;
	}
	
	/**
	 * 保存个人信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static String saveMemberInfo(HashMap<String, String> requestMap) throws OtherException {
		String temp = "0";
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.saveMemberInfo(requestMap);
		if (apiResponse.isSuccess()) {
			temp = apiResponse.getStringValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return temp;
	}
	
	/**
	 * 绑定会员手机号码
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> boundMemberMobile(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.boundMemberMobile(requestMap);
		if (apiResponse.isSuccess()) {
			memberMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberMap;
	}
	
	/**
	 * 修改密码
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> resetPassword(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> resetpasswordInfo = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.resetPassword(requestMap);
		if (apiResponse.isSuccess()) {
			resetpasswordInfo = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return resetpasswordInfo;
	}
	
	/**
	 * 更改个人头像
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static String modifyPatientHead(HashMap<String, String> requestMap) throws OtherException {
		String patientInfo = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberClient.modifyPatientHead(requestMap);
		if (apiResponse.isSuccess()) {
			patientInfo = apiResponse.getStringValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return patientInfo;
	}
	
}
