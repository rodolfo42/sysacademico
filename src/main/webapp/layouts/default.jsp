<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title><decorator:title default="Sistema Pri-si-lá" /></title>
<%@ include file="/layouts/head.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/default.css" />" />
<decorator:head />
</head>
<body>
	<div id="topNavBar" class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<a class="brand" href="javascript:void(0);">Sistema pri-si-lá</a>
			<ul class="nav">
				<li><a rel="tooltip" data-placement="bottom" title="Ir para a página inicial" href="<c:url value="/" />">Início</a>
				</li>
				<li class="dropdown">
					<a id="dLabel" rel="tooltip" data-placement="bottom" title="Gerenciar e efetuar matrículas" data-toggle="dropdown"
						class="dropdown-toggle" href="javascript:void(0);">Alunos</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						<li><a href="<c:url value="/alunos/cadastrar" />">Cadastrar aluno</a></li>
						<li><a href="<c:url value="/alunos/listar" />">Buscar aluno</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a id="dLabel2" rel="tooltip" data-placement="bottom" title="Gerenciar e efetuar matrículas" data-toggle="dropdown"
						class="dropdown-toggle" href="javascript:void(0);">Matrículas</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel2">
						<li><a href="<c:url value="/alunos/cadastrar" />">Nova matrícula</a></li>
						<li><a href="<c:url value="/alunos/listar" />">Buscar matrícula</a></li>
					</ul>
				</li>
				<li><a rel="tooltip" data-placement="bottom" title="Marcação de aulas" href="<c:url value="/aulas" />">Aulas</a>
				</li>
				<li><a rel="tooltip" data-placement="bottom" title="Cadastro de professores"
					href="<c:url value="/professores" />">Professores</a>
				</li>
				<li class="divider-vertical"></li>
				
			</ul>
			<ul class="nav pull-right">
				<li id="loginInfo" class="dropdown">
					<a id="dLabel3" href="javascript:void(0);" rel="tooltip" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-user"></i>
						<span class="username"></span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel3">
						<li><a href="<c:url value="/perfil/editar" />"><i class="icon-wrench"></i> Editar meu perfil</a></li>
						<li id="btnGerenciarUsuarios" style="display: none;">
							<a href="<c:url value="/usuarios/listar"/>" 
								rel="tooltip" data-placement="left" title="Gerencie os usuários deste sistema"><i class="icon-key"></i> Gerenciar usuários</a>
						</li>
						<li><a href="<c:url value="/logout" />"><i class="icon-off"></i> Logout</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<tag:alert />
	<div id="mainContainer" class="container">
		<decorator:body />
	</div>
	<script type="text/javascript">
	$(function(){
		$.ajax({
			url: "<c:url value="/logininfo" />",
			type: "GET",
			dataType: "json",
			context: $("#loginInfo"),
			success: function(response) {
				var login = response.usuario.login;
				var nome = response.usuario.nome;
				var isAdmin = response.usuario.admin;
				
				$('span.username', this).text(login);
				$('p', this).tooltip({
					title: "Você está logado como " + nome,
					placement: "bottom"
				});
				if(isAdmin) {
					$("#btnGerenciarUsuarios").show(0);
				}
			}
		});
		
		/* <c:if test="${not empty mensagem}">
		var alertHolder = null;
		if($('#feedback').length > 0) {
			alertHolder = $('#feedback');
		} else {
			alertHolder = $('<div class="alert"></div>').insertBefore('#mainContainer');
			alertHolder.attr('id', 'feedback');
		}
		alertHolder.addClass("alert-${mensagem.tipo}").text("${mensagem.mensagem}");
		</c:if> */
	});
	</script>
</body>
</html>