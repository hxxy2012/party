<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>生活助手</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<%-- <script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script> --%>
<%-- <script type="text/javascript" src="${global_url}/js/adv.js"></script> --%>
<!-- 幻灯片js -->
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
<section class="tv-channel" style="float: left;width: 100%;">
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
			<li><a><img alt="" src="${global_image_url}/banner_sh.jpg"></a></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
</section>


<!--生活助手-->
<div class="mk">
<section class="module ">
    <div class="tit_bar" style="border:none">
        <h5 class="tit_name"><img class="tit_icon" src="${global_image_url }/ico_bmfw.png"  />&nbsp;生活助手</h5>
    </div>
</section>	
	<table class="fenlei_table">
	    <c:if test="${not empty shequAdieList && fn:length(shequAdieList) > 0}">
			<tr>
				<c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="0" end="3"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
			<tr>
				<c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="4" end="7"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
		    </tr>
		    <tr>
				<c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="8" end="11"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
		    </tr>
		    <tr>
				<c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="12" end="15"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
		    </tr>
		    <tr>
			<c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="16" end="19"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
		    </tr>
		    <tr>
		    <c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="20" end="23"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
		    </tr>
		    <tr>
		    <c:forEach var="adieMap" items="${shequAdieList}" varStatus="stat" begin="24" end="27"> 
	    			<td><a href="${adieMap.lvUrl}"><img src="${adieMap.paramMap.imagePaths}" ></a></td>
	    		</c:forEach>
		    </tr>
		    
	    </c:if>
	    
	    
	     <c:if test="${empty shequAdieList || fn:length(shequAdieList) <= 0}">
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
		    	<td><a href="http://m.46644.com/size/"><img src="${global_image_url }/icon_kj.png" ></a></td>
		    	<td><a href="http://m.kuaidi100.com/uc/index.html"><img src="${global_image_url }/icon_ckd.png" ></a></td>
		        <td><a href="http://cha.wcar.net.cn/uc#&index"><img src="${global_image_url }/icon_cwz.png" ></a></td>
		    </tr>
		    
	    </c:if>
	</table>
</div>




</body>
</html>

