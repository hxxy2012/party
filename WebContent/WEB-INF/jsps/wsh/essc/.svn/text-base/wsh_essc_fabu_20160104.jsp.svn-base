<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>二手货发布</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script src="${global_js_url }/jquery-1.8.3.min.js"></script>  
<script src="${global_js_url }/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">

function essc_submit(){
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
		var title = $("#title").val();
		var price = $("#price").val();
		
	 	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		
		//var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(title == null || title == ""){
			 alert("请输入商品标题");
			return false;
		}
		if(price == null || price == ""){
			 alert("请输入商品价格");
			return false;
		}
		if(name == null || name == ""){
			 alert("请输入联系人");
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
			 alert("请输入商品说明");
			return false;
		}
		
	   // document.getElementById("register_form").action="/Weixin/member/memberRegister.htm";
	    //document.getElementById("register_form").submit();
	    
		document.getElementById("form1").submit();
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
             //alert(returndata);  
         }  
    }); 
}
</script>
</head>
<body>



<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_esh.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;二手货发布</h2>
        <div class="course-content">
        	<p>二手货交易</p>
        	<div class="course-Per">
        		<label>商品图片：</label>
        		<form id= "uploadForm" action="${image_server}servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
		            <input type="file" name="fileField" id="fileField"  />  
		            <input data-role="none" type="button" value="上传" onclick="uploadThumbnail()" /> 
		        </form>
        		<!-- <input type="file" name="imagePath" class="tianxie"> -->
        	</div>
        </div>
	</div>
</div>

<form id="form1" action="${global_url}/wsh/essc/subESSC.htm" method="post">
<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
<input type="hidden" name="memberName" id="memberName" value="${memberMap.name}" />
<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
<input type="hidden" name="imagepaths" id="imagepaths"/>
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;发布信息</h5>
		</div>	
	</section>
    <div class="course-content" style="padding:0 10px;">
    <div class="course-Per"><label>商品标题：</label><input type="text" name="title" id="title"  placeholder="请输入商品标题"  class="tianxie"></div>
    <div class="course-Per"><label>商品价格：</label><input type="text" name="price" id="price"  placeholder="请输入商品价格"  class="tianxie">元</div>
    <div class="course-Per"><label>联系人：</label><input type="text" name="name" id="name"  placeholder="请输入联系姓名"  class="tianxie"></div>
    <div class="course-Per"><label>电话：</label><input type="text" name="phone" id="phone"  placeholder="请输入联系电话"  class="tianxie"></div>
    <div class="course-Per"><label>联系地址：</label><input type="text" name="address" id="address"  placeholder="请输入联系地址"  class="tianxie"></div>
    <div class="course-Per"><label style="position: relative; top: -30px;">说明：</label><textarea name="content" id="content" class="tianxie1" ></textarea></div>
    <!-- <div class="course-Per"><label>上传图片：</label>
    	<input type="file" name="imagePath" id="imagePath"  class="tianxie">
    </div> -->
   
  </div>
</div>

 <div style="float:left; width:100%;height:71px;"></div>

<!-- 底部按钮 -->
<div class="bottominf1">
  <button type="button" class="check-order " id="orders" onclick="essc_submit();">发布商品</button>
</div>
</form>

</body>
</html>

