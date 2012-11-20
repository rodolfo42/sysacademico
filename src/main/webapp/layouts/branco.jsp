<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><decorator:title default="Sistema Pri-si-lá" /></title>
<%@ include file="/layouts/head.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/pagina.css" />" />
<decorator:head />
</head>
<body>
	<div class="container">
		<decorator:body />
	</div>
</body>
</html>