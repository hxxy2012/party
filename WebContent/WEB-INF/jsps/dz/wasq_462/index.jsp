<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>五爱社区</title>
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
<div id="gd" class="messagee" style="overflow:hidden;height:40px;color:#000; position: relative; ">
	
	<div id="gd1">
		<c:if test="${not empty noticesList}">
			<c:forEach var="noticesMap" items="${noticesList}">
				<p><a href="${global_url}/noticesInfo/getNoticesInfo.htm?noticeId=${noticesMap.noticeId}"><span></span>${noticesMap.title}</a></p>
			</c:forEach>
		</c:if>
		<c:if test="${empty noticesList}">
		    <p><span></span>暂无公告</p>
		</c:if>
	</div>
	<div id="gd2"></div>
</div>
<script>
    var speed=80;
    gd2.innerHTML=gd1.innerHTML;
    gd.scrollTop=gd.scrollHeight;
    function Marquee(){
    if(gd1.offsetTop-gd.scrollTop>=0)
    gd.scrollTop+=gd2.offsetHeight;
    else{
    gd.scrollTop--;
    }
    }
    var MyMar=setInterval(Marquee,speed);
    gd.onmouseover=function() {clearInterval(MyMar)};
    gd.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
</script>
<!--通知公告结束-->


<!--主要分类-->
<div class="mk">

	<section class="tv-channel" style="float: left;width: 100%;">
	<div class="zwlist dz_bj">
        <ul>
        	<li>
				<a href="${global_url}/zhsq/feature/getZhsqFeatureList.htm?shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_13jj.png" /></i>
					<p>社区简介</p>
				</a>
			</li>
			 
			 
			<li>
				<a href="${global_url}/dz/initWgry.htm?shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon_home_wgry.png" /></i>
					<p>网格人员</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/dz/initDjjj.htm?shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon_home_djjj.png" /></i>
					<p>党建纪检</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/zhsq/lawGuide/init.htm?shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon_home_msmz.png"/></i>
					<p>民生民政</p>
				</a>
			</li>
			
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=824&className=计生&shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_3js.png" /></i>
					<p>计生</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=825&className=残联&shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_9cl.png" /></i>
					<p>残联</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=827&className=人保&shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_2mz.png" /></i>
					<p>人保</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=826&className=综治&shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_16rb.png" /></i>
					<p>综治</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=828&className=城管&shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_17cg.png" /></i>
					<p>城管</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=829&className=其他服务&shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon-bs_15qt.png" /></i>
					<p>其他服务</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/dz/initShzs.htm?shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon_home_shzs.png"/></i>
					<p>生活助手</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/dz/initQzzs.htm?shequId=462">
					<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/icon_home_qzzs.png" /></i>
					<p>群众之声</p>
				</a>
			</li>
			
        </ul>
    </div>
		
		
	</section>
	

</div>
<!--微服务结束 -->



    

</body>
</html>

