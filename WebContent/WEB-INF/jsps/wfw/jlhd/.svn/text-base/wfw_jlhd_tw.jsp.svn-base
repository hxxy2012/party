<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" import="com.gw.base.util.TokenGen" %>
<%
  TokenGen.getInstance().saveToken(request);
  String token = (String)session.getAttribute("token");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>交流互动-提问</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
function jlhd_submit(){
	    var atDescription = $("#atDescription").val();
		if(atDescription == null || $.trim(atDescription) == "") {
			 alert("请输入描述内容");
			 return false;
		}
		
		document.getElementById("tw_form").submit();
	
}
</script>
</head>
<body>
<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_jlhd.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;交流互动</h2>
        <div class="course-content">
        	<p>交流互动简介</p>
        </div>
	</div>
</div>
<form id="tw_form" name="tw_form" action="${global_url}/wfw/jlhd/addTiwen.htm" method="post">
	<input type="hidden" name="token" value="<%=token%>"/>
	<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
	<div class="container">
		<!--<section class="module">
			<div class="titlebar">
				<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;发布信息</h5>
			</div>	
		</section>-->
	    <div class="course-content" style="padding:0 10px;">
			<div class="course-Per"><label style="position: relative; top: -30px;">描述：</label><textarea class="tianxie1" id="atDescription" name="atDescription"></textarea></div>
		</div>
	</div>
	
	<div style="float:left; width:100%;height:71px;"></div>
	
	<!-- 底部按钮 -->
	<!-- <div class="bottominf1">
		<button class="check-order " id="orders">提交</button>
	</div> -->
	<div class="bottominf1">
  		<button type="button" class="check-order" id="orders" onclick="jlhd_submit();">提交</button>
	</div>
</form>

<!--社区浮动个人中心 -->
<%-- <jsp:include page="../../include/floattoolbar.jsp" /> --%>

</body>
</html>