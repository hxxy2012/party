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
<%-- <link rel="stylesheet" type="text/css" href="${global_css_url }/app.css" /> --%>
<script src="${global_js_url }/zepto.min.js" type="text/javascript"></script>
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
<!-- 分类划动 -->
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
});
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
<!--海报-->
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/banner_fw.jpg"></a></li>
	</ul>
</div> --%>

<!-- 幻灯片 可滑动 -->
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
					<%-- <input type="text" id="indexADCount" value="${indexADSize}"> --%>
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
				<li><a><img alt="" src="${global_image_url}/notadv.png"></a></li>
				</ul>
			</div>
		</c:otherwise>
</c:choose>

<!--通知公告-->
<div class="message">
	<c:if test="${not empty noticesMap}">
		<a href="${global_url}/noticesInfo/getNoticesInfo.htm?noticeId=${noticesMap.noticeId}"><span></span>${noticesMap.title}</a>
	</c:if>
	<c:if test="${empty noticesMap}">
		    <span></span>暂无公告
	</c:if>
</div>
<!--通知公告结束-->


<section class="module ">
    <div class="titlebar">
        <h5><img src="${global_image_url }/ico_bmfw.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;政务服务</h5>
        <div class="more"></div>
    </div>
</section>
<section class="tv-channel" style="float: left;width: 100%;">
	<ul class="channels">
		<!--<c:if test="${shequId!='202'}">
        	<li class="slides js_slides">
				<a href="${global_url}/wfw/gzbx/init.htm?shequId=${shequId}">
					<span class="ico cnl1"></span>
					<p>故障报修</p>
				</a>
			</li>
		</c:if>-->
		<li class="slides js_slides">
			<a href="${global_url}/wfw/sjxx/init.htm?shequId=${shequId}">
				<span class="ico cnl2"></span>
				<p>书记信箱</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/wfw/jbts/init.htm?shequId=${shequId}">
				<span class="ico cnl3"></span>
				<p>意见反馈</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/wfw/jlhd/init.htm?shequId=${shequId}">
				<span class="ico cnl4"></span>
				<p>交流互动</p>
			</a>
		</li>	
		<li class="slides js_slides">
			<a href="${global_url}/wfw/txl/getTXLList.htm?shequId=${shequId}">
				<span class="ico cnl5"></span>
				<p>便民电话</p>
			</a>
		</li>	
		<li class="slides js_slides">
			<a href="${global_url}/wfw/wgy/getWGYList.htm?shequId=${shequId}">
				<span class="ico cnl6"></span>
				<p>网格人员</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/feature/getZhsqFeatureList.htm?shequId=${shequId}">
				<span class="ico cnl7"></span>
				<p>社区特色</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/lawGuide/init.htm?shequId=${shequId}">
				<span class="ico cnl9"></span>
				<p>办事指南</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/yuyue/init.htm?shequId=${shequId}">
				<span class="ico cnl8"></span>
				<p>社区预约</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/question/getZhsqQuestion.htm?shequId=${shequId}">
				<span class="ico cnl10"></span>
				<p>问卷调查</p>
			</a>
		</li>
	</ul>
</section>

<!--生活助手-->
<div style="float:left; margin-top:10px; background:#fff;">
	<div class="module ">
	    <div class="titlebar">
	        <h5><img src="${global_image_url }/ico_bmfw.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;生活助手</h5>
	        <div class="more"><a href="#"></a></div>
	    </div>
	</div>	
	<table class="fenlei_table">
		<tr>
	    	<td><a href="http://yi.baidu.com/search?bidword=&zt=dasoushouji&w=%220_0_%E6%8C%82%E5%8F%B7%22#/home/"><img src="${global_image_url }/icon_gh.png" ></a></td>
	    	<td><a href="https://rentcar.baidu.com/zuche/index/?third_party=wiseshfw_uber&src_from=wiseshfw_uber&w=%220_0_%E6%89%93%E8%BD%A6%22"><img src="${global_image_url }/icon_dc.png" ></a></td>
	    	<td><a href="http://m.ctrip.com/webapp/tour/vacations/?allianceid=297710&sid=762822&sourceid=2232&popup=close&autoawaken=close&ouid=bdviph5_H41qg2sr86ut3_1bi0ka5_4h_0"><img src="${global_image_url }/icon_lv.png" ></a></td>
	        <td><a href="http://map.baidu.com/mobile/webapp/search/search/qt=s&wd=%E9%93%B6%E8%A1%8C&third_party=webapp_wise_nearby&w=%220_0_%E9%93%B6%E8%A1%8C%22&c=315/"><img src="${global_image_url }/icon_yh.png" ></a></td>
	    </tr>
		<tr>
	    	<td><a href="http://uc.weathercn.com/"><img src="${global_image_url }/icon_tq.png" ></a></td>
	        <td><a href="https://qpb.uc.cn/?uc_param_str=nidnvessbifrpfcpcheiwi&utm_source=navi_online_bmsh_bottom&utm_medium=site&utm_campaign=chunyun#!/index"><img src="${global_image_url }/icon_hcp.png" ></a></td>
	        <td><a href="http://touch.qunar.com/h5/flight/?bd_source=haixi"><img src="${global_image_url }/icon_djp.png" ></a></td>
	        <td><a href="http://gj.aibang.com/"><img src="${global_image_url }/icon_gj.png" ></a></td>
	    </tr>
	    <tr>
	    	<td><a href="http://map.baidu.com/mobile/webapp/index/index/foo=bar/vt=map/?third_party=ucsearchbox"><img src="${global_image_url }/icon_dt2.png" ></a></td>
	    	<td><a href="http://h5.m.taobao.com/cph5/awardresult/h5/index.html?ttid=%2051cps0000002"><img src="${global_image_url }/icon_kj.png" ></a></td>
	    	<td><a href="http://m.kuaidi100.com/uc/index.html"><img src="${global_image_url }/icon_ckd.png" ></a></td>
	        <td><a href="http://cha.wcar.net.cn/uc#&index"><img src="${global_image_url }/icon_cwz.png" ></a></td>
	    </tr>
	</table>
</div>

</body>
</html>
