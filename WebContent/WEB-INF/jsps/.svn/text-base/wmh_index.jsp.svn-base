<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>社区门户</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${global_css_url }/village.css" />
<%-- <script type="text/JavaScript" src="${global_js_url}/jquery.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script> --%>
<script src="${global_js_url }/zepto.min.js" type="text/javascript"></script>
<script src="${global_js_url }/Mbase.js" type="text/javascript" ></script>
<script src="${global_js_url }/index.js" type="text/javascript"></script>
<script src="${global_js_url }/swipe.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
});
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
<script type="text/javascript">
var isNotOtherData = false;
var isJiazai = false;	
//滑动到底部事件
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollTop + windowHeight == scrollHeight){
		if(!isNotOtherData){//还有数据
			if(!isJiazai){
				showLoader();
			    isJiazai = true;
			    GetAjaxData();
			}
		}else{//已经全部加载
			endShow();
		}
	}
});

var pageno=1;
var pageSize=10;
var size = 0;
function GetAjaxData(){
	var url = "${global_url}/wmh/getNewsListAsync.htm?shequId="+${shequId}+"&pageno="+(pageno*1+1)+"&pageSize="+pageSize;
	  //var url = "${global_url}/goodsType/getGoodsListAsync.htm?page_on="+(pageno*1+1)+"&page_size="+pageSize+"&cat_id="+${cat_id}+"&city="+${city};
	  $.ajax({
	    type:"POST",
	      dataType: "json",//返回json格式的数据
	      url: url,
	      beforeSend: function () {
	        showLoader();
	    },
	    complete: function () {
	        hideLoader();
	    },
	    success: function (data) {
	    	hideLoader();
	      pageno++;
	      size = data.size;
	      if(size>0){//存在数据
	    	  html(data);
	      }else {//未取得数据
	        if(pageno*1>1){//表示已经全部加载
	          endShow();
	        }
	      }
	      isJiazai = false;
	      },
	      error: function(XMLHttpRequest, textStatus, errorThrown){
	         alert("异常信息："+textStatus);
	      }
	  });
}

function showLoader() {  
	$("#loadingDiv").show();
}  
function hideLoader(){  
	$("#loadingDiv").hide(); 
}
var isHidden = true;
function endShow(){
   if(isHidden){
    isHidden = false;
    var html =$(".Classall");
    html.append("<div id='endDiv' style='width:100%;text-align:center;display:none;'><span style='color:red;font-size:14px;'>亲,已经是所有政务资讯了</span></div>");
      isNotOtherData = true;
      setTimeout(function(){
       $("#endDiv").hide(1000);
       $("#endDiv").remove(); 
       isHidden = true;
     },2000); 
   }
}

function html(data){
	var html= "";
    $.each(data.dataList,function(i,item){
      html+=appendHtml(item);
    });
    $(".Classall ul").append(html);
}

function appendHtml(item){
	var html = '<li><a href="${global_url}/wmh/news/getNewsInfo.htm?newsId='+item.newsId+'"><img src="'+item.paramMap.imagePaths+'" class="pic"></a>'
				+'<div class="rt"><div class="tit"><a href="${global_url}/wmh/news/getNewsInfo.htm?newsId='+item.newsId+'">'+item.title +'</a></div>'
				+'<div class="dush"><div class="haoping">'+item.clientContent+'</div><div class="ljbm0">'+item.viewCount+'人阅读</div></div></div>'
				+'</li>';
	  
	   return html;
}
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
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_mh.jpg"></li>
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

<div class="zwlist">
	<ul>
		<c:if test="${not empty newsClassList && fn:length(newsClassList) > 0}">
			<c:forEach var="classMap" items="${newsClassList}" varStatus="stat"> 
				 <li><a href="${global_url}/wmh/news/getNewsList.htm?classId=${classMap.classId}&className=${classMap.className}&shequId=${shequId}"><i class="zwcn${classMap.classId}"></i><p>${classMap.className}</p></a></li>
			</c:forEach>
		</c:if>
		
    </ul>
</div>


<!-- <div class="fenleilist"> -->
<!-- 	<ul> -->
<%-- 		<c:if test="${not empty newsClassList && fn:length(newsClassList) > 0}"> --%>
<%-- 			<c:forEach var="classMap" items="${newsClassList}" varStatus="stat">  --%>
<%-- 				 <li><div class="fenlei_wmh cn${classMap.classId}"></div><a href="${global_url}/wmh/news/getNewsList.htm?classId=${classMap.classId}&className=${classMap.className}&shequId=${shequId}">${classMap.className}<div class="fenleitou"><img src="${global_image_url }/morem.png"></div></a></li> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:if> --%>
<%-- 	 	<c:if test="${empty newsClassList}"> --%>
<!-- 	 	<li><a href="#">社区风采<div class="fenleitou">暂无信息</div></a></li> -->
<%-- 		</c:if>  --%>
<!--     </ul> -->
<!-- </div> -->

<!--推荐资讯-->
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5 ><img src="${global_image_url}/ico_zxzx.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;推荐资讯</h5>
			<!-- <div class="more"><a href="#"><i class="ico-right"></i></a></div> -->
		</div>	
	</section>
   <div class="Classall">
		<ul class="allcon">
			<c:if test="${not empty newsList}">
	       	<c:forEach var="newsMap" items="${newsList}" begin="0" end="9">
	       		<li>
		        	<a href="${global_url}/wmh/news/getNewsInfo.htm?newsId=${newsMap.newsId}" >
		        	<c:if test="${not empty newsMap.paramMap.imagePaths}">
		        		<img src="${newsMap.paramMap.imagePaths }" class="pic">
	        	 	</c:if>
		        	<c:if test="${empty newsMap.paramMap.imagePaths}">
		        		<img src="${global_image_url}/moren.png" class="pic">
	        	 	</c:if>
		        	</a>
					<div class="rt">
						<div class="tit"><a href="${global_url}/wmh/news/getNewsInfo.htm?newsId=${newsMap.newsId}&className=${newsMap.paramMap.className}"><c:if test="${not empty newsMap.paramMap.className}">[${newsMap.paramMap.className}]</c:if>${newsMap.title }</a></div>
						<div class="dush">
							<div class="haoping">
							${newsMap.clientContent}
							<%-- ${fn:length(newsMap.clientContent) > 30 ? fn:substring(newsMap.clientContent,0,30) : newsMap.clientContent}${fn:length(newsMap.clientContent) > 30 ? '...' : ''} --%>
							</div>
		                    <div class="ljbm0">${newsMap.viewCount}人阅读</div>
						</div>
					</div>
		        </li>
	       	</c:forEach>
	       	</c:if>
	       	<c:if test="${empty newsList}">
	       		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
	       	</c:if>
	    	<div id ="loadingDiv" style="display:none;">正在加载中...</div> 
	    </ul>
	
	
	
    	<!-- <li>
			<a href="#" ><img src="images/class1.png" class="pic"></a>
			<div class="rt">
				<div class="tit"><a href="#">马群村社区开展“关爱老人”志愿服务活动</a></div>
				<div class="dush">
					<div class="haoping">为弘扬中华民族尊老爱老传统美德和“奉献、友爱、互助、进步”志愿精神，近日上午，马群街道马群...</div>
                    <div class="ljbm0">251人阅读</div>
				</div>
			</div>
		</li> -->
		
		
        <!-- <li>
			<a href="#" ><img src="images/class1.png" class="pic"></a>
			<div class="rt">
				<div class="tit"><a href="#">马群村社区开展“关爱老人”志愿服务活动</a></div>
               
				<div class="dush">
					<div class="haoping">为弘扬中华民族尊老爱老传统美德和“奉献、友爱、互助、进步”志愿精神，近日上午，马群街道马群...</div>
                    <div class="ljbm0">251人阅读</div>
				</div>
			</div>
		</li> -->
</div>
</div><!--推荐资讯-->


</body>
</html>

