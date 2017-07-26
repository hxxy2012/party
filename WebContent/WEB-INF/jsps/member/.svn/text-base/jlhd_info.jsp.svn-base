<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>我的咨询-信息</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>

<!--问-->
<div class="jl_ask">
   	<div class="ask_left">
		<c:if test="${not empty jlhdMap.paramMap && not empty jlhdMap.paramMap.accountImage}">
 			<img class="jl_img" src="${jlhdMap.paramMap.accountImage}" />
 		</c:if>
 		<c:if test="${empty jlhdMap.paramMap || empty jlhdMap.paramMap.accountImage}">
 			<c:if test="${not empty jlhdMap.gender && jlhdMap.gender == '1'}">
 				<img class="jl_img" src="${global_image_url}/toux_nv.jpg" />
 			</c:if>
 			<c:if test="${not empty jlhdMap.gender && jlhdMap.gender == '0'}">
 				<img class="jl_img" src="${global_image_url}/toux_nan.jpg" />
 			</c:if>
 			<c:if test="${empty jlhdMap.gender || (jlhdMap.gender != '1' && jlhdMap.gender != '0')}">
 				<img class="jl_img" src="${global_image_url}/u1.jpg" />
 			</c:if>
 		</c:if>
    </div>
    <div class="ask_left_name">
       	<h5 class="jl_name">${jlhdMap.userName}</h5>
        <h6 class="jl_time">${jlhdMap.createdTime}</h6>
    </div>
    <div class="ask_right">
    	<i></i>
        <p>${jlhdMap.atDescription}</p>
    </div>
</div>
	
 <!--答-->
<div class="jl_answer">
	<c:if test="${not empty jlhdMap.paramMap && fn:length(jlhdMap.paramMap.replyList) > 0}">
 		<c:forEach var="jlhd" items="${jlhdMap.paramMap.replyList}" varStatus="stat"> 
 			<div class="answer_right_name">
 				<h6 class="jl_time">${jlhd.createdTime}</h6>
                <h5 class="jl_name">${jlhd.userName}</h5>
        	</div>
 			<div class="answer_left">
     			<i></i>
         		<p>${jlhd.arReplyContent}</p>
    		</div>
			<div class="answer_right">
		 		<c:if test="${not empty jlhd.paramMap && not empty jlhd.paramMap.accountImage}">
		 			<img class="jl_img" src="${jlhd.paramMap.accountImage}" />
		 		</c:if>
		 		<c:if test="${empty jlhd.paramMap || empty jlhd.paramMap.accountImage}">
		 			<c:if test="${not empty jlhd.gender && jlhd.gender == '1'}">
		 				<img class="jl_img" src="${global_image_url}/toux_nv.jpg" />
		 			</c:if>
		 			<c:if test="${not empty jlhd.gender && jlhd.gender == '0'}">
		 				<img class="jl_img" src="${global_image_url}/toux_nan.jpg" />
		 			</c:if>
		 			<c:if test="${empty jlhd.gender || (jlhd.gender != '1' && jlhd.gender != '0')}">
		 				<img class="jl_img" src="${global_image_url}/u1.jpg" />
		 			</c:if>
		 		</c:if>
		     </div>
		</c:forEach>
	</c:if>
</div>
<!--问
<div class="jl_ask">
   	<div class="ask_left">
     	 <c:if test="${not empty jlhdMap.paramMap && not empty jlhdMap.paramMap.accountImage}">
 			<img class="jl_img" src="${jlhdMap.paramMap.accountImage}" />
 		</c:if>
 		<c:if test="${empty jlhdMap.paramMap || empty jlhdMap.paramMap.accountImage}">
 			<img class="jl_img" src="${global_image_url}/u1.jpg" />
 		</c:if>
         <h5 class="jl_name">${jlhdMap.atAccount}</h5>
         <h6 class="jl_time">${jlhdMap.createdTime}</h6>
    </div>
    <div class="ask_right">
    	<i></i>
        <p>${jlhdMap.atDescription}</p>
    </div>
</div>
	
<div class="jl_answer">
	<c:if test="${not empty jlhdMap.paramMap && fn:length(jlhdMap.paramMap.replyList) > 0}">
 		<c:forEach var="jlhd" items="${jlhdMap.paramMap.replyList}" varStatus="stat"> 
 			<div class="answer_left">
     			<i></i>
         		<p>${jlhd.arReplyContent}</p>
    		</div>
			<div class="answer_right">
		 		<c:if test="${not empty jlhd.paramMap && not empty jlhd.paramMap.accountImage}">
		 			<img class="jl_img" src="${jlhd.paramMap.accountImage}" />
		 		</c:if>
		 		<c:if test="${empty jlhd.paramMap || empty jlhd.paramMap.accountImage}">
		 			<img class="jl_img" src="${global_image_url}/u1.jpg" />
		 		</c:if>
		      	<h5 class="jl_name">${jlhd.atAccount}</h5>
		      	<h6 class="jl_time">${jlhd.createdTime}</h6>
		     </div>
		</c:forEach>
	</c:if>
</div>
-->
<!-- 底部按钮 -->
<%-- <div class="bottominf_hf">
	<form id="hf_form" name="hf_form" action="${global_url}/wfw/jlhd/addHuifu.htm" method="post">
		<input type="hidden" id="atId" name="atId" value="${jlhdMap.atId}">
		<div class="hf_text">
			<input type="text" name="arReplyContent" placeholder="请输入回复内容" />
		</div>
		<div class="hf_fs">
			<input type="submit" value="回复" />
		</div>
	</form>
</div> --%>

</body>
</html>