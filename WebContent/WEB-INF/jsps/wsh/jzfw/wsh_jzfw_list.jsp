<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>家政服务</title>
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
 		 	var url = "${global_url}/wfw/jbts/getJBTSListAsync.htm?pageno="+(pageno*1+1)+"&pageSize="+pageSize;
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
		   			alert("异常信息："+textStatus);
		  		}
	   });
	 }
 	 
 	 function endShow(){
 		 if(isHidden){
 			isHidden = false;
 			var html =$(".Classall");
 			html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>亲,已经是所有流水单了</span></div>");
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
		 var html='<li>'
					+'<div class="rt">'
					+'<div class="tit"><a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?rztjId='+${item.rztjId}+'">'+${item.content }+'<p style="float: right;">'+${item.createdTime }+'</p></a></div>'
					+'</div>'
					+'</li>'
			      ;
		 return html;
	 }
</script>

</head>
<body>

<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/banner_jzfw.jpg"></a></li>
	</ul>
</div> 
<!--海报结束-->


<div class="Classall">
	<ul class="allcon">
		<c:if test="${not empty jzfwList}">
	       	<c:forEach var="jzfwMap" items="${jzfwList}">
	       		<li>
		       		<a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?rztjId=${jzfwMap.rztjId}" >
		       			<c:if test="${not empty jzfwMap.paramMap.imagePaths}"><img src="${jzfwMap.paramMap.imagePaths}" class="pic"></c:if>
		       			<c:if test="${empty jzfwMap.paramMap.imagePaths}"><img src="${global_image_url}/mr_jzfw.png" class="pic"></c:if>
		       		</a>
					<div class="rt">
						<div class="tit"><a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?rztjId=${jzfwMap.rztjId}">${jzfwMap.title}</a></div>
						<div class="dush">
							<%-- <div >联系人：${jzfwMap.name}</div>
						    <div >联系手机：${jzfwMap.phone}</div>
						    <div >联系地址：${jzfwMap.address}</div> --%>
		                	<div >服务特色：<i class="esh_jg">${jzfwMap.price}</i></div>
							<div >${jzfwMap.createdTime}</div>
						</div>
					</div>
		        </li>
	       	</c:forEach>
       	</c:if>
       		
       <%--  <li>
			<a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?jzfwId=xxx" ><img src="${global_image_url}/jzay.jpg" class="pic"></a>
			<div class="rt">
				<div class="tit"><a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?jzfwId=xxx">月嫂王阿姨</a></div>
               
				<div class="dush">
                	<div >工种：月嫂</div>
                	<div >月工资：<i class="esh_jg">5000元</i></div>
					<div >2015-10-10</div>
                    <div class="ljbm0">251人查看 <i></i></div>
				</div>
			</div>
		</li> --%>
    </ul>
    <c:if test="${empty jzfwList}">
   		<div style='width:100%;text-align:center;font-size:14px;'>抱歉，目前暂无数据</div>
   	</c:if>
    <div id ="loadingDiv" style="display:none;">正在加载中...</div>
</div>
</body>
</html>