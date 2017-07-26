<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>排行榜</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_lxyz.jpg"></li>
	</ul>
</div>

<div class="Courseinfor" style=" margin-top:10px;">
	<table align="center" border="1" cellpadding="0" cellspacing="0" width="100%" class="phb_table">
		<tbody>
			<tr>
				<th>排名</th>
				<th width="30%">姓名</th>
				<th>分数</th>
				<th>用时</th>
			</tr>
			
			<c:if test="${not empty qsMemberQuestionnaireList}">
				<c:forEach var="qsMemberQuestionnaire" items="${qsMemberQuestionnaireList}" varStatus="status">
					<tr>
						<td align="center">${status.index+1}</td>
						<td align="center">${qsMemberQuestionnaire.name}</td>
						<td align="center">${qsMemberQuestionnaire.grade}</td>
						<td align="center">${qsMemberQuestionnaire.totalTime}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<!-- <div class="wj_fenye">
		<a class="prev" href="#">上一页<span class="#"></span></a> 2/2
		<a class="prev" href="#">下一页<span class="#"></span></a> 
	</div> -->
</div>

<!-- 底部按钮 -->
<div style="float:left; width:100%;height:71px;"></div>
<div class="bottominf1">
	<a href="${global_url}/qsQuestionnaire/initQSQuestionnaireInfo.htm?qs_id=${qsId}"><button class="check-order" id="orders">返回问卷</button></a>
</div>

</body>
</html>
