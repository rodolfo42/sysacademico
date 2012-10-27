<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<decorator:usePage id="thePage" />
<%
String nomeUsuario = thePage.getProperty("meta.usuario.nome");
String loginUsuario = thePage.getProperty("meta.usuario.login");
%>
<!DOCTYPE html>
<html>
<head>
<title><decorator:title default="Sistema Pri-si-lá" /></title>
<%@ include file="/layouts/head.jsp"%>
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
				<li class="dropdown"><a rel="tooltip" data-placement="bottom" title="Gerenciar e efetuar matrículas"
					class="dropdown-toggle" href="javascript:void(0);">Alunos</a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/alunos/cadastrar" />">Cadastrar aluno</a></li>
						<li><a href="<c:url value="/alunos/listar" />">Buscar aluno</a></li>
					</ul>
				</li>
				<li class="dropdown"><a rel="tooltip" data-placement="bottom" title="Gerenciar e efetuar matrículas"
					class="dropdown-toggle" href="javascript:void(0);">Matrículas</a>
					<ul class="dropdown-menu">
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
				<li><p class="navbar-text" rel="tooltip" data-placement="bottom" 
					data-title="Você está logado como <%= nomeUsuario %>"><i class="icon-user"></i> <%= loginUsuario %></p></li>
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="/logout" />"><i class="icon-off"></i> Logout</a></li>
			</ul>
		</div>
	</div>
	<div id="mainContainer" class="container">
		<decorator:body />
	</div>
</body>
</html>