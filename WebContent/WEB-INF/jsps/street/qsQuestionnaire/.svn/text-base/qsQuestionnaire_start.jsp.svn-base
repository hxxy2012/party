<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>问卷</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	var today=new Date();
	var year=today.getFullYear();
    var month=today.getMonth()+1;
    var date=today.getDate();
  	var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();
    $("#startTime").val(year+"-"+month+"-"+date+" "+h+":"+m+":"+s);
});
var ss=0, mm=0, hh=0; //申明秒、分、时变量并赋初始值
function startTime(){
	ss++; //秒值以1为单位递增   
	if(ss>=60) { //当秒数大于等于60     
		mm+=1; //分钟加1      
		ss=0; //秒数退回0   
	}   
	if(mm>=60) { //当分钟大于等于60      
		hh+=1; //小时数加1      
		mm=0; //分钟数退回0   
	}
	ss_str = (ss < 10 ? "0" + ss : ss); //格式化秒数
	mm_str = (mm < 10 ? "0" + mm : mm); //格式化分钟数
	tMsg = hh + ":" + mm_str + ":" + ss_str; //输出的字串
	$("#xxxxxx").html(tMsg);
	setTimeout("startTime()",1000);
}
</script>
</head>
<body onload="startTime()">

<style>
	.good_img{ position:fixed;  z-index:99;     width: 100%; top: 25%;}
	.good_img img{ width:100%;}
</style>

<div id="good_img" class="good_img" style="display: none">
	<img src="${global_image_url}/good.gif" />
</div>
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
<div class="bszl_list">
	<form id="Question_form" name="Question_form" action="" method="post">   
	<input type="hidden" name="token" value="<%=token%>"/>
	<input id="startTime" name="startTime" type="hidden">
	<input id="endTime" name="endTime" type="hidden">
	<input id="totalTime" name="totalTime" type="hidden">
	<input id="qsId" name="qsId" type="hidden" value="${qsId}">
	<input id="name" name="name" type="hidden" value="${name}">
	<input id="phone" name="phone" type="hidden" value="${phone}">
	<input id="unit" name="unit" type="hidden" value="${unit}">
	<input id="nextUnit" name="nextUnit" type="hidden" value="${nextUnit}">
	<input id="correct_totle" name="correct_totle" type="hidden" value="0">
	<input id="qsQuestion_totle" name="qsQuestion_totle" type="hidden" value="${qsQuestion_totle}">
	<div class="bszl_cont bszl_t wj_time">
		<span class="yongshi" >用时</span>
       	<span id="xxxxxx">00:00:00</span>
	</div>
	<c:if test="${not empty qsQuestionList}">
       	<c:forEach items="${qsQuestionList}" var="qsQuestion" varStatus="idxQSQuestion">
       		<c:if test="${idxQSQuestion.index+1==1}">
	       		<div class="bszl_cont bszl_t" id="qsQuestion_${idxQSQuestion.index+1}">
					<span class="bszl_num">${idxQSQuestion.index+1}</span>
			    	<c:choose>
	                      <c:when test="${qsQuestion.questionType=='1'}"><span class="danx" >[单选]</span></c:when>
	                      <c:when test="${qsQuestion.questionType=='2'}"><span class="duox" >[多选]</span></c:when>
	            	</c:choose>
			       	${qsQuestion.question}
				</div>
				<div class="bszl_cont" id="qsQuestion_answer_${idxQSQuestion.index+1}">
					<c:choose>
	              		<c:when test="${qsQuestion.questionType=='1'}">
	            			<!--单选-->
			           		<c:if test="${not empty qsQuestion.qsQuestionnaireAnswerList}">
					       		<c:forEach var="qsQuestionAnswer" items="${qsQuestion.qsQuestionnaireAnswerList}">
					       			<input id="questionType_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" name="questionType_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" type="hidden" value="${qsQuestion.questionType}">
					       			<input id="isRight_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" name="isRight_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" type="hidden" value="${qsQuestionAnswer.isRight}">
					       			<c:if test="${qsQuestionAnswer.isRight=='1'}">
					       				<input id="answer_${qsQuestion.sqId}" name="answer_${qsQuestion.sqId}" type="hidden" value="${qsQuestionAnswer.answer}">
					       			</c:if>
					       			<div class="wj_da"><input type="radio" onclick="onclickRadioAnswer('${idxQSQuestion.index+1}','${qsQuestion.sqId}','${qsQuestionAnswer.answerId}')" name="answer_${qsQuestion.sqId}">${qsQuestionAnswer.answer}</div>
					       		</c:forEach>
					       	</c:if>
	                	</c:when>
	           			<c:when test="${qsQuestion.questionType=='2'}">
					       	<!--多选-->
	           				<c:forEach var="qsQuestionAnswer" items="${qsQuestion.qsQuestionnaireAnswerList}">
					       		<c:if test="${qsQuestionAnswer.isRight=='1'}">
					       			<input id="answer_checkbox_${qsQuestionAnswer.answerId}" name="answer_checkbox_${qsQuestion.sqId}" type="hidden" value="${qsQuestionAnswer.answerId}">
					       		</c:if>
					       		<input id="answer_checkbox_value_${qsQuestionAnswer.answerId}" name="answer_checkbox_value_${qsQuestion.sqId}" type="hidden" value="${qsQuestionAnswer.answer}">
					       		<div class="wj_da"><input type="checkbox" name="answer_${qsQuestion.sqId}" value="${qsQuestionAnswer.answerId}">${qsQuestionAnswer.answer}</div>
					       	</c:forEach>
					       	<div class="bottominf1">
					       		<a class="kswj_btn" href="javascript:void(0);" onclick="nextCheckboxQuestion('${idxQSQuestion.index+1}','${qsQuestion.sqId}')">查看结果</a>
	           				</div>
	           			</c:when>
	            	</c:choose>
				</div>
			</c:if>
			<c:if test="${idxQSQuestion.index+1!=1}">
				<div class="bszl_cont bszl_t" id="qsQuestion_${idxQSQuestion.index+1}" style="display:none;">
					<span class="bszl_num">${idxQSQuestion.index+1}</span>
			    	<c:choose>
	                      <c:when test="${qsQuestion.questionType=='1'}"><span class="danx" >[单选]</span></c:when>
	                      <c:when test="${qsQuestion.questionType=='2'}"><span class="duox" >[多选]</span></c:when>
	            	</c:choose>
			       	${qsQuestion.question}
				</div>
				<div class="bszl_cont" id="qsQuestion_answer_${idxQSQuestion.index+1}" style="display:none;">
					<c:choose>
	              		<c:when test="${qsQuestion.questionType=='1'}">
	            			<!--单选-->
			           		<c:if test="${not empty qsQuestion.qsQuestionnaireAnswerList}">
					       		<c:forEach var="qsQuestionAnswer" items="${qsQuestion.qsQuestionnaireAnswerList}">
					       			<input id="questionType_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" name="questionType_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" type="hidden" value="${qsQuestion.questionType}">
					       			<input id="isRight_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" name="isRight_${qsQuestion.sqId}_${qsQuestionAnswer.answerId}" type="hidden" value="${qsQuestionAnswer.isRight}">
					       			<c:if test="${qsQuestionAnswer.isRight=='1'}">
					       				<input id="answer_${qsQuestion.sqId}" name="answer_${qsQuestion.sqId}" type="hidden" value="${qsQuestionAnswer.answer}">
					       			</c:if>
					       			<div class="wj_da"><input type="radio" onclick="onclickRadioAnswer('${idxQSQuestion.index+1}','${qsQuestion.sqId}','${qsQuestionAnswer.answerId}')" name="answer_${qsQuestion.sqId}">${qsQuestionAnswer.answer}</div>
					       		</c:forEach>
					       	</c:if>
	                	</c:when>
	           			<c:when test="${qsQuestion.questionType=='2'}">
	           				<!--多选-->
	           				<c:forEach var="qsQuestionAnswer" items="${qsQuestion.qsQuestionnaireAnswerList}">
					       		<c:if test="${qsQuestionAnswer.isRight=='1'}">
					       			<input id="answer_checkbox_${qsQuestionAnswer.answerId}" name="answer_checkbox_${qsQuestion.sqId}" type="hidden" value="${qsQuestionAnswer.answerId}">
					       		</c:if>
					       		<input id="answer_checkbox_value_${qsQuestionAnswer.answerId}" name="answer_checkbox_value_${qsQuestion.sqId}" type="hidden" value="${qsQuestionAnswer.answer}">
					       		<div class="wj_da"><input type="checkbox" name="answer_${qsQuestion.sqId}" value="${qsQuestionAnswer.answerId}">${qsQuestionAnswer.answer}</div>
					       	</c:forEach>
					    
					       	<a class="kswj_btn" href="javascript:void(0);" onclick="nextCheckboxQuestion('${idxQSQuestion.index+1}','${qsQuestion.sqId}')">查看结果</a>
	           			
	           			</c:when>
	            	</c:choose>
				</div>
			</c:if>
       	</c:forEach>
	</c:if>
	<c:if test="${empty qsQuestionList}">
		<span>暂无问卷题目</span>
	</c:if>
	</form>
</div>

<!-- 底部按钮 -->
<div style="float:left; width:100%;height:71px;"></div>
<div class="bottominf1" style="display:none;">
  <!--答案-->
  <div class="daan_wrong" >
	  <p class="ts_error">回答错误，正确答案为：</p>
	  <p>A、XXXXXXXX</p>
	  <p>B、YYYYYYYY</p>
  </div>
  <button class="check-order" id="orders" onclick="nextQuestion()">下一题</button>
</div>

</body>
<script type="text/javascript">
//定时显示关闭图片
function showImg(){
	setTimeout('$("#good_img").show("slow")',300);
	setTimeout('$("#good_img").hide("slow")',3000);
};

function onclickRadioAnswer(index,sqId,answerId){
	//选择完禁用单选按钮
	$("input[name='answer_"+sqId+"']").attr("disabled",true);
	
	var qsQuestion_totle = $("#qsQuestion_totle").val();
	$(".bottominf1").hide();
	var isRight = $("#isRight_"+sqId+"_"+answerId).val();
	if("1"==isRight){
		var correct_totle = $("#correct_totle").val();
		$("#correct_totle").val(parseInt(correct_totle)+1);
		if(parseInt(index)<parseInt(qsQuestion_totle)){
			$("#qsQuestion_"+index).hide();
			$("#qsQuestion_answer_"+index).hide();
			$("#qsQuestion_"+(parseInt(index)+1)).show();
			$("#qsQuestion_answer_"+(parseInt(index)+1)).show();
			showImg();
		}else if(parseInt(index)==parseInt(qsQuestion_totle)){
			var today=new Date();
			var year=today.getFullYear();
		    var month=today.getMonth()+1;
		    var date=today.getDate();
		  	var h=today.getHours();
		    var m=today.getMinutes();
		    var s=today.getSeconds();
		    $("#endTime").val(year+"-"+month+"-"+date+" "+h+":"+m+":"+s);
		    $("#totalTime").val($("#xxxxxx").html());
			//进入完成界面
			document.getElementById("Question_form").action = "${global_url}/qsQuestionnaire/subQSQuestion.htm";
			document.getElementById("Question_form").submit();
		}
	}else{
		if(parseInt(index)<parseInt(qsQuestion_totle)){
			var answer = $("#answer_"+sqId).val();
			$(".bottominf1").html("<div class='daan_wrong'><p class='ts_error'>回答错误，正确答案为：</p><p>"+answer+"</p></div><button class='check-order' id='orders' onclick='nextQuestion("+index+")'>下一题</button>");
			$(".bottominf1").show();
		}else if(parseInt(index)==parseInt(qsQuestion_totle)){
			var answer = $("#answer_"+sqId).val();
			$(".bottominf1").html("<div class='daan_wrong'><p class='ts_error'>回答错误，正确答案为：</p><p>"+answer+"</p></div><button class='check-order' id='orders' onclick='subQuestion()'>提交问题</button>");
			$(".bottominf1").show();
		}
	}
}
function nextQuestion(index){
	$(".bottominf1").hide();
	$("#qsQuestion_"+index).hide();
	$("#qsQuestion_answer_"+index).hide();
	$("#qsQuestion_"+(parseInt(index)+1)).show();
	$("#qsQuestion_answer_"+(parseInt(index)+1)).show();
}
function subQuestion(){
	var today=new Date();
	var year=today.getFullYear();
    var month=today.getMonth()+1;
    var date=today.getDate();
  	var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();
    $("#endTime").val(year+"-"+month+"-"+date+" "+h+":"+m+":"+s);
    $("#totalTime").val($("#xxxxxx").html());
    document.getElementById("Question_form").action = "${global_url}/qsQuestionnaire/subQSQuestion.htm";
	document.getElementById("Question_form").submit();
}
function nextCheckboxQuestion(index,sqId){
	var answerStr = document.getElementsByName("answer_"+sqId);
    var ss = "";
    for (var i = 0; i < answerStr.length; i++) {
        if (answerStr[i].checked) {
            ss += answerStr[i].value+",";
        }
    }
    if(""==ss){
    	alert("请至少选择一个答案");
    	return false;
    }
  	//选择完禁用复选按钮
	$("input[name='answer_"+sqId+"']").attr("disabled",true);
    var sss = "";
    var answerString = document.getElementsByName("answer_checkbox_"+sqId);
    for (var i = 0; i < answerString.length; i++) {
		sss += answerString[i].value+",";
    }
    var qsQuestion_totle = $("#qsQuestion_totle").val();
    if(ss!=sss){
    	var answerObj = "";
    	for (var ii = 0; ii < answerString.length; ii++) {
    		answerObj += "<p>"+$("#answer_checkbox_value_"+answerString[ii].value).val()+"</p>";
        }
    	//多选错误
    	if(parseInt(index)<parseInt(qsQuestion_totle)){
			$(".bottominf1").html("<div class='daan_wrong'><p class='ts_error'>回答错误，正确答案为：</p>"+answerObj+"</div><button class='check-order' id='orders' onclick='nextQuestion("+index+")'>下一题</button>");
			$(".bottominf1").show();
		}else if(parseInt(index)==parseInt(qsQuestion_totle)){
			$(".bottominf1").html("<div class='daan_wrong'><p class='ts_error'>回答错误，正确答案为：</p>"+answerObj+"</div><button class='check-order' id='orders' onclick='subQuestion()'>提交问题</button>");
			$(".bottominf1").show();
		}
    }else{
    	var correct_totle = $("#correct_totle").val();
		$("#correct_totle").val(parseInt(correct_totle)+1);
    	//多选正确
    	if(parseInt(index)<parseInt(qsQuestion_totle)){
			$("#qsQuestion_"+index).hide();
			$("#qsQuestion_answer_"+index).hide();
			$("#qsQuestion_"+(parseInt(index)+1)).show();
			$("#qsQuestion_answer_"+(parseInt(index)+1)).show();
			showImg();
			return false;
		}else if(parseInt(index)==parseInt(qsQuestion_totle)){
			var today=new Date();
			var year=today.getFullYear();
		    var month=today.getMonth()+1;
		    var date=today.getDate();
		  	var h=today.getHours();
		    var m=today.getMinutes();
		    var s=today.getSeconds();
		    $("#endTime").val(year+"-"+month+"-"+date+" "+h+":"+m+":"+s);
		    $("#totalTime").val($("#xxxxxx").html());
			//进入完成界面
			document.getElementById("Question_form").action = "${global_url}/qsQuestionnaire/subQSQuestion.htm";
			document.getElementById("Question_form").submit();
		}
    }
}
</script>
</html>
