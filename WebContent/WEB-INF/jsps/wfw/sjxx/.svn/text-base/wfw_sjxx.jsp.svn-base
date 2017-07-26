<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" import="com.gw.base.util.TokenGen" %>
<%
  TokenGen.getInstance().saveToken(request);
  String token = (String)session.getAttribute("token");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>书记信箱</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
function sjxx_submit(){
	var memberId = $("#memberId").val();
	if(memberId == null || memberId == ""){
		var temp = confirm("您还未登录，请先去登录");
		if(temp){
			window.location.href="${global_url}/member/exit.htm";
		}
	}else{
		var name = $("#name").val();
	    var phone = $("#phone").val();
	    var content = $("#content").val();
		//var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		
	  	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		
		if(name == null || name == ""){
			 alert("请输入姓名");
			return false;
		}
		if(phone == null || phone == ""){
			 alert("请输入电话");
			return false;
		}else{
			if(!isPhone.test(phone)&&!isMob.test(phone)){ 
				alert("请输入正确的固话或者手机号");
				return false;
			}
		} 
		if(content == null || content == ""){
			 alert("请输入说明");
			return false;
		}
		//document.getElementById("register_form").action="/Weixin/member/memberRegister.htm";
	    //document.getElementById("register_form").submit();
		document.getElementById("form1").submit();
	}
}
</script>
</head>
<body>
<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_sjxx.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;书记信箱</h2>
        <div class="course-content">
        	<!-- <p>社区书记信箱，可以反馈社区问题给书记</p> -->
             <c:if test="${not empty shequMap}">
             	<c:if test="${not empty shequMap.phone}">
	      			<div class="course-row">
		          		<label>办公电话：</label>${shequMap.phone}
		          		<button onclick="window.location.href='tel:${shequMap.phone}'">电话咨询</button>
	             	</div>
	             </c:if>
	             <c:if test="${not empty shequMap.detail}">
	             	<div class="course-row">
		         		<label>提供服务：</label>${shequMap.detail}
	             	</div>
	             </c:if>
             </c:if>
        </div>
	</div>
</div>

<form id="form1" action="${global_url}/wfw/sjxx/subSJXX.htm" method="post">
	<input type="hidden" name="token" value="<%=token%>"/>
	<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
	<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
	<div class="container">
		<section class="module">
			<div class="titlebar">
				<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;信息索取</h5>
			</div>	
		</section>
    	<div class="course-content" style="padding:0 10px;">
    		<div class="course-Per"><label>姓名：</label><input type="text" name="name" id="name"  placeholder="请输入您的姓名"  class="tianxie"></div>
    		<div class="course-Per"><label>电话：</label><input type="text" name="phone" id="phone" placeholder="请输入您的联系电话"  class="tianxie"></div>
    		<div class="course-Per"><label style="position: relative; top: -30px;">说明：</label><textarea class="tianxie1" name="content" id="content"></textarea></div>
  		</div>
	</div>
	<div style="float:left; width:100%;height:71px;"></div>

	<!-- 底部按钮 -->
	<div class="bottominf1">
  		<button type="button" class="check-order " id="orders" onclick="sjxx_submit();">提交</button>
	</div>
</form>



</body>
</html>

