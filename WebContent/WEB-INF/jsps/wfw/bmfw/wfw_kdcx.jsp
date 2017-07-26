<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
  response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
  String newLocn = "http://m.kuaidi100.com/index_all.html";
  response.setHeader("Location",newLocn);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0;">
<title>快递查询</title>
<link rel="stylesheet" type="text/css" href="${global_css_url }/css.css" />
</head>
<body>
</body>
</html>