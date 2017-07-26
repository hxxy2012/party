<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>处理意见反馈</title>
<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
</style>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url}/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${global_js_url }/json2.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script src="${global_js_url }/jquery-1.8.3.min.js"></script>  
<script src="${global_js_url }/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	//getLocation();
});
function yjfk_submit(){
	var staffId = $("#staffId").val();
	if(staffId == null || staffId == ""){
		var temp = confirm("您还未登录，请先去登录");
		if(temp){
			window.location.href="${global_url}/member/wgy/wgyLogin.htm";
		}
	}else{
		var resolve = $("input[name='resolve']:checked").val() ;
		
	/*     var phone = $("#phone").val();
	    var content = $("#content").val();
		var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		 */
		if(resolve == null || resolve == ""){
			 alert("请选择处理状态");
			return false;
		}
		/* if(phone == null || phone == ""){
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
		} */
		document.getElementById("form1").submit();
	}
}

/* function getLocation(){
	var x=document.getElementById("mapholder");
	if (navigator.geolocation){
		navigator.geolocation.getCurrentPosition(showPosition);
	}else{
		x.innerHTML="Geolocation is not supported by this browser.";
	}
}
function showPosition(position){
	var latitude=position.coords.latitude;
	var longitude=position.coords.longitude;
	$("#latitude").val(latitude);
	$("#longitude").val(longitude);
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
} */

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


<section class="module m_b10">
    <div class="titlebar">
        <h5><img src="${global_image_url}/ico_jbts.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;意见反馈</h5>
    </div>	
</section>

<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
        <div class="course-content" style="padding-top:10px">
        	<div class="szwz" id="mapholder"></div>
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
        </div>
	</div>
</div>

<form id="form1" action="${global_url}/member/wgy/subYJFK.htm" method="post">
		<input type="hidden" name="staffId" id="staffId" value="${memberWgyMap.staffId}" />
		<input type="hidden" name="staffName" id="staffName" value="${memberWgyMap.staffName}" />
		<input type="hidden" name="shequId" id="shequId" value="${memberWgyMap.shequId}" />
		<input type="hidden" name="jbtsId" id="jbtsId" value="${yjfkInfoMap.jbtsId}" />
	<!-- 	<input type="hidden" name="address" id="address" />
		<input type="hidden" name="latitude" id="latitude" />
		<input type="hidden" name="longitude" id="longitude" /> -->
		<input type="hidden" name="imagepaths" id="imagepaths"/>
		<input type="hidden" name="jbtsImg1" id="jbtsImg1"/>
		<input type="hidden" name="jbtsImg2" id="jbtsImg2"/>
		<input type="hidden" name="jbtsImg3" id="jbtsImg3"/>
		<input type="hidden" name="jbtsImg4" id="jbtsImg4"/>
		<input type="hidden" name="jbtsImg5" id="jbtsImg5"/>
		<input type="hidden" name="jbtsImg6" id="jbtsImg6"/>
		<input type="hidden" name="jbtsImg7" id="jbtsImg7"/>
		<input type="hidden" name="jbtsImg8" id="jbtsImg8"/>
		<input type="hidden" name="jbtsImg9" id="jbtsImg9"/>
		<div class="container">
		    <div class="course-content" style="padding:0 10px;">
		   	<div class="course-Per">
		        <label><input type="radio" name="resolve" id="resolve" value="1">已处理</label>
		        <label><input type="radio" name="resolve" id="resolve" value="2">无需处理</label>
		    </div>
		    <div class="course-Per"><textarea placeholder="补充处理说明" class="tianxie1 w_100" name="solution" id="solution"></textarea></div>
		  </div>
		</div><!--基本信息结束-->
			
			
		<!-- 底部按钮 -->
		<div style="float:left; width:100%;height:71px;"></div>
		<div class="bottominf1">
		    <button type="button" class="check-order " id="orders" data-role="none" onclick="yjfk_submit();">提交</button>
		</div>
</form>


</body>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">
var signature = "${signature}";
var access_token = "${access_token}";
wx.config({
      debug: false,
      appId: '${appid}',
      timestamp: "1420774989",
      nonceStr: 'Zhi2015Hui11She13Qu',
      signature: signature,
      jsApiList: [
        'chooseImage',
        'uploadImage'
        //'downloadImage'
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
    	images.serverId = [];
    	function upload() {
      		wx.uploadImage({
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

//获取微信服务器图片ID
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
	        	 $("#jbtsImg"+(i)).val(returndata);
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
