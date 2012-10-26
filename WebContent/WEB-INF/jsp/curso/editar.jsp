<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/cursos/${curso.id }"/>" method="POST">
		<fieldset>

			<legend>Adicionar Curso</legend>

			<label for="nome">Nome:</label> <input id="nome" type="text" name="curso.nome" value="${curso.nome }" />

			<button type="submit" name="_method" value="PUT">Alterar</button>
			<button type="submit" name="_method" value="DELETE">Excluir</button>
		</fieldset>
	</form>
</body>
</html>