<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<title>完成</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script>


function showGoodImg(){
	setTimeout('$("#good_img").show("slow")',300);
	setTimeout('$("#good_img").hide("slow")',3000);
};
function showMidImg(){
	setTimeout('$("#mid_img").show("slow")',300);
	setTimeout('$("#mid_img").hide("slow")',3000);
};
function showBadImg(){
	setTimeout('$("#bad_img").show("slow")',300);
	setTimeout('$("#bad_img").hide("slow")',3000);
};


$(document).ready(function(){
	function getFen(){
		var fenshu = $("#fenshu").html();
		if(fenshu>=80){
			showGoodImg();
			}else if( fenshu<80&&fenshu>=60){
				showMidImg();
				}else if(fenshu<60){
					showBadImg();
					}
		}
	getFen();
});

</script>


</head>
<body>
<style>
	.good_img{ position:fixed;  z-index:99;     width: 100%; top: 25%;}
	.good_img img{ width:100%;}
</style>

<div id="good_img" class="good_img" style="display: none">
	<img src="${global_image_url}/good1.gif" />
</div>
<div id="mid_img" class="good_img" style="display: none">
	<img src="${global_image_url}/mid.gif" />
</div>
<div id="bad_img" class="good_img" style="display: none">
	<img src="${global_image_url}/bad.gif" />
</div>
<div class="Courseinfor">
	<div class="successful"><i><img src="${global_image_url}/successful.png" style="width:50px;" ></i>问卷回答完毕！</div>
    <div class="wj_wc">
		<span class="wj_orger">${qsQuestMap.name}</span>于
		<span class="wj_green" >${qsQuestMap.startTime}</span> 至
		<span class="wj_green">${qsQuestMap.endTime}</span> 完成
		<span class="wj_orger"> “两学一做”问卷调查</span>，总用时
		<span class="wj_red">${qsQuestMap.totalTime}</span>，获得
		<span class="wj_red" id="fenshu">${memberQuestionMap.grade}</span>分。
		<!-- ，当前排名第<span class="wj_red">400</span> 位。 -->
	</div>
	
	<div class="wj_ckphb">
		<a href="${global_url}/qsQuestionnaire/qsQuestionRankList.htm?qsId=${qsId}">查看排行榜</a>
	</div>
	
	<a class="fh_btn" href="${global_url}/maqun/baseMemberInfo.htm">进入个人中心</a>
</div>
</body>
</html>
