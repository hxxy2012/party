<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>群众之声</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<%-- <script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script> --%>
<%-- <script type="text/javascript" src="${global_url}/js/adv.js"></script> --%>
<!-- 幻灯片js -->
<%-- <script src="${global_js_url }/jquery-1.8.3.min.js" type="text/javascript" ></script> --%>
<script src="${global_js_url }/zepto.min.js" type="text/javascript"></script>
<script src="${global_js_url }/Mbase.js" type="text/javascript" ></script>
<script src="${global_js_url }/index.js" type="text/javascript"></script>
<script src="${global_js_url }/swipe.js" type="text/javascript"></script>


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

	 var elem = document.getElementById('mySwipe');
	 window.mySwipe = Swipe(elem, {
		  //startSlide: 1,  //起始图片切换的索引位置
		  auto: 3000, //设置自动切换时间，单位毫秒
		  continuous: true,  //无限循环的图片切换效果
		  disableScroll: false,  //阻止由于触摸而滚动屏幕
		  stopPropagation: false,  //停止滑动事件
		  callback: function(index, element) {},  //回调函数，切换时触发
		  transitionEnd: function(index, element) {}  //回调函数，切换结束调用该函数。
	 });	  
});
</script>	



</head>
<body>
<!-- 幻灯片 可滑动 -->
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/icon/dz_wasq/banner_qzzs.png"></li>
	</ul>
</div>



<!--群众之声分类-->
<div class="mk">
<section class="module ">
        <div class="tit_bar"">
            <h5 class="tit_name"><img class="tit_icon" src="${global_image_url }/ico_jbts.png"  />&nbsp;群众之声</h5>
        </div>
</section>	
<section class="tv-channel" style="float: left;width: 100%;">
	<ul class="channels">
	
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/yuyue/init.htm?shequId=462">
				<span class="ico cnl8"></span>
				<p>社区预约</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/wfw/txl/getTXLList.htm?shequId=462">
				<span class="ico cnl5"></span>
				<p>便民电话</p>
			</a>
		</li>	
		<li class="slides js_slides">
			<a href="${global_url}/wfw/jlhd/init.htm?shequId=462">
				<span class="ico cnl4"></span>
				<p>交流互动</p>
			</a>
		</li>	
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/question/getZhsqQuestion.htm?shequId=462">
				<span class="ico cnl10"></span>
				<p>问卷调查</p>
			</a>
		</li>	
		<li class="slides js_slides">
			<a href="${global_url}/wfw/sjxx/init.htm?shequId=462">
				<span class="ico cnl2"></span>
				<p>书记信箱</p>
			</a>
		</li>	
		<li class="slides js_slides">
			<a href="${global_url}/wfw/jbts/init.htm?shequId=462">
				<span class="ico cnl3"></span>
				<p>意见反馈</p>
			</a>
		</li>		
		
	</ul>
</section>
</div>	
	
	
	
	
	

    
</body>
</html>

