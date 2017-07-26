<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>意见反馈（未处理）</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>


<section class="module m_b10">
    <div class="titlebar">
        <h5><img src="${global_image_url}/ico_jbts.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;意见反馈</h5>
    </div>	
</section>

<c:if test="${not empty yjfkInfoMap}">
<div class="yjfk_cont m_b10">
	<div class="yjfk_add"><span class="yy_icon3"></span>反馈地址：<c:if test="${yjfkInfoMap.address!=''}">${yjfkInfoMap.address}</c:if></div>
    <div class="yjfk_add"><span class="yy_icon4"></span>反馈内容：<c:if test="${yjfkInfoMap.content!=''}">${yjfkInfoMap.content}</c:if></div>
   	<div class="yjfk_time">${yjfkInfoMap.createdTime}<span class="yjfk_icon red">未处理</span> </div>
    <ul class="sc_ul">
    	<c:forEach items="${yjfkInfoMap.paramMap.imagePaths}" var="sysImagePO" >
    		<li><a href="${sysImagePO.path}" target="_blank"><img src="${sysImagePO.path}" /></a></li>
        </c:forEach> 
    </ul>
</div><!--坐标及图片结束-->

    
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_js.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;反馈人信息</h5>
		</div>	
	</section>
    <div class="course-content" style="padding:0 10px;">
    <div class="course-Per"><label>姓名：</label><c:if test="${yjfkInfoMap.name!=''}">${yjfkInfoMap.name}</c:if></div>
    <div class="course-Per"><label>电话：</label><c:if test="${yjfkInfoMap.phone!=''}">${yjfkInfoMap.phone}</c:if><button class="dh_btn" onclick="window.location.href='tel:${yjfkInfoMap.phone}'">拨打电话</button></div>
  </div>
</div><!--基本信息结束-->
</c:if>	
	
<!-- 底部按钮 -->
<div style="float:left; width:100%;height:71px;"></div>
<div class="bottominf1">
    <a href="${global_url}/member/wgy/underway.htm?jbtsId=${yjfkInfoMap.jbtsId}"><button type="button" class="check-order " id="orders" data-role="none" >去处理</button></a>
</div>

</body>
</html>
