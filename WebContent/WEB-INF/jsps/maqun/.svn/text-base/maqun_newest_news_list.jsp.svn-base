<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>最新资讯</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/JavaScript" src="${global_js_url}/jquery.js"></script>
<script type="text/JavaScript" src="${global_js_url}/jquery_lazyload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
});
//检测浏览器是否支持localStorage
var strStoreDate=window.localStorage?localStorage.getItem("member_account"):Cookie.read("member_account");
if(strStoreDate!=null){
	var result = $.ajax({
		url : "${global_url}/member/checkMemberState.htm?member_account="+strStoreDate,
		async : false,
		cache : false
	}).responseText;
}
</script>
<script type="text/javascript">
var isNotOtherData = false;
var isJiazai = false;	
//滑动到底部事件
$(window).scroll(function(){
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollTop + windowHeight == scrollHeight){
		if(!isNotOtherData){//还有数据
			if(!isJiazai){
				showLoader();
			    isJiazai = true;
			    GetAjaxData();
			}
		}else{//已经全部加载
			endShow();
		}
	}
});

var pageno=1;
var pageSize=10;
var size = 0;
function GetAjaxData(){
	var url = "${global_url}/wmh/getNewsListAsync.htm?shequId="+${shequId}+"&pageno="+(pageno*1+1)+"&pageSize="+pageSize;
	  //var url = "${global_url}/goodsType/getGoodsListAsync.htm?page_on="+(pageno*1+1)+"&page_size="+pageSize+"&cat_id="+${cat_id}+"&city="+${city};
	  $.ajax({
	    type:"POST",
	      dataType: "json",//返回json格式的数据
	      url: url,
	      beforeSend: function () {
	        showLoader();
	    },
	    complete: function () {
	        hideLoader();
	    },
	    success: function (data) {
	    	hideLoader();
	      pageno++;
	      size = data.size;
	      if(size>0){//存在数据
	    	  html(data);
	      }else {//未取得数据
	        if(pageno*1>1){//表示已经全部加载
	          endShow();
	        }
	      }
	      isJiazai = false;
	      },
	      error: function(XMLHttpRequest, textStatus, errorThrown){
	         alert("异常信息："+textStatus);
	      }
	  });
}

function showLoader() {  
	$("#loadingDiv").show();
}  
function hideLoader(){  
	$("#loadingDiv").hide(); 
}
var isHidden = true;
function endShow(){
   if(isHidden){
    isHidden = false;
    var html =$(".Classall");
    html.append("<div id='endDiv' style='width:100%;text-align:center;display:none;'><span style='color:red;font-size:14px;'>亲,已经是所有政务资讯了</span></div>");
      isNotOtherData = true;
      setTimeout(function(){
       $("#endDiv").hide(1000);
       $("#endDiv").remove(); 
       isHidden = true;
     },2000); 
   }
}

function html(data){
	var html= "";
    $.each(data.dataList,function(i,item){
      html+=appendHtml(item);
    });
    $(".Classall ul").append(html);
}

function appendHtml(item){
	var html = '<li><a href="${global_url}/wmh/news/getNewsInfo.htm?newsId='+item.newsId+'"><img src="'+item.paramMap.imagePaths+'" class="pic"></a>'
				+'<div class="rt"><div class="tit"><a href="${global_url}/wmh/news/getNewsInfo.htm?newsId='+item.newsId+'">'+item.title +'</a></div>'
				+'<div class="dush"><div class="haoping">'+item.clientContent+'</div><div class="ljbm0">'+item.viewCount+'人阅读</div></div></div>'
				+'</li>';
	  
	   return html;
}
</script>
</head>
<body>
<div class="slider">
	<ul class="slide-banner">
		<li><img src="${global_image_url}/banner_mh.jpg"></li>
	</ul>
</div>

<!--推荐资讯-->
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5 ><img src="${global_image_url}/ico_zxzx.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;最新资讯</h5>
			<!-- <div class="more"><a href="#"><i class="ico-right"></i></a></div> -->
		</div>	
	</section>
   <div class="Classall">
		<ul class="allcon">
			<c:if test="${not empty newsList}">
	       	<c:forEach var="newsMap" items="${newsList}" begin="0" end="9">
	       		<li>
		        	<a href="${global_url}/wmh/news/getNewsInfo.htm?newsId=${newsMap.newsId}" >
		        	<c:if test="${not empty newsMap.paramMap.imagePaths}">
		        		<img src="${newsMap.paramMap.imagePaths }" class="pic">
	        	 	</c:if>
		        	<c:if test="${empty newsMap.paramMap.imagePaths}">
		        		<img src="${global_image_url}/moren.png" class="pic">
	        	 	</c:if>
		        	</a>
					<div class="rt">
						<div class="tit"><a href="${global_url}/wmh/news/getNewsInfo.htm?newsId=${newsMap.newsId}&className=${newsMap.paramMap.className}"><c:if test="${not empty newsMap.paramMap.className}">[${newsMap.paramMap.className}]</c:if>${newsMap.title }</a></div>
						<div class="dush">
							<div class="haoping">
							${newsMap.clientContent}
							<%-- ${fn:length(newsMap.clientContent) > 30 ? fn:substring(newsMap.clientContent,0,30) : newsMap.clientContent}${fn:length(newsMap.clientContent) > 30 ? '...' : ''} --%>
							</div>
		                    <div class="ljbm0">${newsMap.viewCount}人阅读</div>
						</div>
					</div>
		        </li>
	       	</c:forEach>
	       	</c:if>
	       	<c:if test="${empty newsList}">
	       		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
	       	</c:if>
	    	<div id ="loadingDiv" style="display:none;">正在加载中...</div> 
	    </ul>
	
	
	
    	<!-- <li>
			<a href="#" ><img src="images/class1.png" class="pic"></a>
			<div class="rt">
				<div class="tit"><a href="#">马群村社区开展“关爱老人”志愿服务活动</a></div>
				<div class="dush">
					<div class="haoping">为弘扬中华民族尊老爱老传统美德和“奉献、友爱、互助、进步”志愿精神，近日上午，马群街道马群...</div>
                    <div class="ljbm0">251人阅读</div>
				</div>
			</div>
		</li> -->
		
		
        <!-- <li>
			<a href="#" ><img src="images/class1.png" class="pic"></a>
			<div class="rt">
				<div class="tit"><a href="#">马群村社区开展“关爱老人”志愿服务活动</a></div>
               
				<div class="dush">
					<div class="haoping">为弘扬中华民族尊老爱老传统美德和“奉献、友爱、互助、进步”志愿精神，近日上午，马群街道马群...</div>
                    <div class="ljbm0">251人阅读</div>
				</div>
			</div>
		</li> -->
</div>
</div><!--推荐资讯-->


</body>
</html>

