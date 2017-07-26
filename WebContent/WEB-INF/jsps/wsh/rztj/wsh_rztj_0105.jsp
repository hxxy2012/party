<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>商家入驻</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script src="${global_js_url }/jquery-1.8.3.min.js"></script>  
<script src="${global_js_url }/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
function rztj_submit(){
	var memberId = $("#memberId").val();
	if(memberId == null || memberId == ""){
		var temp = confirm("您还未登录，请先去登录");
		if(temp){
			window.location.href="${global_url}/member/exit.htm";
		}
	}else{
		var title = $("#title").val();//店铺名称
		var serviceType = $("#serviceType").val();//店铺服务
		var name = $("#name").val();//联系人
	    var phone = $("#phone").val();//联系人电话
	    var content = $("#content").val();//店铺经营
		var price = $("#price").val();//费用标准
		if(title == null || title == ""){
			 alert("店铺名称不能为空");
			return false;
		}
		if(serviceType == null || serviceType == ""){
			 alert("请选择店铺服务");
			return false;
		}
		if(name == null || name == ""){
			 alert("联系人不能为空");
			return false;
		}
		if(phone == null || phone == ""){
			 alert("联系人电话不能为空");
			return false;
		}
		if(price == null || price == ""){
			 alert("费用标准不能为空");
			return false;
		}
		if(content == null || content == ""){
			 alert("店铺经营不能为空");
			return false;
		}
		document.getElementById("subRztjForm").actoin="${global_url}/wsh/rztj/subRZTJ.htm";
        document.getElementById("subRztjForm").submit();
	}
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
             alert(returndata);  
         }  
    }); 
}
</script>
</head>
<body>
<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url }/ico_rztj.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;认证描述</h2>
        <div class="course-content">
        	<p>认证当前社区的商铺</p>
        	<div class="course-Per">
        		<label>店铺图片：</label>
        		<form id= "uploadForm" action="${image_server}servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
		            <input type="file" name="fileField" id="fileField"  />  
		            <input data-role="none" type="button" value="上传" onclick="uploadThumbnail()" /> 
		        </form>
        		<!-- <input type="file" name="imagePath" class="tianxie"> -->
        	</div>
        </div>
	</div>
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url }/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;认证信息</h5>
		</div>	
	</section>
	<form id="subRztjForm" action="${global_url}/wsh/rztj/subRZTJ.htm" method="post">
		<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
		<input type="hidden" name="memberName" id="memberName" value="${memberMap.name}" />
		<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
		<input type="hidden" name="imagepaths" id="imagepaths"/>
	    <div class="course-content" style="padding:0 10px;">
			<div class="course-Per"><label>店铺名称：</label><input type="text" name="title" id="title" placeholder="请输入您发布的店铺"  class="tianxie"></div>
		    <div class="course-Per"><label>店铺服务：</label>
				<select data-role="none" name="serviceType" id="serviceType">
		             <option value="">--请选择--</option>
		             <option value="0">家政服务</option>
		             <option value="1">餐饮服务</option>
		             <option value="2">其他服务</option>
		         </select>
		 	</div>
		 	<div class="course-Per"><label>联系人：</label><input type="text" name="name" id="name" placeholder="请输入您的姓名"  class="tianxie"></div>
		    <div class="course-Per"><label>联系人电话：</label><input type="text" name="phone" id="phone" placeholder="请输入您的电话"  class="tianxie"></div>
		   	<div class="course-Per"><label>服务特色：</label><input type="text" name="price" id="price" placeholder="请输入您的费用标准"  class="tianxie"></div>
		    <div class="course-Per"><label>详细地址：</label><input type="text" name="address" id="address" placeholder="请输入详细地址"  class="tianxie"></div>
		    <div class="course-Per">
		    	<label style="position: relative; top: -30px;">介绍：</label>
		    	<textarea name="content" id="content" class="tianxie1" ></textarea>
		    </div>
		    <!-- <div class="course-Per"><label>上传图片：</label><input type="file" name="imagePath" class="tianxie"></div> -->
		</div>
	</form>
</div>

<div style="float:left; width:100%;height:71px;"></div>

<!-- 底部按钮 -->
<div class="bottominf1">
	<button data-role="none" type="button" class="check-order" id="orders" onclick="rztj_submit();">提交认证</button>
</div>


</body>
</html>

