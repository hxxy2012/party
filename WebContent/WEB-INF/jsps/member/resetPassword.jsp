<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;" %>
<%@ include file="/common/taglib.jsp"  %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>智慧社区-账号管理</title>
<link rel="stylesheet" type="text/css" href="${global_css_url}/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var error = false;
		var oldPas="";
		$("button:[name=setPassword]").click(function(){
			var oldpassword = $("input:[name=oldPassword]");
			if(oldpassword.val()==null||oldpassword.val()==""){
				/* oldpassword.attr("placeholder","您需要输入原来密码"); */
				/* alert("请输入原密码"); */
				oldpassword.focus();
				jujiao(oldpassword,"<div>您需要输入原来密码</div>");
				return;
			}else{
				/* shijiao(oldpassword); */
				shijiao($("input"));
			}
			var passwordFirst = $("input:[name=passwordFirst]");
			if(passwordFirst.val()==null||passwordFirst.val()==""){
				/* passwordFirst.attr("placeholder","您需要输入新密码"); */
				/* alert("请输入新密码"); */
				passwordFirst.focus();
				jujiao(passwordFirst,"<div>您需要输入新密码</div>");
				return;
			}else{
				/* shijiao(passwordFirst); */
				shijiao($("input"));
			}
			if(oldpassword.val()!=null&&oldpassword.val()!=""){
				if(oldpassword.val()==passwordFirst.val()){
					//passwordFirst.val("");
					//passwordFirst.attr("placeholder","您输入的新密码和原密码一样"); 
					/* alert("您输入的新密码和原密码一样"); */
					jujiao(passwordFirst,'<div>您输入的新密码和原密码一样</div>');
					return;
				}else{
					/* shijiao(passwordFirst); */
					shijiao($("input"));
				}
			}
			
			var passwordSecond = $("input:[name=passwordSecond]");
			if(passwordSecond.val()==null||passwordSecond.val()==""){
				//passwordSecond.attr("placeholder","您需要输入确认新密码");
				/* alert("请输入确认新密码"); */
				passwordSecond.focus();
				jujiao(passwordSecond,'<div>您需要输入确认新密码</div>');
				return;
			}else{
				/* shijiao(passwordSecond); */
				shijiao($("input"));
			}
			
			if(passwordSecond.val()!=""&&passwordSecond.val()!=null){
				if(passwordFirst.val()!=null&&passwordFirst.val()!=""){
					if(passwordFirst.val()!=passwordSecond.val()){
						//passwordSecond.val("");
						//passwordSecond.attr("placeholder","新密码两次输入不一致");
						/* alert("新密码两次输入不一致"); */
						jujiao(passwordSecond,'<div>新密码两次输入不一致</div>');
						return;
					}else{
						/* shijiao(passwordSecond); */
						shijiao($("input"));
					}
				}
			}
	 	 /* 	var result = $.ajax({
				url : "${global_url}/member/saveResPassword.htm?mobile=${phone}&account=${account}&newPassword="+passwordFirst.val()+"&oldPassword="+oldPassword.val(),
				async : false,
				cache : false
				}).responseText;
			if(result=="1"){
				confirm("密码修改成功,请重新登录!");
				//alert("密码修改成功,请重新登录！");
				window.location.href = "${global_url}/member/memberLogin.htm";
			}else if(result=="2"){
				confirm("原密码输入有误!");
			}else {
				confirm("密码修改失败!");
				//alert("密码修改失败！");
			} */  
			
		  	$.ajax({
				type:"POST",
				data:{oldPassword:oldpassword.val(),newPassword:passwordFirst.val(),memberId:"${memberId}",mobile:"${phone}",account:"${account}"},
				dataType: "json",//返回json格式的数据
		   		url: "${global_url}/member/resetPassword.htm",
		    	success: function (data) {
		    		//alert(data);
		    		if(data.success=="0"){//成功
		    			alert("修改成功，重新登录");
		    			location.href="${global_url}/member/memberLogin.htm";
		    		}
		    		if(data.success=="2"){//原密码输入错误
		    			var oldpassword = $("input:[name=oldPassword]");
		    			oldPas = oldpassword.val();
		    			oldpassword.focus();
						jujiao(oldpassword,'<div>'+data.msg+'</div>');
						error = true;
		    		}
		        },
		   		error: function(XMLHttpRequest, textStatus, errorThrown){
		   			alert("异常信息："+textStatus);
		  		}
			});  
		});
		
		$("input:[name=oldPassword]").blur(function(){
			if($(this).val()==null||$(this).val()==""){
				//$(this).attr("placeholder","您需要输入原来密码");
				jujiao($(this),"<div>您需要输入原来密码</div>");
				return;
			}else{
				if(error){
					if(($(this).val()!=oldPas)){
						shijiao($(this));
					}
				}else{
					shijiao($(this));
				}
			}
		});
		
		$("input:[name=passwordSecond]").blur(function(){
			var passwordFirst = $("input:[name=passwordFirst]");
			if($(this).val()!=""&&$(this).val()!=null){
				if(passwordFirst.val()!=null&&passwordFirst.val()!=""){
					if(passwordFirst.val()!=$(this).val()){
						// $(this).val("");
						//$(this).attr("placeholder","新密码两次输入不一致"); 
					/* 	alert("新密码两次输入不一致"); */
						jujiao($(this),"<div>新密码两次输入不一致</div>");
						return;
					}else{
						shijiao($(this));
					}
				}
			}
		});
		
		$("input:[name=passwordFirst]").blur(function(){
			var oldpassword = $("input:[name=oldPassword]");
			if(oldpassword.val()!=null&&oldpassword.val()!=""){
				if(oldpassword.val()==$(this).val()){
					 //$(this).val("");
					//$(this).attr("placeholder","您输入的新密码和原密码一样"); 
					/* alert("您输入的新密码和原密码一样"); */
					jujiao($(this),"<div>您输入的新密码和原密码一样</div>");
					return;
				}else{
					shijiao($(this));
				}
			}
		});
	});
	
	function jujiao(inputEl,msg){
		$("input").css("border-color","#cccccc");
		inputEl.css("border-color","rgb(233, 9, 54)");
		inputEl.next().remove();
		inputEl.after(msg);
	}
	function shijiao(inputEl){
		inputEl.css("border-color","#cccccc");
		inputEl.next().remove();
	}
</script>
</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<span class="icon back" onclick="history.back()"></span>修改密码</div>
</header>
 -->
<div class="loginbody">
  <ul class="logininput">
 		<li><div  class="lgicon lg2"></div><input name="oldPassword"  type="password"  placeholder="原密码"></li>
    	<li><div  class="lgicon lg2"></div><input name="passwordFirst"  type="password" placeholder="新密码" ></li>
        <li><div  class="lgicon lg2"></div><input name="passwordSecond"  type="password" placeholder="确认新密码" ></li><br>
    </ul>
  <button class="denglu" value="确认提交" name="setPassword">确认提交</button>
</div>
<div class="bottominf1">
	  <button onclick="window.location.href='${global_url}/member/exit.htm'"  class="check-order " id="orders">退出登录</button>
</div>
 <%--  <a href="${global_url}/member/exit.htm"><div class="exit">退出登录</div></a>  --%>
</body>
</html>

