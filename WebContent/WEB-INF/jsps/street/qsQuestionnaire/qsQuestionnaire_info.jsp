<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>问卷开始</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
$(function(){
	setImgHeight();
});
function setImgHeight(){
	var windowWidth = $(window).width();
	$(".wj_cont img").css("width",windowWidth*0.9+"px");
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
	   <img src="${global_image_url}/banner_lxyz.jpg">
	</c:if>
</div>
<%-- <div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_lxyz.jpg"></li>
	</ul>
</div> --%>
<form id="questionnaire_form" name="questionnaire_form" action="" method="post"> 
<input id="qs_id" name="qs_id" value="${qs_id }" type="hidden">
<input id="questionCount" name="questionCount" value="${qsQuestionnaireMap.questionCount }" type="hidden">
<div class="bszl_list">
	<div class="bszl_cont bszl_t text_m wj_title">
	<c:if test="${not empty qsQuestionnaireMap.questionCount && qsQuestionnaireMap.questionCount!='0'}">
		${qsQuestionnaireMap.title}(${qsQuestionnaireMap.questionCount}道题)
	</c:if>
	<c:if test="${empty qsQuestionnaireMap.questionCount || qsQuestionnaireMap.questionCount=='0'}">
		${qsQuestionnaireMap.title}(0道题)
	</c:if>
	</div>
	<div class="wj_container">
		<section class="module">
			<div class="titlebar">
				<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;个人信息</h5>
			</div>	
		</section>
		<div class="course-content" style="padding:0 10px;">
			<div class="course-Per"><label>姓名：</label><input type="text" id="name" name="name" value="${memberMap.name}" placeholder="请输入您的姓名"  class="tianxie"></div>
			<div class="ts_error" id="nameErrorDiv" style="display:none;">*请输入您的姓名</div>
			<div class="course-Per"><label>手机：</label><input type="text" id="phone" name="phone" value="${memberMap.phone}" placeholder="请输入您的联系手机"  class="tianxie"></div>
		  	<div class="ts_error" id="phoneErrorDiv" style="display:none;">*请输入您的手机号</div>
			<c:if test="${not empty qsInfoClassList}">
				<div class="course-Per">
					<label>单位：</label>
					<select id="unit" name="unit" onchange="changeUnit()">
						<c:forEach var="qsInfoClassMap" items="${qsInfoClassList}" varStatus="sta">
						    <c:if test="${sta.index==0}" >
								<option value="${qsInfoClassMap.classId }">${qsInfoClassMap.className }</option>
							</c:if>
						</c:forEach>
					</select>
					<select id="nextUnit" name="nextUnit" style="display: none"></select>
				</div>
			</c:if>
		</div>
	</div>
	<c:if test="${not empty qsQuestionnaireMap.content}">
		<div class="wj_cont"><p>${qsQuestionnaireMap.content}</p></div>	
	</c:if>
</div>

<div style="float:left; width:100%;height:71px;"></div>

<div class="bottominf1">
  <c:if test="${not empty qsQuestionnaireMap.questionCount && qsQuestionnaireMap.questionCount!='0'}">
  <!-- <button class="check-order" id="orders" onclick="start_questionnaire();">开始问卷</button> -->
  <a class="kswj_btn" href="javascript:start_questionnaire();">开始问卷</a>
  </c:if>
</div>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	changeUnit();
});
function changeUnit(){
	var supClassId = $("#unit").val();
	var nextUnit = $("#nextUnit");
	nextUnit.empty();
	$.ajax({
		type:"POST",
		data:{supClassId:supClassId},
		dataType: "json",//返回json格式的数据
   		url: "${global_url}/qsQuestionnaire/changeQSInfoClass.htm",
    	success: function (data) {
    		if(data.success=="0"){//成功
    			var qsInfoClassList = data.qsInfoClassList;
    			if(qsInfoClassList!=null){
    				for(var i=0;i<qsInfoClassList.length;i++){
    					var qsInfoClass = qsInfoClassList[i];
    					nextUnit.append("<option value='"+qsInfoClass.classId+"'>"+qsInfoClass.className+"</option>");
    		        }
    				$("#nextUnit").show();
    			}
    		}
        },
   		error: function(XMLHttpRequest, textStatus, errorThrown){
   			//alert("异常信息："+textStatus);
  		}
	});
}
//开始问卷
function start_questionnaire(){
	var name = $("#name").val();
    var phone = $("#phone").val();
	if(name == null || name == ""){
		$("#nameErrorDiv").show();
		return false; 
	}else{
		$("#nameErrorDiv").hide();
	}
	if(phone == null || phone == "") {
		$("#phoneErrorDiv").show();
		return false; 
	}else{
		$("#phoneErrorDiv").hide();
	}
	document.getElementById("questionnaire_form").action="${global_url}/qsQuestionnaire/getQSQuestionList.htm";
	document.getElementById("questionnaire_form").submit();
}
</script>
</html>
