<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value="/aluno/adicionar"/>" method="POST">
	<fieldset>
		<legend>Adicionar Aluno</legend>
		
		<label for="nome">Nome:</label>
		<input id="nome" type="text" name="aluno.nome" />
		
		<label for="data_nasc">Data de Nascimento:</label>
		<input id="data_nasc" name="aluno.dataNascimento" />
		
		<label for="listResponsavel">Responsáveis:</label>
		<select name="aluno.listaResponsavel.id">
			<c:forEach items="${responsavelList }" var="responsavel">
				<option value="${responsavel.id }">${responsavel.nome }</option>
			</c:forEach>
		</select>
	</fieldset>
	<button type="submit" class="btn">Enviar</button>
</form>
</body>
</html>