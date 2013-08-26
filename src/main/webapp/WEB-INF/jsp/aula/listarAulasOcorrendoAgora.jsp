<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/datepicker/css/datepicker.css" />" />
	<script type="text/javascript" src="<c:url value="/bootstrap/datepicker/js/bootstrap-datepicker.js" />"></script>
</head>
<body>
	<form action="<c:url value="/aula/editarStatusAula" />" method="POST" id="formAulas">
		<input type="hidden" id="hiddenIdAula" name="aula.id">
		<input type="hidden" id="hiddenStatusAula" name="aula.statusAula">
		<input type="hidden" id="hiddenMetodo" name="_method">
		
		<table class="table table-bordered collapseTable">
			<tr>
				<th>Aluno(s)</th>
				<th>Curso</th>
				<th>Professor</th>
				<th>Sala</th>
				<th>Tipo da Aula</th>
				<th>Duração</th>
				<th>Status da Aula</th>
				<th>Status do Aluno</th>
				<th>Ações</th>
			</tr>	
			<c:forEach items="${aulaList}" var="aula">
				<tr class="${aula.statusAula.trClass}" id="aula${aula.id}" data-row="containerRow">
					<c:if test="${fn:length(aula.listaAulaMatricula) > 1}">
						<td><i class="icon-arrow-down"></i></td>
					</c:if>
					<c:if test="${fn:length(aula.listaAulaMatricula) == 1}">
						<td>${aula.listaAulaMatricula[0].matricula.aluno.nome}</td>
					</c:if>
					<td>${aula.curso.nome }</td>
					<td>${aula.professor.nome }</td>
					<td>${aula.sala.descricao }</td>
					<td>${aula.tipoAula.nome }</td>
					<td>
						<fmt:formatDate value="${aula.timestamp.time }" pattern="HH:mm" />
						às
						<fmt:formatDate value="${aula.timestampFim.time }" pattern="HH:mm" />
					</td>
					<td>
						<div class="btn-group">
							<a class="btn btn-${aula.statusAula.btnClass} dropdown-toggle" data-toggle="dropdown" href="#">
						    	${aula.statusAula.descricao }
						    	<span class="caret"></span>
						  	</a>
						  	<ul class="dropdown-menu">
						    	<c:forEach items="${statusAulas}" var="statusAula">
						    		<li value="${statusAula}"><a onclick="alterarStatusAula(${aula.id},'${statusAula}');" href="#">${statusAula.descricao }</a></li>
						    	</c:forEach>
						  	</ul>
						</div>
					</td>
					<td nowrap>
						<c:if test="${fn:length(aula.listaAulaMatricula) == 1}">
							<c:set var="aulaMatricula" value="${aula.listaAulaMatricula[0]}" />
							<div class="btn-group">
								<a class="btn btn-${aulaMatricula.statusAulaAluno.btnClass} dropdown-toggle" data-toggle="dropdown" href="#">
							    	${aulaMatricula.statusAulaAluno.descricao }
							    	<span class="caret"></span>
							  	</a>
							  	<ul class="dropdown-menu">
							    	<c:forEach items="${statusAulaAlunos}" var="statusAulaAluno">
							    		<li value="${statusAulaAluno}"><a onclick="alterarStatusAulaAluno(${aulaMatricula.id},'${statusAulaAluno}');" href="#">${statusAulaAluno.descricao }</a></li>
							    	</c:forEach>
							  	</ul>
							</div>
						</c:if>
						<c:if test="${fn:length(aula.listaAulaMatricula) > 1}">
							<i class="icon-arrow-down"></i>
						</c:if>
					</td>
					<td>
						<div class="btn-group">
							<a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon icon-cog"></i>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu pull-right">
								<li><a href="<c:url value="/aula/${aula.id}" />"><i class="icon icon-edit"></i>Editar aula</a></li>
								<c:if test="${aulaMatricula.statusAulaAluno == 'ALUNO_FALTOU'}">
									<li>
										<a href="#" onclick="motivoAusencia('${aulaMatricula.id}', '${aulaMatricula.motivoAusencia}');">
											<i class="icon icon-exclamation-sign"></i>Descrever motivo da ausência
										</a>
									</li>
								</c:if>
							</ul>
						</div>
					</td>
				</tr>
				<c:if test="${fn:length(aula.listaAulaMatricula) > 1}">
					<c:forEach items="${aula.listaAulaMatricula}" var="aulaMatricula">
						<tr data-row="row" data-parent="aula${aula.id}" class="in ${aulaMatricula.statusAulaAluno.trClass}">
							<td colspan="7">${aulaMatricula.matricula.aluno.nome}</td>
							<td colspan="2">
								<div class="btn-group">
									<a class="btn btn-${aulaMatricula.statusAulaAluno.btnClass} dropdown-toggle" data-toggle="dropdown" href="#">
								    	${aulaMatricula.statusAulaAluno.descricao }
								    	<span class="caret"></span>
								  	</a>
								  	<ul class="dropdown-menu">
								    	<c:forEach items="${statusAulaAlunos}" var="statusAulaAluno">
								    		<li value="${statusAulaAluno}"><a onclick="alterarStatusAulaAluno(${aulaMatricula.id},'${statusAulaAluno}');" href="#">${statusAulaAluno.descricao }</a></li>
								    	</c:forEach>
								  	</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
		</table>
	</form>
	<form action="<c:url value="/aula/editarStatusAulaAluno" />" method="POST" id="formAulasAluno">
		<input type="hidden" id="hiddenIdAulaMatricula" name="aulaMatricula.id">
		<input type="hidden" id="hiddenStatusAulaAluno" name="aulaMatricula.statusAulaAluno">
		<input type="hidden" id="hiddenMetodo2" name="_method">
	</form>
	
	<div id="modalReposicao" class="modal hide fade" role="dialog" aria-labelledby="labelReposicao" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="labelReposicao">Reposição de Aula</h3>
	  </div>
	  <div class="modal-body">
	  	<form method="POST" id="formReposicao">
		    <label for="motivo">Motivo da ausência</label>
		    <textarea id="motivoAusencia" name="aulaMatricula.motivoAusencia" rows="3" cols="40"></textarea>
		    <p><button type="button" id="btnRepor" class="btn" data-toggle="button" onclick="mostraInfoReposicao(this);">Marcar Reposição</button></p>
		    <div id="infoReposicao">		    	
		    	<div class="row-fluid">
		    		<div class="span4">
			    		<label for="dataReposicao">Data Reposição</label>
				    	<div class="input-append">
				    		<input type="text" id="dataReposicao" class="input-small required" />
							<div class="add-on">
								<i class="icon icon-calendar"></i>
							</div>
						</div>
					</div>
					<div class="span3">
						<label for="horaReposicao">Hora</label>
						<input type="text" id="horaReposicao" class="span5 required" />
					</div>
				</div>
				<div class="row-fluid">
					<label for="professorReposicao">Professor</label>
					<select id="professorReposicao" name="aulaReposicao.professor.id">
						<option value="0">Selecionar</option>
						<c:forEach items="${professorList}" var="professor">
				    		<option value="${professor.id}">${professor.nome}</option>
				    	</c:forEach>
					</select>
				</div>
				<div class="row-fluid">
					<label for="salaReposicao">Sala</label>
					<select id="salaReposicao" name="aulaReposicao.sala.id">
						<option value="0">Selecionar</option>
						<c:forEach items="${salaList}" var="sala">
				    		<option value="${sala.id}">${sala.descricao}</option>
				    	</c:forEach>
					</select>
				</div>
		    </div>
		    <input type="hidden" name="aulaReposicao.timestampTexto" id="timestampReposicao" />
		    <input type="hidden" name="_method" id="hiddenMetodo3" />
	    </form>	    
	  </div>
	  <div class="modal-footer">
	    <button type="submit" class="btn" onclick="salva();">Salvar</button>
	  </div>
	</div>
	
	<script type="text/javascript">
					
		$(function(){
			$('#infoReposicao').hide();
			$('.datepicker').css('z-index','9999');
			
			$('#dataReposicao').datepicker({
				format: 'dd/mm/yyyy'
			});
			$('.datepicker').css('z-index','9999');
			
			$('#formReposicao').validate({
				rules: {
					'aula.sala.id': {min: 1},
					'aula.professor.id': {min: 1}
				}
			});
		});
		var alterarStatusAula = function(idAula, statusAula){
			$('#hiddenIdAula').val(idAula);
			$('#hiddenStatusAula').val(statusAula);
			$('#hiddenMetodo').val('PUT');
			$('#formAulas').submit();
		};
		var alterarStatusAulaAluno = function(idAulaMatricula, statusAulaAluno){
			$('#hiddenIdAulaMatricula').val(idAulaMatricula);
			$('#hiddenStatusAulaAluno').val(statusAulaAluno);
			$('#hiddenMetodo2').val('PUT');
			$('#formAulasAluno').submit();
		};
		var mostraInfoReposicao = function(btn){
			var $btnMarcarReposicao = $(btn);
			$btnMarcarReposicao.hasClass('active') ? $('#infoReposicao').hide() : $('#infoReposicao').show();
		};
		var idAulaMatriculaReposicao = undefined;
		var motivoAusencia = function(idAulaMatricula, textoMotivo){
			idAulaMatriculaReposicao = idAulaMatricula;
			$('#motivoAusencia').val(textoMotivo);
			$('#modalReposicao').modal();
		};
		var salva = function(){			
			var $formReposicao = $('#formReposicao');			
			if ($('#btnRepor').hasClass('active')){
				var dataReposicao = $('#dataReposicao').val();
				var horaReposicao = $('#horaReposicao').val();
				var professorReposicao = $('#professorReposicao').val();
				var salaReposicao = $('#salaReposicao').val();				
				$('#timestampReposicao').val(dataReposicao+' '+horaReposicao);
				
				$.ajax({
					url: '<c:url value="/aula/reposicao.json" />',
					type: 'GET',
					data: $formReposicao.serialize().replace('&_method=',''),
					dataType: 'json',
					success: function(json){
						var existeAula = json['boolean'];
						if (existeAula){
							alert('Não é possível marcar reposição pois já existe uma aula com estes dados');
						}else{
							if (confirm('Deseja realmente marcar a reposição desta aula?')){
								$formReposicao.attr('action', '<c:url value="/aulaMatricula/reposicao/'+idAulaMatriculaReposicao+'" />');
								$('#hiddenMetodo3').val('POST');
								$formReposicao.submit();
							}
						}
					},
					errorMessage: 'Não foi possível marcar a reposição no momento.'
					
				});
			}else{
				$formReposicao.attr('action', '<c:url value="/aulaMatricula/motivoAusencia/'+idAulaMatriculaReposicao+'" />');
				$('#hiddenMetodo3').val('PUT');
				$formReposicao.submit();
			}			
		};
	</script>
</body>
</html>