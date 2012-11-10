<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			</ul>
			<ul class="nav pull-right">
				<li><a rel="tooltip" data-placement="bottom" title="Gerencie os usuários deste sistema" href="<c:url value="/usuarios/listar" />">Usuários</a></li>
				<li class="divider-vertical"></li>
				<li id="loginInfo">
					<p class="navbar-text cursor-help">
						<i class="icon-user"></i>
						<span></span>
					</p>
				</li>
				<li><a href="<c:url value="/logout" />"><i class="icon-off"></i> Logout</a></li>
			</ul>
		</div>
	</div>
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
				
				$('span', this).text(login);
				$('p', this).tooltip({
					title: "Você está logado como " + nome,
					placement: "bottom"
				});
			}
		});
	});
	</script>
</body>
</html>