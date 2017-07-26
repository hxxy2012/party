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
<title>社区问答</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<%-- <link rel="stylesheet" type="text/css" href="${global_css_url }/app.css" /> --%>
<script src="${global_js_url }/zepto.min.js" type="text/javascript"></script>
<script src="${global_js_url }/Mbase.js" type="text/javascript" ></script>
<script src="${global_js_url }/index.js" type="text/javascript"></script>
<script src="${global_js_url }/swipe.js" type="text/javascript"></script>
<style>
.wj_da{ padding:10px;}
</style>
</head>
<body>
<div class="ban_img m_b10">
	<c:if test="${not empty topImg}">
	   <img src="${topImg}">
	</c:if>
	<c:if test="${empty topImg}">
	   <img src="${global_image_url}/banner_wjdc.jpg">
	</c:if>
</div>
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/banner_wjdc.jpg"></a></li>
	</ul>
</div> --%><!--海报结束-->



<!--社区活动-->
<div class="bszl_list">
<c:if test="${not empty streetQuestionInfoMap.wmspSurveyQuestionList}">
		<c:forEach var="streetQuestion" items="${streetQuestionInfoMap.wmspSurveyQuestionList}" varStatus="status">
			<div class="bszl_cont bszl_t">
					<span class="bszl_num">${status.index+1}</span>
			        	${streetQuestion.question}
			</div>
			<div class="bszl_cont">
				<c:if test="${not empty streetQuestion.wmspSurveyQuestionList}">
			   			<c:forEach var="streetQuestionMap" items="${streetQuestion.wmspSurveyQuestionList}" varStatus="statusMap">
							<div class="wj_da"><input type="radio" name="daan">${statusMap.index+1}、${streetQuestionMap.subject_option}</div>
						</c:forEach>
	    		</c:if>
			</div>
		</c:forEach>
</c:if>  
</div><!--社区活动结束-->


<!-- 底部按钮 -->
<div style="float:left; width:100%;height:71px;"></div>
<div class="bottominf1">
  <button class="check-order " id="orders">提交</button>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
