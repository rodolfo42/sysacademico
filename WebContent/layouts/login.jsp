<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><decorator:title default="Sistema Pri-si-lá - Login" /></title>
	<%@ include file="/layouts/head.jsp" %>
	<decorator:head />
</head>
<body>
	<div id="loginContainer" class="container">
		<decorator:body />
	</div>
</body>
</html>