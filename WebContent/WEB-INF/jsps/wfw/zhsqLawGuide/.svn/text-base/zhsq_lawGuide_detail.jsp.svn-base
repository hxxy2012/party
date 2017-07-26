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
<title>社区办事指南</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<div class="ban_img m_b10">
	<c:if test="${not empty topImg}">
	   <img src="${topImg}">
	</c:if>
	<c:if test="${empty topImg}">
	  <img src="${global_image_url}/banner_bszn.jpg">
	</c:if>
</div>
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_bszn.jpg"></li>
	</ul>
</div> --%>

<div class="bszl_list">
	<c:if test="${not empty zhsqLawGuideMap.title}">
		<div class="bszl_cont bszl_t">
       		${zhsqLawGuideMap.title }
		</div>
	</c:if>
	<c:if test="${not empty zhsqLawGuideMap.competent_department}">
		<div class="bszl_cont">
	       	<div class="bszl_title">主管部门</div>
	       	<div class="bszl_details ">${zhsqLawGuideMap.competent_department }</div>
		</div>
	</c:if>
	<c:if test="${not empty zhsqLawGuideMap.legal_basis}">
	   	<div class="bszl_cont">
	       	<div class="bszl_title">法律依据</div>
	       	<div class="bszl_details ">${zhsqLawGuideMap.legal_basis }</div>
		</div>
	</c:if>
	<c:if test="${not empty zhsqLawGuideMap.bid_condition}">
		<div class="bszl_cont">
	       	<div class="bszl_title">申办条件</div>
	       	<div class="bszl_details ">${zhsqLawGuideMap.bid_condition }</div>
		</div>
	</c:if>
	<c:if test="${not empty zhsqLawGuideMap.application_material}">
		<div class="bszl_cont">
	       	<div class="bszl_title">申请材料</div>
	       	<div class="bszl_details ">${zhsqLawGuideMap.application_material }</div>
		</div>
	</c:if>
	<c:if test="${not empty zhsqLawGuideMap.handling_procedures}">
		<div class="bszl_cont">
	       	<div class="bszl_title">办理程序</div>
	       	<div class="bszl_details ">${zhsqLawGuideMap.handling_procedures }</div>
		</div>
	</c:if>
	<c:if test="${not empty zhsqLawGuideMap.charging_standard}">
	<div class="bszl_cont">
       	<div class="bszl_title">收费标准</div>
       	<div class="bszl_details "><c:out value="${zhsqLawGuideMap.charging_standard }" default="无"></c:out></div>
	</div>
	</c:if>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
