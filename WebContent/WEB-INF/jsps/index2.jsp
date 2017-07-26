<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>智慧社区</title>
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
		 $(".slide").css("height",windowWidth*0.45+"px");
		 $(".slide-banner img").css("height",windowWidth*0.45+"px");
		 $(".slide-banner").css("height",windowWidth*0.45+"px");
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
<header class="header">
	<div class="logo"><img src="${global_image_url }/logo.png" class="logo_img" /></div>
	
<%-- 	<div class="history">
		<a href="${global_url}/search/initSearch.htm"><span class="icon sous"></span></a>
		<a href="${global_url}/index/toCitySelPage.htm" class="menu">${cityName}<span></span></a>
	</div> --%>
</header>
<!--首页幻灯片
<div class="slider">
	<ul class="slide-banner">
		<c:if test="${not empty adv}">
			<c:forEach items="${adv}" var="a" begin="0" end="7" varStatus="xb">
				<c:if test="${not empty a.imageUrl}" >
				
					<c:if test="${xb.count==1 }">
						<li style="display:list-item;"><img src="${a.imageUrl}" /></li>
					</c:if>
					<c:if test="${xb.count>1 }">
						<li style="display:none;"><img src="${a.imageUrl}" /></li>
					</c:if>
				</c:if>
				<c:if test="${empty a.imageUrl}" >
				<c:if test="${xb.count==1 }">
						<li style="display:list-item;"><img src="${global_image_url}/notadv.png" /></li>
					</c:if>
					<c:if test="${xb.count>1 }">
						<li style="display:none;"><img src="${global_image_url}/notadv.png" /></li>
					</c:if>
				</c:if>
				
			</c:forEach>
		</c:if>
	</ul>
	 <div class="slide-controls">
	 	<c:if test="${not empty adv}">
			<c:forEach items="${adv}" var="a" begin="0" end="7" varStatus="status" >
				<span <c:if test="${status.index==0}">class="curr"</c:if> >${status.index+1}</span>
			</c:forEach>
		</c:if>
	</div>
</div>海报结束-->

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




<!-- 分类可滑动 -->

<c:choose>
	<c:when test="${fn:length(goodsTypeList)<=8}">
    		<div style="background-color: #fff;">  
               <ul class="channels">
					<c:if test="${not empty goodsTypeList}">
						<c:forEach var="typeMap" items="${goodsTypeList}" varStatus="stat"> 
							 <c:if test="${stat.index<8}"> 
								<c:if test="${typeMap.goods_type_lv==1}">
									<li id="li_1">
										<a href="${global_url}/goodsType/initGoodsType.htm?cat_id=${typeMap.CAT_ID}">
											<span class="ico cnl${stat.index+1}"></span>
											<p>${typeMap.NAME}</p>
										</a>
									</li>
								</c:if>
							 </c:if> 
						</c:forEach>
					</c:if>
				</ul>   
            </div>  
	</c:when>
	<c:otherwise>
		<section class="tv-channel">
		 <div id="mySwipe" class="swipe" style="max-width:1037px;margin:0 auto;">  
		    <div class="swipe-wrap">  
		            <div>  
		               <ul class="channels">
							<c:if test="${not empty goodsTypeList}">
								<c:forEach var="typeMap" items="${goodsTypeList}" varStatus="stat"> 
									 <c:if test="${stat.index<8}"> 
										<c:if test="${typeMap.goods_type_lv==1}">
											<li id="li_1">
												<a href="${global_url}/goodsType/initGoodsType.htm?cat_id=${typeMap.CAT_ID}">
													<span class="ico cnl${stat.index+1}"></span>
													<p>${typeMap.NAME}</p>
												</a>
											</li>
										</c:if>
									 </c:if> 
								</c:forEach>
							</c:if>
						</ul>   
		            </div>  
		            <div>  
		                <ul class="channels">
							<c:if test="${not empty goodsTypeList}">
								<c:forEach var="typeMap" items="${goodsTypeList}" varStatus="stat"> 
									   <c:if test="${stat.index>=8}"> 
										<c:if test="${typeMap.goods_type_lv==1}">
											<li id="li_2">
												<a href="${global_url}/goodsType/initGoodsType.htm?cat_id=${typeMap.CAT_ID}">
													<span class="ico cnl${stat.index+1}"></span>
													<p>${typeMap.NAME}</p>
												</a>
											</li>
										</c:if>
									 </c:if>
								</c:forEach>
							</c:if>
						</ul> 
		            </div>              
		    </div>  
		</div>
	</section>
	</c:otherwise>
</c:choose>




<!-- 活动公告 -->
<div class="message">
<c:if test="${not empty noticesInfo}">
    <a href="${global_url}/noticesInfo/getNoticesInfo.htm"><span></span>${noticesInfo.NOTICES_TITLE}</a>
</c:if>
<c:if test="${empty noticesInfo}">
    <a><span></span>暂无活动公告</a>
</c:if>
</div><!--通知公告-->

    
    
<div class="container">
	<c:if test="${not empty goodsTypeList}">
			<c:forEach var="typeMap" items="${goodsTypeList}" varStatus="stat"> 
				<c:if test="${typeMap.goods_type_lv==1}">
					<section class="module">
						<div class="titlebar">
							<h5><span class="colormenu line${stat.index+1}"></span>${typeMap.NAME}</h5>
							<div class="more"><a href="${global_url}/goodsType/initGoodsType.htm?cat_id=${typeMap.CAT_ID}"><i class="ico-right"></i></a></div>
						</div>	
					</section>
					<c:if test="${not empty typeMap.twoGoodsTypeList}">
							<div class="module-c">
							     <ul class="module-menu">
							     	<c:forEach var="twoTypeMap" items="${typeMap.twoGoodsTypeList}" varStatus="twoStat"> 
										<c:if test="${twoTypeMap.goods_type_lv==2}">
												<c:if test="${fn:length(typeMap.twoGoodsTypeList)>twoStat.index+1}">
								     				<li><a href="${global_url}/goodsType/initGoodsType.htm?cat_id=${twoTypeMap.CAT_ID}">${twoTypeMap.NAME}</a><span>|</span></li>
								     			</c:if>  
								     			<c:if test="${fn:length(typeMap.twoGoodsTypeList)==twoStat.index+1}">
								     				<li><a href="${global_url}/goodsType/initGoodsType.htm?cat_id=${twoTypeMap.CAT_ID}">${twoTypeMap.NAME}</a></li>
								     			</c:if>  
								    	</c:if>  
				    				</c:forEach>
							     </ul>
						    </div> 
					</c:if>
				</c:if>
			</c:forEach>
	</c:if>
</div>
    
<!--
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu line1 "></span>学习培训</h5>
			<div class="more"><a href="#"><i class="ico-right"></i></a></div>
		</div>	
	</section>
    <div class="module-c">
     <ul class="module-menu">
     	<li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
     </ul>
    </div> 
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu line2 "></span>电脑/IT培训</h5>
			<div class="more"><a href="#"><i class="ico-right"></i></a></div>
		</div>	
	</section>
    <div class="module-c">
     <ul class="module-menu">
     	<li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
     </ul>
    </div> 
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu line3 "></span>职业技能培训</h5>
			<div class="more"><a href="#"><i class="ico-right"></i></a></div>
		</div>	
	</section>
    <div class="module-c">
     <ul class="module-menu">
     	<li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
     </ul>
    </div> 
</div>
-->

</body>
</html>

