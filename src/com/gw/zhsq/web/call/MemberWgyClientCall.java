package com.gw.zhsq.web.call;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;
import com.gw.base.util.ApiResponse;
import com.gw.zhsq.web.client.MemberWgyClient;

/**
 * 获取网格员信息
 * @author hanxu
 *	2015-04-03
 */
public class MemberWgyClientCall extends BaseCall{

	/**
	 * 会员登录
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> memberLogin(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberLoginMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberWgyClient.memberLogin(requestMap);
		if (apiResponse.isSuccess()) {
			memberLoginMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return memberLoginMap;
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
		apiResponse = MemberWgyClient.getMemberMap(requestMap);
		if (apiResponse.isSuccess()) {
			MemberMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return MemberMap;
	}
	
	/**
	 * 获取意见反馈列表
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getYJFKListMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsListMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberWgyClient.getYJFKListMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsListMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsListMap;
	}
	
	/**
	 * 获取网格员意见反馈详情
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> getYJFKMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberWgyClient.getYJFKMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsMap;
	}	
	
	/**
	 * 网格员提交意见反馈
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	public static Map<String, Object> subYJFKMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> jbtsMap = null;
		ApiResponse apiResponse = null;
		apiResponse = MemberWgyClient.subYJFKMap(requestMap);
		if (apiResponse.isSuccess()) {
			jbtsMap = apiResponse.getHashMapValue();
		} else {
			throw new OtherException(apiResponse.getErrorcode(),apiResponse.getErrormsg());
		}
		return jbtsMap;
	}
	
	/**
	 * 会员注册
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 *//*
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
	
	*//**
	 * 会员手机号验证
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 *//*
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
	
	*//**
	 * 重设密码
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 *//*
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
	}*/
	

	
	/**
	 * 保存个人信息
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 *//*
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
	
	*//**
	 * 修改密码
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 *//*
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
	
	*//**
	 * 更改个人头像
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 *//*
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
	}*/
	
}
