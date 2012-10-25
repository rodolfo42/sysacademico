<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<h2>Login</h2>
		</div>
		<div class="row-fluid">
			<tag:alert />
		</div>
		<div class="row-fluid">
			<form action="<c:url value="/login"/>" method="POST">
				<div class="controls">
					<input class="span12" type="text" name="usuario.login" placeholder="Nome de usuário"
						rel="tooltip" data-placement="right" data-trigger="focus" 
						title="Em minúsculo e sem espaços" />
				</div>
				<div class="controls">
					<input class="span12" type="password" name="usuario.senha" placeholder="Senha"
						rel="tooltip" data-placement="right" data-trigger="focus" 
						title="Mínimo de 8 caracteres" />
				</div>
				<div class="controls">
					<button type="submit" class="span4 offset4 btn btn-primary">Login</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>