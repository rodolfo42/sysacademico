<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h3>Cursos</h3>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cursoList}" var="curso">
				<tr>
					<td>${curso.id }</td>
					<td>${curso.nome }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>