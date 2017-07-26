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
<title>街道办事指南</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_bszn.jpg"></li>
	</ul>
</div>

<div class="bszl_list">
	<div class="bszl_cont bszl_t">
       	${streetLawGuideMap.title }
	</div>
	<div class="bszl_cont">
       	<div class="bszl_title">主管部门</div>
       	<div class="bszl_details ">${streetLawGuideMap.competent_department }</div>
	</div>
   	<div class="bszl_cont">
       	<div class="bszl_title">法律依据</div>
       	<div class="bszl_details ">${streetLawGuideMap.legal_basis }</div>
	</div>
       <div class="bszl_cont">
       	<div class="bszl_title">申办条件</div>
       	<div class="bszl_details ">${streetLawGuideMap.bid_condition }</div>
	</div>
	<div class="bszl_cont">
       	<div class="bszl_title">申请材料</div>
       	<div class="bszl_details ">${streetLawGuideMap.application_material }</div>
	</div>
	<div class="bszl_cont">
       	<div class="bszl_title">办理程序</div>
       	<div class="bszl_details ">${streetLawGuideMap.handling_procedures }</div>
	</div>
	<div class="bszl_cont">
       	<div class="bszl_title">收费标准</div>
       	<div class="bszl_details "><c:out value="${streetLawGuideMap.charging_standard }" default="无"></c:out></div>
	</div>
</div>
</body>
</html>
