<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>选择城市</title>
<link rel="stylesheet" type="text/css" href="${global_css_url}/css.css" />
</head>
<body>
  <div class="chengshi">
  	<h1>当前定位城市</h1>
    <div class="nanj">${currentCityInfo.cityName}</div>
  </div>
  <div class="chengshi">
  	<h1>${currentCityInfo.proName}</h1>
      <ul class="shengshi">
      	  <c:if test="${not empty citys }" >
      	  	<c:forEach items="${citys}" var="city">
          		<li>
          		<a ${(city.CITY_ID ==currentCityInfo.cityId)?"class='cityon'":''} href="${global_url}/index/getIndexInfo.htm?ckey=${city.CITY_ID}&pkey=${city.PROVICE_ID}">${city.CITY_NAME }</a></li>
          	</c:forEach>
          </c:if>
      </ul>
  </div>
</body>
</html>

