<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
						    		<li value="${statusAula}"><a href="javascript:alterarStatusAula(${aula.id},'${statusAula}')">${statusAula.descricao }</a></li>
						    	</c:forEach>
						  	</ul>
						</div>
					</td>
					<td>
						<c:if test="${fn:length(aula.listaAulaMatricula) == 1}">
							<c:set var="aulaMatricula" value="${aula.listaAulaMatricula[0]}" />
							<div class="btn-group">
								<a class="btn btn-${aulaMatricula.statusAulaAluno.btnClass} dropdown-toggle" data-toggle="dropdown" href="#">
							    	${aulaMatricula.statusAulaAluno.descricao }
							    	<span class="caret"></span>
							  	</a>
							  	<ul class="dropdown-menu">
							    	<c:forEach items="${statusAulaAlunos}" var="statusAulaAluno">
							    		<li value="${statusAulaAluno}"><a href="javascript:alterarStatusAulaAluno('${aulaMatricula.id}','${statusAulaAluno}')">${statusAulaAluno.descricao }</a></li>
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
								    		<li value="${statusAulaAluno}"><a href="javascript:alterarStatusAulaAluno(${aulaMatricula.id},'${statusAulaAluno}')">${statusAulaAluno.descricao }</a></li>
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
	<script type="text/javascript">
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
	</script>
</body>
</html>