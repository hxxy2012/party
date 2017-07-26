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
<title>社区问卷详情页</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/banner_wjdc.jpg"></a></li>
	</ul>
</div><!--海报结束-->

<!--社区活动-->
<div class="bszl_list">

	<c:if test="${not empty zhsqQuestionInfoMap}">
			<div class="bszl_cont bszl_t text_m wj_title">
	        	${zhsqQuestionInfoMap.title}
			</div>
			<c:if test="${not empty zhsqQuestionInfoMap && not empty zhsqQuestionInfoMap.detail && zhsqQuestionInfoMap.detail!=''}">
			<div class="wj_cont">
	        	<p>
	            	${zhsqQuestionInfoMap.detail}
	            </p>
			</div>
			</c:if>
	</c:if>
</div><!--社区活动结束-->

<!-- 底部按钮 -->
<div style="float:left; width:100%;height:71px;"></div>
<div class="bottominf1">
  <a href="${global_url}/zhsq/question/getZhsqQuestionInfo.htm?qs_id=${zhsqQuestionInfoMap.qs_id }&temp=1"><button class="check-order " id="orders" >开始问卷</button></a>
</div>


</body>
</html>
