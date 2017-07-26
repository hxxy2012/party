<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>网格化管理首页</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<style>
.wgygl_cont{float:left; width:100%; background:#fff;}
.wgyg_list{ width:50%;  float:left; text-align:center; padding:15px; position:relative;}
.wgyg_list img{ width:100%;}
.wgygl_num{ background: red;color: #fff;border-radius: 20px;line-height: 30px;padding: 0px 10px;position: absolute;top: 15px;right: 20px; }
</style>
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_wgygl.jpg" ></li>
	</ul>
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;网格员信息</h5>
		</div>	
	</section>
    <div class="course-content" style="padding:0 10px;">
    <div class="course-Per"><label>姓名：</label><c:if test="${not empty memberWgyMap&&memberWgyMap.staffName!=''}">${memberWgyMap.staffName}</c:if></div>
    <div class="course-Per"><label>手机：</label><c:if test="${not empty memberWgyMap&&memberWgyMap.telphone!=''}">${memberWgyMap.telphone}</c:if></div>
    <div class="course-Per"><label>职责描述：</label><c:if test="${not empty memberWgyMap&&memberWgyMap.dutyDescribe!=''}">${memberWgyMap.dutyDescribe}</c:if></div>
  </div>
</div>

<div class="wgygl_cont">
	<div class="wgyg_list"><a href="${global_url}/member/wgy/getYJFKList.htm?shequId=${memberWgyMap.shequId}"><img src="${global_image_url}/wgy_yjfk.png"></a><!-- <span class="wgygl_num">2</span> --></div>
    <div class="wgyg_list"><img src="${global_image_url}/wgy_rhzf2.png"><!--<span class="wgygl_num">2000</span> --></div>
</div>
</body>
</html>
