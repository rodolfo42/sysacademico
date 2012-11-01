<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="frmMatricula" action="<c:url value="/matriculas/adicionar"/>" method="POST">
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
			
			<label for="curso">Curso:</label>
			<select name="matricula.curso.id" id="curso">
				<option value="0">Selecionar</option>
				<c:forEach items="${cursoList }" var="curso">
					<option value="${curso.id }">${curso.nome }</option>
				</c:forEach>
			</select>
			
			<label for="horaDisp">Horários Disponíveis:</label>
			<select name="matricula.timestamp" id="horaDisp">
				
			</select>
			
			<div class="control-group">
				<label class="control-label">Tipo de Aula:</label>
				<div class="btn-group" data-toggle="buttons-checkbox">
					<c:forEach items="${tipoAulaList }" var="tipoAula">
						<button class="btn btn-warning" name="btnTipoAula">${tipoAula.nome }</button>
					</c:forEach>
				</div>
			</div>


		</fieldset>
		<button type="submit" class="btn">Enviar</button>
	</form>
	<script type="text/javascript">	
		$('div.btn-group .btn').click(function(){
			$(this).button('toggle');
			return false;
		});
		$('#aluno').change(function(){
			carregaComboJson('<c:url value="/matriculas/responsavel.json"/>','idAluno='+$(this).val(),'responsavel');
		});
		
		$('#curso').change(function(){
			carregaHorariosDisponiveis($(this).val());
		});
		
		function carregaHorariosDisponiveis(idCurso){
			var horaDisp = $('#horaDisp');
			var auxDiaDaSemana;
			
			$.ajax({
				url : '<c:url value="/aula/buscarHorario.json/'+idCurso+'"/>',
				dataType : 'json',
				cache : false,
				success : function(json) {
					$(horaDisp).children().remove();
					$(horaDisp).append('<option value="0">Selecionar</option>');
					
					for ( var i = 0; i < json.list.length; i++) {
						professor = json.list[i];
						nomeProfessor = professor.nome;
						auxDiaDaSemana = undefined;
						
						for ( var c = 0; c < professor.listaHorarioProfessor.length; c++) {
							horario = professor.listaHorarioProfessor[c];
							
							if (auxDiaDaSemana != horario.diaDaSemana){
								$(horaDisp).append('<optgroup label="'+nomeProfessor+' - '+horario.nomeDiaDaSemana+'"></optgroup>');
								auxDiaDaSemana = horario.diaDaSemana;
							}
							
							$(horaDisp).find('optgroup:last').append('<option value="' + horario.horaInicio + '">' + horario.horaInicioTexto + '</option>');
						}
					}
				}
			});
		}
	</script>
</body>
</html>