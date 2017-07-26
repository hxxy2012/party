<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head><title>更换会员头像</title>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<meta name="viewport" content="width=device-width, initial-scale=1.0">  
<link rel="stylesheet" href="${global_css_url }/jquery.mobile-1.3.2.min.css">  
<script src="${global_js_url }/jquery-1.8.3.min.js"></script>  
<script src="${global_js_url }/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
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
             $("#thumbnailpaths").val(returndata.saveDirectory);
             document.getElementById("uploadImgForm").actoin="${global_url}/member/modifyPatientHead.htm";
             document.getElementById("uploadImgForm").submit();
         },  
         error: function (returndata) {
             alert(returndata);  
         }  
    }); 
}
</script>
</head>  
<body>  
<div data-role="page">  
	<div data-role="header" data-position="fixed">  
		<h1>更换会员头像</h1>  
	</div>  
	<div class="ui-content">  
	    <div class="file-box">  
	        <form id= "uploadForm" action="${image_server }servlet/UploadServelt" method="post" enctype="multipart/form-data" data-ajax=true>  
	            <input type="file" name="fileField" id="fileField"  />  
	            <input type="button" value="上传" onclick="uploadThumbnail()" /> 
	        </form>
	    </div>
	</div>  
	<form id= "uploadImgForm" action="${global_url}/member/modifyPatientHead.htm" method="post">  
        <input type="hidden" name="thumbnailpaths" id="thumbnailpaths" />  
    </form>
</div>
</body>  
</html> 