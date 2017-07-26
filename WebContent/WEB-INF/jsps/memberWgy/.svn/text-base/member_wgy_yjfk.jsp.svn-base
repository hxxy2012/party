<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>意见反馈列表</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${xishan_js_url}/jquery-1.7.js"></script>
<script type="text/javascript" src="${xishan_js_url}/swipe.js"></script>
<script type="text/JavaScript" src="${xishan_js_url}/jquery_lazyload.js"></script> 
<script type="text/javascript">
$(document).ready(function() {
	$("img").lazyload({
    	threshold : 10,
		effect : "fadeIn"
	});
	$("#reMove").click(function(){
		$("#title").val('');
		
	});
	
	var temp = '${resolve}';
	if(temp=='0'){
		$("#wei").addClass('fl_xz');
	}else if(temp=='1'){
		$("#yi").addClass('fl_xz');
	}else if(temp=='2'){
		$("#wu").addClass('fl_xz');
	}
	
	
    $(".fl_3 li").click(function(){
        $(this).siblings('li').removeClass('fl_xz');  // 删除其他兄弟元素的样式
        $(this).addClass('fl_xz');                            // 添加当前元素的样式
        var resolve = $(this).val();
        window.location.href = "${global_url}/member/wgy/getYJFKList.htm?shequId=${shequId}&resolve="+resolve;
        
    });
	
	
});
</script>
<script type="text/javascript">
var pageno=1;
var pageSize=10;
var isNotOtherData = false;
var isHidden = true;
var size=0;
var isJiazai = false;	
//滑动到底部事件
$(document).bind("scroll", function(event){
	if( $(document).scrollTop()+10 >= $(document).height()-$(window).height() ){
		if(!isNotOtherData){//还有数据
			if(!isJiazai){
			    isJiazai = true;
			    GetAjaxData();
			    }
		}else{//已经全部加载
			endShow();
		}
  	} 
});

function GetAjaxData(){
		var url = "${global_url}/xishan/getNewsListAsync.htm?pageno="+(pageno*1+1)+"&pageSize="+pageSize+"&classId="+${classId};
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
    		pageno++;
    		size = data.size;
    		if(size>0){//存在数据
    			var html= "";
	    		 $.each(data.dataList,function(i,item){
	    			 html+=appendHtml(item);
	    		 });
	    		 $(".Classall ul").append(html);
    		}else {//未取得数据
    			if(pageno*1>1){//表示已经全部加载
    				endShow();
    			}
    		}
    		isJiazai = false;
        },
   		error: function(XMLHttpRequest, textStatus, errorThrown){
   			/* alert("异常信息："+textStatus); */
  		}
});
}

function endShow(){
	 if(isHidden){
		isHidden = false;
		var html =$(".Classall");
		html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>亲,已经是所有了</span></div>");
		  isNotOtherData = true;
		  setTimeout(function(){
			 $("#endDiv").hide(1000);
			 $("#endDiv").remove(); 
			 isHidden = true;
		 },2000); 
	 }
}
function showLoader() {  
  $("#loadingDiv").show();
}  
function hideLoader(){  
$("#loadingDiv").hide(); 
}   

function appendHtml(item){
	 var html='<li><a href="${global_url}/xishan/getNewsMap.htm?newsId='+item.newsId+'"><img onerror="nofind();" src="'+item.paramMap.imagePaths+'" class="pic">'
		+'<div class="rt">'
		+'<div class="tit">'+item.title+'</div>'
		+'<div class="dush">'
		+'<div class="haoping">'+item.clientContent+'</div>'	
		+'<div class="ljbm1">'+item.createdTime+'</div>'
	 	+'<div class="ljbm0">'+item.viewCount+'人阅读</div>'
		+'</div>'
		+'</div>'
		+'</a>'
		+'</li>'
	;
 	return html;
};

function nofind(){
 var img=event.srcElement; 
 img.src="${global_image_url}/noimg.jpg"; 
 img.onerror=null; //控制onerror事件只触发一次 
};
</script>
</head>
<body>

<!-- 状态分类 -->
<div class="fb_fl" >
	<ul class="fl_3">
		<li id="wei" value="0">未处理</li>
		<li id="yi" value="1">已处理</li>
		<li id="wu" value="2">无需处理</li>
	</ul>
</div>

<!--搜索，按 地址或反馈人-->
<%-- <div class="sousuo m_b10" >
	<div class="sousuo-in">
    	<input type="text" placeholder="请输入关键字搜索" >
  		<span><img src="${global_image_url}/cha.png" width="30" height="30"></span>
	</div>
  	<input type="submit" value="" id="submit-ss">
</div> --%>
<div class="Classall">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/ico_jbts.png" width="25px" height="25px;" style="position: relative;top: 5px;" />&nbsp;意见反馈</h5>
		</div>	
	</section>
	<ul class="allcon">
	
		<c:if test="${not empty jbtsList}">
	       	<c:forEach var="jbtsMap" items="${jbtsList}">
	       		<li>
					<a href="${global_url}/wfw/jbts/getJBTSInfo.htm?jbtsId=${jbtsMap.jbtsId}">${jbtsMap.content }<p style="float: right;">${jbtsMap.createdTime }</p></a>
		        </li>
	       	</c:forEach>
       	</c:if>
	
	
		<c:if test="${not empty yjfkList}">
	       	<c:forEach var="yjfkMap" items="${yjfkList}">
		    	<li>
		            <a href="${global_url}/member/wgy/getYJFKInfo.htm?jbtsId=${yjfkMap.jbtsId}" >
		           		<c:if test="${not empty yjfkMap.paramMap.imagePaths}"><img src="${yjfkMap.paramMap.imagePaths}" class="pic"></c:if>
       					<c:if test="${empty yjfkMap.paramMap.imagePaths}"><img src="${global_image_url}/mr_essc.png" class="pic"></c:if>
		            </a>
					<div class="rt">
		            	<a href="${global_url}/member/wgy/getYJFKInfo.htm?jbtsId=${yjfkMap.jbtsId}" ><div class="yy_list_cont"><span class="yy_icon0"></span>${yjfkMap.name}</div> </a>
		                <div class="yy_list_cont"><span class="yy_icon2"></span>${yjfkMap.phone} <button class="dh_btn" onclick="window.location.href='tel:${yjfkMap.phone}'">拨打电话</button></div> 
		                <div class="yy_list_cont"><span class="yy_icon1"></span>${yjfkMap.createdTime}</div>
		                <c:choose>
		                	<c:when test="${yjfkMap.resolve=='0'}"><span class="yjfk_icon2 red">未处理</span></c:when>
		                	<c:when test="${yjfkMap.resolve=='1'}"><span class="yjfk_icon2 green">已处理</span></c:when>
		                	<c:when test="${yjfkMap.resolve=='2'}"><span class="yjfk_icon2 blue">无需处理</span></c:when>
		                </c:choose>
		               
					</div>
				</li>
         	</c:forEach>
       	</c:if>
       
    </ul>
</div>
</body>
</html>
