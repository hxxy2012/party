<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="com.gw.base.util.TokenGen" %>
<%@ page import="java.util.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglib.jsp" %>
<%
  TokenGen.getInstance().saveToken(request);
  String token = (String)session.getAttribute("token");
%>
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
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<style>
.wj_da{ padding:10px;}
</style>
</head>
<body>
<script type="text/javascript">
 $(function(){
	 setImgHeight();
 });
	function setImgHeight(){
		 var windowWidth = $(window).width();
		 $(".slide").css("height",windowWidth*0.3+"px");
		 $(".slide-banner img").css("height",windowWidth*0.3+"px");
		 $(".slide-banner").css("height",windowWidth*0.3+"px");
		 $(".message").css("width",windowWidth+"px");
		 $(".slide-banner img").css("width",windowWidth+"px");
		
	}
</script>



<!--社区活动-->
<form id="form1" action="${global_url}/zhsq/question/subZhsqQuestion.htm" method="post">
	<input type="hidden" name="token" value="<%=token%>"/>
	<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
	<input type="hidden" name="name" id="name" value="${memberMap.name}" />
	<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
	<input type="hidden" id="qs_id" name="qs_id" value="${zhsqQuestionInfoMap.qs_id}">
	
<c:if test="${not empty zhsqQuestionInfoMap.zhsqSurveyQuestionList}">
<%--  <input type="text" id="listSize" name="listSize" value="${fn:length(zhsqQuestionInfoMap.zhsqSurveyQuestionList)}"/> --%>
	<c:forEach var="zhsqQuestion" items="${zhsqQuestionInfoMap.zhsqSurveyQuestionList}" varStatus="status">
		<div class="bszl_list" id="quest_${status.index+1}">
		
					<%-- <input type="hidden" id="question_type_${zhsqQuestion.sq_id}" name="question_type_${zhsqQuestion.sq_id}" value="${zhsqQuestion.question_type}"> --%>
					<input type="hidden" id="sq_id_${zhsqQuestion.sq_id}" name="sq_id_${zhsqQuestion.sq_id}" value="${zhsqQuestion.question_type}">
					<input type="hidden" id="question" name="question" value="${zhsqQuestion.question}">
					<div class="bszl_cont bszl_t">
							<span class="bszl_num">${status.index+1}</span>
					        	${(zhsqQuestion.question=='')?'暂无题目':zhsqQuestion.question}
					</div>
					<div class="bszl_cont">
						<c:if test="${not empty zhsqQuestion.zhsqSurveyQuestionList}">
					   			<c:forEach var="zhsqQuestionMap" items="${zhsqQuestion.zhsqSurveyQuestionList}" varStatus="statusMap">
									<div class="wj_da"><input disabled="disabled" ${(zhsqQuestion.subject_option_id==zhsqQuestionMap.sq_id)?'checked':'' } type="radio" name="radio_${zhsqQuestion.sq_id}" id="radio_${zhsqQuestion.sq_id}" value="${zhsqQuestionMap.sq_id}">${statusMap.index+1}、${zhsqQuestionMap.subject_option}</div>
								</c:forEach>
			    		</c:if>
			    		<c:if test="${empty zhsqQuestion.zhsqSurveyQuestionList}">
									<div class="wj_da">暂无选项</div>
			    		</c:if>
					</div>
				
		</div><!--社区活动结束-->
	</c:forEach>
</c:if>  

<c:if test="${empty zhsqQuestionInfoMap.zhsqSurveyQuestionList}">
	<div class="bszl_list">
			暂无题目
	</div>
</c:if>

</form>


<!--社区浮动个人中心 -->
<%-- <jsp:include page="../../include/floattoolbar.jsp" /> --%>

</body>
</html>
