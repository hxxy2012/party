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
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body>
<div class="loginbody">
	<ul class="logininput">
		<li><div  class="lgicon lg3"></div><input placeholder="请输入手机号码" name="mobile" id="mobile" type="text" ><span id="mobile_result" style="display: none;"></span><a href="javascript:void(0)" id="btn"><i>发送验证码</i></a></li>
        <li><div  class="lgicon lg2"></div><input placeholder="请输入收到的验证码" name="code" id="code" type="text" ></li><br>
	</ul>
	<button class="denglu" value="验证" id="yanzheng" onclick="checkMobileCode();">提&nbsp;&nbsp;交</button>
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
				
		       /*  var countdown = setInterval(CountDown, 1000); */
		        $("#mobile").attr("readonly","readonly");
				$.ajax({
					url:"${global_url}/member/saveCode.htm",
					type:"POST",
					data:"code="+random()+"&sendTime="+new Date().getTime()+"&mobile="+$("#mobile").val()+"&forType=2",
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
	var input = $("#mobile");
	var mobile  = input.val();
	
	var patrn=/^[\d|-|\+]{11}$/;
	if (!patrn.exec(mobile)) {
		return false; 
	}
		
	return true;
}

//验证手机验证码
function checkMobileCode(){
		var temp = $("#code").val();
		if(temp==null || ""==temp){
			confirm("请输入收到的验证码！");
			//return false;
		}else{
			var result = $.ajax({
				url : "${global_url}/member/checkMobileCode.htm?regCode="+temp,
				async : false,
				cache : false
				}).responseText;
			if(result=="0"){
				alert("验证码输入有误！");
				//return false;
			}else if(result=="2"){
				alert("验证码已失效,请重新发送！");
				//return false;
			}else {
				var input = $("#mobile");
				var mobile  = input.val();
			  window.location.href = "${global_url}/member/resPassword.htm?mobile="+mobile;
			}
		
		}
}

//检测手机
function checkMobile(){
	var input = $("#mobile");
	var mobile  = input.val();

	var patrn=/^[\d|-|\+]{11}$/;
	if (!patrn.exec(mobile)){
		$("#btn").attr("disabled", true);
		$("#mobile_result").html("手机号不正确！");
		$("#mobile_result").show();
		$("#yanzheng").attr("disabled", true);
		return false;
	} 
	
	var result = $.ajax({
		url : "${global_url}/member/checkMobile.htm?registerMobile="+mobile,
		async : false,
		cache : false
		}).responseText;
	if(result=="1"){
		//$("#btn").attr("disabled", false);
		$("#btn").show();
		$("#mobile_result").html("");
		$("#yanzheng").attr("disabled", false);
		//$(".denglu").attr("disabled","disabled");
		return true;
	} else {
		$("#mobile_result").html("该手机号不存在！");	
		$("#mobile_result").show();
	//	$("#btn").attr("disabled", true);
		$("#btn").hide();
		//$(".denglu").removeAttr("disabled");
		$("#yanzheng").attr("disabled", true);
		return false;
	}
	
}

</script>
</html>


