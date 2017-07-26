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
<title>智慧社区-我的报名</title>
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
 		 	var url = "${global_url}/member/getOrderAsync.htm?page_on="+(pageno*1+1)+"&page_size="+pageSize;
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
 			html.append("<div id='endDiv' style='width:100%;text-align:center;'><span style='color:red;font-size:14px;'>亲,已经是所有报名了</span></div>");
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
	var price_detail = "";
		if(item.goods_price=='0' || item.goods_price==0){
			price_detail += "电话咨询";
		}else{
			price_detail += '￥'+item.goods_price;
		}
		if(item.payinfo) {
			price_detail += '<input type="button" value="去支付" onclick="getBrandWCPayRequest(\'' + item.timeStamp + "', '" + item.nonceStr + "', '" + item.package + "', '" + item.paySign + '\')">';
		} else if(item.PAY_STATUS=='1' || item.PAY_STATUS==1) {
			price_detail += '<font color="green;">(已支付)</font>'; 
		}
		  var html='<li style=" margin-bottom:15px; background: #fff;">'+
				'<div class="jiaoyi"><span class="jiaoyjg"><a href="${global_url}/company/getCompanyInfo.htm?id='+item.company_id+'" >'+item.company_name+'&nbsp;&nbsp;>'+'</a></span></div>'+
					'<div class="baomingdd">'+
					'<a href="${global_url}/goods/getGoodsInfo.htm?goods_id='+item.goods_id+'"><img src="'+item.goods_image+'" onerror="nofind();" class="pic"></a>'+
	                '<div class="rt">'+
					'<div class="tit"><a style="float: left;" href="${global_url}/goods/getGoodsInfo.htm?goods_id='+item.goods_id+'">'+item.goods_name+'</a></div>'+
					'<div class="price">'+price_detail+'</div> '+
					'<div class="tits" style="font-size: 13px;line-height: 25px;overflow: hidden;">'+item.SHIP_NAME+'&nbsp;&nbsp;'+item.SHIP_MOBILE+'</div>'+
					'<div class="dush">'+
					'<div class="haoping" style="line-height: 20px;font-size: 13px;">'+item.create_time+'</div>'+
					'</div>'+
					'</div>'+
					'</div>'+
			'</li>'; 
		 return html;
	 };
	 
	 function nofind(){
		 var img=event.srcElement; 
		 img.src="${global_image_url}/noimg.jpg"; //需改成默认课程图片
		 img.onerror=null; //控制onerror事件只触发一次 
	 }
</script>
</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<span class="icon back" onclick="history.back()"></span>
        我的报名
		 <span class="icon note"></span> 
	</div>
</header>
 --> 
<div class="Classall">
	<ul class="allcon" style="background:#f1f1f1">
		<c:if test="${not empty orderList}">
			<c:forEach var="orderMap" items="${orderList}">
         		<li style=" margin-bottom:15px; background: #fff;">
		           <div class="jiaoyi"><span class="jiaoyjg"><a href="${global_url}/company/getCompanyInfo.htm?id=${orderMap.company_id}">${orderMap.company_name }&nbsp;&nbsp;></a></span><!-- <span class="jiaoyizt">交易成功</span> --></div>
					<div class="baomingdd">
		            <a href="${global_url}/goods/getGoodsInfo.htm?goods_id=${orderMap.goods_id}" >
			            <c:choose>
				            <c:when test="${not empty orderMap.goods_image}">
				            	<img src="${orderMap.goods_image}" onerror="nofind();" class="pic">
				            </c:when>
				            <c:otherwise>
				            	<img src="${global_image_url}/noimg.jpg"  onerror="nofind();" class="pic">
				            </c:otherwise>
			            </c:choose>
		            </a>			
		            <div class="rt">
						<div class="tit"><a style="float: left;" href="${global_url}/goods/getGoodsInfo.htm?goods_id=${orderMap.goods_id}">${orderMap.goods_name}</a></div>
		                <div class="price">
		                <c:if test="${orderMap.goods_price=='0' || orderMap.goods_price==0}">电话咨询</c:if>
		                <c:if test="${orderMap.goods_price!='0' && orderMap.goods_price!=0}">￥${orderMap.goods_price}</c:if>
		                <c:if test="${not empty orderMap.payinfo }"><input type="button" value="去支付" onclick="getBrandWCPayRequest('${orderMap.timeStamp}', '${orderMap.nonceStr}', '${orderMap.package}', '${orderMap.paySign}')"></c:if>
		                <c:if test="${orderMap.PAY_STATUS=='1' || orderMap.PAY_STATUS==1}"><font color="green;">(已支付)</font></c:if>
		                </div> 
		               	<div class="tits" style="font-size: 12px;line-height: 20px;overflow: hidden; color:#666">${orderMap.SHIP_NAME}&nbsp;&nbsp;${orderMap.SHIP_MOBILE}</div>
						<%-- <div class="tits" style="font-size: 15px;line-height: 30px;height: 30px;overflow: hidden;">联系电话:${orderMap.SHIP_MOBILE}</div> --%>
						<div class="dush">
							<div class="haoping" style="line-height: 20px;font-size: 10px;">${orderMap.create_time}</div>
		                   
						</div>
					
					</div>
		            </div>
				</li>
         	</c:forEach>
		</c:if>
		<c:if test="${empty orderList}">
			<li style="text-align: center;">暂无信息</li>
		</c:if>
    </ul>
       <div id ="loadingDiv" style="display:none;">正在加载中...</div>
</div>

</body>
</html>
<script>
  function getBrandWCPayRequest(timeStamp, nonceStr, package, paySign){
	   WeixinJSBridge.invoke( 
	       'getBrandWCPayRequest', {
	           "appId" : "wx9209e42ac6c9532a",     //公众号名称，由商户传入     
	           "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
	           "nonceStr" : nonceStr, //随机串     
	           "package" : package,     
	           "signType" : "MD5",         //微信签名方式:     
	           "paySign" : paySign //微信签名 
	       },
	       function(res){      
	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
	        	   alert("支付成功！");
	        	   location.href=location.href;
	           } else { alert("支付失败！");}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
	       }
	   );  
  }  
</script>