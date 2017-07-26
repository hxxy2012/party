<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>亮身份</title>
<link rel="stylesheet" type="text/css" href="${dj_css_url }/css.css" />
<script type="text/javascript">
//检测浏览器是否支持localStorage
var strStoreDate=window.localStorage?localStorage.getItem("member_account"):Cookie.read("member_account");
if(strStoreDate!=null){
	var shequId = "${shequId}";
	var result = $.ajax({
		url : "${global_url}/member/checkMemberState.htm?member_account="+strStoreDate+"&shequId="+shequId,
		async : false,
		cache : false
	}).responseText;
}
</script>



<script type="text/javascript">
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
<!-- 幻灯片 -->

</head>
<body>
<!--欢迎头-->
<div class="message">
    <p>欢迎 HOME，光临先锋党建平台</p>
</div>
<div class="mes_mag"></div><!--欢迎头-->

<div class="slider">
	<ul class="slide-banner">
		<li><a href="#"><img src="../images_dj/banner_dt_djxf.png"></a></li>
	</ul>
</div><!--海报结束-->

<!--主体内容-->
<ul class="hd_content">
	<a href="dj_lsf_dyrz.html"><li>
		<img src="../images_dj/liang_renzheng.png"/>
		<p>党员认证</p>
	</li></a>
	<a href="dj_lsf_zgx.html"><li>
		<img src="../images_dj/liang_zhuanguanxi.png"/>
		<p>转关系</p>
	</li></a>
  <a href="/dj_lsf_jdf.html">  <li>
		<img src="../images_dj/liang_jiaofabgfei.png" />
		<p>交党费</p>
	</li></a>

	<div class="clear"></div>
</ul><!--主体内容结束-->

<!--底部-->
<div class="fd_mag"></div>
<ul class="footer">
   	<li><a  href="dj_lsf_index.html">
    	<img src="../images_dj/icon_liangshenfeng_hong@2x.png" />
        <p class="red">亮身份</p></a>
   	 </li>
   	 <li><a href="dj_zzs_index.html">
    	<img src="../images_dj/icon_zhangzhishi_hui@2x.png" />
        <p>涨姿势</p></a> 
   	 </li>
     <li><a href="dj_ajl_dsh.html">
    	<img src="../images_dj/icon_aijiaoliu_hui@2x.png" />
        <p>爱交流</p></a>
   	 </li>
	 <li><a href="#">
    	<img src="../images_dj/icon_wode_hui.png" />
        <p>我的</p> </a>
   	 </li>
</ul><!--底部结束-->
    

</body>
</html>
