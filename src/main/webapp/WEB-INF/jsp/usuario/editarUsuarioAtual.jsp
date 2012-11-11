<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<div class="row">
		<div class="span8">
			<h2>Editar meu perfil</h2>
			
			<c:if test="${not empty errors}">
				<div class="alert alert-block alert-error">
					<h4>Corrija os seguintes erros</h4>
					<ul>
						<c:forEach items="${errors}" var="error">
							<li>${error.message}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			
			<form action="" method="POST" class="form-horizontal">
				<div class="control-group">
					<div class="control-label">Nome completo</div>
					<div class="controls">
						<input type="text" maxlength="50" name="usuario.nome" class="input-xlarge" value="${usuario.nome}" />
					</div>
				</div>
				<div class="control-group">
					<div class="control-label">Nome de usuário</div>
					<div class="controls">
						<input type="text" maxlength="50" class="input-large" placeholder="somente letras e números" name="usuario.login" value="${usuario.login}" />
					</div>
				</div>

				<div class="control-group">
					<div class="control-label">Senha atual</div>
					<div class="controls">
						<input type="password" maxlength="50" class="input-medium" name="senhaAtual" />
					</div>
				</div>
				<div class="control-group">
					<div class="control-label">Nova senha</div>
					<div class="controls">
						<input type="password" maxlength="50" class="input-medium" name="usuario.senha" /> <span class="help-block">
							<span class="label label-info">Dica</span> não use datas de aniversário ou nomes de pessoas relacionadas </span>
					</div>
				</div>
				<div class="control-group">
					<div class="control-label">Confirmação da nova senha</div>
					<div class="controls">
						<input type="password" maxlength="50" class="input-medium" name="confirmacaoSenha" /> <span class="help-block">Repita
							a senha para evitar erros de digitação</span>
					</div>
				</div>
				<div class="form-actions">
					<button class="btn btn-primary pull-left" type="submit">Atualizar cadastro</button>
					<a class="btn pull-right" href="<c:url value="/usuarios/listar" />">voltar</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>