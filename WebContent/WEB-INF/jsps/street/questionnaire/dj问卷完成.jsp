<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>完成</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
</head>
<body>

<div class="Courseinfor">
	<div class="successful"><i><img src="${global_image_url}/successful.png" style="width:50px;" ></i>问卷回答完毕！</div>
    <div class="wj_wc">
		<span class="wj_green">夏家佳</span>于
		<span class="wj_orger" >2016-06-16 12:20</span> 至
		<span class="wj_orger">2016-06-16 12:40</span> 完成
		<span class="wj_green"> “两学一做”问卷调查</span>，总用时
		<span class="wj_red">20</span>分钟 ，获得
		<span class="wj_red">90</span>分，当前排名第
		<span class="wj_red">400</span> 位。
	</div>
	
	<div class="wj_ckphb">
		<a href="#">查看排行榜</a>
	</div>
	
	<a class="fh_btn" href="#">返回个人中心</a>
</div>




</body>
</html>
