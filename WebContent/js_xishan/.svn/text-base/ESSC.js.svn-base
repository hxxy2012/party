var length = 0;
var imageShow="";
var uploadLimit = 4;
var queueLength=0;
var basImagesArray = new Array();
//上传视频相关变量
var videoShow="";
var thumbnailShow="";
var videoLength = 0;
var thumbnailLength = 0;
var uploadVideoLimit = 1;
var uploadThumbnailLimit = 1;
var queueVideoLength=0;
var queueThumbnailLength=0;
var basVideoArray = new Array();
var basThumbnailArray = new Array();



var ESSC ={
		//上传缩略图
		upload_thumbnail_init:function(){
			if(thumbnailJson!="" && thumbnailJson!=null){
				basThumbnailArray = $.parseJSON(thumbnailJson);
			}
			thumbnailLength = basThumbnailArray.length;
			uploadThumbnail();
			if(basThumbnailArray.length==uploadThumbnailLimit){
				$("#fileQueueThumbnail").hide();
				$("#uploadifyThumbnail").hide();
			}
		}
};


function doAdd(){
	var editorCount = editor.text();
	if(editorCount!=null){
		if(editorCount.length>2000){
			alert("无法保存，资讯内容字段已超过2000个字符");
			return;
		}
		$("#clientContent").val(editorCount);
	}
	$(".form_but").hide();
	$(".loadMsg").show();
	adminForm.submit(); 
}


function uploadThumbnail(){
	$("#uploadifyThumbnail").uploadify({
		'uploader' : image_server+'servlet/UploadServelt',
        'swf' : global_js_url+'uploadify/uploadify.swf',
        'cancelImg' : global_js_url+'img/uploadify-cancel.png',
        'folder' : 'uploads',//您想将文件保存到的路径
        'queueID' : 'fileQueueThumbnail',//与下面的id对应
        'queueSizeLimit' : 1,
        'fileTypeDesc' : 'png文件或jpg文件或者jpeg文件',
        'fileTypeExts' : '*.png;*.jpg;*jpeg', //控制可上传文件的扩展名，启用本项时需同时声明fileTypeDesc
        'auto' : false,
        'multi' : false,
        'sizeLimit':1024*2,
        'removeCompleted': true,
        'removeTimeout'  : 0, 
        'uploadLimit' : uploadThumbnailLimit,//定义允许的最大上传数量。
        'buttonText' : '选择文件',
       	'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
	   		var serverData = $.parseJSON(data);
	   		var obj=new Object();
			obj.saveDirectory = serverData.saveDirectory;
			basThumbnailArray.push(obj);
			thumbnailShow +="<li style='float:left'><img id='thumbnail'  src='"+serverData.urlFile+"' style='height: 100px;'/><input type='button'  onClick='delThumbnail(this);' style='width: 60px;' value='删除'/></li>";
	   	},'onSelect' : function(file) {//当每个文件添加至队列后触发
	   		$("#upload_thumbnail").show();
	   		$("#cancel_thumbnail").show();
	   	},'onCancel' : function(file) {
	        //alert('The file ' + file.name + ' was cancelled.');
	   		thumbnailLength = thumbnailLength-1;
	   		if(thumbnailLength<uploadThumbnailLimit){
				$("#uploadifyThumbnail").uploadify('disable', false);
			}
	   		
	   		queueThumbnailLength = queueThumbnailLength-1;
	   		
	   		if(queueThumbnailLength>0){
	   			
	   		} else {
	   			$("#upload_thumbnail").hide();
	    		$("#cancel_thumbnail").hide();
	   		}
	    },'onDialogClose' : function(swfuploadifyQueue) {//当文件选择对话框关闭时触发
	    	thumbnailLength += swfuploadifyQueue.queueLength;
	    	queueThumbnailLength = swfuploadifyQueue.queueLength;
	    	if(thumbnailLength==1){
	    		$("#uploadifyThumbnail").uploadify('disable', true);
	    	}
	   	},'onQueueComplete' : function(stats) {//当队列中的所有文件全部完成上传时触发
	   		$("#uploadifyThumbnail").uploadify('disable', false);
	   		$("#thumbnailShow").show();
	   		$("#thumbnailShow ul").append(thumbnailShow);
	   		thumbnailShow = "";
	   		$("#thumbnailpaths").val($.toJSON(basThumbnailArray));
	   		
	   		if(basThumbnailArray.length==1){
	   			$("#fileQueueThumbnail").hide();
		   		$("#uploadifyThumbnail").hide();
	    	}
	   		
	   		$("#upload_thumbnail").hide();
	   		$("#cancel_thumbnail").hide();
	   	},'onUploadStart': function(file) {//上传开始时触发（每个文件触发一次）
	   		$("#uploadifyThumbnail").uploadify("settings", "uploadLimit", ++uploadThumbnailLimit);
	   	}
	});
	//上传按钮向右位移
	$(".uploadifyThumbnail").css('margin-left','80px');
}

function delThumbnail(t){
	var index = $("#thumbnailShow ul input").index(t);
	basThumbnailArray.splice(index, 1);
	thumbnailLength = basThumbnailArray.length;
	$("#thumbnailpaths").val($.toJSON(basThumbnailArray));
	if (basThumbnailArray.length == 0) {
		$("#thumbnailShow ul").html('');
	} else {
		$("#thumbnailShow ul li").eq(index).remove();
	}
	if(basThumbnailArray.length<1){
		$("#fileQueueThumbnail").show();
		$("#uploadifyThumbnail").show();
	}
}
