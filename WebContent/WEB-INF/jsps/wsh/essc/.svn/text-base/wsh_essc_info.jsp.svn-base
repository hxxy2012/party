<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>二手市场详情</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<!--幻灯片-->
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
</div>
<!--幻灯片结束-->

<div class="Courseinfor">
	<div class="course-header">
	<c:if test="${not empty esscInfoMap}">
	 	<p>商品标题：${esscInfoMap.title }</p>
	    <p>商品价格：${esscInfoMap.price }元</p>
        <p>发布人姓名：${esscInfoMap.name }</p>
        <p>发布人手机：${esscInfoMap.phone }</p>
        <p>发布人地址：${esscInfoMap.address }</p>
    </c:if>    
    </div>
</div>
<div class="Courseinfor xqcon">
    <div class="course-content">
    <h4>商品说明</h4>
	    <c:if test="${not empty esscInfoMap&&esscInfoMap.content!=''}">
	    	${esscInfoMap.content}
	     </c:if> 
    </div>
    
    <div class="course-content">
    	<h4>二手货图片</h4>
    	
   	 <c:if test="${not empty esscInfoMap.paramMap.imagePaths}">
   		<c:forEach items="${esscInfoMap.paramMap.imagePaths}" var="sysImagePO" >
			<a href="${sysImagePO.path}" target="_blank"><img id='hphoto'  src="${sysImagePO.path}" style='height: 100px;' border="0"/></a>
		</c:forEach> 
	</c:if>
	<c:if test="${empty esscInfoMap.paramMap.imagePaths}">
		<img height="50" width="50" src="${global_image_url}/mr_essc.png" class="pic">
	</c:if> 
    
    </div>
</div>

</body>
</html>

