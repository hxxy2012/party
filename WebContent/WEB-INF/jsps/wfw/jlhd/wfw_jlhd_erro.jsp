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
<title>交流互动-提交失败</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<span class="icon back" onclick="history.back()"></span>提交失败
	</div>
</header>
 -->
<div class="Courseinfor">
	<div class="successful"><i><img src="${global_image_url }/failure.png" ></i>提交失败</div>
	<a class="fh_btn" href="${global_url}/wfw/init.htm?shequId=${shequId}" onclick="history.back()">返回</a>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
