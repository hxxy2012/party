<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>分类列表</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/JavaScript" src="${global_js_url}/jquery.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
});
</script>
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/sqzw.jpg"></a></li>
	</ul>
	<!--<div class="indicator">
		<div class="indicator-num">
			<span class="point"></span>
			<span class="point"></span>
			<span class="point selected"></span>
			<span class="point"></span>
			<span class="point"></span>
		</div>
	</div>-->
</div>

<div class="fenleilist">
	<ul>
		<c:if test="${not empty newsClassList && fn:length(newsClassList) > 0}">
			<c:forEach var="classMap" items="${newsClassList}" varStatus="stat"> 
				 <li><div class="fenlei_wmh cn${stat.index+1}"></div><a href="${global_url}/wmh/news/getNewsList.htm?classId=${classMap.classId}&className=${classMap.className}&shequId=${shequId}">${classMap.className}<div class="fenleitou"><img src="${global_image_url }/morem.png"></div></a></li>
			</c:forEach>
		</c:if>
		<c:if test="${empty newsClassList || fn:length(newsClassList) == 0}">
			<li>暂无数据</li>
		</c:if>
    </ul>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>

