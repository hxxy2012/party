<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>血糖测量历史数据</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>

</head>
<body>

<div class="jksb_list">
	 <div class="jksb_title">
		<b>血糖正常值(空腹) 3.9-6.1 (mmol/L)</b>
	</div> 
  
	<ul >
		<i class="s_time"></i>
		<c:if test="${not empty bloodSugarList}">
	       	<c:forEach var="sugarMap" items="${bloodSugarList}">
		    	<li>
		    		<div class="s_icon_div"><span class="s_icon jc_5"></span></div>
					<div class="s_content">
						<p class="sb_g">${sugarMap.measureTime }<p>
						<p>血糖值：<b>${sugarMap.bsValue}(mmol/L)</b>&nbsp;&nbsp; <p>
					</div>
				</li>
			</c:forEach>
	   	</c:if>
    </ul>
   	<c:if test="${empty bloodSugarList}">
      		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
   	</c:if>
</div>

</body>
</html>
