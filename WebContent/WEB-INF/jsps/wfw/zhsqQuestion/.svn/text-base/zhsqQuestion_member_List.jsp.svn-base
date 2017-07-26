<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>我的问卷</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<ul class="dqpm_list">
	<c:if test="${not empty qsMemberQuestionnaireList}">
		<c:forEach var="qsMemberQuestionnaire" items="${qsMemberQuestionnaireList}" varStatus="status">
			<li class="dqpm_cont">
				<div class="dqpm">
					<div class="dqpm_title">当前排名</div>
					<div class="dqpm_pm"><a href="${global_url}/qsQuestionnaire/qsQuestionRankList.htm?qsId=${qsMemberQuestionnaire.qsId}">${qsMemberQuestionnaire.xuhao}</a></div>
				</div>
		        <div class="dqpm_content">
					<div class="bszl_details ">
						<span class="jd_title_c">[马群街道]</span> “两学一做”问卷调查”
					</div>
		               <div class="dt_time">${qsMemberQuestionnaire.createdTime}</div>
		               <div class="dqpm2">
		                   <div class="dqpm_title2">得分</div>
		                   <div class="dqpm_pm2">${qsMemberQuestionnaire.grade}</div>
					</div>
		               <div class="dqpm3">
		                   <div class="dqpm_title3">用时</div>
		                   <div class="dqpm_pm3">${qsMemberQuestionnaire.totalTime}</div>
		               </div>
				</div> 
			</li>
		</c:forEach>
	</c:if>
	<c:if test="${empty qsMemberQuestionnaireList}">
		<li class="dqpm_cont"><span>暂无问卷</span></li>
	</c:if>
</ul>
</body>
</html>
