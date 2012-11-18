<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/aula/marcar"/>" method="POST">
		<fieldset>
			<legend>Marcar Aulas</legend>
			
			<div class="container" data-toggle="buttons-checkbox">
				<c:forEach items="${tipoAulas }" var="tipoAula" varStatus="status">
					<div class="row-fluid">
						<span class="span2">
							<button type="button" data-placement="right" rel="tooltip" title="Clique para marcar esse tipo de aula" class="btn">
								${tipoAula.nome }
							</button>
						</span>
						<div class="span10">
							<input type="hidden" class="hidden-tipo-aula" name="aulas[].tipoAula" disabled="disabled" value="${tipoAula}" />
							<input type="hidden" class="hidden-professor" name="aulas[].professor.id" disabled="disabled" />
							<input type="hidden" class="hidden-timestamp" name="aulas[].timestampLong" disabled="disabled" />
							<input type="hidden" class="hidden-dia-semana" name="aulas[].diaDaSemana" disabled="disabled" />
							
							<span class="muted">Tipo de aula não usado para esta matrícula.</span>
							
							<table class="table table-hover span6">
								<thead>
									<tr>
										<th>Professor</th>
										<th>Dia da Semana</th>
										<th>Horário</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${professorList }" var="professor">
										<c:forEach items="${professor.listaHorarioProfessor }" var="horario">
											<tr data-timestamp="${horario.horaInicio }" data-id-professor="${professor.id }" data-dia-semana="${horario.diaDaSemana }">
												<td class="span3">${professor.nome }</td>
												<td class="span2">${horario.nomeDiaDaSemana }</td>
												<td class="span1">${horario.horaInicioTexto }</td>
											</tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
							
							<select name="aulas[].sala.id" class="sala" disabled="disabled">
								<c:forEach items="${salaList }" var="sala">
									<option value="${sala.id }">${sala.descricao }</option>
								</c:forEach>
							</select>
							
						</div>
					</div>
				</c:forEach>
			</div>
		</fieldset>
		
		<button type="submit" class="btn">Marcar</button>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			
			$('table.table-hover').hide();
			$('select.sala').hide();
			
			$('div.row-fluid button.btn').click(function(){
				var div = $(this).parent().next();
				mostraOcultaInfo(div);
				habilitaDesabilitaHiddens(div);
			});
			
			$('table.table-hover tbody tr').click(function(){
				$(this).toggleClass('warning');
				$(this).siblings().removeClass('warning');
				var div = $(this).parent().parent().parent();
				setValueNasHiddens(div);
			});
			
			var mostraOcultaInfo = function(div){
				div.find('table.table-hover').toggle();
				div.find('span.muted').toggle();
				div.find('select.sala').toggle();
			};
			
			var habilitaDesabilitaHiddens = function(div){
				var isDisabled = div.find('input.hidden-tipo-aula').prop('disabled');
				div.find('input.hidden-tipo-aula').prop('disabled', !isDisabled);
				div.find('input.hidden-professor').prop('disabled', !isDisabled);
				div.find('input.hidden-timestamp').prop('disabled', !isDisabled);
				div.find('input.hidden-dia-semana').prop('disabled', !isDisabled);
				div.find('select.sala').prop('disabled', !isDisabled);
			};
			
			var setValueNasHiddens = function(div){
				div.find('input.hidden-professor').val($(this).attr('data-id-professor'));
				div.find('input.hidden-timestamp').val($(this).attr('data-timestamp'));
				div.find('input.hidden-dia-semana').val($(this).attr('data-dia-semana'));
			};
		});
	</script>
</body>
</html>