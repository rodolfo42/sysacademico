<%@include file="../taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Responsáveis</h3>
<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>CPF</th>
			<th>Endereço</th>
			<th>CEP</th>
			<th>Telefone</th>
			<th>Celular</th>
			<th>Email</th>
			<th>Data de Confirmação</th>
			<th>Alunos</th>
		</tr>
	</thead>
<tbody>
<c:forEach items="${responsavelList}" var="responsavel">
	<tr>
		<td>${responsavel.id }</td>
		<td>${responsavel.nome }</td>
		<td>${responsavel.cpf }</td>
		<td>${responsavel.endereco }</td>
		<td>${responsavel.cep }</td>
		<td>${responsavel.telefone }</td>
		<td>${responsavel.celular }</td>
		<td>${responsavel.email }</td>
		<td>${responsavel.dataConfirmacao }</td>
		<td>
			<c:forEach items="${responsavel.listaAluno }" var="responsavel">
				${responsavel.nome }<br />
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>