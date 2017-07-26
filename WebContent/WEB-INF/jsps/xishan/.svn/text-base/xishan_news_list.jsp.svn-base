<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>
	<c:if test="${classId=='3'}">简讯</c:if>
	<c:if test="${classId=='4'}">法律法规</c:if>
	<c:if test="${classId=='5'}">办理流程</c:if>
	<c:if test="${classId=='6'}">政策指南</c:if>
	<c:if test="${classId=='7'}">招标公告</c:if>
</title>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
<script type="text/javascript" src="${xishan_js_url}/jquery-1.7.js"></script>
<script type="text/javascript" src="${xishan_js_url}/swipe.js"></script>
<%-- <script type="text/JavaScript" src="${xishan_js_url}/jquery.js"></script> --%>
<script type="text/JavaScript" src="${xishan_js_url}/jquery_lazyload.js"></script> 
<script type="text/javascript">

$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
	$("#reMove").click(function(){
		$("#title").val('');
		
	});
});

</script>
<script type="text/javascript">
var isNotOtherData = false;
var isNotOtherData = false;
var isJiazai = false;
//滑动到底部事件
$(window).scroll(function() {
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if (scrollTop + windowHeight == scrollHeight) {
		if (!isNotOtherData) {//还有数据
			if (!isJiazai) {
				showLoader();
				isJiazai = true;
				GetAjaxData();
			}
		} else {//已经全部加载
			endShow();
		}
	}
});

var pageno = 1;
var pageSize = 10;
var size = 0;

function GetAjaxData() {
	var title = "${title}";
	var classId = "${classId}";
	/* var search_company_name = $("#search_company_name").val(); */
	var url = "${global_url}/xishan/getNewsListAsync.htm?pageno=" + (pageno * 1 + 1) + "&pageSize=" + pageSize;
	if(title != null && title != "" ){
		url = url+"&title="+title;
	}
	if(classId != null && classId != "" ){
		url = url+"&classId="+classId;
	}
	$.ajax({
		type : "POST",
		dataType : "json",//返回json格式的数据
		url : url,
		beforeSend : function() {
			showLoader();
		},
		complete : function() {
			hideLoader();
		},
		success : function(data) {
			hideLoader();
			pageno++;
			size = data.size;
			if (size > 0) {//存在数据
				html(data);
				isJiazai = false;
			} else {//未取得数据
				endShow();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("异常信息："+textStatus);
		}
	});
}

var isHidden = true;
function endShow(){
	 if(isHidden){
		isHidden = false;
		var html =$(".Classall");
		//html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>亲,已经是所有了</span></div>");
		  isNotOtherData = true;
		  setTimeout(function(){
			 $("#endDiv").hide(1000);
			 $("#endDiv").remove(); 
			 isHidden = true;
		 },2000);
	 }
}
function showLoader() {  
  $("#loadingDiv").show();
}  
function hideLoader(){  
$("#loadingDiv").hide(); 
}   

function html(data) {
	var html = "";
	$.each(data.dataList, function(i, item) {
		html += appendHtml(item);
	});
	$(".Classall ul").append(html);
}

function appendHtml(item){
	var html = "";
	if(item.paramMap.imagePaths != "" && item.paramMap.imagePaths != null){
		 html='<li><a href="${global_url}/xishan/getNewsMap.htm?newsId='+item.newsId+'&className='+item.className+'">'
	   		+'<img src="'+item.paramMap.imagePaths +'" class="pic">'
		 	+'<div class="rt">'
			+'<div class="tit">'+item.title+'</div>'
			+'<div class="dush">'
			+'<div class="haoping">'+item.clientContent+'</div>'	
			+'<div class="ljbm1">'+item.createdTime+'</div>'
		 	+'<div class="ljbm0">'+item.viewCount+'人阅读</div>'
			+'</div>'
			+'</div>'
			+'</a>'
			+'</li>'
		;
			
		var u = navigator.userAgent;
		if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
			//安卓手机
		} else if (u.indexOf('iPhone') > -1) {
			//苹果手机
			if(item.isFile == '1'){
				html = "";
				html='<li><a href="${image_server}'+item.fileUrl+'" onclick="hrefOnclick('+item.newsId+','+item.className+');">'
				+'<img src="'+item.paramMap.imagePaths +'" class="pic">'
				+'<div class="rt">'
				+'<div class="tit">'+item.title+'</div>'
				+'<div class="dush">'
				+'<div class="haoping">'+item.clientContent+'</div>'	
				+'<div class="ljbm1">'+item.createdTime+'</div>'
			 	+'<div class="ljbm0">'+item.viewCount+'人阅读</div>'
				+'</div>'
				+'</div>'
				+'</a>'
				+'</li>'
			;
			}
		} else if (u.indexOf('Windows Phone') > -1) {
			//winphone手机
		}else{
			//alert("浏览器");
		}
	}else{
		html='<li><a href="${global_url}/xishan/getNewsMap.htm?newsId='+item.newsId+'&className='+item.className+'">'
   		+'<img src="${xishan_image_url}/moren.png" class="pic">'
	 	+'<div class="rt">'
		+'<div class="tit">'+item.title+'</div>'
		+'<div class="dush">'
		+'<div class="haoping">'+item.clientContent+'</div>'
		+'<div class="ljbm1">'+item.createdTime+'</div>'
	 	+'<div class="ljbm0">'+item.viewCount+'人阅读</div>'
		+'</div>'
		+'</div>'
		+'</a>'
		+'</li>'
	;
		
	var u = navigator.userAgent;
	if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
		//安卓手机
	} else if (u.indexOf('iPhone') > -1) {
		//苹果手机
		if(item.isFile == '1'){
			html = "";
			html='<li><a href="${image_server}'+item.fileUrl+'" onclick="hrefOnclick('+item.newsId+','+item.className+');">'
			+'<img src="${xishan_image_url}/moren.png" class="pic">'
			+'<div class="rt">'
			+'<div class="tit">'+item.title+'</div>'
			+'<div class="dush">'
			+'<div class="haoping">'+item.clientContent+'</div>'	
			+'<div class="ljbm1">'+item.createdTime+'</div>'
		 	+'<div class="ljbm0">'+item.viewCount+'人阅读</div>'
			+'</div>'
			+'</div>'
			+'</a>'
			+'</li>'
		;
		}
	} else if (u.indexOf('Windows Phone') > -1) {
		//winphone手机
	}else{
		//alert("浏览器");
	}
	}
 	return html;
};

function nofind(){
 var img=event.srcElement; 
 img.src="${global_image_url}/noimg.jpg"; 
 img.onerror=null; //控制onerror事件只触发一次 
}

function hrefOnclick(newsId,className) {
	/* var search_company_name = $("#search_company_name").val(); */
	var url = "${global_url}/xishan/getNewsMap.htm?iosClick=1&newsId="+newsId+"&className="+className;
	$.ajax({
		type : "POST",
		dataType : "json",//返回json格式的数据
		url : url,
		beforeSend : function() {
			
		},
		complete : function() {
			
		},
		success : function(data) {
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("异常信息："+textStatus);
		}
	});
}

</script>
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${xishan_image_url }/xs_banner_list.png"></li>
	</ul>
</div><!--海报结束-->

<div class="sousuo">
	<div class="sousuo-in">
		<form id="form1" action="${global_url}/xishan/getNewsListMap.htm" method="post">
    		<input type="hidden" id="classId" name="classId" value="${classId}" >
    		<input type="text" id="title" name="title" value="${title}" placeholder="请输入关键字搜索" >
    		<span><img src="${xishan_image_url }/cha.png" width="20" height="20"></span>
    		<input type="submit" value="&nbsp" id="submit-ss" data-role="none">

    	</form>
	</div>
</div>

<div class="container">
	<div class="Classall">
		<ul class="allcon">
		<c:if test="${not empty newsList}">
			<div id="other">
		       	<c:forEach var="newsMap" items="${newsList}">
	    		<li>
					<a href="${global_url}/xishan/getNewsMap.htm?newsId=${newsMap.newsId}&className=${className}" >
						<c:if test="${not empty newsMap.paramMap.imagePaths}">
				       		<img src="${newsMap.paramMap.imagePaths }" class="pic">
			         	</c:if>
				       	<c:if test="${empty newsMap.paramMap.imagePaths}">
				       		<img src="${xishan_image_url}/moren.png" class="pic">
			       	 	</c:if>
		               	<div class="rt">
		                   	<div class="tit">${newsMap.title}</div>
		                   	<div class="dush">
		                       	<div class="haoping">${newsMap.clientContent}</div>
		                       	<div class="ljbm1">${newsMap.createdTime}</div>
		                       	<div class="ljbm0">${newsMap.viewCount}人阅读</div>
		                   	</div>
		               	</div>
					</a>
				</li>
	          	</c:forEach>
          	</div>
          	<div id="ios" style="display: none">
		       	<c:forEach var="newsMap" items="${newsList}">
	    		<li>
		    		<c:if test="${not empty newsMap.fileUrl}">
						<a href="${image_server}${newsMap.fileUrl}" onclick="hrefOnclick('${newsMap.newsId}','${className}');">
							<c:if test="${not empty newsMap.paramMap.imagePaths}">
					       		<img src="${newsMap.paramMap.imagePaths }" class="pic">
				         	</c:if>
					       	<c:if test="${empty newsMap.paramMap.imagePaths}">
					       		<img src="${xishan_image_url}/moren.png" class="pic">
				       	 	</c:if>
			               	<div class="rt">
			                   	<div class="tit">${newsMap.title}</div>
			                   	<div class="dush">
			                       	<div class="haoping">${newsMap.clientContent}</div>
			                       	<div class="ljbm1">${newsMap.createdTime}</div>
			                       	<div class="ljbm0">${newsMap.viewCount}人阅读</div>
			                   	</div>
			               	</div>
						</a>
					</c:if>
					<c:if test="${empty newsMap.fileUrl}">
						<a href="${global_url}/xishan/getNewsMap.htm?newsId=${newsMap.newsId}&className=${className}" >
							<c:if test="${not empty newsMap.paramMap.imagePaths}">
				        		<img src="${newsMap.paramMap.imagePaths }" class="pic">
			        	 	</c:if>
				        	<c:if test="${empty newsMap.paramMap.imagePaths}">
				        		<img src="${xishan_image_url}/moren.png" class="pic">
			        	 	</c:if>
		                	<div class="rt">
		                    	<div class="tit">${newsMap.title}</div>
		                    	<div class="dush">
		                        	<div class="haoping">${newsMap.clientContent}</div>
		                        	<div class="ljbm1">${newsMap.createdTime}</div>
		                        	<div class="ljbm0">${newsMap.viewCount}人阅读</div>
		                    	</div>
		                	</div>
						</a>
					</c:if>
				</li>
	          	</c:forEach>
          	</div>
	    </c:if>
	    <c:if test="${empty newsList}">
	       	<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
	    </c:if>
	    <div id ="loadingDiv" style="display:none;">正在加载中...</div>
		</ul>
	</div>
</div>
</body>

<script type="text/javascript">

	var div_other = document.getElementById('other');
	var div_ios = document.getElementById('ios');
	window.onload = function() {
		var u = navigator.userAgent;
		if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
			//安卓手机
			//div_android.style.display='block';
		} else if (u.indexOf('iPhone') > -1) {
			//苹果手机
			//alert("苹果手机");
			div_ios.style.display='block';
			div_other.style.display='none';
		} else if (u.indexOf('Windows Phone') > -1) {
			//winphone手机
			//systemType = 2;
		}else{
			//div_computer.style.display='block';
		}
	}

</script>

</html>
