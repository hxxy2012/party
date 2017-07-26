<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>血压测量历史数据</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>

</head>
<body>

<div class="jksb_list">
	<div class="jksb_title">
		<b>血压正常值 89/59-130/89 (mmHg)</b>
	</div> 
  
	<ul >
		<i class="s_time"></i>
		<c:if test="${not empty bloodPressList}">
	       	<c:forEach var="pressMap" items="${bloodPressList}">
		    	<li>
					<div class="s_icon_div"><span class="s_icon jc_5"></span></div>
					<div class="s_content">
						<p class="sb_g">${pressMap.measureTime }<p>
						<p>高压：<b>${pressMap.highPressure}</b>(mmHg) <p>
						<p>低压：<b>${pressMap.lowPressure}</b>(mmHg)<p>
						<p>平均压：<b>${pressMap.averagePressure}</b>(mmHg)<p>
						<p>脉率：<b>${pressMap.pulseRate}</b>(bpm)<p>
						
					</div>
				</li>
			</c:forEach>
	   	</c:if>
    </ul>
   	<c:if test="${empty bloodPressList}">
      		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
   	</c:if>
</div>

</body>
</html>
