<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${xishan_image_url }/xs_banner1.jpg"></li>
	</ul>
</div>

<div class="index_cont">
    <a href="${global_url}/xishan/getNewsListMap.htm?classId=3"><div class="con w50 h100 con_1">简讯</div></a>
    <a href="${global_url}/xishan/getNewsListMap.htm?classId=4"><div class="con w50 h100 con_2">法律法规</div></a>
    <a href="${global_url}/xishan/getNewsListMap.htm?classId=5"><div class="con w100 h100 con_3">办理流程</div></a>
	<div class="con w60 ">
    	<a href="${global_url}/xishan/getNewsListMap.htm?classId=6"><div class="con w100 h100 con_4">政策指南</div></a>
        <a href="http://www.investinxishan.gov.cn/cn/files.aspx?key=003001005"><div class="con w100 h100 con_5">文件下载</div></a>
<%--         <a href="${xishan_image_url }/xs_banner1.jpg" download="下载"><div class="con w100 h100 con_5">文件下载</div></a> --%>
    	<a href="${global_url}/xishan/contactUs.htm"><div class="con w100 h100 con_4">联系我们</div></a>
    	<a href="${global_url}/xishan/yuyue/init.htm"><div class="con w100 h100 con_4">在线预约</div></a>
    </div>
    <a href="${global_url}/xishan/getNewsListMap.htm?classId=7"><div class="con w40 con_6 h200">招标公告</div></a>
</div>
</body>
</html>
