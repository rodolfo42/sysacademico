<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="frmMatricula" action="<c:url value="/matriculas/guardaNaSessao"/>" method="POST">
		<fieldset>
			<legend>Matrícula</legend>
			
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
	<script type="text/javascript">	
		
		$('#aluno').change(function(){
			carregaComboJson('<c:url value="/matriculas/responsavel.json"/>',
							 'idAluno='+$(this).val(),
							 'responsavel',
							 montaComboResponsavel);
		});
		
		var montaComboResponsavel = function(json, $combo){
			for ( var i = 0; i < json.list.length; i++) {
				item = json.list[i];
				$combo.append('<option value="' + item.id + '">' + item.nome + '</option>');
			}
		};
	</script>
</body>
</html>