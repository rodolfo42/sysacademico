<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<form action="<c:url value="/cursos/adicionar"/>" method="POST">
		<fieldset>
			<legend>Adicionar Curso</legend>

			<label for="nome">Nome:</label> <input id="nome" type="text" name="curso.nome" />

		</fieldset>
		<button type="submit" class="btn">Enviar</button>
	</form>
</body>
</html>