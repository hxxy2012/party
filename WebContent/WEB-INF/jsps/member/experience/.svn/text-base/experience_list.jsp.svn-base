<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>健康设备</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>

</head>
<body>
<div class="jksb_list">
	<ul >
	
	
		<li>
			<a href="${global_url}/zhsq/experience/getMemberBloodPressList.htm?shequId=${shequId}">
				<div class="s_icon_div">
					<span class="s_icon jc_1"></span>
				</div>
				<div class="s_content">
					电子血压计
					<p class="sb_g">最新数据：<p>
					<p class="sb_g">高压${bloodPressMap.highPressure}(mmHg)<p>
					<p class="sb_g">低压${bloodPressMap.lowPressure}(mmHg)<p>
					<p class="sb_g">平均压${bloodPressMap.averagePressure}(mmHg)<p>
					<p class="sb_g">脉率${bloodPressMap.pulseRate}(bpm)<p>
				</div>
           	</a>
		</li>
		<li>
			<a href="${global_url}/zhsq/experience/getMemberBloodOxygenList.htm?shequId=${shequId}">
				<div class="s_icon_div">
					<span class="s_icon jc_2"></span>
				</div>
				<div class="s_content">
					电子血氧仪
					<p class="sb_g">最新数据：血氧值${bloodOxygenMap.oxygenValue}(%/dpm)、脉率${bloodOxygenMap.pulseRate}(bpm)<p>
				</div>
           	</a>
		</li>
		<li>
			<a href="${global_url}/zhsq/experience/getMemberBloodSugarList.htm?shequId=${shequId}">
				<div class="s_icon_div">
					<span class="s_icon jc_3"></span>
				</div>
				<div class="s_content">
					电子血糖仪
					<p class="sb_g">最新数据：血糖值${bloodSugarMap.bsValue}(mmol/L)<p>
				</div>
           	</a>
		</li>
		<li>
			<a href="${global_url}/zhsq/experience/getMemberWeightList.htm?shequId=${shequId}">
				<div class="s_icon_div">
					<span class="s_icon jc_4"></span>
				</div>
				<div class="s_content">
					电子健康秤
					<p class="sb_g">最新数据：体重${weightMap.weight}(kg)<p>
				</div>
           	</a>
		</li>
	
    </ul>
</div>


</body>
</html>
