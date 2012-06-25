<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div id="aniversarios" class="well">

	<table id="tabelaAniversariantes" class="table table-condensed" width="100%">
		<thead>
			<tr>
				<th><h3>Aniversariantes do Mês</h3></th>
				<th><img alt="Bexigas" src="<c:url value="/img/bexigas.png"/>"></th>
			</tr>
			<tr>
				<th>Aluno</th>
				<th>Dia</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${alunosList}" var="aluno">
				<tr>
					<td>${aluno.nome }</td>
					<td>${aluno.diaAniversario}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>