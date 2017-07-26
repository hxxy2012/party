<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--<c:set var="global_url" value="http://weixin.xuexipindao.cn" />-->
<c:set var="global_url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<c:set var="global_css_url" value="${pageContext.request.contextPath}/styles" />
<c:set var="global_js_url" value="${pageContext.request.contextPath}/js" />
<c:set var="global_image_url" value="${pageContext.request.contextPath}/images" />
<c:set var="image_server" value="http://localhost:8080/fileServer/"/>
<!--锡山-->
<c:set var="xishan_css_url" value="${pageContext.request.contextPath}/styles_xishan" />
<c:set var="xishan_js_url" value="${pageContext.request.contextPath}/js_xishan" />
<c:set var="xishan_image_url" value="${pageContext.request.contextPath}/images_xishan" />
<!--党建 -->
<c:set var="dj_css_url" value="${pageContext.request.contextPath}/styles_dj" />
<c:set var="dj_js_url" value="${pageContext.request.contextPath}/js_dj" />
<c:set var="dj_image_url" value="${pageContext.request.contextPath}/images_dj" />