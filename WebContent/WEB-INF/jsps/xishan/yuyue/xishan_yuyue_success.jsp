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
<title>在线预约成功</title>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
</head>
<body>
<div class="Courseinfor">
	<div class="successful"><i><img src="${global_image_url }/successful.png" ></i>已提交预约</div>
	<%-- <a class="fh_btn" href="${global_url}/member/baseMemberInfo.htm">个人中心</a> --%>
	<a class="fh_btn" href="${global_url}/xishan/yuyue/init.htm">返回</a>
</div>
</body>
</html>
