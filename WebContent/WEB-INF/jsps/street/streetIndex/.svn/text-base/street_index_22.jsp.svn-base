<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>东台镇</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<%-- <link rel="stylesheet" type="text/css" href="${global_css_url }/app.css" /> --%>
<script src="${global_js_url }/zepto.min.js" type="text/javascript"></script>
<script src="${global_js_url }/Mbase.js" type="text/javascript" ></script>
<script src="${global_js_url }/index.js" type="text/javascript"></script>
<script src="${global_js_url }/swipe.js" type="text/javascript"></script>
<script type="text/javascript">
function showWindow(shequName,shequId){
	var div = document.getElementById('jump_window');
	if(div.style.display == 'none'){
		div.style.display='block';
		document.getElementById('shequ_name').innerHTML = shequName;
		
		document.getElementById('dt').href = "${global_url}/wmh/init.htm?shequId="+shequId;
		document.getElementById('fw').href = "${global_url}/wfw/init.htm?shequId="+shequId;
		document.getElementById('sh').href = "${global_url}/wsh/init.htm?shequId="+shequId;
	}else{
		div.style.display='none';
	}
}
</script>
<script type="text/javascript">
//检测浏览器是否支持localStorage
var strStoreDate=window.localStorage?localStorage.getItem("member_account"):Cookie.read("member_account");
if(strStoreDate!=null){
	var shequId = "${shequId}";
	var result = $.ajax({
		url : "${global_url}/member/checkMemberState.htm?member_account="+strStoreDate+"&shequId="+shequId,
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
				<li><a><img alt="" src="${global_image_url}/banner_fw.jpg"></a></li>
				</ul>
			</div>
		</c:otherwise>
</c:choose>

<%-- <div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_fw.jpg"></li>
	</ul>
</div> --%>

<section class="tv-channel" style="margin-top:10px;">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_jd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;新东街道</h5>
			<div class="more"></div>
		</div>	
	</section>
	<ul class="channels">
		<li>
			<!-- <a id="shequ_dy" name="shequ" value="东园社区" onclick='showWindow("东园社区","321");'> -->
			<a id="shequ_wh" name="shequ" value="东园社区" href="${global_url}/wfw/init.htm?shequId=321">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_01.png"/>
				<p>东园社区</p>
			</a>
		</li>
		
		<li>
			<a id="shequ_wh" name="shequ" value="东关社区" href="${global_url}/wfw/init.htm?shequId=441">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_02.png"/>
				<p>东关社区</p>
			</a>
		</li>
        <li>
			<a id="shequ_wh" name="shequ" value="东坝社区" href="${global_url}/wfw/init.htm?shequId=442">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_03.png"/>
				<p>东坝社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="长青社区" href="${global_url}/wfw/init.htm?shequId=443">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_18.png"/>
				<p>长青社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="何垛桥社区" href="${global_url}/wfw/init.htm?shequId=444">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_04.png"/>
				<p>何垛桥社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="二女桥社区" href="${global_url}/wfw/init.htm?shequId=445">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_05.png"/>
				<p>二女桥社区</p>
			</a>
		</li>
	</ul>   
</section>

<section class="tv-channel" style="margin-top:10px;">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_jd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;金海街道</h5>
			<div class="more"></div>
		</div>	
	</section>
	<ul class="channels">
		<li>
			<!-- <a id="shequ_xx" name="shequ" value="新兴社区" onclick='showWindow("新兴社区","322");'> -->
			<a id="shequ_wh" name="shequ" value="新兴社区" href="${global_url}/wfw/init.htm?shequId=322">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_06.png"/>
				<p>新兴社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="北灶社区" href="${global_url}/wfw/init.htm?shequId=446">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_07.png"/>
				<p>北灶社区</p>
			</a>
		</li>
        <li>
			<a id="shequ_wh" name="shequ" value="金陈社区" href="${global_url}/wfw/init.htm?shequId=447">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_08.png"/>
				<p>金陈社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="新海社区" href="${global_url}/wfw/init.htm?shequId=448">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_09.png"/>
				<p>新海社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="北海社区" href="${global_url}/wfw/init.htm?shequId=449">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_10.png"/>
				<p>北海社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="新坝社区" href="${global_url}/wfw/init.htm?shequId=450">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_11.png"/>
				<p>新坝社区</p>
			</a>
		</li>
	</ul>   
</section>

<section class="tv-channel" style="margin-top:10px;">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_jd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;启平街道</h5>
			<div class="more"></div>
		</div>	
	</section>
	<ul class="channels">
		<li>
			<!-- <a id="shequ_wh" name="shequ" value="望海社区" onclick='showWindow("望海社区","323");'> -->
			<a id="shequ_wh" name="shequ" value="望海社区" href="${global_url}/wfw/init.htm?shequId=323">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_12.png"/>
				<p>望海社区</p>
			</a>
		</li>
        <li>
			<a id="shequ_wh" name="shequ" value="玉带桥社区" href="${global_url}/wfw/init.htm?shequId=451">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_13.png"/>
				<p>玉带桥社区</p>
			</a>
		</li>
        <li>
			<a id="shequ_wh" name="shequ" value="公园区" href="${global_url}/wfw/init.htm?shequId=452">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_14.png"/>
				<p>公园社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="汤泊社区" href="${global_url}/wfw/init.htm?shequId=453">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_15.png"/>
				<p>汤泊社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="八林社区" href="${global_url}/wfw/init.htm?shequId=454">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_16.png"/>
				<p>八林社区</p>
			</a>
		</li>
		<li>
			<a id="shequ_wh" name="shequ" value="金墩社区" href="${global_url}/wfw/init.htm?shequId=455">
                <img class="feilei_icon" src="${global_image_url}/icon_sq_17.png"/>
				<p>金墩社区</p>
			</a>
		</li>
	</ul>   
</section>

<!--弹出层
<div class="tanc" id="jump_window" style="display: none">
	<div class="tanc_cont">
    	<div class="tanc_cont_title">
        	<div class="tanc_cont_title_t"  onclick='showWindow("关闭");'><span id="shequ_name">东园社区</span><span class="close" id="close" ></span></div>
        	<a id="dt" ><div class="tanc_list"><img src="${global_image_url}/icon_dt.png" /></div></a>
            <a id="fw"><div class="tanc_list"><img src="${global_image_url}/icon_fw.png" /></div></a>
            <a id="sh"><div class="tanc_list"><img src="${global_image_url}/icon_sh.png" /></div></a>
		</div>
	</div>
	<div class="tanc_bj"></div>
</div>
-->
</body>
</html>
