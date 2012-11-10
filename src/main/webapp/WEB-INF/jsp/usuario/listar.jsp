<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<div class="row">
		<div class="span7">
			<a href="<c:url value="/usuarios/adicionar" />" class="btn btn-success pull-right aoLadoH2"><i
				class="icon-white icon-plus"></i> novo usuário</a>
			<h2>Usuários cadastrados</h2>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Nome completo</th>
						<th>Login</th>
						<th style="width: 65px;">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="not empty ${usuarioList}">
							<c:forEach items="${usuarioList}" var="usuario">
								<tr>
									<td>${usuario.nome}</td>
									<td>${usuario.login}</td>
									<td>
										<div class="btn-group pull-right">
											<a class="btn btn-mini" href="<c:url value="/usuarios/editar/${usuario.login}" />">editar</a> <a
												class="btn btn-mini dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span> </a>
											<ul class="dropdown-menu">
												<li><a data-confirmation-text="Deseja realmente remover o usuário ${usuario.nome}?"
													href="<c:url value="/usuarios/remover/${usuario.login}" />"><i class="icon icon-remove"></i> remover</a>
												</li>
											</ul>
										</div></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="3" class="textCenter muted">
									Não foram encontrados outros usuários além de você.<br />
									Use o botão "novo usuário" para registrar novos usuários!
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<p class="textRight">
				<strong>${usuarioList.size()}</strong> usuários no total
			</p>
		</div>
		<div class="span5">
			<div class="well voceSabiaQue textJustify">
				<h3>Instruções</h3>
				<p>
					Para <strong>editar</strong> os dados de um usuário, clique em "editar".
				</p>
				<p>
					Para <strong>remover</strong> um usuário do sistema, impossibilitando-o de acessar o sistema, clique na seta ao
					lado de "editar", e então verá a opção "remover". Clique e confirme sua escolha.
				</p>
				<p><span class="label label-info">DICA</span> para editar seus próprios dados pessoais, <strong>clique em seu nome de usuário</strong> no canto superior direito da tela, e então escolha "editar perfil".</p>
				<p><span class="label label-warning">AVISO</span> não é possível alteração de senha de outros usuários</p>
			</div>
			<div class="alert alert-block alert-info">
				<h4>Sugestão</h4>
				<p>Ao utilizar este sistema, evite compartilhar contas de usuário entre mais de uma pessoa. Isso pode levar á
					desorganização de processos e problemas técnicos futuros. Sempre utilize sua própria conta, e de mais ninguém.</p>
			</div>
		</div>
	</div>
</body>
</html>