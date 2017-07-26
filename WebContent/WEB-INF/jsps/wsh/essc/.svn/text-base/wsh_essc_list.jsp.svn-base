<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>二手货列表</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<!--海报-->
<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/esh.jpg"></a></li>
	</ul>
</div>

<div class="Classall">
	<ul class="allcon">
		<c:if test="${not empty esscList}">
       	<c:forEach var="esscMap" items="${esscList}" begin="0" end="9">
       		<li>
				<a href="${global_url}/wsh/essc/getESSCInfo.htm?esscId=${esscMap.esscId}" >
       			<c:if test="${not empty esscMap.paramMap.imagePaths}"><img src="${esscMap.paramMap.imagePaths}" class="pic"></c:if>
       			<c:if test="${empty esscMap.paramMap.imagePaths}"><img src="${global_image_url}/mr_essc.png" class="pic"></c:if>
				</a>
				<div class="rt">
					<div class="tit">
						<a href="${global_url}/wsh/essc/getESSCInfo.htm?esscId=${esscMap.esscId}">
							<c:if test="${esscMap.auditStatus=='0'}">【未审核】${esscMap.title}</c:if>
							<c:if test="${esscMap.auditStatus=='1'}"><span style="color: green">【审核通过】</span>${esscMap.title}</c:if>
							<c:if test="${esscMap.auditStatus=='2'}"><span style="color: red">【审核不通过】</span>${esscMap.title}</c:if>
						</a>
					</div>
					<div class="dush">
	                	<div>交易价格：<i class="esh_jg">${esscMap.price}元</i></div>
						<div>${esscMap.createdTime}</div>
	                    <!-- <div class="ljbm0">251人查看 <i><i></div> -->
					</div>
				</div>
			</li>
       	</c:forEach>
       	</c:if>
    </ul>
</div>
	
  <div style="float:left; width:100%;height:71px;"></div>

<!-- 底部按钮 -->
<div class="bottominf1">
  <a href="${global_url}/wsh/essc/getESSCYuYue.htm?shequId=${shequId }"><button class="check-order " id="orders">发布二手货</button></a>
</div>

</body>
</html>

