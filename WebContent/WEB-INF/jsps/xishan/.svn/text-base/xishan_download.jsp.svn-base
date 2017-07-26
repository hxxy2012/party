<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*;"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>文件下载</title>

<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
<link rel="stylesheet" type="text/css"
	href="${xishan_css_url }/village.css" />
<script type="text/javascript" src="${xishan_js_url }/zepto.min.js"></script>
<script type="text/javascript" src="${xishan_js_url }/Mbase.js"></script>
<script type="text/javascript" src="${xishan_js_url }/index.js"></script>
<script type="text/javascript" src="${xishan_js_url }/swipe.js"></script>
<!-- <script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script> -->
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
	/* var search_company_name = $("#search_company_name").val(); */
	var url = "${global_url}/xishan/getFileListAsync.htm?pageno=" + (pageno * 1 + 1) + "&pageSize=" + pageSize;
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
			var html = jq(".Classall");
			//html.append("<div id='endDiv' style='float:left;height:30px; width:100%;text-align:center;'><span style='color:#999;font-size:14px;'>亲,已经是所有了</span></div>");
			isNotOtherData = true;
			setTimeout(function() {
				jq("#endDiv").hide(1000);
				jq("#endDiv").remove();
				isHidden = true;
			}, 2000);
		}
	}
	function showLoader() {
		jq("#loadingDiv").show();
	}
	function hideLoader() {
		jq("#loadingDiv").hide();
	}
	
	function html(data) {
		var html = "";
		$.each(data.dataList, function(i, item) {
			html += appendHtml(item);
		});
		$(".Classall ul").append(html);
	}
	
	function appendHtml(item) {
		var html = '<li class="bszl_cont">'
				+ '<div class="bszl_details">'
				+ '<div class="tit">' + item.projectName + '</div>'
				+ '<div class="shzt" style="position:relative;">' 
				+ '<div class="shzt_t">'+item.createdTime + '</div>'
				+ '<a class="a_phone" style="top:0px" href="'+item.fileUrl+'">下载</a>'
				+ '</div></div></li>';
		
		return html;
	}
	function nofind() {
		var img = event.srcElement;
		img.src = "${xishan_image_url}/noimg_vote.jpg";
		img.onerror = null; //控制onerror事件只触发一次 
	}
</script>

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

.swipe-wrap>div {
	float: left;
	width: 100%;
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
	box-shadow: inset 0 1px 3px black, 0 0 1px 1px #202020;
	margin: 0 2px;
	cursor: pointer
}

nav #position li.on {
	box-shadow: inset 0 1px 3px -1px #28b4ea, 0 1px 2px rgba(0, 0, 0, .5);
	background-color: #1293dc;
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #1293dc),
		color-stop(100%, #0f6297));
	background-image: -webkit-linear-gradient(top, #1293dc, #0f6297);
	background-image: -moz-linear-gradient(top, #1293dc, #0f6297);
	background-image: -ms-linear-gradient(top, #1293dc, #0f6297);
	background-image: -o-linear-gradient(top, #1293dc, #0f6297);
	background-image: linear-gradient(top, #1293dc, #0f6297)
}
</style>


</head>
<body>
	<div class="slider">
		<ul class="slide-banner">
			<li><img src="${xishan_image_url }/banner_wjxz.png"></li>
		</ul>
	</div>
	<!--海报结束-->


	<div class="container">
		<div class="bszl_list">
			<ul >
				<c:if test="${not empty fileList}">
					<c:forEach var="fileMap" items="${fileList}">
						<li class="bszl_cont">
							<div class="bszl_details">
								<div class="tit">${fileMap.fileName}</div>
								<div class="shzt" style="position:relative;">
									<div class="shzt_t">${fileMap.createdTime}</div>
									<a class="a_phone" style="top:0px" href="${fileMap.fileUrl }">下载</a>
									<%-- <a class="a_phone" style="top:0px" 
										href="${global_url}/xishan/pdftest.htm?fileUrl=${image_server}${fileMap.fileUrl }">下载</a> --%>
								</div>
							</div>

						</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty fileList}">
					<div style='width: 100%; text-align: center; font-size: 14px;'>抱歉，目前暂无数据</div>
				</c:if>
				<div id="loadingDiv" style="display: none;">正在加载中...</div>
			</ul>
		</div>
	</div>


</body>
</html>
