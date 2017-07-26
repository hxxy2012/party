<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<title>意见反馈</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${global_js_url }/json2.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script src="${global_js_url }/jquery-1.8.3.min.js"></script>  
<script src="${global_js_url }/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	getLocation();
});
function jbts_submit(){
	var memberId = $("#memberId").val();
	if(memberId == null || memberId == ""){
		var temp = confirm("您还未登录，请先去登录");
		if(temp){
			window.location.href="${global_url}/member/exit.htm";
		}
	}else{
		var name = $("#name").val();
	    var phone = $("#phone").val();
	    var content = $("#content").val();
		var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		
		if(name == null || name == ""){
			 alert("请输入姓名");
			return false;
		}
		if(phone == null || phone == ""){
			 alert("请输入电话");
			return false;
		}else{
			if(!isPhone.test(phone)&&!isMob.test(phone)){ 
				alert("请输入正确的固话或者手机号");
				return false;
			}
		}
		if(content == null || content == ""){
			 alert("请输入内容");
			return false;
		}
		document.getElementById("form1").submit();
	}
}
function getLocation(){
	var x=document.getElementById("mapholder");
	if (navigator.geolocation){
		navigator.geolocation.getCurrentPosition(showPosition);
	}else{
		x.innerHTML="Geolocation is not supported by this browser.";
	}
}
function showPosition(position){
	//var x=document.getElementById("demo");
	//x.innerHTML="纬度: " + position.coords.latitude + "<br />经度: " + position.coords.longitude;
	var latitude=position.coords.latitude;
	var longitude=position.coords.longitude;
	$.ajax({
		type:"POST",
		url : "${global_url}/member/getAddress.htm",
		data:{latitude:latitude,longitude:longitude},
		async:false,
		dataType:"json",
		success:function(json) {
			var x=document.getElementById("mapholder");
			x.innerHTML="所在位置："+json.result.formatted_address+json.result.sematic_description;
			$("#address").val(json.result.formatted_address+json.result.sematic_description);
		},
        error: function (returndata) {
            alert("error="+returndata);  
		}
	});
}
function uploadThumbnail(){
	var formData = new FormData($( "#uploadForm" )[0]);  
	$.ajax({  
         url: '${image_server }servlet/UploadServelt' ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {
        	 //图片上传成功，调用更换头像
             $("#imagepaths").val(returndata.saveDirectory);
         },  
         error: function (returndata) {
             //alert(returndata);  
         }  
    }); 
}
</script>
</head>
<body>
<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_jbts.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;意见反馈</h2>
        <div class="course-content">
        	<div id="mapholder"></div>
        	<form id= "uploadForm" action="${image_server}servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
	            <input type="file" name="fileField" id="fileField"  />  
	            <input data-role="none" type="button" value="上传" onclick="uploadThumbnail()" /> 
	        </form>
        </div>
	</div>
</div>
<form id="form1" action="${global_url}/wfw/jbts/subJBTS.htm" method="post">
<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
<input type="hidden" name="memberName" id="memberName" value="${memberMap.name}" />
<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
<input type="hidden" name="address" id="address" />
<input type="hidden" name="imagepaths" id="imagepaths"/>
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;意见反馈</h5>
		</div>	
	</section>
    <div class="course-content" style="padding:0 10px;">
    <div class="course-Per"><label>姓名：</label><input type="text" name="name" id="name" placeholder="请输入您的姓名" value="${memberMap.name}" class="tianxie"></div>
    <div class="course-Per"><label>电话：</label><input type="text" name="phone" id="phone" placeholder="请输入您的联系电话" ${memberMap.phone} class="tianxie"></div>
    <div class="course-Per"><label style="position: relative; top: -30px;">内容：</label><textarea class="tianxie1" name="content" id="content"></textarea></div>
   
  </div>
</div>

 <div style="float:left; width:100%;height:71px;"></div>

<!-- 底部按钮 -->
<div class="bottominf1">
  <button type="button" class="check-order " id="orders" data-role="none" onclick="jbts_submit();">提交</button>
</div>
</form>

</body>
</html>

