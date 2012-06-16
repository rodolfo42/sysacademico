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
<form action="<c:url value="/aluno/${aluno.id }"/>" method="POST">
	<fieldset>
	
	<legend>Adicionar Aluno</legend>
	
	<label for="nome">Nome:</label>
	<input id="nome" type="text" name="aluno.nome" value="${aluno.nome }" />
	
	<label for="data_nasc">Data de Nascimento:</label>
	<input id="data_nasc" name="aluno.dataNascimento" value="${aluno.dataNascimento }" />
	
	<label for="listResponsavel">Responsáveis:</label>
	<select name="aluno.listaResponsavel.id">
		<c:forEach items="${responsavelList }" var="responsavel">
			<option value="${responsavel.id }">${responsavel.nome }</option>
		</c:forEach>
	</select>
	
	<button type="submit" name="_method" value="PUT">Alterar</button>
	<button type="submit" name="_method" value="DELETE">Excluir</button>
	</fieldset>
</form>
</body>
</html>