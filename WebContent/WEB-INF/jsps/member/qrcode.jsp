<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>二维码</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${global_js_url }/zepto.min.js"></script>
<script type="text/javascript" src="${global_js_url }/Mbase.js"></script>
<script type="text/javascript" src="${global_js_url }/index.js"></script>
<script type="text/javascript" src="${global_js_url }/swipe.js"></script>
<script type="text/javascript">
$(function(){
	setImgHeight();
});
function setImgHeight(){
	var windowWidth = $(window).width();
	//$(".qrcodeDiv img").css("width",windowWidth+"px");
	$("#qrcodeDiv img").css("width",windowWidth*0.9+"px");
}
</script>
</head>
<body>
<div id="qrcodeDiv" style='width:100%;text-align:center;'><img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${ticket}"></div>
</body>
</html>
