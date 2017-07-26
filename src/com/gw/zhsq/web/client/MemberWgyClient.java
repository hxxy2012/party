package com.gw.zhsq.web.client;

import java.util.HashMap;
import com.gw.base.util.ApiResponse;
import com.gw.base.util.ParseUtil;
import com.gw.base.util.SimpleHttp;

/**
 * 智慧社区网格员接口
 * @author hanxu
 *	2015-10-19
 */
public class MemberWgyClient extends BaseClient{

	/**
	 * 网格员登录
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse memberLogin(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=localhostWgyLogin";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("网格员登录失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取网格员信息
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
			apiResponse.setErrormsg("获取网格员基本信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取意见反馈列表
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getYJFKListMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getYJFKList";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parsePageResult(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取意见反馈列表失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 获取网格员意见反馈详情
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse getYJFKMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=getYJFKInfo";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("获取网格员意见反馈详情失败！");
		}
		return apiResponse;
	}		
	
	/**
	 * 网格员提交意见反馈
	 * @param requestMap
	 * @return
	 */
	public static ApiResponse subYJFKMap(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=subYJFK";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("网格员处理意见反馈信息失败！");
		}
		return apiResponse;
	}
	
	/**
	 * 会员登录
	 * @param requestMap
	 * @return
	 *//*
	public static ApiResponse memberRegister(HashMap<String, String> requestMap) {
		String rUrl = "/SheQu.gw?requestName=localhostRegist";
		ApiResponse apiResponse = null;
		try {
			String returnMsg = SimpleHttp.invokePostHttp(zhsqUrl + rUrl,requestMap, "UTF-8");
			apiResponse = ParseUtil.parse(returnMsg);
		} catch (Exception e) {
			apiResponse = new ApiResponse();
			apiResponse.setErrorcode("9999");
			apiResponse.setErrormsg("网格员注册失败！");
		}
		return apiResponse;
	}
	
	*//**
	 * 会员手机号验证存在
	 * @param requestMap
	 * @return
	 *//*
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
	
	*//**
	 * 重设密码
	 * @param requestMap
	 * @return
	 *//*
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
	}*/
	
	
	/**
	 * 保存个人信息
	 * @param requestMap
	 * @return
	 *//*
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
	
	*//**
	 * 修改密码
	 * @param requestMap
	 * @return
	 *//*
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
	
	*//**
	 * 更改个人头像
	 * @param requestMap
	 * @return
	 *//*
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
	}*/
	
}
