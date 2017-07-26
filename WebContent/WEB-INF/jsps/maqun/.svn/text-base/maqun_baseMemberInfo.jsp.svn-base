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
</head>
<body>
<div class="gerenzs">
	<div class="geren-tx">
		<c:if test="${not empty memberMap.image}">
			<img src="${memberMap.image}">
		</c:if>
		<c:if test="${empty memberMap.image}">
			<img src="${global_image_url}/toux_nan.jpg">
		</c:if>
	</div>
	<div class="geren-con">
		<c:if test="${not empty memberMap.account}">
			<div class="geren-mc">${memberMap.account}</div>
		</c:if>
		<c:if test="${not empty memberMap.name}">
			<div class="geren-mc">${memberMap.name}</div>
		</c:if>
    </div>
</div>

<div class="gerenlist" style="margin-bottom: 10px;">
	<ul>
		<li><a href="${global_url}/qsQuestionnaire/getMemberQuestionnaireList.htm?streetId=${streetId}"><div class="gerenicon cn9"></div>我的两学一做问卷<div class="grjiantou"><img src="${global_image_url}/morem.png"/></div></a></li>
    </ul>
</div>
<div style="float:left; width:100%;height:71px;"></div>
</body>
</html>
