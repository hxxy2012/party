package com.gw.zhsq.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gw.base.exception.OtherException;
import com.gw.base.util.StringUtil;
import com.gw.zhsq.web.call.MemberWgyClientCall;
import com.gw.zhsq.web.service.MemberWgyService;

/**
 * 网格员逻辑层
 * @author hanxu
 *	2015-04-03 13:47
 */
@Service("memberWgyService")
public class MemberWgyServiceImpl implements MemberWgyService {
	
	//会员登录
	@Override
	public Map<String, Object> memberLogin(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberLoginMap = MemberWgyClientCall.memberLogin(requestMap);
		return memberLoginMap;
	}
	
	//获取个人信息
	@Override
	public Map<String, Object> getMemberMap(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberMap = MemberWgyClientCall.getMemberMap(requestMap);
		return memberMap;
	}
	
	
	//获取意见反馈列表
	@Override
	public Map<String, Object> getYJFKListMap(HashMap<String, String> requestMap)throws OtherException {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String resolve = requestMap.get("resolve");
		String pageno = "1",pageSize = "1000";
		if(StringUtils.isNotBlank(requestMap.get("pageno"))){
			pageno = requestMap.get("pageno");
		}
		if(StringUtils.isNotBlank(requestMap.get("pageSize"))){
			pageSize = requestMap.get("pageSize");
		}
		String shequId = requestMap.get("shequId");//社区ID
		paramMap.put("shequId", shequId);
		paramMap.put("resolve", resolve);//状态类型 0未处理 1已处理 2无需处理
		paramMap.put("page", pageno);//当前页数
		paramMap.put("pageSize", pageSize);//每页条数
		Map<String, Object> yjfkListMap = MemberWgyClientCall.getYJFKListMap(paramMap);
		return yjfkListMap;
	}
	
	//获取网格员意见反馈详情
	@Override
	public Map<String, Object> getYJFKInfoMap(HashMap<String, String> requestMap) throws OtherException {
		
		Map<String, Object> yjfkInfoMap = MemberWgyClientCall.getYJFKMap(requestMap);
		if(yjfkInfoMap!=null){
			String content = StringUtil.nvl(yjfkInfoMap.get("content"));
			content = StringUtil.HtmlText(content);
			yjfkInfoMap.put("content", content);
		}
		return yjfkInfoMap;
	}
	
	//网格员处理意见反馈
	@Override
	public Map<String, Object> subYJFKMap(HashMap<String, String> requestMap)throws OtherException {
		
		Map<String, Object> yjfkMap = MemberWgyClientCall.subYJFKMap(requestMap);
			
		return yjfkMap;
	}
	
	/*//会员注册
	@Override
	public Map<String, Object> memberRegister(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> memberRegisterMap = MemberClientCall.memberRegister(requestMap);
		return memberRegisterMap;
	}
	
	//会员手机号验证存在
	@Override
	public Map<String, Object> getMemberListByMobile(HashMap<String, String> requestMap) throws OtherException {
		Map<String, Object> list = MemberClientCall.getMemberListByMobile(requestMap);
		return list;
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
	}*/
	
	/*//保存个人信息
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
	}	*/

}
