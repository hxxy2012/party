<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>社区生活</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript">
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
</head>
<body>
<!--海报-->
<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="${global_image_url}/banner_sh.jpg"></a></li>
	</ul>
</div>
	
<div class="wsh_index">
	
	<div class="wsh_100 essc_position bor-bot">
		<a href="${global_url}/wsh/essc/getESSC.htm?shequId=${shequId}">
			<h3 class="essc_text" style="color:#2DA7B9">二手市场<p>安全可靠</p></h3>
            <img class="essc_img essc_img1"  width="100px;" height="100px" src="${global_image_url}/wsh_essc.png" />
     	</a>
	</div>
       
	<div class="wsh_100 bor-bot">
		<a href="${global_url}/wsh/jzfw/init.htm?shequId=${shequId}">
			<div class="wsh_50 wsh_two essc_position bor-rig">
				<h3 class="essc_text" style="color:#FAB240">家政服务<p>安全可靠</p></h3>
          		<img class="essc_img essc_img1"  width="70px;" height="100px" src="${global_image_url}/wsh_jzfw.png" />
			</div>
		</a>
           
		<a href="${global_url}/wsh/cyfw/init.htm?shequId=${shequId}">
			<div class="wsh_50 wsh_two essc_position" style="color:#ACCE07">
           		<h3 class="essc_text">餐饮服务<p>安全可靠</p></h3>
           		<img class="essc_img essc_img1"  width="70px;" height="100px" src="${global_image_url}/wsh_cyfw.png" />
			</div>
		</a>
	</div>
       
	<div class="wsh_100 bor-bot">
		<a href="${global_url}/wsh/qtfw/init.htm?shequId=${shequId}">
			<div class="wsh_70 wsh_two essc_position bor-rig">
      			<h3 class="essc_text" style="color:#529CE5">其他服务<p>安全可靠</p></h3>
                <img class="essc_img essc_img1"  width="100px;" height="100px" src="${global_image_url}/wsh_qtfw.png" />
			</div>
		</a>
           
		<a href="${global_url}/wsh/rztj/init.htm?shequId=${shequId}">
			<div class="wsh_30 wsh_two essc_position">
				<h3 class="essc_rztj">商户入驻</h3>
  			</div>
		</a>
	</div>
</div>
    
<!--推荐家政-->
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5 > <img src="${global_image_url}/ico_jzfw.png" width="25px" height="25px;" style="position: relative;top: 6px;" /> &nbsp;推荐家政</h5>
			<div class="more"><a href="${global_url}/wsh/jzfw/init.htm?shequId=${shequId}"><i class="ico-right"></i></a></div>
		</div>	
	</section>
   <div class="Classall">
	<ul class="allcon">
	
		<c:if test="${not empty fwList}">
		       	<c:forEach var="fwMap" items="${fwList}" begin="0" end="4">
			       	<c:if test="${not empty fwMap && fwMap.serviceType=='0'}">
			       		<li>
				       		<a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?rztjId=${fwMap.rztjId}" >
				       			<c:if test="${not empty fwMap.paramMap.imagePaths}"><img src="${fwMap.paramMap.imagePaths}" class="pic"></c:if>
		       					<c:if test="${empty fwMap.paramMap.imagePaths}"><img src="${global_image_url}/mr_jzfw.png" class="pic"></c:if>
				       		</a>
							<div class="rt">
								<div class="tit"><a href="${global_url}/wsh/jzfw/getJzfwDetail.htm?rztjId=${fwMap.rztjId}">${fwMap.title}</a></div>
								<div class="dush">
				                	<div ><i class="esh_jg">${fwMap.price}</i></div>
									<div >${fwMap.createdTime}</div>
								</div>
							</div>
				        </li>
		        	</c:if>
		       	</c:forEach>
       	</c:if>
    </ul>
</div>
</div><!--推荐家政结束-->

<!--推荐餐饮-->
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><img src="${global_image_url}/tjcy01.png" width="25px" height="25px;" style="position: relative;top: 6px;" /> &nbsp;推荐餐饮</h5>
			<div class="more"><a href="${global_url}/wsh/cyfw/init.htm?shequId=${shequId}"><i class="ico-right"></i></a></div>
		</div>	
	</section>
   <div class="Classall">
	<ul class="allcon">
		<c:if test="${not empty fwList}">
		       	<c:forEach var="fwMap" items="${fwList}" begin="0" end="4">
			       	<c:if test="${not empty fwMap && fwMap.serviceType=='1'}">
			       		<li>
				       		<a href="${global_url}/wsh/cyfw/getCyfwDetail.htm?rztjId=${fwMap.rztjId}" >
				       			<c:if test="${not empty fwMap.paramMap.imagePaths}"><img src="${fwMap.paramMap.imagePaths}" class="pic"></c:if>
		       					<c:if test="${empty fwMap.paramMap.imagePaths}"><img src="${global_image_url}/mr_cyfw.png" class="pic"></c:if>
				       		</a>
							<div class="rt">
								<div class="tit"><a href="${global_url}/wsh/cyfw/getCyfwDetail.htm?rztjId=${fwMap.rztjId}">${fwMap.title}</a></div>
								<div class="dush">
				                	<div ><i class="esh_jg">${fwMap.price}</i></div>
									<div >${fwMap.createdTime}</div>
								</div>
							</div>
				        </li>
		        	</c:if>
		       	</c:forEach>
       	</c:if>
    </ul>
</div>
</div>
</body>
</html>

