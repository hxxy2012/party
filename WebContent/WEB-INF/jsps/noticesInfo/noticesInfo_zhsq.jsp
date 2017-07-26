<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>公告详情</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<div class="bodycon">
	<div class="biaoticon">
		<c:if test="${not empty noticesMap}">
			<h1>${noticesMap.title}</h1>
	        <h3>${noticesMap.createdTime} <!-- <i>已阅读251次</i> --></h3>
		</c:if>
    </div>
    
    <!--幻灯片-->
   <%--  <div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url }/banner1.jpg"></a></li>
	</ul>
	<div class="indicator">
		<div class="indicator-num">
			<span class="point"></span>
			<span class="point"></span>
			<span class="point selected"></span>
			<span class="point"></span>
			<span class="point"></span>
		</div>
	</div> --%>
</div>
<!--幻灯片结束-->
    
<div class="textcon">
	<c:if test="${not empty noticesMap && noticesMap.newContent!=''}">
		${noticesMap.newContent}
	</c:if>
   	<c:if test="${noticesMap.newContent==''}">
		暂无公告
	</c:if>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../include/floattoolbar.jsp" />

</body>
</html>

