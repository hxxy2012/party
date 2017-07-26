<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>故障报修预约</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript">
function gzbx_submit(){
	var memberId = $("#memberId").val();
	if(memberId == null || memberId == ""){
		var temp = confirm("您还未登录，请先去登录");
		if(temp){
			window.location.href="${global_url}/member/exit.htm";
		}
	}else{
		var name = $("#name").val();
	    var phone = $("#phone").val();
	    var yuyueTime = $("#yuyueTime").val();
	    var content = $("#content").val();
	    var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		//var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		var address = $("#address").val();
		if(name == null || $.trim(name) == ""){
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
		if(address == null || $.trim(address) == ""){
			 alert("请输入详细地址");
			return false;
		}
		if(yuyueTime == null || yuyueTime == "") {
			 alert("请输入预约时间");
			 return false;
		}
		if(content == null || $.trim(content) == ""){
			 alert("请输入情况说明");
			return false;
		}
		//document.getElementById("register_form").action="/Weixin/member/memberRegister.htm";
	    //document.getElementById("register_form").submit();
		document.getElementById("form1").submit();
	}
}
</script>
</head>
<body>
<%-- <div class="Courseinfor" >
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_gzbx.png" width="25px" height="25px;" style="position: relative;top: 6px;;" />&nbsp;马群社区故障报修</h2>
        <div class="course-content">
             <div class="course-row"><label>电话：</label>025-88888888</div>
             <div class="course-row"><label>地址：</label>马群街道马群社区</div>
        </div>
	</div>
</div> --%>

<%-- <div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_js.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;某某社区故障报修预约介绍</h2>
        <div class="course-content">
        	<p>社区可以解决故障报修问题会及时为各位业主解决，如果需要有偿维修也会回单告知。</p>
            <p>预约记录可以在个人中心查询。</p>
        </div>
	</div>
</div> --%>
<form id="form1" action="${global_url}/wfw/gzbx/subGZBX.htm" method="post">
	<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
	<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
	<div class="container">
	<%-- 	<section class="module">
			<div class="titlebar">
				<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;预约表</h5>
			</div>	
		</section> --%>
    	<div class="course-content" style="padding:0 10px;">
	    	<div class="course-Per"><label>姓名：</label><input type="text" id="name" name="name" placeholder="请输入您的姓名" value="${memberMap.name}" class="tianxie"></div>
	   		<div class="course-Per"><label>电话：</label><input type="text" id="phone" name="phone" placeholder="请输入您的联系电话" value="${memberMap.phone}" class="tianxie"></div>
	    	<div class="course-Per"><label>详细地址：</label><input type="text" id="address" name="address" placeholder="请输入您的详细地址"  class="tianxie"></div>      
 		</div>
    	<div class="course-content"><label>预约时间：</label><input type="date" id="yuyueTime" name="yuyueTime" class="tianxie"></div>
     	<div class="course-content"><label style="float: left;">情况说明：</label><textarea id="content" name="content" class="tianxie1"></textarea></div>
	</div>
	<div style="float:left; width:100%;height:71px;"></div>

	<div class="bottominf1">
  		<button type="button" class="check-order" id="orders" onclick="gzbx_submit();">提交</button>
	</div>
</form>  

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>

