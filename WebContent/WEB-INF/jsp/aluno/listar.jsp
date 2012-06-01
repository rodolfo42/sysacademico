<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Alunos</h3>
<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Data de Nascimento</th>
			<th>Responsáveis</th>
		</tr>
	</thead>
<tbody>
<c:forEach items="${alunoList}" var="aluno">
	<tr>
		<td>${aluno.id }</td>
		<td>${aluno.nome }</td>
		<td>${aluno.dataNascimento }</td>
		<td>
			<ul>
				<c:forEach items="${aluno.listaResponsavel }" var="responsavel">
					<li>${responsavel.nome }</li>
				</c:forEach>
			</ul>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>