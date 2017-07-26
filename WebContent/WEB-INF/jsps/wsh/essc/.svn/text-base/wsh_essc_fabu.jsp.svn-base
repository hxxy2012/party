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
<link rel="stylesheet" href="http://demo.open.weixin.qq.com/jssdk/css/style.css?ts=1420774989">
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
    	<h2><img src="${global_image_url}/ico_esh.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;二手货发布</h2>
        <div class="course-content">
        	<p>二手货交易</p>
        	<div class="course-Per">
        		<!-- <label>商品图片：</label> -->
       		 	<img src="" height="120px;" width="150px;" id="targetPic" style="display:none;">
        		
        		<%-- <form id= "uploadForm" action="${image_server}servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
		            <input type="file" name="fileField" id="fileField"  />  
		            <input data-role="none" type="button" value="上传" onclick="uploadThumbnail()" /> 
		        </form> --%>
        		<!-- <input type="file" name="imagePath" class="tianxie"> -->
        		
        	<%-- 	<c:if test="${not empty memberMap.image}">
					<img src="${memberMap.image}" height="50" width="50" id="targetPic">
				</c:if>
				<c:if test="${empty memberMap.image}">
					<img src="${global_image_url}/toux_nan.jpg" height="50" width="50" id="targetPic">
				</c:if> --%>
				<!-- <h3 id="menu-image">选取图片</h3>
		      	<span class="desc">拍照或从手机相册中选取图片</span>
		      	<button class="btn btn_primary" id="chooseImage">选取图片</button>
		      	<span class="desc" style="display:none;">上传图片至微信服务器</span> -->
		      	<button class="sc_but" data-role="none" id="chooseImage"></button> <br>
		      	<button class="btn btn_primary" id="uploadImage" style="display:none;">上传图片</button>
		      	<!-- <span class="desc">上传图片</span>
		      	<img alt="abc" id="qianming" src="" width="150px;" height="120px;" style="display:none;">
		      	<button class="btn btn_primary" id="downloadImage">上传</button> -->
		    	<%--  <form id= "uploadForm" action="${image_server}servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
		            <input type="file" name="fileField" id="fileField"  />  
		            <input data-role="none" type="button" value="上传" onclick="uploadThumbnail()" /> 
		        </form> --%>
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
			<h5><img src="${global_image_url}/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;发布信息</h5>
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
  <button type="button" class="check-order " id="orders" data-role="none" onclick="essc_submit();">发布商品</button>
</div>
</form>

</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">
//获取access_token:https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx535d7cbd1928fd83&secret=91d960558a6cf8bc07a80f036a779aa9
//var access_token="rxJEZWzdNObkzg0eHdRVGVBhFGVvstG7Ja2ALfQEOuDDBokRudbRjH2RKoKrIdaAhmd3QhBjqJoa6Fkx3X99CF9rYIllayZLPrPQvI2kOyEMVHbACATCO";
//获取ticket：https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=rxJEZWzdNObkzg0eHdRVGVBhFGVvstG7Ja2ALfQEOuDDBokRudbRjH2RKoKrIdaAhmd3QhBjqJoa6Fkx3X99CF9rYIllayZLPrPQvI2kOyEMVHbACATCO&type=jsapi
//var ticket="kgt8ON7yVITDhtdwci0qeZGxYUKahgp7Saz0crSYTC9FamTi9vt2iAjl3g_56MhRzfgrcXMycv5R9DynGgC94g";
//var jsapi_ticket="kgt8ON7yVITDhtdwci0qeZGxYUKahgp7Saz0crSYTC9TYDZbU4p2A146juy5YXOumCyfabYCi4mRKfXiiouEcw";
//var string1 = "jsapi_ticket=kgt8ON7yVITDhtdwci0qeZGxYUKahgp7Saz0crSYTC816oJxBezVSHJZurBa7zeS-4aha4vbFFLyzadEUD623g&noncestr=Zhi2015Hui11She13Qu&timestamp=1420774989&url=http://weixin.gangwaninfo.com/Weixin/member/initWeixinDemo.htm";
//生成签名页面http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign
//jsapi_ticket=kgt8ON7yVITDhtdwci0qeZGxYUKahgp7Saz0crSYTC9TYDZbU4p2A146juy5YXOumCyfabYCi4mRKfXiiouEcw&noncestr=Zhi2015Hui11She13Qu&timestamp=1420774989&url=http://weixin.gangwaninfo.com/Weixin/member/initWeixinDemo.htm
var signature = "${signature}";
var access_token = "${access_token}";
wx.config({
      debug: false,
      appId: '${appId}',
      timestamp: "1420774989",
      nonceStr: 'Zhi2015Hui11She13Qu',
      signature: signature,
      jsApiList: [
        'chooseImage',
        'uploadImage'
       // 'downloadImage'
      ]
  });
</script>
<script src="http://demo.open.weixin.qq.com/jssdk/js/api-6.1.js?ts=1420774989"> </script>
<script type="text/javascript">
wx.ready(function () {
	// 5 图片接口
	// 5.1 拍照、本地选图
	var images = {
		localId: [],
    	serverId: []
	};
	document.querySelector('#chooseImage').onclick = function () {
		wx.chooseImage({
      		success: function (res) {
        		images.localId = res.localIds;
        		var imageLocalId =  res.localIds[0];
        		$('#targetPic').attr('src',imageLocalId).show();
        		//alert('已选择 ' + res.localIds.length + ' 张图片');
        		document.querySelector('#uploadImage').onclick();
      		}
		});
	};

	// 5.3 上传图片
	document.querySelector('#uploadImage').onclick = function () {
	    if (images.localId.length == 0) {
      		alert('请先选取图片');
      		return;
    	}
    	images.serverId = [];
    	function upload() {
      		wx.uploadImage({
        		localId: images.localId[0],
        		success: function (res) {
          			//alert('已上传：' + i + '/' + length);
          			images.serverId.push(res.serverId);
          			//下载上传到微信服务器的图片
          			download(res.serverId);
          			//禁用选择图片
          			//$("#chooseImage").attr("disabled","true"); 
        		},
        		fail: function (res) {
          			alert(JSON.stringify(res));
        		}
      		});
		}
		upload();
	};

	var shareData = {
		title: '微信JS-SDK DEMO',
    	desc: '微信JS-SDK,帮助第三方为用户提供更优质的移动web服务',
    	link: 'http://www.cnblogs.com/txw1958/',
    	imgUrl: 'http://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRt8Qia4lv7k3M9J1SKqKCImxJCt7j9rHYicKDI45jRPBxdzdyREWnk0ia0N5TMnMfth7SdxtzMvVgXg/0'
	};
	wx.onMenuShareAppMessage(shareData);
	wx.onMenuShareTimeline(shareData);
});

wx.error(function (res) {
	alert(res.errMsg);
});

function download(media_id) {
	//获取微信服务器图片地址
	var result = $.ajax({
	url : "${global_url}/member/downloadImage.htm?media_id="+media_id+"&access_token="+access_token,
	async : false,
	cache : false
	}).responseText;
	
	$("#qianming").attr("src",result).show();; 
	//下载微信服务器图片至外网服务器
	//$("#fileField").val(result);
	$.ajax({
         url: 'http://weixin.gangwaninfo.com/fileServer/servlet/UploadWeixinImgServelt?imgUrl='+result+"&media_id="+media_id,  
         type: 'POST',
         data: {},  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {
        	 //图片上传成功，调用更换头像
        	 $("#imagepaths").val(returndata);
        	 
           /*   document.getElementById("uploadImgForm").actoin="${global_url}/wsh/essc/subESSC.htm";
             document.getElementById("uploadImgForm").submit(); */
         },  
         error: function (returndata) {
             //alert(returndata);  
         }  
    });
}
</script>
</html>

