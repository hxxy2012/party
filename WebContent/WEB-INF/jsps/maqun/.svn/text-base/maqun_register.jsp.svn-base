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
<title>会员注册</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body>
<div class="loginbody">
<form id="register_form" name="register_form" action="${global_url}/maqun/memberRegister.htm" method="post">    
  <ul class="logininput">
    	<li><div  class="lgicon lg3"></div><input placeholder="请填写手机号码" name="registerMobile" id="registerMobile"  type="text" onblur="checkRegisterMobile();"><span id="register_mobile_result" style="display: none;"></span></li>
        <li><div  class="lgicon lg4"></div><input placeholder="请填写手机验证码" name="reg_code" id="reg_code"  type="text" onblur="checkMobileCode();"><span id="register_code_result" style="display: none;"></span><a href="javascript:void(0)" id="btn"><i>发送验证码</i></a></li><br>
        <li><div  class="lgicon lg2"></div><input placeholder="请填写密码" name="registerPassword" id="registerPassword"  type="password" onblur="checkPassword();"><span id="register_password_result" style="display: none;"></span></li>
  </ul>
    <div style="padding-bottom: 25px;"><input type="checkbox" id="license"  value="agree" name="license" checked="checked">已阅读并同意此<a href="${global_url}/member/agreement.htm"><b style="color:#3b9fed">服务协议和隐私条款</b></a></div>
     <button type="button" style="background-color: #4ea12c;" class="denglu" id="register"  onclick="register_submit();">注&nbsp;&nbsp;册</button>  
</form>
</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	//敲击回车登录
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) {
			register_submit();
		}
	};
	
	$('#btn').click(function(){
		if(!checkRegMobile()){
			alert("请输入正确的手机号!");
		}else{
			checkRegisterMobile();
			var count = 300;
			
		    var intervalId;  
		    clearInterval(intervalId);  
		    intervalId=setInterval(function (){  
		    	CountDown();            
		     }, 1000);   
			
	       // var countdown = setInterval(CountDown, 1000);
	        $("#registerMobile").attr("readonly","readonly");
			$.ajax({
				url:"${global_url}/member/saveCode.htm",
				type:"POST",
				data:"code="+random()+"&sendTime="+new Date().getTime()+"&mobile="+$("#registerMobile").val()+"&forType=1",
				dataType:"json",
				success:function(json){
					if(json==0){
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
	});

	//获取6位随机验证码
	function random() {
	    var num = "";
	    for (i = 0; i < 4; i++) {
	        num = num + Math.floor(Math.random() * 10);
	    }
	    return num;
	}	
	  
});

function checkRegMobile(){
	var mobile = $("#registerMobile").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/; 
	if(!myreg.test(mobile)){ 
		return false;
	}
	return true;
}
//验证密码
function checkPassword(){
	var psw = $("#registerPassword").val();
	if(psw==null || ""==psw){
		$("#register_password_result").html("请输入密码");
		$("#register_password_result").show();
		$("#register").attr("disabled", true);
		return false;
	}else{
		var patrn=/^(\w){6,15}$/;  
		if (!patrn.exec(psw)){
			//return "输入6-15个英文字母或数字";
			$("#register_password_result").html("输入6-15个英文字母或数字");
			$("#register_password_result").show();
			$("#register").attr("disabled", true);
		}else{
			$("#register_password_result").html("");	
			$("#register").attr("disabled", false);
		}
	}
	return true;
}

//验证手机验证码
function checkMobileCode(){
		var temp = $("#reg_code").val();
		if(temp==null || ""==temp){
			$("#register_code_result").html("请输入收到的验证码");
			$("#register_code_result").show();
			$("#register").attr("disabled", true);
			return false;
		}else{
			var result = $.ajax({
				url : "${global_url}/member/checkMobileCode.htm?regCode="+temp,
				async : false,
				cache : false
				}).responseText;
			if(result=="0"){
				$("#register_code_result").html("验证码输入有误");
				$("#register_code_result").show();
				//$("#register").attr("disabled", true);
				return false;
			}else if(result=="2"){
				$("#register_code_result").html("验证码已失效,请重新发送");
				$("#register_code_result").show();
				//$("#register").attr("disabled", true);
				return false;
			}else {
				$("#register_code_result").html("");	
				$("#register").attr("disabled", false);
			}
		
		}
}

//检测手机
function checkRegisterMobile(){
	var mobile = $("#registerMobile").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(mobile==null || ""==mobile){
		$("#btn").hide();
		$("#register_mobile_result").html("手机号不能为空！");
		$("#register_mobile_result").show();
		$("#register").attr("disabled", true);
		return false;
	}else{
		if(!myreg.test(mobile)){ 
			$("#btn").hide();
			$("#register_mobile_result").html("手机号不正确！");
			$("#register_mobile_result").show();
			$("#register").attr("disabled", true);
			return false;
		}else{
			$("#btn").show();
			$("#register_mobile_result").hide();
			$("#register").attr("disabled", false);
		} 
	}
	
 	var result = $.ajax({
		url : "${global_url}/maqun/checkMobile.htm?registerMobile="+mobile,
		async : false,
		cache : false
		}).responseText;
	if(result=="1"){
		//$("#btn").attr("disabled", true);
		$("#btn").hide();
		$("#register_mobile_result").html("手机号已被注册");
		$("#register_mobile_result").show();
		$("#register").attr("disabled", true);
		//$(".denglu").attr("disabled","disabled");
		return false;
	} else {
		//$("#btn").attr("disabled", false);
		$("#btn").show();
		$("#register_mobile_result").html("");	
		//$(".denglu").removeAttr("disabled");
		$("#register").attr("disabled", false);
	} 
	
}

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
/* 	var result = $.ajax({
		url : "/jsbc_LearningChannel_weixin/member/checkLoginName.htm?login_name="+login_name,
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
	} */
} 



//会员注册
function register_submit(){
	var register_mobile = $("#registerMobile").val();
    var register_password = $("#registerPassword").val();
    var reg_code = $("#reg_code").val();
    var register_code_result = $("#register_code_result").html(); 
    
	if(register_mobile == null || register_mobile == ""){
		 alert("请输入手机号");
		return false;
	}
	if(reg_code == null || reg_code == ""){
		 alert("请输入验证码");
		return false;
	}
	if(register_password == null || register_password == "") {
		 alert("请输入密码");
		 return false;
	}
	if(register_code_result == "验证码输入有误") {
		 alert("验证码输入有误");
		 return false;
	}
	
	//检测用户协议
   	if($("#license").is(':checked')){
   	}else{
   		alert("请阅读用户协议并同意！");
   		return false;
   	}
    //$("#register").submit();
   // document.getElementById("register_form").action="/Weixin/member/memberRegister.htm";
    //document.getElementById("register_form").submit();
    
	document.getElementById("register_form").submit();
}
</script>
</html>

