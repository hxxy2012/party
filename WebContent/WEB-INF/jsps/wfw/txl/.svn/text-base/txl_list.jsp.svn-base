<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>通讯录列表</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_url}/js/jquery-1.7.js"></script>
<script type="text/javascript">
		var pageno=1;
		var pageSize=9;
		var isNotOtherData = false;
		var isHidden = true;
		var size=0;
		var isJiazai = false;	
	    //滑动到底部事件
	   	$(document).bind("scroll", function(event){
	   		//alert( $(document).scrollTop()+"---"+$(document).height()+"---"+$(window).height());
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
 		 	var url = "${global_url}/wfw/txl/getTXLListAsync.htm?shequId="+${shequId}+"&pageno="+(pageno*1+1)+"&pageSize="+pageSize;
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
			    		 $(".txl_list ul").append(html);
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
 	 
 	 function endShow(){
 		 if(isHidden){
 			isHidden = false;
 			var html =$(".txl_list");
 			html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>亲,已经是所有通讯录了</span></div>");
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
		var html =  '<li>'
						+'<label>部门:</label>'+${item.department}+'&nbsp;&nbsp;<label>职位:</label>'+${item.job}+'</br>'
			            +'<label>姓名:</label>'+${item.name}+'</br>'
			            +'<label>办公室电话：</label>'+${item.telephone}+'<button class="dh_btn" onclick="window.location.href='tel:${item.telephone}'">拨打电话</button></br>'
		            	+'<label>联系人电话：</label>'+${item.phone}+'<button class="dh_btn" onclick="window.location.href='tel:${item.phone}'">拨打电话</button>'   
		            +'</li>';			
		 return html;
	 }
</script>
</head>
<body>
<div class="ban_img m_b10">
	<c:if test="${not empty topImg}">
	   <img src="${topImg}">
	</c:if>
	<c:if test="${empty topImg}">
	  <img src="${global_image_url}/banner_bmdh.jpg">
	</c:if>
</div>
<!-- <div class="slider"> -->
<!-- 	<ul class="slide-banner"> -->
<%-- 		<li><a href="#"><img src="${global_image_url}/gzbx.jpg"></a></li> --%>
<!-- 	</ul> -->
<!-- </div> -->

<div class="Courseinfor" >
	<div class="course-header" style="border-bottom:none">
    	<h2><img src="${global_image_url}/ico_txl.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;社区通讯录</h2>
        <div class="course-content">
	        <ul class="txl_list">
	            <c:if test="${not empty txlList}">
			       	<c:forEach var="txlMap" items="${txlList}">
			        	<li>
		                    <c:if test="${not empty txlMap.department}"><label>部门:</label>${txlMap.department}&nbsp;&nbsp;</c:if><c:if test="${not empty txlMap.job}"><label>职位:</label>${txlMap.job}<br></c:if>
		                    <c:if test="${not empty txlMap.name}"><label>姓名:</label>${txlMap.name}</c:if><br>
		                    <c:if test="${not empty txlMap.telephone}"><label>办公室电话：</label>
		                    ${txlMap.telephone} <button class="dh_btn" onclick="window.location.href='tel:${txlMap.telephone}'">拨打电话</button><br></c:if>
		                    <%-- <c:if test="${empty txlMap.telephone}">未提供<br></c:if> --%>
		                     <c:if test="${not empty txlMap.phone}"><label>联系人电话：</label>
		                   ${txlMap.phone}<button class="dh_btn" onclick="window.location.href='tel:${txlMap.phone}'">拨打电话</button></c:if>
		                	<%-- <c:if test="${empty txlMap.phone}">未提供</c:if> --%>
		                </li>
			       	</c:forEach>
		       	</c:if>
	       </ul> 
           <c:if test="${empty txlList}">
       		 <div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
       	   </c:if>
		   <div id ="loadingDiv" style="display:none;">正在加载中...</div>
        </div>
	</div>
</div>

<!--社区浮动个人中心 -->
<jsp:include page="../../include/floattoolbar.jsp" />

</body>
</html>

