<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>街道预约详情</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
</head>
<body>
<c:if test="${not empty yuyueInfoMap}">
	<div class="slider">
		<ul class="slide-banner">
			<li><img src="${global_image_url}/banner_fw.jpg"></li>
		</ul>
	</div>
	<div class="bszl_list">
		<div class="bszl_cont">
			<div class="bszl_title">姓名</div>
			<div class="bszl_details ">${yuyueInfoMap.name}</div>
		</div>
		<div class="bszl_cont">
			<div class="bszl_title">手机</div>
			<div class="bszl_details ">${yuyueInfoMap.phone}</div>
		</div>
		<div class="bszl_cont">
			<div class="bszl_title">预约事项</div>
			<div class="bszl_details ">${yuyueInfoMap.action}</div>
		</div>
		<div class="bszl_cont">
			<div class="bszl_title">预约日期</div>
			<div class="bszl_details ">${yuyueInfoMap.yuyueDate}</div>
		</div>
		<div class="bszl_cont">
			<div class="bszl_title">预约时间</div>
			<div class="bszl_details ">${yuyueInfoMap.yuyueTime}</div>
		</div>
		<div class="bszl_cont">
			<div class="bszl_title">受理状态</div>
			<c:choose>
				<c:when test="${yuyueInfoMap.state=='0'}"><div class="bszl_details ">未受理</div></c:when>
				<c:when test="${yuyueInfoMap.state=='1'}"><div class="bszl_details ">已受理</div></c:when>
				<c:otherwise>未受理</c:otherwise>
			</c:choose>
		</div>
		<div class="bszl_cont">
			<div class="bszl_title">受理理由</div>
	    	<div class="course-content">${yuyueInfoMap.reason}</div>
		</div>

	</div>

	<!--
	<div style="float: left; width: 100%; height: 71px;"></div>
	<div class="bottominf1">
		<a class="fh_btn" id="orders" href="${global_url}/street/yuyue/init.htm?streetId=${yuyueInfoMap.streetId}">预约</a>
	</div>-->
</c:if> 
</body>
</html>