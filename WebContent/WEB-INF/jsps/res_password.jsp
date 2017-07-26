<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body>
<div class="loginbody">
<input type="hidden" name="mobile" id="mobile" value="${mobile}"/>
  <ul class="logininput">
    	<li><div  class="lgicon lg2"></div><input placeholder="请输入新密码" autofocus="autofocus" name="newPassword" id="newPassword" type="password" onblur="checkPassword();"><span id="new_password_result" style="display: none;"></span></li>
        <li><div  class="lgicon lg2"></div><input placeholder="确认新密码" name="yesNewPassword" id="yesNewPassword" type="password" ></li><br>
    </ul>
  <button class="denglu" value="确认提交" id="sub" onclick="sub();">确认提交</button>
</div>

</body>
<script type="text/javascript">

//验证密码
function checkPassword(){
		var psw = $("#newPassword").val();
		if(psw==null || ""==psw){
			$("#new_password_result").html("请输入密码");
			$("#new_password_result").show();
			$("#sub").attr("disabled", true);
			return false;
		}else{
			var patrn=/^(\w){6,15}$/;  
			if (!patrn.exec(psw)){
				$("#new_password_result").html("输入6-15个英文字母或数字");
				$("#new_password_result").show();
				$("#sub").attr("disabled", true);
			}else{
				$("#new_password_result").html("");	
				$("#sub").attr("disabled", false);
			}
		}
	//return true;
}

function sub(){
	var mobile = $("#mobile").val();
	var newPassword = $("#newPassword").val();
//	alert("mobile==="+mobile);
	if( $("#yesNewPassword").val()!=$("#newPassword").val() ){
		confirm("密码不一致");
		/* alert("密码不一致");
		return false; */
	}
	
	var result = $.ajax({
		url : "${global_url}/member/saveResPassword.htm?mobile="+mobile+"&newPassword="+newPassword,
		async : false,
		cache : false
		}).responseText;
	if(result=="1"){
		confirm("密码修改成功,请重新登录！");
		//alert("密码修改成功,请重新登录！");
		window.location.href = "${global_url}/member/memberLogin.htm";
	}else {
		confirm("密码修改失败！");
		//alert("密码修改失败！");
	}
}
</script>
</html>


