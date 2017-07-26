<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>${shequName}</title>
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
<script>
$(function(){
		//社区政务更多
		$("#fw_more").click(function(){
		  $("#fw_more").hide()
		  $(".fw_duo").show();
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


<!--微服务-->
<div class="mk">
<section class="module ">
        <div class="tit_bar"">
            <h5 class="tit_name"><img class="tit_icon" src="${global_image_url }/ico_jbts.png"  />&nbsp;服务中心</h5>
        </div>
</section>	
<section class="tv-channel" style="float: left;width: 100%;">
	<ul class="channels">
		<!--<li class="slides js_slides">
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
		</li>-->
		<li class="slides js_slides">
			<a href="${global_url}/wfw/wgy/getWGYList.htm?shequId=${shequId}">
				<span class="ico cnl6"></span>
				<p>网格人员</p>
			</a>
		</li>			
		<!--<li class="slides js_slides">
			<a href="${global_url}/zhsq/question/getZhsqQuestion.htm?shequId=${shequId}">
				<span class="ico cnl10"></span>
				<p>问卷调查</p>
			</a>
		</li>-->
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/lawGuide/init.htm?shequId=${shequId}">
				<span class="ico cnl9"></span>
				<p>办事指南</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/zhsq/feature/getZhsqFeatureList.htm?shequId=${shequId}">
				<span class="ico cnl7"></span>
				<p>社区特色</p>
			</a>
		</li>
		<li class="slides js_slides">
			<a href="${global_url}/wfw/txl/getTXLList.htm?shequId=${shequId}">
				<span class="ico cnl5"></span>
				<p>便民电话</p>
			</a>
		</li>
		<!-- <li class="slides js_slides">
			<a href="${global_url}/zhsq/yuyue/init.htm?shequId=${shequId}">
				<span class="ico cnl8"></span>
				<p>社区预约</p>
			</a>
		</li>
			<li id="fw_more" class="slides js_slides">
				<span class="ico cnlmore"></span>
				<p>更多</p>
		</li>
		<li class="slides js_slides fw_duo">
			<a href="${global_url}/zhsq/feature/getZhsqFeatureList.htm?shequId=${shequId}">
				<span class="ico cnl7"></span>
				<p>社区特色</p>
			</a>
		</li>
		<li class="slides js_slides fw_duo">
			<a href="${global_url}/wfw/txl/getTXLList.htm?shequId=${shequId}">
				<span class="ico cnl5"></span>
				<p>便民电话</p>
			</a>
		</li> -->
	</ul>
</section>
</div>
<!--微服务结束 -->

<!--社区政务海报 -->
<div class="ban_img m_b10">
	<img src="${global_image_url}/banner_sqzw.png"></a>
</div><!--海报结束-->

<!--微门户-->
<div class="mk">
    <section class="module ">
        <div class="tit_bar" style="border:none">
            <h5 class="tit_name"><img class="tit_icon" src="${global_image_url }/ico_rztj.png"  />&nbsp;社区政务</h5>
        </div>
    </section>	
    <div class="zwlist">
        <ul>
	        <c:if test="${not empty newsClassList && fn:length(newsClassList) > 0}">
				<c:forEach var="classMap" items="${newsClassList}" varStatus="stat"> 
					 <li><a href="${global_url}/wmh/news/getNewsList.htm?classId=${classMap.classId}&className=${classMap.className}&shequId=${shequId}"><i class="zwcn${classMap.classId}"></i><p>${classMap.className}</p></a></li>
				</c:forEach>
			</c:if>
        </ul>
    </div>
</div>
<!--微门户结束 -->

<!--社区生活海报 -->
<div class="ban_img m_b10">
	<img src="${global_image_url}/banner_sqsh.png">
</div><!--海报结束-->



<!--微生活-->
<div class="mk">
<section class="module ">
    <div class="tit_bar" style="border:none">
        <h5 class="tit_name"><img class="tit_icon" src="${global_image_url}/ico_wgy.png"  />&nbsp;社区生活</h5>
    </div>
</section>	
<table class="fenlei_table">
	<%--  
	<tr>
    	<td><a href="${global_url}/wsh/essc/getESSC.htm?shequId=${shequId}"><img src="${global_image_url}/icon_essc.png" ></a></td><!--二手市场-->
        <td><a href="${global_url}/wsh/cyfw/init.htm?shequId=${shequId}"><img src="${global_image_url}/icon_cyfw.png" ></a></td><!--餐饮服务-->
        <td><a href="${global_url}/wsh/jzfw/init.htm?shequId=${shequId}"><img src="${global_image_url}/icon_jzfw.png" ></a></td><!--家政市场-->
        <td><a href="${global_url}/wsh/qtfw/init.htm?shequId=${shequId}"><img src="${global_image_url}/icon_qtfw.png" ></a></td><!--其他市场-->
    </tr>
    <tr>
    	<td><a href="${global_url}/wsh/rztj/init.htm?shequId=${shequId}"><img src="${global_image_url}/icon_shrz.png" ></a></td><!--商户入驻-->
    	<td><a href="${global_url}/shopping/init.htm?shequId=${shequId}"><img src="${global_image_url}/icon_shrz.png" ></a></td><!--进入商城-->
    </tr>
    --%>
    <tr>
    	<td><a href="http://h5.dianping.com/platform/easylife/index.html?url_from=m_nav_9&url_from=m_nav_1_1_%E7%94%9F%E6%B4%BB%E6%9C%8D%E5%8A%A1"><img src="${global_image_url}/icon_dz_shfw.png" ></a></td><!--生活服务-->
        <td><a href="http://m.dianping.com/shoplist/845/d/1/c/10/s/s_-1?url_from=m_nav_1&url_from=m_nav_1_1_%E7%BE%8E%E9%A3%9F"><img src="${global_image_url}/icon_dz_ms.png" ></a></td><!--美食-->
       <td><a href="http://m.dianping.com/shoplist/845/d/1/c/30/s/s_-1?url_from=m_nav_4&url_from=m_nav_1_1_%E4%BC%91%E9%97%B2%E5%A8%B1%E4%B9%90"><img src="${global_image_url}/icon_dz_xxyl.png" ></a></td><!--休闲娱乐-->
        <td><a href="http://m.dianping.com/moviemm/movie"><img src="${global_image_url}/icon_dz_dy.png" ></a></td><!--电影-->
    </tr>
    <tr>
    	<td><a href="http://m.dianping.com/shoplist/845/r/0/c/60/s/s_0?url_from=m_nav_3&url_from=m_nav_1_1_%E9%85%92%E5%BA%97"><img src="${global_image_url}/icon_dz_jd.png" ></a></td><!--酒店-->
    	<td><a href="http://t.dianping.com/deal/travel/index/around?cityid=845&appVersion=aroundCity&platform=3?url_from=m_nav_7&url_from=m_nav_1_1_%E5%91%A8%E8%BE%B9%E6%B8%B8"><img src="${global_image_url}/icon_dz_zby.png" ></a></td><!--周边游-->
    </tr>
    
</table>
</div>
<!--微生活结束-->

<!--生活助手海报 -->
<div class="ban_img m_b10">
	<img src="${global_image_url}/banner_shzs.png">
</div><!--海报结束-->


<!--生活助手-->
<div class="mk">
<section class="module ">
    <div class="tit_bar" style="border:none">
        <h5 class="tit_name"><img class="tit_icon" src="${global_image_url }/ico_bmfw.png"  />&nbsp;生活助手</h5>
    </div>
</section>	
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
</div>

</body>
</html>

