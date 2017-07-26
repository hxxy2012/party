<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title><c:if test="${not empty qtfwInfoMap}">${qtfwInfoMap.title}</c:if></title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>

<%-- <div class="kechengtu">
	<ul>
    	<li><img src="${global_image_url}/esh_001.jpg" style="width:100%; overflow:hidden"></li>
    </ul>
</div> --%>

<div class="Courseinfor">
	<div class="course-header">
    	<c:if test="${not empty qtfwInfoMap}">
    	<!-- <h2>${qtfwInfoMap.title}</h2> -->
        <p>联系人：${qtfwInfoMap.name}</p>
        <p>联系方式：${qtfwInfoMap.phone}</p>
        <p>详细地址：${qtfwInfoMap.address}</p>
        <p>服务特色：${qtfwInfoMap.price}</p>
        <p>${qtfwInfoMap.createdTime}</p>
     </c:if>   
    </div>
</div>

<div class="Courseinfor xqcon">
    <div class="course-content">
    <h4>服务介绍</h4>
    	<c:if test="${not empty qtfwInfoMap&&qtfwInfoMap.content!=''}">
	    	${qtfwInfoMap.content}
	     </c:if> 
    </div>
    
    <div class="course-content">
    	<h4>其他图片</h4>
    	<c:if test="${not empty qtfwInfoMap.paramMap.imagePaths}">
   	 	<c:forEach items="${qtfwInfoMap.paramMap.imagePaths}" var="sysImagePO" >
			<a href="${sysImagePO.path}" target="_blank"><img id='hphoto'  src="${sysImagePO.path}" style='height: 100px;' border="0"/></a>
		</c:forEach> 
		</c:if>
		<c:if test="${empty qtfwInfoMap.paramMap.imagePaths}">
			<img height="50" width="50" src="${global_image_url}/mr_essc.png" class="pic">
		</c:if> 
    </div>
</div>
</body>
</html>