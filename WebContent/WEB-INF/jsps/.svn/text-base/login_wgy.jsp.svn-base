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
<title>网格员登录</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body style=" background:#fff;">
<div class="login_body">
    <div class="slider">
        <ul class="slide-banner">
            <li><img src="${global_image_url}/banner_wgydl.png" ></li>
        </ul>
	</div>
	<form id="login_form" name="login_form" action="${global_url}/member/wgy/wgyLogin.htm" method="post"> 
		<input name="shequId" id="shequId"  type="hidden" value="${shequId}">
	    <div class="loginbody">
	      <ul class="logininput">
	            <li>
	            	<div class="lgicon lg3"></div>
	            	<input name="login_name" id="login_name"  type="text" placeholder="请输入网格员登录名">
	    			<span id="login_name_result" style="display: none;"></span>
	            </li>
	            <li>
		            <div class="lgicon lg2"></div>
		            <input name="login_password" id="login_password" type="password" placeholder="请输入网格员密码">
	        		<span id="login_password_result" style="display: none;"></span>
	            </li>
	            <br>
	        </ul>
	      <button type="button" class="denglu" value="登录" onclick="login_submit();">登&nbsp;&nbsp;录</button>
	    </div>
    </form>
    <div class="tishi" style="margin-top:100px;"><a class="green">提示</a>：此模块只针对社区网格员使用；<br>如密码遗忘请联系本社区后台管理员。</div>
</div>
</body>

<script type="text/javascript">
$(document).ready(function(){
	//敲击回车登录
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) {
			login_submit();
		}
	};
	

});

function checkPassword(){
	var login_password = $("#login_password").val();
	if(login_password.length==0) {
		alert("请输入网格员密码");
		return false;
	}
}

//会员登录
function login_submit(){
	var shequId = "${shequId}";
	var login_name = $("#login_name").val();
    var login_password = $("#login_password").val();
	
	if(login_name == null || login_name == ""){
		//confirm("请输入登录名");
		 alert("请输入网格员登录名");
		return false; 
	}else if(login_password == null || login_password == "") {
		//confirm("请输入密码");
		  alert("请输入网格员密码");
		 return false; 
	}else{
		var result = $.ajax({
			url : "${global_url}/member/wgy/checkLogin.htm?login_name="+login_name+"&login_password="+login_password+"&shequId="+shequId,
			async : false,
			cache : false
			}).responseText;
		if(result=="0"){
			//confirm("登录名与密码不匹配，请重新输入！");
			 alert("网格员登录名与网格员密码不匹配，请重新输入！");
			return false; 
		}else{
			document.getElementById("login_form").submit();
		}
	}
}
</script>
</html>

