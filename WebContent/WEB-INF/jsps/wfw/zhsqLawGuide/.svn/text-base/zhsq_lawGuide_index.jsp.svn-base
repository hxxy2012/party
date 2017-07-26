<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*;"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>社区办事指南</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<%-- <link rel="stylesheet" type="text/css" href="${global_css_url }/app.css" /> --%>
<script src="${global_js_url }/zepto.min.js" type="text/javascript"></script>
<script src="${global_js_url }/Mbase.js" type="text/javascript"></script>
<script src="${global_js_url }/index.js" type="text/javascript"></script>
<script src="${global_js_url }/swipe.js" type="text/javascript"></script>

<style>
/* #mySwipe div {
  display:block;
  font-weight:bold;
  color:#14ADE5;
  font-size:20px;
  text-align:center;
  margin:10px;
  padding:100px 10px;
  box-shadow: 0 1px #EBEBEB;
  background: #fff;
  border-radius: 3px;
  border: 1px solid;
  border-color: #E5E5E5 #D3D3D3 #B9C1C6;
} */
  
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
		 $(".slide").css("height",windowWidth*0.3+"px");
		 $(".slide-banner img").css("height",windowWidth*0.3+"px");
		 $(".slide-banner").css("height",windowWidth*0.3+"px");
		 $(".message").css("width",windowWidth+"px");
		 $(".slide-banner img").css("width",windowWidth+"px");
		
	}
</script>
<!-- 幻灯片 -->
<script>
$(function(){
	//var indexADJsonArray = ${indexADJsonArray};
	//alert("indexADJsonArray=="+indexADJsonArray);
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
	
	
//处理分类滑动	
/*  $("#li_1").swipe(function(){
	$(this).hide();
	$("#li_2").show();
});  */

/* $('#li_1').bind("touchmove",function(){   
	
	$(this).hide();
	$("#li_2").show();  
	
});  */	

/* 	 var slider = Swipe(document.getElementById('slider'), {  
		  //startSlide: 4,  //起始图片切换的索引位置
		  auto: 3000, //设置自动切换时间，单位毫秒
		  continuous: true,  //无限循环的图片切换效果
		  disableScroll: false,  //阻止由于触摸而滚动屏幕
		  stopPropagation: false,  //停止滑动事件
		  callback: function(index, element) {},  //回调函数，切换时触发
		  transitionEnd: function(index, element) {}  //回调函数，切换结束调用该函数。
	  });  */ 
	 var elem = document.getElementById('mySwipe');
	// var bullets = document.getElementById('position').getElementsByTagName('li');
	 window.mySwipe = Swipe(elem, {
		  //startSlide: 1,  //起始图片切换的索引位置
		  auto: 3000, //设置自动切换时间，单位毫秒
		  continuous: true,  //无限循环的图片切换效果
		  disableScroll: false,  //阻止由于触摸而滚动屏幕
		  stopPropagation: false,  //停止滑动事件
		  callback: function(index, element) {},  //回调函数，切换时触发
	 /* 	  callback: function(pos) {  
		        var i = bullets.length; 
		         while(i--){  
			            bullets[i].className = ' ';  
			        }  
	        	 bullets[pos].className = 'on';
		          
		    },  */
		  transitionEnd: function(index, element) {}  //回调函数，切换结束调用该函数。
	 });	  
	  	
	
});
</script>
</head>
<body>
	<div class="slider">
		<ul class="slide-banner">
			<li><img src="${global_image_url}/banner_bszn.jpg"></li>
		</ul>
	</div>
	<section class="main_body" style="margin-top: 10px;float: left; width: 100%;">
		<section class="module">
			<div class="titlebar">
				<h5>
					<img src="${global_image_url}/ico_bmfw.png" width="25px"
						height="25px;" style="position: relative; top: 5px;" />&nbsp;办事指南
				</h5>
				<div class="more"></div>
			</div>
		</section>

		<div class="mk bszn_fl">
			<table class="fenlei_table">
				<c:if
					test="${not empty basLawGuideList && fn:length(basLawGuideList) > 0}">
					<tr>
					<ul class="channels">
						<c:forEach var="liveMap" items="${basLawGuideList}" varStatus="stat" begin="0" end="3">
							
							<li>
								<%-- <a href="${liveMap.lgUrl}"> 
									<img class="feilei_icon" src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a> --%>
								<a href="${global_url}/zhsq/lawGuide/getZhsqLawGuideListMap.htm?type=${liveMap.classId}"> 
									<img src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a>
							</li>

						</c:forEach>
						<ul/>
					</tr>
					<tr>
					<ul class="channels">
						<c:forEach var="liveMap" items="${basLawGuideList}" varStatus="stat" begin="4" end="7">
							<li>
								<%-- <a href="${liveMap.lgUrl}"> 
									<img class="feilei_icon" src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a> --%>
								<a href="${global_url}/zhsq/lawGuide/getZhsqLawGuideListMap.htm?type=${liveMap.classId}"> 
									<img src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a>
							</li>
						</c:forEach>
						<ul/>
					</tr>
					<tr>
					<ul class="channels">
						<c:forEach var="liveMap" items="${basLawGuideList}" varStatus="stat" begin="8" end="11">
							<li>
								<%-- <a href="${liveMap.lgUrl}"> 
									<img class="feilei_icon" src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a> --%>
								<a href="${global_url}/zhsq/lawGuide/getZhsqLawGuideListMap.htm?type=${liveMap.classId}"> 
									<img src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a>
							</li>
						</c:forEach>
						<ul/>
					</tr>
					<tr>
					<ul class="channels">
						<c:forEach var="liveMap" items="${basLawGuideList}" varStatus="stat" begin="12" end="15">
							<li>
								<%-- <a href="${liveMap.lgUrl}"> 
									<img class="feilei_icon" src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a> --%>
								<a href="${global_url}/zhsq/lawGuide/getZhsqLawGuideListMap.htm?type=${liveMap.classId}"> 
									<img src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a>
							</li>
						</c:forEach>
						<ul/>
					</tr>
					<tr>
					<ul class="channels">
						<c:forEach var="liveMap" items="${basLawGuideList}" varStatus="stat" begin="16" end="19">
							<li>
								<%-- <a href="${liveMap.lgUrl}"> 
									<img class="feilei_icon" src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a> --%>
								<a href="${global_url}/zhsq/lawGuide/getZhsqLawGuideListMap.htm?type=${liveMap.classId}"> 
									<img src="${liveMap.paramMap.imagePaths}">
									<p>${liveMap.className}</p>
								</a>
							</li>
						</c:forEach>
						<ul/>
					</tr>
				</c:if>
			</table>
		</div>
	
	</section>

	<!--社区浮动个人中心 -->
	<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
