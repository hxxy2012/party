<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="${global_js_url }/jquery-1.8.3.min.js" type="text/javascript" ></script>
<script>
jQuery.noConflict();    //将变量$的控制权移交给JsCOM.js
function click_layer() {
	var ret = jQuery("#show").attr("mark");
	if (ret == "show") {
		jQuery("#show").hide();
		jQuery("#show").attr("mark", "hide");
	} else {
		jQuery("#show").show();
		jQuery("#show").attr("mark", "show");
	}
}
</script>
<!--  
<div class="layer">
	<a class="layer_menu" href="javascript:void(0);" onclick="click_layer();"><img src="${global_image_url}/layer_05.png" width="35" height="35"><span class="black_bg"></span></a>
	<div class="layer_show" id="show" mark="show"  >
		<ul>
			<li><a href="${global_url}/wfw/init.htm?shequId=${shequId}"><img src="${global_image_url}/layer_01.png" width="20" height="20"><p>首页</p></a></li>
			<li><a href="${global_url}/member/initBaseMember.htm?shequId=${shequId}"><img src="${global_image_url}/layer_04.png" width="20" height="20"><p>个人中心</p></a></li>
		</ul>
		<div class="show_bg"></div>
	</div>
</div>
-->

<div class="fd_mag"></div>
<div class="bottom_fd">
	<ul class="bottom_fd_3">
		<li><a href="${global_url}/wfw/init.htm?shequId=${shequId}"><img src="${global_image_url }/bottom_ico_home.png" /><p style="line-height:30px;color: #39B54D;">首页</p></a></li>
		<li><a href="${global_url}/member/wgy/wgyLogin.htm?shequId=${shequId}"><img src="${global_image_url }/bottom_ico_net.png" /><p style="line-height:30px;color: #39B54D;">网格员</p></a></li>
		<li><a href="${global_url}/member/initBaseMember.htm?shequId=${shequId}"><img src="${global_image_url }/bottom_ico_user.png" /><p style="line-height:30px;color: #39B54D;">个人中心</p></a></li>
	</ul>
</div>
