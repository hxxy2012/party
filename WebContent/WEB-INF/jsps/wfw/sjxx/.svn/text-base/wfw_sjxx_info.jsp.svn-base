<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>书记信箱详情</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
function satisfact_submit(){
	var memberId = $("#memberId").val();
	if(memberId == null || memberId == ""){
		var temp = confirm("您还未登录，请先去登录");
		if(temp){
			window.location.href="${global_url}/member/exit.htm";
		}
	}else{
		
		var satisfactType = $("input[name='satisfactType']:checked").val();
		if(satisfactType==undefined){
			alert("请选择满意度");
			return false;
		}
		
		document.getElementById("form1").submit();
	}
}
</script>
</head>
<body>
<!--幻灯片-->
<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url }/banner1.jpg"></a></li>
	</ul>
	<div class="indicator">
		<div class="indicator-num">
			<span class="point"></span>
			<span class="point"></span>
			<span class="point selected"></span>
			<span class="point"></span>
			<span class="point"></span>
		</div>
	</div>
</div>
<!--幻灯片结束-->

<div class="Courseinfor">
	<div class="course-header">
	<c:if test="${not empty sjxxInfoMap}">
        <p>发布人姓名：${sjxxInfoMap.name }</p>
        <p>发布人手机：${sjxxInfoMap.phone }</p>
        <p>解决状态：
        	<c:choose>
				<c:when test="${sjxxInfoMap.resolve=='0'}">未解决</c:when>
				<c:when test="${sjxxInfoMap.resolve=='1'}">已解决</c:when>
				<c:when test="${sjxxInfoMap.resolve=='2'}">不必解决</c:when>
				<c:otherwise>无</c:otherwise>
			</c:choose>
        </p>
    </c:if>    
    </div>
</div>
<div class="Courseinfor xqcon">
    <div class="course-content">
    	<h4>信箱内容：</h4>
	    <c:if test="${not empty sjxxInfoMap&&sjxxInfoMap.content!=''}">
	    	${sjxxInfoMap.content}
	     </c:if> 
    </div>
    
    <div class="course-content">
    	<h4>解决方案：</h4>
	    <c:if test="${not empty sjxxInfoMap&&sjxxInfoMap.solution!=''}">
	    	${sjxxInfoMap.solution}
	     </c:if> 
    </div>
    
    <c:if test="${not empty sjxxInfoMap.resolve && sjxxInfoMap.resolve=='1'}">
	    <form id="form1" action="${global_url}/wfw/sjxx/subSatisfact.htm" method="post">
		    <input type="hidden" name="memberId" id="memberId" value="${sjxxInfoMap.memberId}" />
			<input type="hidden" name="shequId" id="shequId" value="${sjxxInfoMap.shequId}" />
			<input type="hidden" name="sjxxId" id="sjxxId" value="${sjxxInfoMap.sjxxId}" />
	    	<div class="course-content" style="padding:0 10px;">
	    		<div class="course-Per">
		    		<label>满意度：</label>
		    		<input ${(sjxxInfoMap.satisfactType=='1')?'checked':''} type="radio" name="satisfactType" id="satisfactType" value="1" class="tianxie">非常满意&nbsp;&nbsp;
		    		<input ${(sjxxInfoMap.satisfactType=='2')?'checked':''} type="radio" name="satisfactType" id="satisfactType" value="2" class="tianxie">满意&nbsp;&nbsp;
		    		<input ${(sjxxInfoMap.satisfactType=='3')?'checked':''} type="radio" name="satisfactType" id="satisfactType" value="3" class="tianxie">不满意
	    		</div>
	    		<div class="course-Per">
		    		<label style="position: relative; top: -30px;">意见：</label>
		    		<textarea class="tianxie1" name="satisfactContent" id="satisfactContent">${sjxxInfoMap.satisfactContent}</textarea>
	    		</div>
	  		</div>
	  		<div style="float:left; width:100%;height:71px;"></div>
			<c:if test="${empty sjxxInfoMap.satisfactType && sjxxInfoMap.satisfactType==''}">
				<!-- 底部按钮 -->
				<div class="bottominf1">
			  		<button type="button" class="check-order " id="orders" onclick="satisfact_submit();">提交</button>
				</div>
			</c:if>
	   
    </c:if>
     </form>
   <%--  <div class="course-content">
    	<h4>二手货图片</h4>
    	
   	 <c:if test="${not empty esscInfoMap.paramMap.imagePaths}">
   		<c:forEach items="${esscInfoMap.paramMap.imagePaths}" var="sysImagePO" >
			<a href="${sysImagePO.path}" target="_blank"><img id='hphoto'  src="${sysImagePO.path}" style='height: 100px;' border="0"/></a>
		</c:forEach> 
	</c:if>
	<c:if test="${empty esscInfoMap.paramMap.imagePaths}">
		<img height="50" width="50" src="${global_image_url}/mr_essc.png" class="pic">
	</c:if> 
    
    </div> --%>
</div>

<!--社区浮动个人中心 -->
<%-- <jsp:include page="../../include/floattoolbar.jsp" /> --%>

</body>
</html>

