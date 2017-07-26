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
<link rel="stylesheet" href="http://demo.open.weixin.qq.com/jssdk/css/style.css?ts=1420774989">
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
    	<h2><img src="${global_image_url }/ico_rztj.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;认证描述</h2>
        <div class="course-content">
        	<p>认证当前社区的商铺</p>
        	<div class="course-Per">
        		<!-- <label>店铺图片：</label> -->
        	<%-- 	<form id= "uploadForm" action="${image_server}servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
		            <input type="file" name="fileField" id="fileField"  />  
		            <input data-role="none" type="button" value="上传" onclick="uploadThumbnail()" /> 
		        </form> --%>
		       <!--  <img src="" height="120px;" width="150px;" id="targetPic" style="display:none;"> -->
		        
		      <!--   <h3 id="menu-image">选取图片</h3>
		      	<span class="desc">拍照或从手机相册中选取图片</span>
		      	<button class="btn btn_primary" id="chooseImage">选取图片</button>
		      	<span class="desc" style="display:none;">上传图片至微信服务器</span> -->
		      <!-- 	<button class="sc_but" data-role="none" id="chooseImage"></button> <br> -->
		      	
	      	<div class="target_img">
				
        		<button class="sc_but" data-role="none" id="chooseImage"></button> 
	        	<img src="" height="50" width="50" id="targetPic1" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic2" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic3" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic4" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic5" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic6" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic7" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic8" style="display:none;">
	        	<img src="" height="50" width="50" id="targetPic9" style="display:none;">
        	
        	
<!-- 	      	<span class="desc">拍照或从手机相册中选取图片</span> -->
<!-- 	      	<button class="btn btn_primary" id="chooseImage"></button> -->
		      	<img src="" height="50" width="50" id="downloadPic1" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic2" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic3" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic4" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic6" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic7" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic8" style="display:none;">
	        	<img src="" height="50" width="50" id="downloadPic9" style="display:none;">
        	</div>
		      	
		      	
		      	<button class="btn btn_primary" id="uploadImage" style="display:none;">上传图片</button>
		      <!-- 	<span class="desc">上传图片</span>
		      	<img alt="abc" id="qianming" src="" width="150px;" height="120px;" style="display:none;">
		      	<button class="btn btn_primary" id="downloadImage">上传</button> -->
        		<!-- <input type="file" name="imagePath" class="tianxie"> -->
        	</div>
        </div>
	</div>
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url }/ico_bd.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;认证信息</h5>
		</div>	
	</section>
	<form id="subRztjForm" action="${global_url}/wsh/rztj/subRZTJ.htm" method="post">
		<input type="hidden" name="memberId" id="memberId" value="${memberMap.id}" />
		<input type="hidden" name="memberName" id="memberName" value="${memberMap.name}" />
		<input type="hidden" name="shequId" id="shequId" value="${shequId}" />
		<input type="hidden" name="imagepaths" id="imagepaths"/>
		<input type="hidden" name="rztjImg1" id="rztjImg1"/>
		<input type="hidden" name="rztjImg2" id="rztjImg2"/>
		<input type="hidden" name="rztjImg3" id="rztjImg3"/>
		<input type="hidden" name="rztjImg4" id="rztjImg4"/>
		<input type="hidden" name="rztjImg5" id="rztjImg5"/>
		<input type="hidden" name="rztjImg6" id="rztjImg6"/>
		<input type="hidden" name="rztjImg7" id="rztjImg7"/>
		<input type="hidden" name="rztjImg8" id="rztjImg8"/>
		<input type="hidden" name="rztjImg9" id="rztjImg9"/>
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
        		/* images.localId = res.localIds;
        		var imageLocalId =  res.localIds[0];
        		$('#targetPic').attr('src',imageLocalId).show();
        		//alert('已选择 ' + res.localIds.length + ' 张图片');
        		document.querySelector('#uploadImage').onclick(); */
        		
        		images.localId = res.localIds;
        		var imgLength = res.localIds.length;
        		if(imgLength>0){
        			for(var i=0;i<imgLength;i++){
        				var imageLocalId =  res.localIds[i];
                		$('#targetPic'+(i+1)).attr('src',imageLocalId).show();
        			}
        		}
        		document.querySelector('#uploadImage').onclick();
      		}
		});
	};

	// 5.3 上传图片
	document.querySelector('#uploadImage').onclick = function () {
		var i = 0, length = images.localId.length;
		if (length == 0) {
      		alert('请先选取图片');
      		return;
    	}
		
	   /*  if (images.localId.length == 0) {
      		alert('请先选取图片');
      		return;
    	} */
    	images.serverId = [];
    	function upload() {
      		wx.uploadImage({
        		/* localId: images.localId[0],
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
        		} */
        		
        		localId: images.localId[i],
        		success: function (res) {
        			i++;
          			//alert('已上传：' + i + '/' + length+"==="+res.serverId);
          			images.serverId.push(res.serverId);
          			//下载上传到微信服务器的图片
          			download(i,res.serverId);
          			if (i<length) {
          	            upload();
          	        }
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
	//alert(res.errMsg);
});


function download(i,serverId) {
	if(serverId!=null && serverId!=""){
		//获取微信服务器图片地址
		var result = $.ajax({
		url : "${global_url}/member/downloadImage.htm?media_id="+serverId+"&access_token="+access_token,
		async : false,
		cache : false
		}).responseText;
		//$('#downloadPic'+(i)).attr('src',result).show();
		
		$.ajax({
	         url: 'http://weixin.gangwaninfo.com/fileServer/servlet/UploadWeixinImgServelt?imgUrl='+result+"&media_id="+serverId,  
	         type: 'POST',
	         data: {},  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (returndata) {
	        	 //图片上传成功，保存到input中
	        	 $("#rztjImg"+(i)).val(returndata);
	        	 //alert("returndata=="+returndata);
	         },  
	         error: function (returndata) {
	             //alert(returndata);  
	         }  
	    });
	}
}
</script>
</html>

