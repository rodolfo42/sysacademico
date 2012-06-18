<%@include file="../tags.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="menu">
	<ul>
		<li><img id="btmenu" alt="Menu Principal" title="Menu Principal" src="<c:url value="/img/home.png"/>"></li>
		<li><img id="btmatricula" alt="Matricula" title="Matricula" src="<c:url value="/img/matricula.png"/>"></li>
		<li><img id="btcurso" alt="Curso" title="Curso" src="<c:url value="/img/curso.png"/>"></li>
		<li><img id="btaula" alt="Aula" title="Aula" src="<c:url value="/img/aula.png"/>"></li>
		<li><img id="btprofessor" alt="Professor" title="Professor" src="<c:url value="/img/professor.png"/>"></li>
		<li><img id="btconfiguracoes" alt="Configurações" title="Configurações" src="<c:url value="/img/configuracoes.png"/>"></li>
	</ul>
</div>
<%@include file="../principal.jsp" %>
<script type="text/javascript">
	$('#btmatricula').click(function(){
		location.href = '<c:url value="/responsavel/adicionar"/>';
	});
</script>
</body>
</html>