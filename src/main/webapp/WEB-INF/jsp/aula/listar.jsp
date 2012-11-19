<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
				<c:set var="classeTr" value="warning" />
				<c:if test="${aulaMatricula.statusAula == 'ALUNO_PRESENTE'}">
					<c:set var="classeTr" value="success" />
				</c:if>
				<tr class="${classeTr}">
					<td>${aulaMatricula.matricula.curso.nome }</td>
					<td>${aulaMatricula.aula.tipoAula.nome }</td>
					<td><fmt:formatDate value="${aulaMatricula.aula.timestamp.time }" pattern="dd/MM/yyyy 'às' HH:mm (EEEE)" /></td>
					<td>${aulaMatricula.statusAula.descricao }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>