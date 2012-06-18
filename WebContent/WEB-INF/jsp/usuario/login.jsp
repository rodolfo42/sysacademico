<%@include file="../tags.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="alert alert-error" id="alerta">
O nome de usuário ou a senha inserido está incorreto!
</div>
<div id="login">
<form action="<c:url value="/login"/>" method="POST" class="well">
	<fieldset>
		<legend>Login</legend>
		<label for="login">Usuário:</label>
		<input id="login" type="text" name="usuario.login"/>
		<br />
		<label for="senha">Senha:</label>
		<input id="senha" type="password" name="usuario.senha" />
		<br />
		<button type="submit" class="btn">Entrar</button>
	</fieldset>
</form>
</div>
<%@include file="../principal.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('div#alerta').hide();
	<c:if test="${falha}">
		$('div#alerta').show();
	</c:if>
});
</script>
</body>
</html>