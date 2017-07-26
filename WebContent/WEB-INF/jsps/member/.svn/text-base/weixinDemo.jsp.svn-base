<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>更换会员头像</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
<!-- <link rel="stylesheet" href="http://demo.open.weixin.qq.com/jssdk/css/style.css?ts=1420774989"> -->
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${global_js_url }/json2.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script src="${global_js_url }/jquery-1.8.3.min.js"></script>  
<script src="${global_js_url }/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
function submit(){
		document.getElementById("uploadImgForm").submit();
}

</script>
</head>
<body>
<div class="wxapi_container">
    <div class="lbox_close wxapi_form" style="margin-top: 0px;padding-top: 10px;">

		<c:if test="${not empty memberMap.image}">
			<img src="${memberMap.image}" height="50" width="50" id="targetPic">
		</c:if>
		
		<c:if test="${empty memberMap.image}">
			<c:choose>
				<c:when test="${not empty memberMap.gender && memberMap.gender=='1'}">
					<img src="${global_image_url}/toux_nv.jpg" height="50" width="50" id="targetPic">
				</c:when>
				<c:otherwise>
					<img src="${global_image_url}/toux_nan.jpg" height="50" width="50" id="targetPic">
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
			<div class="course-header" style="border-bottom:none">
		        <div class="course-content" style="padding-top:10px">
		        	<div class="szwz" id="mapholder"></div>
					<div class="target_img">
		        		<button class="sc_but" data-role="none" id="chooseImage"></button> 
		        	</div>
		 	      	<button class="btn btn_primary" id="uploadImage" style="display:none;">上传图片</button>
		        </div>
			</div>
		</div>
		
	   	<form id="uploadImgForm" action="${global_url}/member/modifyPatientHead.htm" method="post">  
        	<input type="hidden" name="thumbnailpaths" id="thumbnailpaths" /> 
        	
        	<div style="float:left; width:100%;height:71px;"></div>
			<!-- 底部按钮 -->
			<div class="bottominf1">
				<button type="button" class="check-order " id="orders" data-role="none" onclick="submit();">提交</button>
			</div>
    	</form>
    </div>
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

	// 5.4 下载图片
/* 	document.querySelector('#downloadImage').onclick = function () {
		//获取微信服务器图片ID
		var media_id = images.serverId[0];
		function download() {
			//获取微信服务器图片地址
			var result = $.ajax({
			url : "${global_url}/member/downloadImage.htm?media_id="+media_id+"&access_token="+access_token,
			async : false,
			cache : false
			}).responseText;
			
			//下载微信服务器图片至外网服务器
			$("#fileField").val(result);
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
		        	 $("#thumbnailpaths").val(returndata);
		             document.getElementById("uploadImgForm").actoin="${global_url}/member/modifyPatientHead.htm";
		             document.getElementById("uploadImgForm").submit();
		         },  
		         error: function (returndata) {
		             alert(returndata);  
		         }  
		    });
		}
		download();
	}; */
	
	
	//获取微信服务器图片ID
	function download(media_id) {
		//var media_id = images.serverId[0];
		if(media_id!=null && media_id!=""){
			//获取微信服务器图片地址
			var result = $.ajax({
			url : "${global_url}/member/downloadImage.htm?media_id="+media_id+"&access_token="+access_token,
			async : false,
			cache : false
			}).responseText;
			//$('#downloadPic'+(i)).attr('src',result).show();
			
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
		        	 $("#thumbnailpaths").val(returndata);
		            /*  document.getElementById("uploadImgForm").actoin="${global_url}/member/modifyPatientHead.htm";
		             document.getElementById("uploadImgForm").submit(); */
		         },  
		         error: function (returndata) {
		            // alert(returndata);  
		         }  
		    });
		}
	}	
	

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
</script>
</html>