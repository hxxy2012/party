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
<title>个人中心</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
<script type="text/javascript">
//存储，IE6~IE7 cookie 其他浏览器HTML5本地存储
if(window.localStorage){
	localStorage.setItem("member_account",'${memberMap.account}');  //进行本地存储
}else{
	Cookie.write("member_account",'${memberMap.account}');   //进行Cookie存储
}

var weixinLogin = "${weixinLogin}";
if(weixinLogin=="1"){
	window.history.go(-3);
}
</script>
<!-- <style type="text/css">
.imgtest{margin:10px 5px;
overflow:hidden;}
.list_ul figcaption p{
font-size:12px;
color:#aaa;
}
.imgtest figure div{
display:inline-block;
margin:5px auto;
width:100px;
height:100px;
border-radius:100px;
border:2px solid #fff;
overflow:hidden;
-webkit-box-shadow:0 0 3px #ccc;
box-shadow:0 0 3px #ccc;
}
.imgtest img{width:100%;
min-height:100%; text-align:center;}
</style> -->

</head>
<body>
<c:if test="${not empty weixinLogin && weixinLogin=='1'}">
	<input type="hidden" name="weixinLogin" id="weixinLogin" value="${weixinLogin}"/>
	<!-- <a href="javascript:history.go(-3);">向上三页 </a> -->
</c:if>
<c:if test="${empty weixinLogin}">
	<input type="hidden" name="weixinLogin" id="weixinLogin" value=""/>
</c:if>
<div class="gerenzs">
	<div class="geren-tx">
		<!-- <a href="${global_url}/member/initPatientHead.htm"> -->
		<a href="${global_url}/member/initWeixinDemo.htm">
		<c:if test="${not empty memberMap.image}">
			<img src="${memberMap.image}">
		</c:if>
		<c:if test="${empty memberMap.image}">
			<c:choose>
				<c:when test="${not empty memberMap.gender && memberMap.gender=='1'}">
					<img src="${global_image_url}/toux_nv.jpg">
				</c:when>
				<c:otherwise>
					<img src="${global_image_url}/toux_nan.jpg">
				</c:otherwise>
			</c:choose>
		</c:if>
		<%-- <c:if test="${empty memberMap.image}">
			<img src="${global_image_url}/toux_nan.jpg">
		</c:if> --%>
		</a>
	</div>
	<div class="geren-con">
		<c:if test="${not empty memberMap.name}">
			<div class="geren-mc">${memberMap.name}</div>
		</c:if>
		<c:if test="${empty memberMap.name}">
			<div class="geren-mc">${memberMap.account}</div>
		</c:if>
		<c:if test="${not empty memberMap.shequName}">
			<div class="geren-mc">${memberMap.shequName}</div>
		</c:if>
    </div>
</div>

<div class="gerenlist" style="margin-bottom: 10px;">
	<ul>
		<c:if test="${empty streetId||not empty streetId&&streetId!='81'}">
			<li><a href="${global_url}/member/memberInfo.htm?shequId=${shequId}"><div class="gerenicon cn2"></div>个人信息<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<li><a href="${global_url}/wfw/sjxx/getSJXXList.htm?shequId=${shequId}"><div class="gerenicon cn10"></div>我的书记信箱<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<li><a href="${global_url}/wfw/jbts/getJBTSList.htm?shequId=${shequId}"><div class="gerenicon cn3"></div>我的意见<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<li><a href="${global_url}/wfw/jlhd/getJLHDList.htm?shequId=${shequId}"><div class="gerenicon cn4"></div>我的互动<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<li><a href="${global_url}/zhsq/yuyue/getShequYuyueList.htm?shequId=${shequId}"><div class="gerenicon cn9"></div>我的社区预约<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<li><a href="${global_url}/wsh/essc/getESSCList.htm?shequId=${shequId}"><div class="gerenicon cn6"></div>我的二手货<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<li><a href="${global_url}/zhsq/experience/init.htm?shequId=${shequId}"><div class="gerenicon cn11"></div>我的体检<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			<c:if test="${not empty streetId&&streetId=='81'||streetId=='22'}">
				<li><a href="${global_url}/qsQuestionnaire/getMemberQuestionnaireList.htm"><div class="gerenicon cn12"></div>我的两学一做问卷<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			</c:if>
			<li><a href="${global_url}/zhsq/question/getMemberZhsqQuestion.htm?shequId=${shequId}"><div class="gerenicon cn9"></div>我的社区问卷<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
        	<!-- <li><a href="${global_url}/street/yuyue/getYuyueList.htm"><div class="gerenicon cn10"></div>我的街道预约<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li> -->
	        <!-- <c:if test="${not empty memberMap.thirdParty&&memberMap.thirdParty=='0'}">
				 <li><a href="${global_url}/member/toResetPassword.htm?shequId=${shequId}"><div class="gerenicon cn7"></div>账号管理<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
			</c:if>-->
		</c:if>
		<!-- <li><a href="${global_url}/qsQuestionnaire/getMemberQuestionnaireList.htm"><div class="gerenicon cn9"></div>我的街道问卷<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li> -->
		
		
    </ul>
</div>
<!-- <div style="float:left; width:100%;height:71px;"></div> -->
<%-- <div class="bottominf1">
  <button onclick="window.location.href='${global_url}/member/exit.htm'"  class="check-order " id="orders">退出登录</button>
</div> --%>

<%-- <a class="denglu" href="${global_url}/member/exit.htm"><div class="exit">退出登录</div></a>  --%>

<!--社区浮动个人中心 -->
<jsp:include page="../include/floattoolbar.jsp" />
</body>
</html>
