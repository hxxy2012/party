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
<title>会员登录</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body>
<div class="loginbody">
	<c:if test="${not empty memberMap.face}">
    	<div class="logintoux"> <img src="${memberMap.face}"></div>
    </c:if>
	<form id="login_form" name="login_form" action="${global_url}/maqun/memberLogin.htm" method="post">    
	  <input type="hidden" id="login_type" name="login_type" value="${login_type}" />
	  <input type="hidden" id="shequId" name="shequId" value="${shequId}" />
	  <input type="hidden" id="streetId" name="streetId" value="${streetId}" />
	  <input type="hidden" id="forwardPage" name="forwardPage" value="${forwardPage}" />
	  <input type="hidden" id="param" name="param" value="${kcid}" />
	  <!-- <input type="hidden" id="loginType" name="loginType" value="isLogin" /> -->
	  <ul class="logininput">
	    	<li>
	    		<div class="lgicon lg1"></div>
	    		<input placeholder="用户名/手机号码" name="login_name" id="login_name" type="text" >
	    		<span id="login_name_result" style="display: none;"></span>
	    	</li>
	        <li>
		        <div  class="lgicon lg2"></div>
		        <input placeholder="请输入密码" name="login_password" id="login_password" type="password" >
	        	<span id="login_password_result" style="display: none;"></span>
	        </li>
	    </ul>
	  <button  type="button" class="denglu" value="登录" onclick="login_submit();">登&nbsp;&nbsp;录</button>
	  <div class="wjmima"><a href="${global_url}/maqun/memberRegister.htm" style=" float:left">立即注册</a><a href="${global_url}/maqun/findPassword.htm" style="float:right">忘记密码</a></div>
	</form>
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
	
/*   $(".denglu").click(function(){
	  var login_name = $("#login_name").val();
	  var login_password = $("#login_password").val();
	  
	  $.ajax({
			url:"http://localhost:8080/jsbc_LearningChannel_weixin/member/memberLogin.htm",
			type:"POST",
			data:{login_name:login_name,login_password:login_password},
			dataType:"json",
			success:function(json){
				if(json.result!=1){
					callback(false,"用户名不存在！");
					obj.attr('disabled',disabled);
				}else{
					callback(true,"");
					obj.removeAttr("disabled");
				}
			}		
		});	
  }); */
	  
	  
});

function checkName(){
	var login_name = $("#login_name").val();
	if(login_name == null || login_name == ""){
		$("#login_name_result").html("请输入用户名/手机号");
		$("#login_name_result").show();	
		$(".denglu").attr("disabled","disabled");
		return false;
	}else{
		$("#login_name_result").html("");
		$(".denglu").removeAttr("disabled");
	}
	var result = $.ajax({
		url :"${global_url}/member/checkLoginName.htm?login_name="+login_name,
		async : false,
		cache : false
		}).responseText;
	if(result=="0"){
		$("#login_name_result").html("用户名不存在");
		$("#login_name_result").show();
		$(".denglu").attr("disabled","disabled");
		return false;
	} else {
		$("#login_name_result").html("");	
		$(".denglu").removeAttr("disabled");
	}
}

function checkPassword(){
	var login_password = $("#login_password").val();
	if(login_password.length==0) {
		$("#login_password_result").html("请输入密码");
		$("#login_password_result").show();
		$(".denglu").attr("disabled","disabled");
		return false;
	}else{
		$("#login_password_result").html("");
		$(".denglu").removeAttr("disabled");
	}
}

//会员登录
function login_submit(){
	var login_name = $("#login_name").val();
    var login_password = $("#login_password").val();
	//var validate_login = $("#validate_login").val();
	//var goods_id = $("#goods_id").val();
	
	if(login_name == null || login_name == ""){
		//confirm("请输入用户名");
		 alert("请输入用户名");
		return false; 
	}else if(login_password == null || login_password == "") {
		//confirm("请输入密码");
		  alert("请输入密码");
		 return false; 
	}else{
		var result = $.ajax({
			url : "${global_url}/member/checkLogin.htm?login_name="+login_name+"&login_password="+login_password,
			async : false,
			cache : false
			}).responseText;
		if(result=="0"){
			//confirm("用户名与密码不匹配，请重新输入！");
			 alert("用户名与密码不匹配，请重新输入！");
			return false; 
		}else{
			document.getElementById("login_form").submit();
		}
	}
}
</script>
</html>

