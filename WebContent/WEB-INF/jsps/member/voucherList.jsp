<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>个人中心-代金券流水</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.7.js"></script>

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
 		 	var url = "${global_url}/voucher/getVoucherAsync.htm?page_on="+(pageno*1+1)+"&page_size="+pageSize;
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
			    		 $(".daijinjuan ul").append(html);
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
 			var html =$(".daijinjuan");
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
		  var html='<li>'+
		  		   '<p style="font-size:21px;">'+item.REASON+'<b>'+item.POINT+'</b></p>'+
		  		   '<p>'+item.time+'</p>'+
		  		   '</li>';
		 return html;
	 };
</script>
</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<span class="icon back" onclick="history.back()"></span>
        代金券流水	
	</div>
</header>
 -->
    <div class="Classall">
        <h1>${memberMap.login_name}</h1>
        <h2>代金券余额:${voucherTotal}</h2>
    </div>    
    <div class="daijinjuan">
        <ul class="djjcon">
	        <c:if test="${not empty voucherList}">
				<c:forEach var="voucherMap" items="${voucherList}" varStatus="voucherSta">
					<li>
		            	<p style="font-size:21px;">${voucherMap.REASON}<b>${voucherMap.POINT}</b></p>
		               <%--  <p>序号：${voucherSta.index+1}</p>  --%>
		                <p>${voucherMap.time} <!-- <span>有效期至2015-04-02</span> --></p>
		            </li>
	         	</c:forEach>
			</c:if>
        </ul>
          <div id ="loadingDiv" style="display:none;">正在加载中...</div>
    </div>
</body>
</html>
