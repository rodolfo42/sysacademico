<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<form id="frmMatricula" action="<c:url value="/matriculas/guardaNaSessao"/>" method="POST">
		<fieldset>
			<legend>Matrícula</legend>
			<tag:validationalert />
			
			<label for="aluno">Aluno:</label>
			<p class="lead">${matricula.aluno.nome}</p>
			<input type="hidden" name="matricula.aluno.id" value="${matricula.aluno.id}" />
	
			
			<label for="responsavel">Responsável:</label>
			<select name="matricula.responsavel.id" id="responsavel">
				<option value="0">Selecionar</option>
				<c:forEach items="${responsavelListPorAluno }" var="responsavel">
					<option value="${responsavel.id }">${responsavel.nome }</option>
				</c:forEach>
			</select>
			
			<label for="curso">Curso:</label>
			<select name="matricula.curso.id" id="curso">
				<option value="0">Selecionar</option>
				<c:forEach items="${cursoList }" var="curso">
					<option value="${curso.id }">${curso.nome }</option>
				</c:forEach>
			</select>

		</fieldset>
		<button type="submit" class="btn">Enviar</button>
	</form>
</body>
</html>