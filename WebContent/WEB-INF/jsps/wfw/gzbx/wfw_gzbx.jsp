<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>故障报修列表</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript">
//存储，IE6~IE7 cookie 其他浏览器HTML5本地存储
if(window.localStorage){
	localStorage.setItem("member_account",'${memberMap.account}');  //进行本地存储
}else{
	Cookie.write("member_account",'${memberMap.account}');   //进行Cookie存储
}
//检测浏览器是否支持localStorage
var strStoreDate=window.localStorage?localStorage.getItem("member_account"):Cookie.read("member_account");
if(strStoreDate!=null){
	var result = $.ajax({
		url : "${global_url}/member/checkMemberState.htm?member_account="+strStoreDate,
		async : false,
		cache : false
	}).responseText;
}
</script>
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/gzbx.jpg"></li>
	</ul>
</div>

<div class="Courseinfor" >
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_gzbx.png" width="25px" height="25px;" style="position: relative;top: 6px" />&nbsp;<c:if test="${not empty shequMap.shequName}">${shequMap.shequName}</c:if></h2>
        <div class="course-content">
        <!-- <button class="check-tell" onclick="window.location.href='tel:4000358320'"><span class="tell"></span>电话咨询</button> -->
			<c:if test="${not empty shequMap}">
             	<c:if test="${not empty shequMap.phone}">
	      			<div class="course-row">
		          		<label>办公电话：</label>${shequMap.phone}
		          		<button class="dh_btn"  onclick="window.location.href='tel:${shequMap.phone}'">电话咨询</button>
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
<!-- 
<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_js.png" width="25px" height="25px;" style="position: relative;top: 6px" />&nbsp;马群社区故障报修预约介绍</h2>
        <div class="course-content">
        	<p>社区可以解决故障报修问题会及时为各位业主解决，如果需要有偿维修也会回单告知。</p>
            <p>预约记录可以在个人中心查询。</p>
        </div>
	</div>
</div>
 -->
<form action="${global_url}/wfw/gzbx/getGZBXYuYue.htm?shequId=${shequId}">
	<!--预约列表-->
	<div class="container">
		<section class="module">
			<div class="titlebar">
				<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 6px" />&nbsp;预约记录</h5>
			</div>	
		</section>
	    <ul class="course-content" style="padding:0 10px;"> 
	    	<c:if test="${not empty gzbxList}">
	       	<c:forEach var="gzbxMap" items="${gzbxList}" begin="0" end="9">
	       		<li class="course-Per">
		       		<a href="${global_url}/wfw/gzbx/getGZBXInfo.htm?gzbxId=${gzbxMap.gzbxId}&shequId=${shequId}">
			       		<label>${gzbxMap.createdTime}</label>
			       		${gzbxMap.name}预约报修
			       		<c:if test="${not empty gzbxMap.repair&&gzbxMap.repair=='0'}">[未修复]</c:if>
			       		<c:if test="${not empty gzbxMap.repair&&gzbxMap.repair=='1'}">[已修复]</c:if>
			       		<c:if test="${not empty gzbxMap.repair&&gzbxMap.repair=='2'}">[不必修复]</c:if>
			       		<c:if test="${not empty gzbxMap.repair&&gzbxMap.repair=='3'}">[已受理]</c:if>
			       		<c:if test="${not empty gzbxMap.repair&&gzbxMap.repair=='4'}">[待受理]</c:if>
		       		</a>
				</li>
	       	</c:forEach>
			</c:if>
		</ul>
	</div>
	
	<div style="float:left; width:100%;height:71px;"></div>

	<!-- 底部按钮 -->
	<div class="bottominf1">
	  <button class="check-order " id="orders">填写预约</button>
	</div>
</form>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>

