<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title><c:if test="${not empty className}">${className}</c:if></title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript"
	src="${global_js_url}/jquery-1.8.3.min.js"></script>
<%-- <script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery.js"></script> --%>
<script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script>

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
	var title = "${title}";
	var url = "${global_url}/wmh/news/getNewsListAsync.htm?shequId="+${shequId}+"&classId="+${classId}+"&pageno="+(pageno*1+1)+"&pageSize="+pageSize;
	if(title != null && title != "" ){
		url = url+"&title="+title;
	}
	//var url = "${global_url}/wmh/news/getNewsListAsync.htm?shequId="+${shequId}+"&pageno="+(pageno*1+1)+"&pageSize="+pageSize;
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
	    	  isJiazai = false;
	      }else {//未取得数据
	        if(pageno*1>1){//表示已经全部加载
	          endShow();
	        }
	      }
	      
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

function sousuo_submit() {
	
	var title = $("#title").val();
	while(title.lastIndexOf(" ")>=0){
		title = title.replace(" ","");
	}
	if(title.length == 0){
		alert("请输入搜索内容");
		return false;
	}else{
		document.getElementById("form_sousuo").submit();
	}
}

</script>
</head>
<body>
	<div id="divObj">
		<ul class="slide-banner">
			<li><img src="${global_image_url}/banner_dj.jpg"></li>
		</ul>

		<!-- 搜索 -->
		<div class="sousuo">
			<form id="form_sousuo" action="${global_url}/wmh/news/getNewsList.htm?shequId=${shequId}&className=${className}&classId=${classId}" method="post">
				<input type="hidden" id="classId" name="classId" value="${classId}">
				<div class="sousuo-in">
					<input type="text" id="title" name="title" value="${title }" placeholder="请输入关键字搜索" /> <span id="reMove"><img
						src="${global_image_url}/cha.png" width="30" height="30"></span>
				</div>
				<input type="button" value="" id="submit-ss" onclick="sousuo_submit();">
			</form>
		</div>
		<!-- 搜索结束 -->

		<!--资讯列表-->
		<div class="container">
			<div class="Classall">
				<ul class="allcon">
					<c:if test="${not empty newsList}">
						<c:forEach var="newsMap" items="${newsList}">
							<li><a
								href="${global_url}/wmh/news/getNewsInfo.htm?newsId=${newsMap.newsId}&className=${className}">
									<c:if test="${not empty newsMap.paramMap.imagePaths}">
										<img src="${newsMap.paramMap.imagePaths }" class="pic">
									</c:if> <c:if test="${empty newsMap.paramMap.imagePaths}">
										<img src="${global_image_url}/moren.png" class="pic">
									</c:if> <%-- <img src="${newsMap.paramMap.imagePaths }" class="pic"> --%>
							</a>
								<div class="rt">
									<div class="tit">
										<a
											href="${global_url}/wmh/news/getNewsInfo.htm?newsId=${newsMap.newsId}&className=${className}">${newsMap.title }</a>
									</div>
									<div class="dush">
										<div class="haoping">
											${newsMap.clientContent}
											<%-- ${fn:length(newsMap.clientContent) > 30 ? fn:substring(newsMap.clientContent,0,30) : newsMap.clientContent}${fn:length(newsMap.clientContent) > 30 ? '...' : ''} --%>
											<%-- ${fn:length(newsMap.newsContent) > 30 ? fn:substring(newsMap.newsContent,0,30) : newsMap.newsContent}${fn:length(newsMap.newsContent) > 30 ? '...' : ''} --%>
										</div>
										<div class="ljbm0">${newsMap.viewCount}人阅读</div>
									</div>
								</div>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${empty newsList}">
						<div id="nodata" style='width: 100%; text-align: center; font-size: 14px;'>抱歉，目前暂无数据</div>
					</c:if>
					<div id="loadingDiv" style="display: none;">正在加载中...</div>
				</ul>
			</div>
		</div>
	</div>
	
	<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
