<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>社区简介</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css"/>
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css"/>
<script type="text/javascript" src="${global_js_url }/zepto.min.js"></script>
<script type="text/javascript" src="${global_js_url }/Mbase.js"></script>
<script type="text/javascript" src="${global_js_url }/index.js"></script>
<script type="text/javascript" src="${global_js_url }/swipe.js"></script>
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
.swipe {
  overflow: hidden;
  visibility: hidden;
  position: relative;
}
.swipe-wrap {
  overflow: hidden;
  position: relative;
}
.swipe-wrap > div {
  float:left;
  width:100%;
  position: relative;
} 
nav #position {  
    text-align: center;  
    list-style: none;  
    margin: 0;  
    padding: 0  
}  
nav #position li {  
    display: inline-block;  
    width: 10px;  
    height: 10px;  
    border-radius: 10px;  
    background: #141414;  
    box-shadow: inset 0 1px 3px black,0 0 1px 1px #202020;  
    margin: 0 2px;  
    cursor: pointer  
}  
nav #position li.on {  
    box-shadow: inset 0 1px 3px -1px #28b4ea,0 1px 2px rgba(0,0,0,.5);  
    background-color: #1293dc;  
    background-image: -webkit-gradient(linear,left top,left bottom,color-stop(0%,#1293dc),color-stop(100%,#0f6297));  
    background-image: -webkit-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -moz-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -ms-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -o-linear-gradient(top,#1293dc,#0f6297);  
    background-image: linear-gradient(top,#1293dc,#0f6297)  
}  
</style>
<script type="text/javascript">
$(function(){
	setImgHeight();
});
function setImgHeight(){
	var windowWidth = $(window).width();
	$(".slide-banner img").css("width",windowWidth+"px");
	$(".textcon img").css("width",windowWidth*0.9+"px");
}
</script>
<script>
$(function(){
	var indexADJsonArrays = new Array();
	indexADJsonArrays = ${indexADJsonArray};
	var jsArgs = {};
	jsArgs['index'] = {
		sliderImgList: indexADJsonArrays,
		themeIdList: ['theme8'],
		seckill: {
			seckillTime: '2015-01-17 11:59:59',
			timeRemain: 7121
		},
		downloadHideTime: 1
	};
	jsArgs['html5_2015'] = {
		head: null,
		footer: {
			toPcHomeUrl: null
		}
	};
	M.setRunMod(['html5_2015']);

	M.setRunMod('index');
	jsArgs['BuriedPoint'] = '';
	M.setRunMod(['BuriedPoint']);
	M.runner(jsArgs);
	
	setImgHeight();
	
});
</script>	

</head>
<body>
<div class="biaoticon">
	<h1><c:if test="${not empty featureInfoMap && featureInfoMap.title!=''}">${featureInfoMap.title}</c:if></h1>
	<h3><c:if test="${not empty featureInfoMap && featureInfoMap.createdTime!=''}">${featureInfoMap.createdTime}</c:if> <i>已阅读<c:if test="${not empty featureInfoMap && featureInfoMap.viewCount!=''}">${featureInfoMap.viewCount}</c:if>次</i></h3>
</div>
    
<div class="textcon"  style="width:100%;">
	<c:if test="${not empty featureInfoMap && featureInfoMap.featureContent!=''}">${featureInfoMap.featureContent}</c:if>
	<c:if test="${empty featureInfoMap || featureInfoMap.featureContent==''}"></c:if>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>

