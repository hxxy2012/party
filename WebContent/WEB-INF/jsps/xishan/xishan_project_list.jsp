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
<title>工程列表</title>
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
<script type="text/javascript" src="${xishan_js_url}/jquery-1.6.4.js"></script>
<script type="text/JavaScript" src="${xishan_js_url}/jquery.js"></script>
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
	var projectName = "${projectName}";
	var projectType = "${projectType}";
	/* var search_company_name = $("#search_company_name").val(); */
	var url = "${global_url}/xishan/getProjectListAsync.htm?pageno=" + (pageno * 1 + 1) + "&pageSize=" + pageSize;
	if(projectName != null && projectName != "" ){
		url = url+"&projectName="+projectName;
	}
	if(projectType != null && projectType != "" ){
		url = url+"&projectType="+projectType;
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
		var html =jq(".Classall");
		//html.append("<div id='endDiv' style='float:left;height:30px; width:100%;text-align:center;'><span style='color:#999;font-size:14px;'>亲,已经是所有了</span></div>");
		 isNotOtherData = true;
		  setTimeout(function(){
			 jq("#endDiv").hide(1000);
			jq("#endDiv").remove(); 
			 isHidden = true;
		 },2000); 
	}
}
  function showLoader() {  
	  jq("#loadingDiv").show();
 }  
 function hideLoader(){  
	 jq("#loadingDiv").hide(); 
 }  
 
function html(data) {
		var html = "";
		$.each(data.dataList, function(i, item) {
			html += appendHtml(item);
		});
		$(".Classall ul").append(html);
}

function appendHtml(item){
	var html='<li>'
			+'<a href="${global_url}/xishan/getProjectMap.htm?projectId='+item.projectId+'">'
		+'<div class="rt">'
		+'<div class="tit">'+item.projectName+'</div>'
		+'<div class="shzt">'
		+'<div class="shzt_t">'+item.createdTime+'</div>';
	if(item.projectType=='1'){
		var sgxk = "";
		if(item.newState == '0'){
			sgxk = '<div class="shzt01"><span class="sh01">施工合同备案中</span></div>';
			
			html = html + sgxk;
		}
		if(item.newState == '1'){
			sgxk = '<div class="shzt01"><span class="sh01">安监手续办理中</span></div>';
			
			html = html + sgxk;
		}
		if(item.newState == '2'){
			sgxk = '<div class="shzt01"><span class="sh01">质监手续办理中</span></div>';
			
			html = html + sgxk;
		}
		if(item.newState == '3'){
			sgxk = '<div class="shzt01"><span class="sh01">监理合同备案中</span></div>';
			
			html = html + sgxk;
		}
		if(item.newState == '5'){
			sgxk = '<div class="shzt01"><span class="sh01">施工许可证网上申报中</span></div>';
			
			html = html + sgxk;
		}
		if(item.newState == '4'){
			sgxk = '<div class="shzt01"><span class="sh01">施工许可办理完结</span></div>';
			
			html = html + sgxk;
		}
		
	}else if(item.projectType=='2'){
		var dkks = "";
		if(item.audits=='0'){
			dkks = '<div class="shzt01"><span class="sh01">审核中</span></div>';
		}
		if(item.audits=='1'){
			dkks = '<div class="shzt01"><span class="sh03">审核通过</span></div>';
		}
		if(item.audits=='2'){
			dkks = '<div class="shzt01"><span class="sh02">审核未通过</span></div>';
		}
		html = html + dkks;
	}
	var end = '</div></div></a></li>';
	html = html + end;
	
	 return html;
 }
function nofind(){
	var img=event.srcElement; 
	img.src="${xishan_image_url}/noimg_vote.jpg"; 
	img.onerror=null; //控制onerror事件只触发一次 
}
</script>
<style>
 .shzt{ line-height:30px; font-size:16px; color:#999;}
.shzt_t{ float:left;}
.shzt01{ float:right;}
.sh01{ color:#437dc7}
.sh02{ color:#f16767;}
.sh03{ color:#33c87a;}
</style>
</head>
<body>

<div class="slider">
	<ul class="slide-banner">
		<li><img src="${xishan_image_url }/xs_banner_bzdt.png"></li>
	</ul>
</div><!--海报结束-->

<%-- <div class="zwlist">
	<ul>
		<c:choose>
			<c:when test="${empty projectType || projectType=='1'}">
				<li id="temp0" class="s_zxfenlie" style="width:50%" ><a href="${global_url}/xishan/getProjectListMap.htm?projectType=1">施工许可</a></li>
        		<li id="temp1" style="width:50%"><a href="${global_url}/xishan/getProjectListMap.htm?projectType=2">道口开设</a></li>
			</c:when>
			<c:when test="${projectType=='2'}">
				<li id="temp0" style="width:50%" ><a href="${global_url}/xishan/getProjectListMap.htm?projectType=1">施工许可</a></li>
				<li id="temp1" class="s_zxfenlie" style="width:50%"><a href="${global_url}/xishan/getProjectListMap.htm?projectType=2">道口开设</a></li>
			</c:when>
		</c:choose>
    </ul>
</div> --%>

<!--搜索-->
<div class="sousuo">
  <div class="sousuo-in">
  	<form action="${global_url}/xishan/getProjectListMap.htm?projectType=${projectType}" method="post">
	    <input type="text" placeholder="请输入工程项目名称" name="projectName" id="projectName" value="${projectName}"> 
	    <span><img src="${xishan_image_url }/cha.png" width="20" height="20"></span>
	    <input type="submit" value="&nbsp" id="submit-ss" data-role="none">
    </form>
  </div>
</div>

<!--列表-->
<div class="container">
   <div class="Classall">
	<ul class="allcon">
		<c:if test="${not empty projectList}">
	       	<c:forEach var="newsMap" items="${projectList}">
		    	<li>
			    	<a href="${global_url}/xishan/getProjectMap.htm?projectId=${newsMap.projectId}" >
			            <div class="rt">
			                <div class="tit">${newsMap.projectName}</div>
			                <div class="shzt">
			                    <div class="shzt_t">${newsMap.createdTime}</div>
			                    <div class="shzt01">
			                    	<c:if test="${newsMap.projectType=='1'}">
			                    		<c:if test="${newsMap.newState == '0'}">
			                    		<span class="sh01">施工合同备案中</span>
					                    <%-- <c:choose>
					                    	<c:when test="${newsMap.sght=='0'}"><span class="sh01">施工合同备案中</span></c:when>
					                    	<c:when test="${newsMap.sght=='1'}"><span class="sh03">施工合同备案通过</span></c:when>
					                    	<c:when test="${newsMap.sght=='2'}"> <span class="sh02">施工合同备案未通过</span></c:when>
					                    </c:choose> --%>
					                    </c:if>
					                    <c:if test="${newsMap.newState == '1'}">
					                    <span class="sh01">安监手续办理中</span>
					                    <%-- <c:choose>
					                    	<c:when test="${newsMap.ajsx=='0'}"><span class="sh01">安监手续办理中</span></c:when>
					                    	<c:when test="${newsMap.ajsx=='1'}"><span class="sh03">安监手续通过</span></c:when>
					                    	<c:when test="${newsMap.ajsx=='2'}"> <span class="sh02">安监手续未通过</span></c:when>
					                    </c:choose> --%>
					                    </c:if>
					                    <c:if test="${newsMap.newState == '2'}">
					                    <span class="sh01">质监手续办理中</span>
					                    <%-- <c:choose>
					                    	<c:when test="${newsMap.zjsx=='0'}"><span class="sh01">质监手续办理中</span></c:when>
					                    	<c:when test="${newsMap.zjsx=='1'}"><span class="sh03">质监手续通过</span></c:when>
					                    	<c:when test="${newsMap.zjsx=='2'}"> <span class="sh02">质监手续未通过</span></c:when>
					                    </c:choose> --%>
					                    </c:if>
					                    <c:if test="${newsMap.newState == '3'}">
					                    <span class="sh01">监理合同备案中</span>
					                    <%-- <c:choose>
					                    	<c:when test="${newsMap.jlht=='0'}"><span class="sh01">监理合同备案中</span></c:when>
					                    	<c:when test="${newsMap.jlht=='1'}"><span class="sh03">监理合同备案通过</span></c:when>
					                    	<c:when test="${newsMap.jlht=='2'}"> <span class="sh02">监理合同备案未通过</span></c:when>
					                    </c:choose> --%>
					                    </c:if>
					                    <c:if test="${newsMap.newState == '5'}">
					                    <span class="sh01">施工许可证网上申报中</span>
					                    <%-- <c:choose>
					                    	<c:when test="${newsMap.audits=='0'}"><span class="sh01">施工许可办理中</span></c:when>
					                    	<c:when test="${newsMap.audits=='1'}"><span class="sh03">施工许可审核通过</span></c:when>
					                    	<c:when test="${newsMap.audits=='2'}"> <span class="sh02">施工许可未通过</span></c:when>
					                    </c:choose> --%>
					                    </c:if>
					                    <c:if test="${newsMap.newState == '4'}">
					                    <span class="sh01">施工许可办理完结</span>
					                    <%-- <c:choose>
					                    	<c:when test="${newsMap.audits=='0'}"><span class="sh01">施工许可办理中</span></c:when>
					                    	<c:when test="${newsMap.audits=='1'}"><span class="sh03">施工许可审核通过</span></c:when>
					                    	<c:when test="${newsMap.audits=='2'}"> <span class="sh02">施工许可未通过</span></c:when>
					                    </c:choose> --%>
					                    </c:if>
					                </c:if>
					                <c:if test="${newsMap.projectType=='2'}">
					                    <c:choose>
					                    	<c:when test="${newsMap.audits=='0'}"><span class="sh01">审核中</span></c:when>
					                    	<c:when test="${newsMap.audits=='1'}"><span class="sh03">审核通过</span></c:when>
					                    	<c:when test="${newsMap.audits=='2'}"> <span class="sh02">审核未通过</span></c:when>
					                    	<c:otherwise><span class="sh01">未审核</span></c:otherwise>
					                    </c:choose>
					                </c:if>
			                    </div>
			                </div>
			            </div>
		            </a>
				</li>
        	</c:forEach>
       	</c:if>
       	<c:if test="${empty projectList}">
       		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
       	</c:if>
    	<div id ="loadingDiv" style="display:none;">正在加载中...</div>
    </ul>
</div>
</div>
</body>
</html>
