<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>在线预约</title>
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script src="${global_js_url}/mobiscroll/mobiscroll_002.js" type="text/javascript"></script>
<script src="${global_js_url}/mobiscroll/mobiscroll_004.js" type="text/javascript"></script>
<script src="${global_js_url}/mobiscroll/mobiscroll_003.js" type="text/javascript"></script>
<script src="${global_js_url}/mobiscroll/mobiscroll.js" type="text/javascript"></script>
<script src="${global_js_url}/mobiscroll/mobiscroll_005.js" type="text/javascript"></script>
<link href="${global_css_url }/mobiscroll/mobiscroll.css" rel="stylesheet" type="text/css">
<link href="${global_css_url }/mobiscroll/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="${global_css_url }/mobiscroll/mobiscroll_003.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${xishan_css_url }/css.css" />
</head>
<body>
<form id="form_yuyue" action="${global_url}/xishan/yuyue/yuyuesuccess.htm" method="post">
	<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
	<input type="hidden" name="streetId" id="streetId" value="${streetId}" />
	<div class="slider">
		<ul class="slide-banner">
			<li><img src="${global_image_url}/banner_yybs.jpg"></li>
		</ul>
	</div>
	<div class="container">
		<section class="module">
			<div class="titlebar">
				<h5>
					<img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative; top: 5px;" />&nbsp;预约信息
				</h5>
			</div>
		</section>
		<div class="course-content" style="padding: 0 10px; background: #fff;">
			<div class="course-Per">
				<label>预约单位：</label>
				<input id="companyName" name="companyName" type="text" placeholder="请输入单位名称" class="tianxie">
			</div>
			<div class="course-Per">
				<label>联系人：</label>
				<input id="name" name="name" type="text" placeholder="请输入您的姓名" class="tianxie">
			</div>
			<div class="course-Per">
				<label>预约事项：</label> <select name="action" id="action">
					<option value="0">请选择</option>
					<option value="施工许可">施工许可</option>
					<option value="道口开设">道口开设</option>
				</select>
			</div>
			<div class="course-Per">
				<label>预约日期：</label>
				<select name="yuyueDate" id="yuyueDate">
				<option value="0">请选择</option>
				<c:if test="${not empty dayMapList}">
					<c:forEach var="dayMap" items="${dayMapList}">
						<option value="${dayMap.day}">${dayMap.day}</option>
					</c:forEach>
				</c:if>
				</select>
				<!-- <input class="tianxie" readonly="readonly" name="yuyueDate" id="yuyueDate" placeholder="请选择日期" type="text" onclick="getDate();"> -->
			</div>
			<div class="course-Per">
				<label>预约时段：</label> <select name="yuyueTime" id="yuyueTime">
					<option value="0">请选择</option>
					<option value="09:00-11:00">09:00-11:00</option>
					<option value="13:00-17:00">13:00-17:00</option>
				</select>
			</div>
			<div class="course-Per">
				<label>身份证号：</label>
				<input id="idcard" name="idcard" type="text" placeholder="请输入您的身份证号" class="tianxie">
			</div>
			<div class="course-Per">
				<label>手机：</label><input id="phone" name="phone" type="text" placeholder="请输入您的联系手机" class="tianxie"> 
				<a id="btn" name="btn" type="button" class="yzm" onclick="getTestNumber();">获取验证码</a>
			</div>
			<div class="course-Per">
				<label>验证码：</label>
				<input id="testnumber" name="testnumber" type="text" placeholder="请输入验证码" class="tianxie">
			</div>
		</div>
	</div>
	<div style="float: left; width: 100%; height: 71px;"></div>
	<div class="bottominf1">
		<button type="button" class="check-order " id="orders" onclick="yuyue_submit();">提交</button>
	</div>
</form>
<script type="text/javascript">
function getDate(){
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.yuyueMode = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
        startYear: currYear, //开始年份
        endYear: currYear + 10 //结束年份
	};
  	$("#yuyueDate").mobiscroll($.extend(opt['date'], opt['yuyueMode']));
}
function checkRegMobile(){
	var phone = $("#phone").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/; 
	if(!myreg.test(phone)){ 
		return false;
	}
	return true;
}
//获取6位随机验证码
function random() {
	var num = "";
	for (i = 0; i < 4; i++) {
		num = num + Math.floor(Math.random() * 10);
	}
	return num;
}

function getTestNumber(){
	if (!checkRegMobile()) {
		alert("请输入正确的手机号!");
	} else {
		var count = 60;
		var intervalId;
		clearInterval(intervalId);
		intervalId = setInterval(function() {
			CountDown();
		}, 1000);
		$("#phone").attr("readonly", "readonly");
		$.ajax({
			url : "${global_url}/member/saveYuyueCode.htm",
			type : "POST",
			data : "code=" + random() + "&sendTime="
					+ new Date().getTime() + "&mobile="
					+ $("#phone").val() + "&forType=5",
			dataType : "json",
			success : function(json) {
				if (json == 0) {
					alert("验证码已成功发送，请注意查收！");
				} else {
					alert("验证码发送失败，请稍后再试！");
				}
			}
		});
		function CountDown() {
			$("#btn").attr("disabled", true);
			$("#btn").html(count + " 秒后再获取");
			if (count == 0) {
				$("#phone").removeAttr("readonly");
				$("#btn").html("重新获取验证码").removeAttr(
						"disabled");
				clearInterval(intervalId);
			}
			count--;
		}
	}
}

//验证手机验证码
function checkMobileCode() {
	var temp = $("#testnumber").val();
	if (temp == null || "" == temp) {
		alert("请输入收到的验证码");
		return false;
	} else {
		var result = $.ajax({
			url : "${global_url}/member/checkMobileCode.htm?regCode="
					+ temp,
			async : false,
			cache : false
		}).responseText;
		
		if (result == "0") {
			alert("验证码输入有误");
			return false;
		} else if (result == "2") {
			alert("验证码已失效,请重新发送");
			return false;
		} else {
			$("#testnumber").html("");
			return true;
		}
		
	}
}

function yuyue_submit() {
	var name = $("#name").val();
	var phone = $("#phone").val();
	var date = $("#yuyueDate").val();
	var time = $("#yuyueTime").val();
	var action = $("#action").val();
	var idcard = $("#idcard").val();

	if (name == null || name == "") {
		alert("请输入姓名");
		return false;
	}
	if (action == "0") {
		alert("请选择预约事项");
		return false;
	}
	if (date == "0") {
		alert("请选择日期");
		return false;
	}
	if (time == "0") {
		alert("请选择时段");
		return false;
	}

	var isPhone = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/; 
	var isMob = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	var isIdCard=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
	
	if(idcard == null || idcard == ""){
		alert("请输入身份证号码");
		return false;
	}else{
		if(!isIdCard.test(idcard)){
			alert("请输入正确的身份证号码");
			return false;
		}
	}
	if (phone == null || phone == "") {
		alert("请输入手机号");
		return false;
	} else {
		if (!isPhone.test(phone)) {
			alert("请输入正确的手机号");
			return false;
		}
	}
	if (!checkMobileCode()) {
		return false;
	}
	document.getElementById("form_yuyue").submit();
}
</script>
</body>
</html>
