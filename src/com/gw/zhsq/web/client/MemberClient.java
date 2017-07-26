package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 智慧社区会员接口
 * @author fuyun
 *	2015-10-19
 */
public class MemberClient extends BaseClient{

	/**
	 * 会员登录
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse memberLogin(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=localhostLogin";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("会员登录失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 会员登录
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse memberRegister(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=localhostRegist";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("会员注册失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 会员手机号验证存在
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getMemberListByMobile(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getMemberByPhone";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("会员手机号验证失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 验证手机号和openid
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse checkMobileOpenid(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=checkMobileOpenid";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("验证手机号和openid失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 重设密码
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse resPassword(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=updatePasswordByWeixin";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("密码重设失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取个人信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getMemberMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getPatientInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取个人基本信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 保存个人信息
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse saveMemberInfo(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=modifyPatientInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("保存个人信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 绑定会员手机号码
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse boundMemberMobile(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=boundMemberMobile";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("绑定会员手机号码失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 修改密码
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse resetPassword(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=updatePasswordByWeixin";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("修改密码失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 更改个人头像
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse modifyPatientHead(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=modifyPatientHead";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("更改会员头像失败！");
		}
		return apiResponse;
	}
	
}
