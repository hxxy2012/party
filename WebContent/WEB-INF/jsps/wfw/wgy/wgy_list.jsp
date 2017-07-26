<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>网格员列表</title>
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
 		 	var url = "${global_url}/wfw/wgy/getWGYListAsync.htm?shequId="+${shequId}+"&pageno="+(pageno*1+1)+"&pageSize="+pageSize;
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
 			html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>亲,已经是所有网格员了</span></div>");
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
<!-- <div class="slider"> -->
<!-- 	<ul class="slide-banner"> -->
<%-- 		<li><a href="#"><img src="${global_image_url}/gzbx.jpg"></a></li> --%>
<!-- 	</ul> -->
<!-- </div> -->


<div class="Courseinfor" style="border-top: 1px #e3e3e3 solid;">
	<div class="course-header" style="border-bottom:none">
    	<h2 class="wgy_title"><img src="${global_image_url}/ico_wgy.png" width="25px" height="25px;" style="position: relative;top: 6px;" />&nbsp;社区网格员</h2>
       <div class="course-content">
            <ul class="wgy_list">
            	<c:if test="${not empty wgyList}">
			       	<c:forEach var="wgyMap" items="${wgyList}">
				        	<li>
				        		<c:if test="${not empty wgyMap.paramMap.imagePaths}"><img src="${wgyMap.paramMap.imagePaths }" class="pic"></c:if>
			        			<c:if test="${empty wgyMap.paramMap.imagePaths}"><img src="${global_image_url}/moren.png" class="pic"></c:if>
			                    <div class="gwy_xinxi">
			                    	<c:if test="${not empty wgyMap.staffName}"><label>姓名:</label>${wgyMap.staffName}<br></c:if>
			                        <c:if test="${not empty wgyMap.departmentName}"><label>部门:</label>${wgyMap.departmentName}<br></c:if>
			                        <c:if test="${not empty wgyMap.posts}"><label>网格职位:</label>
			                        <c:choose>
				                        <c:when test="${wgyMap.posts=='1'}">书记</c:when>
				                        <c:when test="${wgyMap.posts=='2'}">副书记</c:when>
				                        <c:when test="${wgyMap.posts=='3'}">主任</c:when>
				                        <c:when test="${wgyMap.posts=='4'}">副主任</c:when>
				                        <c:when test="${wgyMap.posts=='5'}">科长</c:when>
				                        <c:when test="${wgyMap.posts=='6'}">副科长</c:when>
				                        <c:when test="${wgyMap.posts=='7'}">科员</c:when>
				                        <c:when test="${wgyMap.posts=='8'}">组长</c:when>
				                        <c:when test="${wgyMap.posts=='9'}">信息员</c:when>
				                        <c:when test="${wgyMap.posts=='10'}">会计</c:when>
				                        <c:when test="${wgyMap.posts=='11'}">社工</c:when>
				                        <c:when test="${wgyMap.posts=='12'}">干事</c:when>
				                        <c:when test="${wgyMap.posts=='13'}">大学生村官</c:when>
				                        <c:when test="${wgyMap.posts=='14'}">助理</c:when>
				                        <c:when test="${wgyMap.posts=='15'}">大学生社工</c:when>
				                        <c:when test="${wgyMap.posts=='16'}">劳动协理员</c:when>
				                        <c:when test="${wgyMap.posts=='17'}">工作人员</c:when>
				                        <c:when test="${wgyMap.posts=='18'}">其他</c:when>
				                        <c:otherwise>无</c:otherwise>
			                        </c:choose>
			                        <br></c:if>
			                        <c:if test="${not empty wgyMap.dutyDescribe}"><label>职责描述:</label>${wgyMap.dutyDescribe}<br></c:if>
			                        <c:if test="${not empty wgyMap.phone}"><label>固话：</label>${wgyMap.phone}<br>
			                       		<button class="wgy_btn" onclick="window.location.href='tel:${wgyMap.phone}'">电话咨询</button>
			                        </c:if>
			                        <c:if test="${not empty wgyMap.telphone}"><label>手机：</label>${wgyMap.telphone}<br>
			                       		<button class="wgy_btn" onclick="window.location.href='tel:${wgyMap.telphone}'">电话咨询</button>
			                        </c:if>
			                    </div>
			                </li>
			       	</c:forEach>
		       	</c:if>
		       			<!--<li>
			                	<a href="#" ><img src="images/wgy_1.jpg" class="pic"></a>
			                    <div class="gwy_xinxi">
			                    	<label>姓名:</label>徐桂萍</br>
			                        <label>部门:</label>金陵驿街道</br>
			                        <label>网格职位:</label>一级网格责任人</br>
			                        <label>责任区域:</label>茉莉园、金桂园</br>
			                        <label>联系电话：</label>13112345555</br>
			                        <button class="wgy_btn" onclick="window.location.href='tel:${shequMap.phone}'">电话咨询</button>
			                    </div>
			                </li> -->
            </ul>   
            
           	<c:if test="${empty wgyList}">
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

