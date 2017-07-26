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
<title>
<c:choose>
	<c:when test="${newsInfoMap.projectType=='1'}">施工许可</c:when>
	<c:when test="${newsInfoMap.projectType=='2'}">道口开设</c:when>
</c:choose>
</title>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css"/>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css"/>
<script type="text/javascript" src="${xishan_js_url }/zepto.min.js"></script>
<script type="text/javascript" src="${xishan_js_url }/Mbase.js"></script>
<script type="text/javascript" src="${xishan_js_url }/index.js"></script>
<script type="text/javascript" src="${xishan_js_url }/swipe.js"></script>
<!-- <script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script> -->
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
});
</script>
<style>

.sh01{ color:#437dc7}
.sh02{ color:#f16767;}
.sh03{ color:#33c87a;}
</style>
	
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${xishan_image_url }/xs_banner_bzdt.png"></li>
	</ul>
</div><!--海报结束-->

<div class="xs_xq">
	<div class="biaoticon">
		<h1><c:if test="${not empty newsInfoMap && newsInfoMap.projectName!=''}">${newsInfoMap.projectName}</c:if></h1>
		<h3><c:if test="${not empty newsInfoMap && newsInfoMap.createdTime!=''}">${newsInfoMap.createdTime}</c:if></h3>
	</div>
	<c:if test="${newsInfoMap.projectType=='1'}">
		<ul class="xs_yy_zt">
			<c:if test="${newsInfoMap.newState=='0'}">
				<li><span>当前办理状态：</span></span><span class="sh03">施工合同备案中</span></li>
				<!-- <li><span class="sh01">安检手续办理</span></li>
				<li><span class="sh01">质监手续办理</span></li>
				<li><span class="sh01">监理合同备案</span></li>
				<li><span class="sh01">施工许可办理</span></li> -->
			</c:if>
			<c:if test="${newsInfoMap.newState=='1'}">
				<!-- <li><span class="sh01">施工合同备案</span></li> -->
				<li><span>当前办理状态：</span><span class="sh03">安检手续办理中</span></li>
				<!-- <li><span class="sh01">质监手续办理</span></li>
				<li><span class="sh01">监理合同备案</span></li>
				<li><span class="sh01">施工许可办理</span></li> -->
			</c:if>
			<c:if test="${newsInfoMap.newState=='2'}">
				<!-- <li><span class="sh01">施工合同备案</span></li>
				<li><span class="sh01">安检手续办理</span></li> -->
				<li><span>当前办理状态：</span><span class="sh03">质监手续办理中</span></li>
				<!-- <li><span class="sh01">监理合同备案</span></li>
				<li><span class="sh01">施工许可办理</span></li> -->
			</c:if>
			<c:if test="${newsInfoMap.newState=='3'}">
				<!-- <li><span class="sh01">施工合同备案</span></li>
				<li><span class="sh01">安检手续办理</span></li>
				<li><span class="sh01">质监手续办理</span></li> -->
				<li><span>当前办理状态：</span><span class="sh03">监理合同备案中</span></li>
				<!-- <li><span class="sh01">施工许可办理</span></li> -->
			</c:if>
			<c:if test="${newsInfoMap.newState=='5'}">
				<!-- <li><span class="sh01">施工合同备案</span></li>
				<li><span class="sh01">安检手续办理</span></li>
				<li><span class="sh01">质监手续办理</span></li>
				<li><span class="sh01">监理合同备案</span></li> -->
				<li><span>当前办理状态：</span><span class="sh03">施工许可证网上申报中</span></li>
			</c:if>
			<c:if test="${newsInfoMap.newState=='4'}">
				<!-- <li><span class="sh01">施工合同备案</span></li>
				<li><span class="sh01">安检手续办理</span></li>
				<li><span class="sh01">质监手续办理</span></li>
				<li><span class="sh01">监理合同备案</span></li> -->
				<li><span>当前办理状态：</span><span class="sh03">施工许可办理完结</span></li>
			</c:if>
		</ul>
	
		<div class="textcon xs_con">
			工程类型：
				<c:choose>
		      		<c:when test="${newsInfoMap.projectType=='1'}">施工许可</c:when>
		      		<c:when test="${newsInfoMap.projectType=='2'}">道口开设</c:when>
				</c:choose><br/>
			建设单位：<c:if test="${not empty newsInfoMap && newsInfoMap.company!=''}">${newsInfoMap.company}</c:if><br/>
			工程名称：<c:if test="${not empty newsInfoMap && newsInfoMap.projectName!=''}">${newsInfoMap.projectName}</c:if><br/>
			建筑面积：<c:if test="${not empty newsInfoMap && newsInfoMap.buildArea!=''}">${newsInfoMap.buildArea}</c:if><br/>
			合同造价：<c:if test="${not empty newsInfoMap && newsInfoMap.contractPrice!=''}">${newsInfoMap.contractPrice}</c:if><br/>
			建设地点：<c:if test="${not empty newsInfoMap && newsInfoMap.address!=''}">${newsInfoMap.address}</c:if><br/>
			开工日期：<c:if test="${not empty newsInfoMap && newsInfoMap.startTime!=''}">${newsInfoMap.startTime}</c:if><br/>
			竣工日期：<c:if test="${not empty newsInfoMap && newsInfoMap.endTime!=''}">${newsInfoMap.endTime}</c:if><br/>
			勘察单位：<c:if test="${not empty newsInfoMap && newsInfoMap.kanchaUnit!=''}">${newsInfoMap.kanchaUnit}</c:if><br/>
			设计单位：<c:if test="${not empty newsInfoMap && newsInfoMap.shejiUnit!=''}">${newsInfoMap.shejiUnit}</c:if><br/>
			施工单位：<c:if test="${not empty newsInfoMap && newsInfoMap.shigongUnit!=''}">${newsInfoMap.shigongUnit}</c:if><br/>
			监理单位：<c:if test="${not empty newsInfoMap && newsInfoMap.jianliUnit!=''}">${newsInfoMap.jianliUnit}</c:if><br/>
		</div>
	
	</c:if>
	
	<c:if test="${newsInfoMap.projectType=='2'}">
		<ul class="xs_yy_zt">
			<li><span>当前办理状态：</span>
				<c:choose>
					<c:when test="${newsInfoMap.audits=='0'}"><p class="sh02">办理中</p></c:when>
					<c:when test="${newsInfoMap.audits=='1'}"><span class="sh03">审核通过</span></c:when>
					<c:when test="${newsInfoMap.audits=='2'}"> <span class="sh02">审核不通过</span></c:when>
					<c:otherwise><span class="sh01">未审核</span></c:otherwise>
				</c:choose> 
		    </li>
	    </ul>
	    
	    <div class="textcon xs_con">
			工程类型：
				<c:choose>
		      		<c:when test="${newsInfoMap.projectType=='1'}">施工许可</c:when>
		      		<c:when test="${newsInfoMap.projectType=='2'}">道口开设</c:when>
				</c:choose><br/>
			申请单位：<c:if test="${not empty newsInfoMap && newsInfoMap.projectName!=''}">${newsInfoMap.projectName}</c:if><br/>
			申请日期：<c:if test="${not empty newsInfoMap && newsInfoMap.applyDate!=''}">${newsInfoMap.applyDate}</c:if><br/>
			挖掘占用地点：<c:if test="${not empty newsInfoMap && newsInfoMap.address!=''}">${newsInfoMap.address}</c:if><br/>
			挖掘占用时间：<c:if test="${not empty newsInfoMap && newsInfoMap.applyNeedTime!=''}">${newsInfoMap.applyNeedTime}</c:if><br/>
		</div>
	</c:if>

</div>

</body>
</html>
