<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>书记信箱-提交成功</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<span class="icon back" onclick="history.back()"></span>报名成功
	</div>
</header>
 -->
<div class="Courseinfor">
	<div class="successful"><i><img src="${global_image_url }/successful.png" ></i>提交成功</div>
	<a class="fh_btn" href="${global_url}/wfw/init.htm?shequId=${shequId}">返回</a>
</div>
<%-- <div class="container" style="padding: 10px 0 50px 0;" >
    <div class="course-content" style="padding:0 10px;">
   		<div class="course-Per"><label>流水号：</label>${orderMap.SN}</div>
    	<div class="course-Per"><label>真实姓名：</label>${orderMap.SHIP_NAME}</div>
    	<div class="course-Per"><label>手机号码：</label>${orderMap.SHIP_MOBILE}</div>
    	<c:if test="${orderMap.SHIP_EMAIL!=null && orderMap.SHIP_EMAIL!=''}">
    		<div class="course-Per"><label>邮箱地址：</label>${orderMap.SHIP_EMAIL}</div>
    	</c:if>
	 <div class="loginbody">
	 	 <button style="background-color: #4ea12c;" class="denglu" id="register"  onclick="location.href='${global_url}/member/memberOrder.htm';">去支付</button>
	 
	 </div>
	</div>
</div> --%>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>
