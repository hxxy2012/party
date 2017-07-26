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
<title><c:if test="${not empty newsInfoMap && newsInfoMap.title!=''}">${newsInfoMap.title}</c:if></title>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css"/>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/village.css"/>
<script type="text/javascript" src="${xishan_js_url }/zepto.min.js"></script>
<script type="text/javascript" src="${xishan_js_url }/Mbase.js"></script>
<script type="text/javascript" src="${xishan_js_url }/index.js"></script>
<script type="text/javascript" src="${xishan_js_url }/swipe.js"></script>
<script src="${xishan_js_url }/pdf/pdf.js"></script>
<!-- <script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script> -->
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
});
</script>

<script id="script">
/* var url = '${image_server}${newsInfoMap.fileUrl}'; */
  var url = '${image_server}${newsInfoMap.fileUrl}';
 

PDFJS.workerSrc = '${xishan_js_url }/pdf/pdf.worker.js';

var pdfDoc = null;
var	pageNum = 1;
var	pageRendering = false;
var	pageNumPending = null; 
var container = this.viewer;

function renderPage(num) {
	pageRendering = true;
	pdfDoc.getPage(num).then(function(page) {
		var DEFAULT_SCALE= 3;
		var viewport = page.getViewport(page.getViewport(DEFAULT_SCALE).height / page.getViewport(DEFAULT_SCALE).width);
		
		var canvasId = 'the-canvas';
		var canvas = document.getElementById(canvasId);
		var ctx = canvas.getContext('2d');
		
		canvas.height = viewport.height;
		canvas.width = viewport.width;
		var renderContext = {
			canvasContext : ctx,
			viewport : viewport
		};
		var renderTask = page.render(renderContext);
		renderTask.promise.then(function() {
			pageRendering = false;
			if (pageNumPending !== null) {
				renderPage(pageNumPending);
				pageNumPending = null;
			}
		});
	});
	document.getElementById('page_num').textContent = pageNum;
}

function queueRenderPage(num) {
	if (pageRendering) {
		pageNumPending = num;
	} else {
		renderPage(num);
	}
}

function onPrevPage() {
	if (pageNum <= 1) {
		return;
	}
	pageNum--;
	queueRenderPage(pageNum);
}

function onNextPage() {
	if (pageNum >= pdfDoc.numPages) {
		return;
	}
	pageNum++;
	queueRenderPage(pageNum);
}

PDFJS.getDocument(url).then(function(pdfDoc_) {
	pdfDoc = pdfDoc_;
	document.getElementById('page_count').textContent = pdfDoc.numPages;
	renderPage(pageNum);
});

</script>

<style>
.sh01{ color:#437dc7}
.sh02{ color:#f16767;}
.sh03{ color:#33c87a;}

html,body{
	height:100%;
}
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
// 	 $(".slide").css("height",windowWidth*0.3+"px");
// 	 $(".slide-banner img").css("height",windowWidth*0.3+"px");
// 	 $(".slide-banner").css("height",windowWidth*0.3+"px");
// 	 $(".message").css("width",windowWidth+"px");
 	 $(".slide-banner img").css("width",windowWidth+"px");
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

function iFrameHeight() {   
	var ifm= document.getElementById("iframepage");   
	var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	}   
}   
</script>	
</head>
<body>
<div class="biaoticon">
	<h1><c:if test="${not empty newsInfoMap && newsInfoMap.title!=''}">${newsInfoMap.title}</c:if></h1>
	<h3>
		<c:if test="${not empty newsInfoMap && newsInfoMap.createdTime!=''}">${newsInfoMap.createdTime}</c:if> 
		<i>已阅读<c:if test="${not empty newsInfoMap && newsInfoMap.viewCount!=''}">${newsInfoMap.viewCount}</c:if>次</i>
	</h3>
</div>

<c:if test="${not empty newsInfoMap.isFile && newsInfoMap.isFile=='1' }">
	<div>
			<div>
				<a class="sh01" href="${image_server}${newsInfoMap.fileUrl}">下载原文件</a>
				<button id="prev" onclick="onPrevPage();">上一页</button>
				<button id="next" onclick="onNextPage();">下一页</button>
				&nbsp; &nbsp; <span>当前页: <span id="page_num"></span> / 
				<span id="page_count"></span></span>
			</div>
			
			<canvas id="the-canvas" style="border: 1px solid black;width:100%;height:200%;"></canvas>
	</div>
</c:if>

<c:if test="${empty newsInfoMap.isFile || newsInfoMap.isFile=='0' }">
<c:choose>
	<c:when test="${indexADSize>1}">
		<div id="slider" class="slider" >
			<ul id="slider_touch" class="slide-banner"></ul>
			<div class="indicator">
				<div id="serial-number" class="indicator-num">
				<c:if test="${not empty indexADListNew}">
					<c:forEach var="indexMap" items="${indexADListNew}" varStatus="stat">
		       			<c:choose>
				       		<c:when test="${stat.index==0}">
								<span class="point selected" ></span>
					   		</c:when>
					   		<c:otherwise>
								<span class="point"></span>
			      			</c:otherwise>
	     				</c:choose>
					</c:forEach>
				</c:if>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${indexADSize==1}">
		<div id="slider" class="slider" class="oneImg">
			<ul id="slider_touch" class="slide-banner">
			<li><a><img alt="" src="${imageUrl}"></a></li>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<div id="slider" class="slider" class="oneImg">
			<ul id="slider_touch" class="slide-banner">
			<li><%-- <a><img alt="" src="${xishan_image_url}/zhsq_banner1.jpg"></a> --%></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>

<div class="textcon">
	<c:if test="${not empty newsInfoMap && newsInfoMap.newsContent!=''}">${newsInfoMap.newsContent}</c:if>
	<br/><br/>
</div>

</c:if>
  
</body>
</html>
