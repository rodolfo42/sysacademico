<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<form action="<c:url value="/aula/editarStatusAulaAluno" />" method="POST" id="formAulas">
		<input type="hidden" id="hiddenIdAulaMatricula" name="aulaMatricula.id">
		<input type="hidden" id="hiddenStatusAulaAluno" name="aulaMatricula.statusAulaAluno">
		<input type="hidden" id="hiddenMetodo" name="_method">
		<h3>Aluno: ${aulaMatriculaList[0].matricula.aluno.nome }</h3>
		
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Curso</th>
					<th>Professor</th>
					<th>Sala</th>
					<th>Tipo da Aula</th>
					<th>Data</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${aulaMatriculaList }" var="aulaMatricula">
					<tr class="${aulaMatricula.statusAulaAluno.trClass}">
						<td>${aulaMatricula.matricula.curso.nome }</td>
						<td>${aulaMatricula.aula.professor.nome }</td>
						<td>${aulaMatricula.aula.sala.descricao }</td>
						<td>${aulaMatricula.aula.tipoAula.nome }</td>
						<td><fmt:formatDate value="${aulaMatricula.aula.timestamp.time }" pattern="dd/MM/yyyy 'às' HH:mm (EEEE)" /></td>
						<td>
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
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		var alterarStatusAulaAluno = function(idAulaMatricula, statusAulaAluno){
			$('#hiddenIdAulaMatricula').val(idAulaMatricula);
			$('#hiddenStatusAulaAluno').val(statusAulaAluno);
			$('#hiddenMetodo').val('PUT');
			$('#formAulas').submit();
		};
	</script>
</body>
</html>