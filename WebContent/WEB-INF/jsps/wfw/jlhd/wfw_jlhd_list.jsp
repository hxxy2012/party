<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>交流互动</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript">
//存储，IE6~IE7 cookie 其他浏览器HTML5本地存储
if(window.localStorage){
	localStorage.setItem("member_account",'${memberMap.account}');  //进行本地存储
}else{
	Cookie.write("member_account",'${memberMap.account}');   //进行Cookie存储
}
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
</head>
<body>
<!--海报-->
<div class="ban_img m_b10">
	<c:if test="${not empty topImg}">
	   <img src="${topImg}">
	</c:if>
	<c:if test="${empty topImg}">
	   <img src="${global_image_url}/ban_jlhd.jpg">
	</c:if>
</div>
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/ban_jlhd.jpg"></li>
	</ul>
</div> --%>

<div class="container">
	<div class="Classall">
		<div class="jl_list" style="margin-bottom:0px; padding-bottom:0px;" >
			<ul class="jlhd_list">
				<c:if test="${not empty jlhdList && fn:length(jlhdList) > 0}">
					<c:forEach var="jlhdMap" items="${jlhdList}" varStatus="stat"> 
						<li>
				            <a href="${global_url}/wfw/jlhd/info.htm?shequId=${shequId}&atId=${jlhdMap.atId}">
				                <b class="jlhd_ico">?</b>
				                ${jlhdMap.atDescription}
				                <div class="jlhd_list_bz">
				                    <i>${jlhdMap.createdTime}</i>
				                    <c:if test="${jlhdMap.atStatus=='0'}"><span class="jlhd_whf">未回复</span></c:if>
				                    <c:if test="${jlhdMap.atStatus!='0'}"><span class="jlhd_yhf">已回复</span></c:if>
				                </div>
				            </a>   
		        		</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty jlhdList}">
					<li>暂无数据！</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<div style="float:left; width:100%;height:71px;"></div>

<div class="bottominf1">
	<a href="${global_url}/wfw/jlhd/tiwen.htm?shequId=${shequId}"><button class="check-order " id="orders">提问</button></a>
</div>

<!--社区浮动个人中心 -->
<%-- <jsp:include page="../../include/floattoolbar.jsp" /> --%>

</body>
</html>