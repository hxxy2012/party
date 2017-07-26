<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>我的社区问卷</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>

</head>
<body>
<div class="bszl_list">
	<ul >
		<c:if test="${not empty memberZhsqQuestionList}">
       		<c:forEach var="zhsqQuestion" items="${memberZhsqQuestionList}" varStatus="status">
		    	<li>
					<a href="${global_url}/zhsq/question/getZhsqMemberQuestionInfo.htm?qs_id=${zhsqQuestion.qs_id}&memberId=${memberId}">
		                <span class="bszl_num">${status.index+1}</span>
		                <span class="wj_title">${zhsqQuestion.title}</span>
		           	</a>
				</li>
        	</c:forEach>
	    </c:if>
	    <c:if test="${empty memberZhsqQuestionList}">
	    	<li>暂无数据</li>
	    </c:if>
    </ul>
</div>


</body>
</html>
