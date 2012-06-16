<%@include file="../tags.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value="/responsavel/adicionar"/>" method="POST">
	<fieldset>
		<legend>Adicionar Responsável</legend>
		
		<label for="nome">Nome:</label>
		<input id="nome" type="text" name="responsavel.nome" />
		
		<label for="cpf">CPF:</label>
		<input id="cpf" name="responsavel.cpf" />
		
		<label for="endereco">Endereço:</label>
		<input id="endereco" name="responsavel.endereco" />
		
		<label for="cep">CEP:</label>
		<input id="cep" name="responsavel.cep" />
		
		<label for="telefone">Telefone:</label>
		<input id="telefone" name="responsavel.telefone" />
		
		<label for="telefone">Celular:</label>
		<input id="telefone" name="responsavel.celular" />
		
		<label for="email">Email:</label>
		<input id="email" name="responsavel.email" />
		
		<label for="dataConfirmacao">Data de Confirmação:</label>
		<input id="dataConfirmacao" name="responsavel.dataConfirmacao" />
		
		<label for="aluno">Aluno:</label>
		<select name="responsavel.listaAluno.id" id="aluno">
			<c:forEach items="${alunoList }" var="aluno">
				<option value="${aluno.id }">${aluno.nome }</option>
			</c:forEach>
		</select>
	</fieldset>
	<button type="submit" class="btn">Enviar</button>
</form>
</body>
</html>