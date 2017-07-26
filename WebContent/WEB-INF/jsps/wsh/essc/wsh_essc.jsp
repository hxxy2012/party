<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>故障报修列表</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>


<div class="Courseinfor" >
	<div class="course-header" style="border-bottom:none">
    	<h2>马群社区</h2>
        <div class="course-content">
             <div class="course-row"><label>电话：</label>025-88888888</div>
             <div class="course-row"><label>地址：</label>马群街道马群社区</div>
        </div>
	</div>
</div>

<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2>马群社区故障报修预约介绍</h2>
        <div class="course-content">
        	<p>社区可以解决故障报修问题会及时为各位业主解决，如果需要有偿维修也会回单告知。</p>
            <p>预约记录可以在个人中心查询。</p>
        </div>
	</div>
</div>
<form action="${global_url}/wfw/gzbx/getGZBXYuYue.htm?shequId=${shequId}">
<!--预约列表-->
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu yyjl "></span>预约记录</h5>
		</div>	
	</section>
    <ul class="course-content" style="padding:0 10px;"> 
        <li class="course-Per"><label>2015-10-22 10:13:00</label>夏家佳提交[待确定]</li>
        <li class="course-Per"><label>2015-10-22 10:13:00</label>韩旭提交[待确定]</li>
        <li class="course-Per"><label>2015-10-22 10:13:00</label>付云提交[待确定]</li>
        <li class="course-Per"><label>2015-10-22 10:13:00</label>宋旭东提交待确定]</li>
  </ul>
</div><!--预约列表结束-->
	
  <div style="float:left; width:100%;height:71px;"></div>

<!-- 底部按钮 -->
<div class="bottominf1">
  <button class="check-order " id="orders">填写预约</button>
</div>
</form>

</body>
</html>

