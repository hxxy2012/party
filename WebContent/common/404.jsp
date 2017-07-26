<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${global_url }css/css.css" />
</head>
<body>
   <!-- 首页主题右侧开始 -->
	<div id="boxright">
	      
	     <div class="boxright_top"><img src="${global_image_url }icon.jpg">页面不存在</div>
		 
		 
        <div id="adminform">
		
		
            <div class="construction">
			
			   <div class="construction_left"><img src="${global_image_url }peple.jpg"></div>
			   <div class="construction_right">
			     <h3 style="margin-bottom:5px;">抱歉，您访问的页面不存在！</h3>
				 <p>无法访问本页的原因是：</p>
                 <p>所访问的页面正在建设或者已被管理员删除！</p>
				 <input type="button" value="返回" class="back" onclick="javascript:window.history.back(-1);" />
			   </div>
			</div>
	
       </div>
	</div>
</body>
</html>
