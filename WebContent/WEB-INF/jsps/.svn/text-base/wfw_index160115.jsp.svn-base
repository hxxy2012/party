<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>社区服务</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/app.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/hdp_css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/fl_css.css" />

<script type="text/javascript" src="${global_js_url }/jquery-1.8.3.min.js"></script><!-- 分类滑动 -->
<script type="text/javascript" src="${global_js_url }/responsiveslides.min.js"></script><!-- 幻灯片 -->
<script type="text/javascript" src="${global_js_url }/jquery.promptumenu.min.js"></script><!-- 分类滑动 -->
<script type="text/javascript">
		
<%-- <script src="${global_js_url }/zepto.min.js" type="text/javascript"></script> --%>
<script src="${global_js_url }/Mbase.js" type="text/javascript" ></script>
<script src="${global_js_url }/index.js" type="text/javascript"></script>
<script src="${global_js_url }/swipe.js" type="text/javascript"></script>

<script type="text/javascript">
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
<!-- 分类划动
<script src="${global_js_url }/highlight.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script src="${global_js_url }/lory.min.js"></script>
<script>
'use strict';
document.addEventListener('DOMContentLoaded', function () {  
    var multiSlides = document.querySelector('.js_multislides');
    lory(multiSlides, {
        infinite: 4,
        slidesToScroll: 4
    });
}); -->
</script>

 <!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->

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
 var windowWidth = $(window).width();
	function setImgHeight(){
		
		 $(".slide").css("height",windowWidth*0.3+"px");
		 $(".slide-banner img").css("height",windowWidth*0.3+"px");
		 $(".slide-banner").css("height",windowWidth*0.3+"px");
		 $(".message").css("width",windowWidth+"px");
		 $(".slide-banner img").css("width",windowWidth+"px");
		
	}
	
	 $(function(){
		 /*幻灯片*/
		 $("#slider").responsiveSlides({
			    auto: true,
			    pager: false,
			    nav: true,
			    speed: 100,
			    // 对应外层div的class : slide_container
			    namespace: "slide"
			    });
		 
		 /*分类*/
		$('#lanrenzhijia_3 ul').promptumenu({
			/*'width': 500,*/
			'height': 150,
			'rows': 1,
			'columns': 4,
			'direction': 'horizontal',
			'pages': true
		});
		
	});
	
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

<!-- 幻灯片 -->
<c:choose>
	<c:when test="${indexADSize>1}">
	    <div class="slide_container">
			<ul class="rslides" id="slider">
				<c:if test="${not empty indexADListNew}">
					<c:forEach var="indexMap" items="${indexADListNew}" varStatus="stat">
						<li><a><img alt="" src="${indexMap.img}"></a></li>
					</c:forEach>
				</c:if>
			</ul>
	    </div>
	 </c:when>
	<c:otherwise>
		 <div class="slide_container">
			<ul id="slider_touch" class="slide-banner">
				<li><a><img alt="" src="${global_image_url}/notadv.png"></a></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
<!-- 幻灯片 end -->

<!-- 分类 -->
<div class="tv-channel" id="lanrenzhijia_3"> 
	<ul class="channels">
		<c:if test="${shequId!='202'}">
			<li >
				<a href="${global_url}/wfw/gzbx/init.htm?shequId=${shequId}"><span class="ico cnl1"></span><p>故障报修</p></a>
			</li>
		</c:if>
		<li >
			<a href="${global_url}/wfw/sjxx/init.htm?shequId=${shequId}"><span class="ico cnl2"></span><p>书记信箱</p></a>
		</li>
		<li >
			<a href="${global_url}/wfw/jbts/init.htm?shequId=${shequId}"><span class="ico cnl3"></span><p>意见反馈</p></a>
		</li>
		<li >
			<a href="${global_url}/wfw/jlhd/init.htm?shequId=${shequId}"><span class="ico cnl4"></span><p>交流互动</p></a>
		</li>	
		<li >
			<a href="${global_url}/wfw/txl/getTXLList.htm?shequId=${shequId}"><span class="ico cnl5"></span><p>便民电话</p></a>
		</li>	
		<li >
			<a href="${global_url}/wfw/wgy/getWGYList.htm?shequId=${shequId}"><span class="ico cnl6"></span><p>网格人员</p></a>
		</li>	
	</ul>
</div><!-- 分类end -->
   
<!--通知公告-->
<div class="message">
	<c:if test="${not empty noticesMap}">
		<a href="${global_url}/noticesInfo/getNoticesInfo.htm?noticeId=${noticesMap.noticeId}"><span></span>${noticesMap.title}</a>
	</c:if>
	<c:if test="${empty noticesMap}">
		    <a href="javaScript void(0)"><span></span>暂无公告</a>
	</c:if>
</div>
<!--通知公告结束-->
    
<!--便民服务-->
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_bmfw.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;便民服务</h5>
			<div class="more"></div>
		</div>	
	</section>
	<div class="Classall">
		<div class="fenleilist" style="margin-bottom:0px; padding-bottom:0px;" >
			<ul>
		    	<li><div class="bmfw_wmh bmfw_cn1"></div><a href="${global_url}/wfw/bmfw/tqyb.htm">天气报告<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		        <li><div class="bmfw_wmh bmfw_cn2"></div><a href="${global_url}/wfw/bmfw/kdcx.htm">快递查询<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		        <li><div class="bmfw_wmh bmfw_cn3"></div><a href="${global_url}/wfw/bmfw/wzcx.htm">违章查询<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		        <li><div class="bmfw_wmh bmfw_cn4"></div><a href="${global_url}/wfw/bmfw/sfzcx.htm">身份证查询<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		        <!-- <li><div class="bmfw_wmh bmfw_cn5"></div><a href="#">抢火车票<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li> -->
		        <li><div class="bmfw_wmh bmfw_cn6"></div><a href="${global_url}/wfw/bmfw/wnlcx.htm">万年历<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		        <li><div class="bmfw_wmh bmfw_cn7"></div><a href="${global_url}/wfw/bmfw/xzys.htm">星座运势<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		        <li><div class="bmfw_wmh bmfw_cn8"></div><a href="${global_url}/wfw/bmfw/zpsg.htm">占仆算卦<div class="fenleitou"><img src="${global_image_url}/morem.png"></div></a></li>
		     </ul>
		</div>
	</div>
</div>
</body>
</html>

