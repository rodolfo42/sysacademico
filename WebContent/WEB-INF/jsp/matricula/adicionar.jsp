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
<div class="span12">
<form id="frmMatricula" action="<c:url value="/matricula/adicionar"/>" class="well" method="POST">
	<fieldset>
		<legend>Matrícula</legend>
		
		<label for="data">Data:</label>
		<input id="data" class="input-small" type="text" name="matricula.data" />
		
		<label for="aluno">Aluno:</label>
		<select name="matricula.aluno.id" id="aluno">
			<option value="0">Selecionar</option>
			<c:forEach items="${alunoList }" var="aluno">
				<option value="${aluno.id }">${aluno.nome }</option>
			</c:forEach>
		</select>

		
		<label for="responsavel">Responsável:</label>
		<select name="matricula.responsavel.id" id="responsavel">
			<option value="0">Selecionar</option>
			<c:forEach items="${responsavelList }" var="responsavel">
				<option value="${responsavel.id }">${responsavel.nome }</option>
			</c:forEach>
		</select>
		
		<label for="tipoMatricula">Tipo de Matrícula:</label>
		<select name="matricula.tipoMatricula" id="tipoMatricula">
			<c:forEach items="${tipoMatriculaList }" var="tipoMatricula">
				<option value="${tipoMatricula }">${tipoMatricula.nome }</option>
			</c:forEach>
		</select>
		
		<label for="curso">Curso:</label>
		<select name="matricula.curso.id" id="curso">
			<c:forEach items="${cursoList }" var="curso">
				<option value="${curso.id }">${curso.nome }</option>
			</c:forEach>
		</select>
		
		<label for="tipoAula">Tipo de Aula:</label>
		<select name="matricula.listaTipoAula" id="tipoAula">
			<c:forEach items="${tipoAulaList }" var="tipoAula">
				<option value="${tipoAula }">${tipoAula.nome }</option>
			</c:forEach>
		</select>
		
		
		
	</fieldset>
	<button type="submit" class="btn">Enviar</button>
</form>
<div class="btn-group" data-toggle="buttons-checkbox">
	<c:forEach items="${tipoAulaList }" var="tipoAula">
		<button class="btn btn-warning">${tipoAula.nome }</button>
	</c:forEach>
</div>
</div>
<%@include file="../principal.jsp" %> 
<script type="text/javascript">
	
	$('#aluno').change(function(){
		carregaComboJson('<c:url value="/matricula/responsavel.json"/>','idAluno='+$(this).val(),'responsavel');
	});
</script>
</body>
</html>