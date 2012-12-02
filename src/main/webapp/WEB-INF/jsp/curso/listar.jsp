<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h3>Cursos <small>${cursoList.size()} cursos</small></h3>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cursoList}" var="curso">
				<tr>
					<td>${curso.id}</td>
					<td>${curso.nome}</td>
					<td>
						<a href="<c:url value="/cursos/${curso.id}" />">editar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>