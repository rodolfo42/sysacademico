<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/matriculas/${matricula.id }"/>" method="POST">
		<fieldset>
			<legend>Matr�cula</legend>

			<label for="data">Data:</label> <input id="data" type="text" name="matricula.data" value="${matricula.data }" /> <label
				for="aluno">Aluno:</label> <select name="matricula.aluno.id" id="aluno">
				<option value="0">Selecionar</option>
				<c:forEach items="${alunoList }" var="aluno">
					<option value="${aluno.id }">${aluno.nome }</option>
				</c:forEach>
			</select> <label for="responsavel">Respons�vel:</label> <select name="matricula.responsavel.id" id="responsavel">
				<option value="0">Selecionar</option>
				<c:forEach items="${responsavelList }" var="responsavel">
					<option value="${responsavel.id }">${responsavel.nome }</option>
				</c:forEach>
			</select> <label for="curso">Curso:</label> <select name="matricula.curso.id" id="curso">
				<c:forEach items="${cursoList }" var="curso">
					<option value="${curso.id }">${curso.nome }</option>
				</c:forEach>
			</select> <label for="tipoAula">Tipo de Aula:</label> <select name="matricula.listaTipoAula" id="tipoAula">
				<c:forEach items="${tipoAulaList }" var="tipoAula">
					<option value="${tipoAula }">${tipoAula.nome }</option>
				</c:forEach>
			</select>

		</fieldset>
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
	</form>
</body>
</html>