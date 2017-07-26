package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.zhsq.web.call.MemberClientCall;
import com.gw.zhsq.web.service.MemberService;

/**
 * 个人中心逻辑层
 * @author fuyun
 *	2015-04-03 13:47
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	//会员登录
	@Override
	public Map<String, Object> memberLogin(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberLoginMap = MemberClientCall.memberLogin(requestMap);
		return memberLoginMap;
	}
	
	//会员注册
	@Override
	public Map<String, Object> memberRegister(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberRegisterMap = MemberClientCall.memberRegister(requestMap);
		return memberRegisterMap;
	}
	
	//会员手机号验证存在
	@Override
	public Map<String, Object> getMemberListByMobile(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberMap = MemberClientCall.getMemberListByMobile(requestMap);
		return memberMap;
	}
	
	//验证手机号和openid
	@Override
	public Map<String, Object> checkMobileOpenid(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberMap = MemberClientCall.checkMobileOpenid(requestMap);
		return memberMap;
	}
	
	//重设密码
	@Override
	public int resPassword(HashMap<String, String> requestMap) throws OtherException {
		String temp = MemberClientCall.resPassword(requestMap);
		int temps = 0;
		if("0".equals(temp)){
			temps = 1;
		}else if("2".equals(temp)){
			temps = 2;
		}
		return temps;
	}
	
	//获取个人信息
	@Override
	public Map<String, Object> getMemberMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberMap = MemberClientCall.getMemberMap(requestMap);
		return memberMap;
	}
		
	//绑定会员手机号码
	@Override
	public Map<String, Object> boundMemberMobile(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberMap = MemberClientCall.boundMemberMobile(requestMap);
		return memberMap;
	}
		
	//保存个人信息
	@Override
	public int saveMemberInfo(HashMap<String, String> requestMap) throws OtherException {
		String temp = MemberClientCall.saveMemberInfo(requestMap);
		int temps = 0;
		if("1".equals(temp)){
			temps = 1;
		} 
		if("2".equals(temp)){
			temps = 2;
		}
		return temps;
	}
		
	//修改密码
	@Override
	public Map<String, Object> resetPassword(HashMap<String, String> requesetMap) throws OtherException {
		return MemberClientCall.resetPassword(requesetMap);
	}

	//更改个人头像
	@Override
	public String modifyPatientHead(HashMap<String, String> requesetMap)throws OtherException {
		return MemberClientCall.modifyPatientHead(requesetMap);
	}	

}
