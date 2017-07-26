<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>故障报修详情</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<div class="slider">
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
	</div>
</div><!--幻灯片结束-->

<div class="Courseinfor">
	<div class="course-header">
	<c:if test="${not empty gzbxInfoMap}">
        <p>报修人：${gzbxInfoMap.name }</p>
        <p>报修人手机：${gzbxInfoMap.phone }</p>
        <p>报修人地址：${gzbxInfoMap.address }</p>
        <p>预约时间：${gzbxInfoMap.yuyueTime }</p>
    <%--     <c:if test="${not empty gzbxInfoMap.solution&&gzbxInfoMap.solution!=''}">
        	<p>修复情况：${gzbxInfoMap.solution }</p>
        </c:if> --%>
        <p>报修状态：
            <c:if test="${not empty gzbxInfoMap.repair&&gzbxInfoMap.repair=='0'}">未修复</c:if>
       		<c:if test="${not empty gzbxInfoMap.repair&&gzbxInfoMap.repair=='1'}">已修复</c:if>
       		<c:if test="${not empty gzbxInfoMap.repair&&gzbxInfoMap.repair=='2'}">不必修复</c:if>
       		<c:if test="${not empty gzbxInfoMap.repair&&gzbxInfoMap.repair=='3'}">已受理</c:if>
       		<c:if test="${not empty gzbxInfoMap.repair&&gzbxInfoMap.repair=='4'}">待受理</c:if>
        </p>
     
    </c:if>    
    </div>
</div>
<div class="Courseinfor xqcon">
    <div class="course-content">
    <h4>报修说明</h4>
	    <c:if test="${not empty gzbxInfoMap&&gzbxInfoMap.content!=''}">
	    	${gzbxInfoMap.content}
	     </c:if> 
    </div>
    <c:if test="${not empty gzbxInfoMap&&gzbxInfoMap.solution!=''}">
	    <div class="course-content">
	    <h4>修复情况</h4>
		    	${gzbxInfoMap.solution}
	    </div>
     </c:if> 
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>

