<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>智慧社区-公告详情</title>
<link rel="stylesheet" type="text/css" href="../styles/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>
<script type="text/javascript">
  
  
  $(function(){
	  var windowWidth = $(window).width();
	  $(".bodycon").css("width",windowWidth+"px");
	  
	  //修改富文本图片适应
	  //var img=  $(".textcon img").attr("style");
	  //var imgHeigth=  $(".textcon img").attr("heigth");
	  
	  //var imgArr = img.split(";");
	  // var imgWidth= imgArr[0].substring(7,imgArr[0].indexOf("p"));
	  // var imgHeigth= imgArr[1].substring(9,imgArr[1].indexOf("p"));
	  
	 // alert("宽度>"+imgWidth+"<");
	 // alert("高度>"+imgHeigth+"<");
	  
	 // var n=(imgWidth*1)/(imgHeigth*1) ;
	 // alert("n"+Math.floor(n));
	 // alert("windowWidth"+windowWidth);
	 // var nowHeigth = windowWidth/n;R
	 //alert("nowHeigth:"+nowHeigth);
	 //alert("width:"+Math.floor(n*1)+"px;heigth:"+Math.floor(nowHeight*1)+"px;");
	  $(".textcon img").attr("style","max-width: 85%;");
	  
  });
	

</script>


</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<a href="${global_url}/index/getIndexInfo.htm"><span class="icon back"></span></a>
				公告详情
	</div>
</header>
 -->
<c:if test="${not empty noticesInfo}">
	<div class="bodycon">
		<div class="biaoticon">
		        <h1>${noticesInfo.NOTICES_TITLE}</h1>
		        <h3>${noticesInfo.NOTICES_DATE}</h3>
		</div>
	    <div class="textcon" contenteditable="true">
	    	<%-- ${noticesInfo.NOTICES_CONTENT} --%>
	    	<%-- ${noticesInfo.newContent} --%>
	    	<%-- <c:if test="${not empty noticesInfo.REMARK}">${noticesInfo.REMARK}</c:if>
	    	<c:if test="${empty noticesInfo.REMARK}"><div style="text-align: center;">暂无公告</div></c:if>
	    	${noticesInfo.NOTICES_CONTENT} --%>
	    	${noticesInfo.NOTICES_CONTENT}
	    </div>
	</div>
</c:if>

<!--社区浮动个人中心 -->
<jsp:include page="../include/floattoolbar.jsp" />

</body>
</html>