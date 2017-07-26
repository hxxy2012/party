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
<title>绑定手机</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body>
<input id="openId" name="openId" type="hidden" value="${openId }"/>
<div class="loginbody">
	<ul class="logininput">
		<li><div  class="lgicon lg3"></div><input placeholder="请输入手机号码" name="login_name" id="login_name" type="text" ><span id="mobile_result" style="display: none;"></span><a href="javascript:void(0)" id="btn"><i>发送验证码</i></a></li>
        <li><div  class="lgicon lg2"></div><input placeholder="请输入收到的验证码" name="code" id="code" type="text" ></li><br>
	</ul>
	<button class="denglu" value="验证" id="yanzheng" onclick="saveMember();">绑&nbsp;&nbsp;定</button>
	<!-- <div class="tishi">暂时不需要绑定手机号码，<a class="green" href="${global_url}/member/baseMemberInfo.htm">直接进入</a></div> -->
</div>
<!-- <div class="tishi" style="margin-top:100px;">为了您的使用便捷，建议绑定手机号，<a class="green" href="">完善个人信息</a></div> -->
	
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#btn').click(function(){
		if(!checkRegMobile()){
				confirm("请输入正确的手机号!");
		}else{
			if(checkMobile()){
				var count = 300;
				var intervalId;  
				clearInterval(intervalId);  
				intervalId=setInterval(function ()  
				{  
					CountDown();            
				 }, 1000); 
				
		        $("#login_name").attr("readonly","readonly");
				$.ajax({
					url:"${global_url}/member/sendAuthCode.htm",
					type:"POST",
					data:"sendTime="+new Date().getTime()+"&mobile="+$("#login_name").val()+"&forType=3",
					dataType:"json",
					success:function(json){
						if(json==0){
							$("#btn").attr("disabled", true);
							alert("验证码已成功发送，请注意查收！");
						}else{
							alert("验证码发送失败，请稍后再试！");
						}
					}		
				});	
				 function CountDown() {
		             $("#btn").attr("disabled", true);
		             $("#btn").html(count + " 秒后可以再次获取");
		             if (count == 0) {
		            	 $("#registerMobile").removeAttr("readonly");
		                 $("#btn").html("重新获取验证码").removeAttr("disabled");
		                 clearInterval(intervalId);
		             }
		             count--;
		         }
			}
		}
	});
});

function checkRegMobile(){
	var login_name = $("#login_name").val();
	var patrn=/^[\d|-|\+]{11}$/;
	if (!patrn.exec(login_name)) {
		return false; 
	}
	return true;
}

//检测手机
function checkMobile(){
	var login_name = $("#login_name").val();
	var patrn=/^[\d|-|\+]{11}$/;
	if (!patrn.exec(login_name)){
		$("#btn").attr("disabled", true);
		$("#mobile_result").html("手机号不正确！");
		$("#mobile_result").show();
		$("#yanzheng").attr("disabled", true);
		return false;
	}
	var openId = $("#openId").val();
	var result = $.ajax({
		url : "${global_url}/member/checkMobileOpenid.htm?registerMobile="+login_name+"&openId="+openId,
		async : false,
		cache : false
		}).responseText;
	if(result!="1"){
		$("#btn").show();
		$("#mobile_result").html("");
		$("#yanzheng").attr("disabled", false);
		return true;
	} else {
		$("#mobile_result").html("该手机号已被注册");	
		$("#mobile_result").show();
		$("#btn").hide();
		$("#yanzheng").attr("disabled", true);
		return false;
	}
}
function saveMember(){
	var code = $("#code").val();
	var openId = $("#openId").val();
	var login_name = $("#login_name").val();
	if(login_name!=""){
		var result = $.ajax({
			url : "${global_url}/member/boundMobile.htm",
			data:{code:code,account:login_name,openId:openId},
			async : false,
			cache : false,
		}).responseText;
		if("4" == result){
			alert("未发送验证码或验证码已过期，请重新获取");
		}else if("3" == result){
			alert("验证码填写有误");
		}else{
			window.location.href = "${global_url}/member/baseMemberInfo.htm";
		}
	}
}
</script>
</html>
