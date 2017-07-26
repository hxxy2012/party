<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>智慧社区</title>
<script type="text/javascript" src="../js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="../js/adv.js"></script>
<link rel="stylesheet" type="text/css" href="../styles/css.css" />
</head>
<body>
<header class="header">
	<div class="logo"><img src="../images/logo.png" class="logo_img" /></div>
	<div class="history">
		<a href="#" class="menu">南京<span></span></a>
	</div>
</header>
<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="../images/banner1.jpg"></a></li>
		<li style="display:none;"><a href="#"><img src="../images/banner1.jpg"></a></li>
	</ul>
	 <div class="slide-controls">
			<span class="curr">1</span>
			<span class="">2</span>
		</div>
	<div class="indicator">
		<div class="indicator-num">
			<span class="point"></span>
			<span class="point"></span>
			<span class="point selected"></span>
			<span class="point"></span>
			<span class="point"></span>
		</div>
	</div>
</div><!--海报结束-->

<section class="tv-channel">
	
	<ul class="channels">
		<c:if test="${not empty goodsTypeList}">
			<c:forEach var="typeMap" items="${goodsTypeList}" varStatus="stat"> 
				<c:if test="${typeMap.goods_type_lv==1}">
					<li>
						<a href="${global_url}/index/goodsType/getGoodsType.htm?id=${typeMap.CAT_ID }">
							<span class="ico cnl${stat.index+1}"></span>
							<p>${typeMap.NAME}</p>
						</a>
					</li>
				</c:if>
			</c:forEach>
		</c:if>
		
	<!--
		<li>
			<a href="#">
				<span class="ico cnl1"></span>
				<p>学习培训</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl2"></span>
				<p>电脑/IT培训</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl3"></span>
				<p>职业技能</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl4"></span>
				<p>资格认证</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl5"></span>
				<p>企业管理</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl6"></span>
				<p>文体艺术</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl7"></span>
				<p>中小学幼儿</p>
			</a>
		</li>
		<li>
			<a href="#">
				<span class="ico cnl8"></span>
				<p>出国留学</p>
			</a>
		</li>
		-->  
	</ul><!--分类结束-->   
</section>
<div class="message">
    <a href="#"><span></span>关于新版本上线的通知</a>
    </div><!--通知公告-->
    
    
<div class="container">
	<c:if test="${not empty goodsTypeList}">
			<c:forEach var="typeMap" items="${goodsTypeList}" varStatus="stat"> 
				<c:if test="${typeMap.goods_type_lv==1}">
					<section class="module">
						<div class="titlebar">
							<h5><span class="colormenu line${stat.index+1}"></span>${typeMap.NAME}</h5>
							<div class="more"><a href="#"><i class="ico-right"></i></a></div>
						</div>	
					</section>
					<c:if test="${not empty typeMap.twoGoodsTypeList}">
							<div class="module-c">
							     <ul class="module-menu">
							     	<c:forEach var="twoTypeMap" items="${typeMap.twoGoodsTypeList}" varStatus="twoStat"> 
										<c:if test="${twoTypeMap.goods_type_lv==2}">
												<c:if test="${fn:length(typeMap.twoGoodsTypeList)>twoStat.index+1}">
								     				<li><a href="#">${twoTypeMap.NAME}</a><span>|</span></li>
								     			</c:if>  
								     			<c:if test="${fn:length(typeMap.twoGoodsTypeList)==twoStat.index+1}">
								     				<li><a href="#">${twoTypeMap.NAME}</a></li>
								     			</c:if>  
								    	</c:if>  
				    				</c:forEach>
							     </ul>
						    </div> 
					</c:if>
				</c:if>
			</c:forEach>
	</c:if>
</div>
    
<!--
<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu line1 "></span>学习培训</h5>
			<div class="more"><a href="#"><i class="ico-right"></i></a></div>
		</div>	
	</section>
    <div class="module-c">
     <ul class="module-menu">
     	<li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
     </ul>
    </div> 
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu line2 "></span>电脑/IT培训</h5>
			<div class="more"><a href="#"><i class="ico-right"></i></a></div>
		</div>	
	</section>
    <div class="module-c">
     <ul class="module-menu">
     	<li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
     </ul>
    </div> 
</div>

<div class="container">
	<section class="module">
		<div class="titlebar">
			<h5><span class="colormenu line3 "></span>职业技能培训</h5>
			<div class="more"><a href="#"><i class="ico-right"></i></a></div>
		</div>	
	</section>
    <div class="module-c">
     <ul class="module-menu">
     	<li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a><span>|</span></li>
        <li><a href="#">语言培训</a></li>
     </ul>
    </div> 
</div>
-->

</body>
</html>

