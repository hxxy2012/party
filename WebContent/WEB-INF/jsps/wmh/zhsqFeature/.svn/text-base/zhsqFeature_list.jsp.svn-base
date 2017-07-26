<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>社区简介</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("img").lazyload({
			threshold : 10,
			effect : "fadeIn"
		});
		$("#reMove").click(function() {
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
		/* var search_company_name = $("#search_company_name").val(); */
		var url = "${global_url}/zhsq/feature/getZhsqFeatureListAsync.htm?shequId="+${shequId}+"pageno="
				+ (pageno * 1 + 1) + "&pageSize=" + pageSize;
		if(title != null && title != "" ){
			url = url+"&title="+title;
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
	function endShow() {
		if (isHidden) {
			isHidden = false;
			//var html = $(".Classall");
			//html.append("<div id='endDiv' style='float:left;height:30px; width:100%;text-align:center;display:none;'><span style='color:#999;font-size:14px;'>亲,已经是所有社区特色了</span></div>");
			isNotOtherData = true;
			setTimeout(function() {
				$("#endDiv").hide(1000);
				$("#endDiv").remove();
				isHidden = true;
			}, 2000);
		}
	}
	function showLoader() {
		$("#loadingDiv").hide();
	}
	function hideLoader() {
		$("#loadingDiv").hide();
	}

	function html(data) {
		var html = "";
		$.each(data.dataList, function(i, item) {
			html += appendHtml(item);
		});
		$(".Classall ul").append(html);
	}

	function appendHtml(item) {
		var html = '<li><a href="${global_url}/zhsq/feature/getZhsqFeatureInfo.htm?featureId='
				+ item.featureId
				+ '"><img src="'+item.paramMap.imagePaths+'" class="pic"></a>'
				+ '<div class="rt"><div class="tit"><a href="${global_url}/zhsq/feature/getZhsqFeatureInfo.htm?featureId='
				+ item.featureId
				+ '">'
				+ item.title
				+ '</a></div>'
				+ '<div class="dush"><div class="haoping">'
				+ item.clientContent
				+ '</div><div class="ljbm0">'
				+ item.viewCount + '人阅读</div></div></div>' + '</li>';

		return html;
	}
	function nofind() {
		var img = event.srcElement;
		img.src = "${global_image_url}/noimg_vote.jpg";
		img.onerror = null; //控制onerror事件只触发一次 
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
	<div class="ban_img m_b10">
	<c:if test="${not empty topImg}">
	   <img src="${topImg}">
	</c:if>
	<c:if test="${empty topImg}">
	  <img src="${global_image_url}/banner_sqts.jpg">
	</c:if>
</div>
	<%-- <ul class="slide-banner">
		<li><img src="${global_image_url}/sqfc.jpg"></li>
	</ul> --%>

	<!-- 搜索 -->
	<div class="sousuo">
		<form id="form_sousuo" action="${global_url}/zhsq/feature/getZhsqFeatureList.htm?shequId=${shequId}"
			method="post">
			<div class="sousuo-in">
				<input type="text" id="title" name="title" value="${title}"
					placeholder="请输入关键字搜索" /> <span id="reMove"><img
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
				<c:if test="${not empty featureList}">
					<c:forEach var="featureMap" items="${featureList}">
						<li><a
							href="${global_url}/zhsq/feature/getZhsqFeatureInfo.htm?featureId=${featureMap.featureId}">
								<c:if test="${not empty featureMap.paramMap.imagePaths}">
									<img src="${featureMap.paramMap.imagePaths }" class="pic">
								</c:if> <c:if test="${empty featureMap.paramMap.imagePaths}">
									<img src="${global_image_url}/moren.png" class="pic">
								</c:if>
						</a>
							<div class="rt">
								<div class="tit">
									<a
										href="${global_url}/zhsq/feature/getZhsqFeatureInfo.htm?featureId=${featureMap.featureId}">${featureMap.title }</a>
								</div>
								<div class="dush">
									<div class="haoping">${featureMap.clientContent}</div>
									<div class="ljbm0">${featureMap.viewCount}人阅读</div>
								</div>
							</div></li>
					</c:forEach>
				</c:if>
				<c:if test="${empty featureList}">
					<div style='width: 100%; text-align: center; font-size: 14px;'>抱歉，目前暂无数据</div>
				</c:if>
				<div id="loadingDiv" style="display: none;">正在加载中...</div>
			</ul>
		</div>
	</div>
	
	<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
