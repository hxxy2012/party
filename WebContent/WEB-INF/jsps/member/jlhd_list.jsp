<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>我的咨询</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<!--海报-->
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_fw.jpg"></li>
	</ul>
</div> --%>

<div class="container">
	<div class="Classall">
		<div class="jl_list" style="margin-bottom:0px; padding-bottom:0px;" >
			<ul class="jlhd_list">
				<c:if test="${not empty jlhdList && fn:length(jlhdList) > 0}">
					<c:forEach var="jlhdMap" items="${jlhdList}" varStatus="stat"> 
						<li>
				            <a href="${global_url}/wfw/jlhd/memberInfo.htm?shequId=${shequId}&atId=${jlhdMap.atId}">
				                <b class="jlhd_ico">?</b>
				                ${jlhdMap.atDescription}
				                <div class="jlhd_list_bz">
				                    <i>${jlhdMap.createdTime}</i>
				                    <c:if test="${jlhdMap.atStatus=='0'}"><span class="jlhd_whf">未回复</span></c:if>
				                    <c:if test="${jlhdMap.atStatus!='0'}"><span class="jlhd_yhf">已回复</span></c:if>
				                </div>
				            </a>   
		        		</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty jlhdList}">
					<li>暂无数据！</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<div style="float:left; width:100%;height:71px;"></div>
<%-- 
<div class="bottominf1">
	<a href="${global_url}/wfw/jlhd/tiwen.htm?shequId=${shequId}"><button class="check-order " id="orders">提问</button></a>
</div> --%>
</body>
</html>