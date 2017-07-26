<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>党建纪检</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>



<style>
.swipe {
  overflow: hidden;
  visibility: hidden;
  position: relative;
}
.swipe-wrap {
  overflow: hidden;
  position: relative;
}
.swipe-wrap > div {
  float:left;
  width:100%;
  position: relative;
} 
nav #position {  
    text-align: center;  
    list-style: none;  
    margin: 0;  
    padding: 0  
}  
nav #position li {  
    display: inline-block;  
    width: 10px;  
    height: 10px;  
    border-radius: 10px;  
    background: #141414;  
    box-shadow: inset 0 1px 3px black,0 0 1px 1px #202020;  
    margin: 0 2px;  
    cursor: pointer  
}  
nav #position li.on {  
    box-shadow: inset 0 1px 3px -1px #28b4ea,0 1px 2px rgba(0,0,0,.5);  
    background-color: #1293dc;  
    background-image: -webkit-gradient(linear,left top,left bottom,color-stop(0%,#1293dc),color-stop(100%,#0f6297));  
    background-image: -webkit-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -moz-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -ms-linear-gradient(top,#1293dc,#0f6297);  
    background-image: -o-linear-gradient(top,#1293dc,#0f6297);  
    background-image: linear-gradient(top,#1293dc,#0f6297)  
}  
</style>

<script type="text/javascript">
console.log("aaa",111);
$(function(){
	 setImgHeight();
});
	function setImgHeight(){
		 var windowWidth = $(window).width();
		 $(".slide").css("height",windowWidth*0.3+"px");
		 $(".slide-banner img").css("height",windowWidth*0.3+"px");
		 $(".slide-banner").css("height",windowWidth*0.3+"px");
		 $(".message").css("width",windowWidth+"px");
		 $(".slide-banner img").css("width",windowWidth+"px");
		
	}
</script>





</head>
<body>
<!-- 幻灯片 可滑动 -->
<div class="slider">
		<ul class="slide-banner">
			<li><img src="${global_image_url}/icon/dz_wasq/banner_djjj.png"></li>
	</ul>
</div>




<!--党建纪检分类-->

<div class="mk">
    <section class="module ">
        <div class="tit_bar" style="border:none">
            <h5 class="tit_name"><img class="tit_icon" src="${global_image_url }/ico_rztj.png"  />&nbsp;党建纪检</h5>
        </div>
    </section>	
    <div class="zwlist">
        <ul>
        	
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=1&className=社区风采&shequId=462}">
						<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/dj_fc.png" /></i><p>社区风采</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=2&className=通知公告&shequId=462">
						<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/dj_tz.png" /></i><p>通知公告</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=14&className=动态发布&shequId=462}">
						<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/dj_gg.png" /></i><p>动态发布</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=722&className=社区活动&shequId=462">
						<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/dj_hd.png" /></i><p>社区活动</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=802&className=廉政之窗&shequId=462">
						<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/dj_lz.png" /></i><p>廉政之窗</p>
				</a>
			</li>
			<li>
				<a href="${global_url}/wmh/news/getNewsList.htm?classId=822&className=社区党建&shequId=462">
						<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/icon/dz_wasq/dj_dj.png" /></i><p>社区党建</p>
				</a>
			</li>
        
<%-- 	        <c:if test="${not empty newsClassList && fn:length(newsClassList) > 0}"> --%>
<%-- 				<c:forEach var="classMap" items="${newsClassList}" varStatus="stat">  --%>
<!-- 					<li> -->
<%-- 						<a href="${global_url}/wmh/news/getNewsList.htm?classId=${classMap.classId}&className=${classMap.className}&shequId=${shequId}"> --%>
<%-- 							<c:if test="${not empty classMap.paramMap.imagePaths}"> --%>
<%-- 								<i class="zwcn1"><img class="zwcn_img" src="${classMap.paramMap.imagePaths}" /></i><p>${classMap.className}</p> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${empty classMap.paramMap.imagePaths}"> --%>
<%-- 								<i class="zwcn1"><img class="zwcn_img" src="${global_image_url }/moren50.png" /></i><p>${classMap.className}</p> --%>
<%-- 							</c:if> --%>
<!-- 						</a> -->
<!-- 					</li> -->
<%-- 				</c:forEach> --%>
<%-- 			</c:if> --%>
        </ul>
    </div>
</div>
<!--党建纪检结束 -->
    
</body>
</html>

