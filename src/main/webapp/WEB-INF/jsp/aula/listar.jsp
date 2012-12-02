<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<form action="<c:url value="/aula/editarStatusAula" />" method="POST" id="formAulas">
		<input type="hidden" id="hiddenIdAula" name="aulaMatricula.aula.id">
		<input type="hidden" id="hiddenStatusAula" name="aulaMatricula.statusAula">
		<input type="hidden" id="hiddenMetodo" name="_method">
		<h3>Aluno: ${matriculaSessao.matricula.aluno.nome }</h3>
		
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Curso</th>
					<th>Tipo da Aula</th>
					<th>Data</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${aulaMatriculaList }" var="aulaMatricula">
					<c:set var="classeTr" value="error" />
					<c:set var="classeBtn" value="danger" />
					<c:if test="${aulaMatricula.statusAula == 'ALUNO_PRESENTE'}">
						<c:set var="classeTr" value="success" />
						<c:set var="classeBtn" value="success" />
					</c:if>
					<c:if test="${aulaMatricula.statusAula == 'AULA_NAO_REALIZADA'}">
						<c:set var="classeTr" value="warning" />
						<c:set var="classeBtn" value="warning" />
					</c:if>
					<tr class="${classeTr}">
						<td>${aulaMatricula.matricula.curso.nome }</td>
						<td>${aulaMatricula.aula.tipoAula.nome }</td>
						<td><fmt:formatDate value="${aulaMatricula.aula.timestamp.time }" pattern="dd/MM/yyyy 'às' HH:mm (EEEE)" /></td>
						<td>
							<div class="btn-group">
								<a class="btn btn-${classeBtn} dropdown-toggle" data-toggle="dropdown" href="#">
							    	${aulaMatricula.statusAula.descricao }
							    	<span class="caret"></span>
							  	</a>
							  	<ul class="dropdown-menu">
							    	<c:forEach items="${statusAulas}" var="statusAula">
							    		<li value="${statusAula}"><a href="javascript:alterarStatusAula(${aulaMatricula.aula.id},'${statusAula}')">${statusAula.descricao }</a></li>
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
		var alterarStatusAula = function(idAula, statusAula){
			$('#hiddenIdAula').val(idAula);
			$('#hiddenStatusAula').val(statusAula);
			$('#hiddenMetodo').val('PUT');
			$('#formAulas').submit();
		};
	</script>
</body>
</html>