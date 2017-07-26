<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>街道预约</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>
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
var pageno = 1;
var pageSize = 10;
var size = 0;
function GetAjaxData() {
	var url = "${global_url}/street/yuyue/getYuyueListAsync.htm?pageno="
			+ (pageno * 1 + 1) + "&pageSize=" + pageSize;
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
			pageno++;
			size = data.size;
			if (size > 0) {//存在数据
				var html = "";
				$.each(data.dataList, function(i, item) {
					html += appendHtml(item);
				});
				$(".Classall ul").append(html);
			} else {//未取得数据
				if (pageno * 1 > 1) {//表示已经全部加载
					endShow();
				}
			}
			isJiazai = false;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("异常信息：" + textStatus);
		}
	});
}
var isHidden = true;
function endShow() {
	if (isHidden) {
		isHidden = false;
		//var html = $(".Classall");
		//html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>全部加载完成</span></div>");
		isNotOtherData = true;
		setTimeout(function() {
			$("#endDiv").hide(1000);
			$("#endDiv").remove();
			isHidden = true;
		}, 2000);
	}
}
function showLoader() {
	$("#loadingDiv").show();
}
function hideLoader() {
	$("#loadingDiv").hide();
}
function appendHtml(item) {
	var html = '<li>'
				+ '<a href="${global_url}/street/yuyue/getYuyueInfo.htm?yuyueId='+${item.yuyueId}+'">'
				+'<img src="'+${global_image_url}+'/class1.png" class="pic"></a>'
				+ '<div class="rt">'
				+ '<div class="tit"><a href="${global_url}/street/yuyue/getYuyueInfo.htm.htm?yuyueId='
				+ ${item.yuyueId}+'">'+'预约事项：'+${item.action} + '</a></div>'
				+ '<div class="yy_list_cont">'+'预约日期：'+${item.yuyueDate}+'</div>'
				+ '<div class="yy_list_cont">'+'预约时间：'+${item.yuyueTime}+'</div>'
				+ '</div>' 
				+ '</li>';
	return html;
}
</script>
</head>
<body>

	<div class="slider">
		<ul class="slide-banner">
			<li><img src="${global_image_url}/banner_yybs.jpg"></li>
		</ul>
	</div>
	<%-- 
<div class="sousuo">
  <div class="sousuo-in">
    <input type="text" placeholder="请输入关键字搜索" >
  <span><img src="${global_image_url}/cha.png" width="30" height="30"></span> </div>
  <input type="submit" value="" id="submit-ss">
</div>
--%>

<div class="Classall">
	<ul class="allcon">
		<c:if test="${not empty yuyueList}">
			<c:forEach var="yuyueMap" items="${yuyueList}">
				<li><a href="#"><img src="${global_image_url}/class1.png" class="pic"></a>
					<div class="rt">
						<div class="tit">
							<a href="${global_url}/street/yuyue/getYuyueInfo.htm?yuyueId=${yuyueMap.yuyueId}">预约事项：${yuyueMap.action}</a>
						</div>
						<div class="yy_list_cont">
							<!-- <span class="yy_icon1"></span> -->预约日期：${yuyueMap.yuyueDate}
						</div>
						<div class="yy_list_cont">
							<!-- <span class="yy_icon3"></span> -->预约时间：${yuyueMap.yuyueTime}
						</div>
					</div>
				</li>
			</c:forEach>
		</c:if>
	</ul>
	<c:if test="${empty yuyueList}">
      		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
      	</c:if>
    <div id ="loadingDiv" style="display:none;">正在加载中...</div>
</div>

</body>
</html>
