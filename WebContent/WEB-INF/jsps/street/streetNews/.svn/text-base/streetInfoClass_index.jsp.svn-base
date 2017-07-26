<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>街道资讯分类</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/JavaScript" src="${global_js_url}/jquery.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
});
//检测浏览器是否支持localStorage
var strStoreDate=window.localStorage?localStorage.getItem("member_account"):Cookie.read("member_account");
if(strStoreDate!=null){
	var result = $.ajax({
		url : "${global_url}/member/checkMemberState.htm?member_account="+strStoreDate,
		async : false,
		cache : false
	}).responseText;
}
</script>

</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_mh.jpg"></li>
	</ul>
</div>

<div class="zwlist">
	<ul>
		<c:if test="${not empty streetInfoClassList && fn:length(streetInfoClassList) > 0}">
			<c:forEach var="classMap" items="${streetInfoClassList}" varStatus="stat"> 
				 <li><a href="${global_url}/streetNews/getStreetNewsList.htm?classId=${classMap.classId}&streetId=${streetId}"><i class="zwcn${classMap.classId}"></i><p>${classMap.className}</p></a></li>
			</c:forEach>
		</c:if>
		
    </ul>
</div>


</body>
</html>

