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
<title>个人信息</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
<script type="text/javascript" src="${global_js_url }/jquery-1.6.4.js"></script>
</head>
<body>
<!-- 
<header class="header">
	<div class="caption">
		<span class="icon back" onclick="history.back()"></span>个人信息
	</div>
</header>
 -->
<div class="gerenlist">
   <input type="hidden" name="id" id="id" value="${memberMap.id}" />
  		 <!-- <div class="gerenicon xx1"></div>头像 -->
<%--    	<center  style="width:100%;padding-top: 10px;">
	    	<c:choose>
		    	<c:when test="${not empty memberMap.face}">
		    		 <img src="${memberMap.face}" name="face" id="face" width="90" height="90">
		    	</c:when>
		    	<c:otherwise>
		    		<img src="${global_image_url}/toux.png" name="face" id="face" width="90" height="90">
		    	</c:otherwise>
	    	</c:choose>
	    	<!-- <img src="images/camon.png" width="28" height="28" class="xiangji"> -->
   	</center> --%>
	<ul>
        <li><div class="gerenicon xx2"></div>用户名<input type="text" value="${memberMap.account}" readonly="readonly" name="account" id="account" class="grxinxi"></li>
        <li><div class="gerenicon xx3"></div>真实姓名<input type="text" value="${memberMap.name}" name="name" id="name" class="grxinxi"></li>
        <li><div class="gerenicon xx5"></div>性别
        	<%-- <input type="text" value="${memberMap.sex}" class="grxinxi"> --%>
       	   <select name="gender" id="gender" style="float: right;height:40px;border: none;">
            	<option value="">不详</option>
            	<option ${(memberMap.gender=='0')?'selected':'' } value="0">男</option>
            	<option ${(memberMap.gender=='1')?'selected':'' } value="1">女</option>
            </select>
        </li>
        <li><div class="gerenicon xx6"></div>联系电话<input type="text" value="${memberMap.phone}" name="phone" id="phone" class="grxinxi"></li>
        <%-- <li><div class="gerenicon xx7"></div>地址<input type="text" value="${memberMap.address}" name="address" id="address" class="grxinxi"></li> --%>
    </ul>
</div>

 <div  class="baocun" ><button onclick="saveMember();">保&nbsp;&nbsp;存</button></div>
</body>
<script type="text/javascript">

function saveMember(){
	var id = $("#id").val();
	var account = $("#account").val();
	var name = $("#name").val();
	//var age_flag = $("#age_flag").val();
	var gender = $("#gender").val();
	//var address = $("#address").val();
	var phone = $("#phone").val();
	
	 /* $.ajax({
			url:"${global_url}/member/saveMemberInfo.htm",
			type:"POST",
			data:{member_id:member_id,login_name:login_name,
				name:name,age_flag:age_flag,
				sex:sex,address:address},
			dataType:"json",
			success:function(json){
						if(json.result!=1){
							callback(false,"用户名不存在！");
							obj.attr('disabled',disabled);
						}else{
							callback(true,"");
							obj.removeAttr("disabled");
						}
					}		
				
		});	 */
	
	  if (confirm("你确定保存吗？")) {  
	  		var result = $.ajax({
				url : "${global_url}/member/saveMemberInfo.htm",
				data:{id:id,
					name:name,gender:gender,
					phone:phone},
				async : false,
				cache : false
				}).responseText;
	  		if(result=="1"){
	  			alert("保存成功");
	  			window.location.href = "${global_url}/member/memberLogin.htm";
	  		}else if(result=="2"){
	  			alert("手机号已被注册，保存失败");
	  			window.location.href = "${global_url}/member/memberInfo.htm";
	  		}
            
        }else{
        	window.location.href = "${global_url}/member/memberInfo.htm";
        }
	
 /* 	var result = $.ajax({
		url : "${global_url}/member/saveMemberInfo.htm",
		data:{member_id:member_id,login_name:login_name,
			name:name,age_flag:age_flag,
			sex:sex,address:address},
		async : false,
		cache : false
		}).responseText;
	if(result=="1"){
		alert("保存成功！");
		window.location.href = "/jsbc_LearningChannel_weixin/member/memberInfo.htm";
	}else {
		alert("保存失败！");
	}  */
}
</script>
</html>
