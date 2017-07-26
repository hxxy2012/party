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
<%-- <script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script> --%>
</head>
<body>



<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_esh.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;二手货发布</h2>
        <div class="course-content">
        	<p>二手货交易须知</p>
        </div>
	</div>
</div>

<form action="${global_url}/wsh/essc/subESSC.htm" method="post">
<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
<input type="hidden" name="memberName" id="memberName" value="${memberMap.name}" />
<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;发布信息</h5>
		</div>	
	</section>
    <div class="course-content" style="padding:0 10px;">
    <div class="course-Per"><label>商品标题：</label><input type="text" name="title" id="title"  placeholder="请输入您发布的商品标题"  class="tianxie"></div>
    <div class="course-Per"><label>商品价格：</label><input type="text" name="price" id="price"  placeholder="请输入您的商品价格"  class="tianxie">元</div>
    <div class="course-Per"><label>联系人：</label><input type="text" name="name" id="name"  placeholder="请输入您的联系姓名"  class="tianxie"></div>
    <div class="course-Per"><label>手机：</label><input type="text" name="phone" id="phone"  placeholder="请输入您的联系手机"  class="tianxie"></div>
    <div class="course-Per"><label>联系地址：</label><input type="text" name="address" id="address"  placeholder="请输入您的联系地址"  class="tianxie"></div>
    <div class="course-Per"><label style="position: relative; top: -30px;">商品说明：</label><textarea name="content" id="content" class="tianxie1" ></textarea></div>
    <div class="course-Per"><label>上传图片：</label>
    	<!-- <input type="file" name="imagePath" id="imagePath"  class="tianxie"> -->
    	
    	<input name="thumbnailpaths" id="thumbnailpaths" type="hidden" />
		<div id="thumbnailShow" style="display: none">
			<ul></ul>
		</div>
		<div style="clear: both;"></div>
		<input type="file" name="uploadifyThumbnail" id="uploadifyThumbnail" />
		<div id="fileQueueThumbnail" style="margin-left: 80px;"></div>
		<div style="margin-left: 80px;">
			<input type="button" onclick="$('#uploadifyThumbnail').uploadify('upload','*')" id="upload_thumbnail" style="display: none; width: 60px;" value="上传" /> 
			<input type="button" onclick="$('#uploadifyThumbnail').uploadify('cancel','*')" style="display: none; width: 60px;" id="cancel_thumbnail" value="取消" />
		</div>
		<div style="margin-left: 80px;">注：支持png,jpg,jpeg等格式，大小限制2M，不允许超过1张图片</div>
    </div>
   
  </div>
</div>

 <div style="float:left; width:100%;height:71px;"></div>

<!-- 底部按钮 -->
<div class="bottominf1">
  <button class="check-order " id="orders">发布商品</button>
</div>
</form>

</body>
<script type="text/javascript">
	var global_js_url = '${global_js_url}';
	var image_server = '${image_server}';
	var imageJson = '';
	var editor = null;
	var videoJson = '';
	var thumbnailJson = '';
	var thePlayer;
	var video;
	
	$LAB
	.script (global_js_url+"uploadify/jquery-1.9.1.js").wait()
	.script (global_js_url+"uploadify/jquery.uploadify.min.js").wait()
	.script (global_js_url+"/ESSC.js").wait(function(){
		ESSC.upload_thumbnail_init();
	});
	
</script>
</html>

