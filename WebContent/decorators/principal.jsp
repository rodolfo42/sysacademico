<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap-responsive.min.css" />"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css" />"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/estilo.css" />"/>
	<script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.maskedinput-1.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.alphanumeric.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/bootstrap-button.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/funcoes.js"/>"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<decorator:head/>
	<title><decorator:title default="Sistema Pri-si-lá"/></title>
</head>
<body>
<img id="logo" alt="Pri-si-lá" src="<c:url value="/img/logotipo.png" />" />
<div id="menu">
	<ul>
		<li><a href="<c:url value="/menu/inicio" />"><img id="btmenu" alt="Menu Principal" title="Menu Principal" src="<c:url value="/img/home.png"/>"></a></li>
		<li><a href="<c:url value="/responsavel/adicionar" />"><img id="btmatricula" alt="Matricula" title="Matricula" src="<c:url value="/img/matricula.png"/>"></a></li>
		<li><img id="btcurso" alt="Curso" title="Curso" src="<c:url value="/img/curso.png"/>"></li>
		<li><img id="btaula" alt="Aula" title="Aula" src="<c:url value="/img/aula.png"/>"></li>
		<li><img id="btprofessor" alt="Professor" title="Professor" src="<c:url value="/img/professor.png"/>"></li>
		<li><img id="btconfiguracoes" alt="Configurações" title="Configurações" src="<c:url value="/img/configuracoes.png"/>"></li>
	</ul>
</div>
<div id="conteudo" class="well">
	<decorator:body/>
</div>
</body>

</html>