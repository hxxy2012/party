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
<title>街道问卷调查</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_wjdc.jpg"></li>
	</ul>
</div>

<!-- <div class="sousuo">
  <div class="sousuo-in">
    <input type="text" placeholder="请输入关键字搜索" >
  <span><img src="${global_image_url}/cha.png" width="30" height="30"></span> </div>
  <input type="submit" value="" id="submit-ss">
</div> -->
    
<div class="bszl_list">
	<ul >
		<c:if test="${not empty streetQuestionList}">
       		<c:forEach var="streetQuestion" items="${streetQuestionList}" varStatus="status">
		    	<li>
					<a href="${global_url}/street/streetQuestion/getStreetQuestionInfo.htm?qs_id=${streetQuestion.qs_id }">
		                <span class="bszl_num">${status.index+1}</span>
		                <span class="wj_title">${streetQuestion.title}</span>
		                <p class="wj_p">${streetQuestion.detail}</p>
		           	</a>
				</li>
        	</c:forEach>
	    </c:if>
	    <c:if test="${empty streetQuestionList}">
	    	<li>暂无数据</li>
	    </c:if>
    </ul>
</div>
</body>
</html>
