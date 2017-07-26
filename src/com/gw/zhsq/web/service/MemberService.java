package com.gw.zhsq.web.service;

import java.util.HashMap;
import java.util.Map;
import com.gw.base.exception.OtherException;

/**
 * 个人中心逻辑层
 * @author fuyun
 *	2015-04-02 15:59
 */
public interface MemberService {

	/**
	 * 会员登录
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> memberLogin(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 会员注册
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> memberRegister(HashMap<String, String> requestMap) throws OtherException;
	
	/**
	 * 会员手机号验证存在
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getMemberListByMobile(HashMap<String, String> requestMap) throws OtherException;
	
	/**
	 * 验证手机号和openid
	 * @param requestMap
	 * @return
	 * @throws OtherException
	 */
	Map<String, Object> checkMobileOpenid(HashMap<String, String> requestMap) throws OtherException;
	
	/**
	 * 重设密码
	 * @param requestMap
	 * @return
	 */
	int resPassword(HashMap<String, String> requestMap) throws OtherException ;
	
	/**
	 * 获取个人信息
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> getMemberMap(HashMap<String,String> requestMap) throws OtherException;
	
	/**
	 * 保存个人信息
	 * @param requestMap
	 * @return
	 */
	int saveMemberInfo(HashMap<String, String> requestMap) throws OtherException ;
	
	/**
	 * 绑定会员手机号码
	 * @param requestMap
	 * @return
	 */
	Map<String, Object> boundMemberMobile(HashMap<String, String> requestMap) throws OtherException ;
	
	/**
	 * 修改密码
	 * @param requesetMap
	 * @return
	 * @throws OtherException
	 */
	Map<String,Object> resetPassword(HashMap<String,String> requesetMap) throws OtherException;
	
	/**
	 * 更改个人头像
	 * @param requesetMap
	 * @return
	 * @throws OtherException
	 */
	String modifyPatientHead(HashMap<String,String> requesetMap) throws OtherException;
	
}
