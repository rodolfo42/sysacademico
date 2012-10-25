<%@ include file="../taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/responsaveis/${responsavel.id }"/>" method="POST">
		<fieldset>
			<legend>Responsável</legend>
			
			<label for="nome">Nome:</label>
			<input id="nome" type="text" name="responsavel.nome" value="${responsavel.nome }" />
			
			<label for="cpf">CPF:</label>
			<input id="cpf" type="text" name="responsavel.cpf" value="${responsavel.cpf }" />
			
			<label for="endereco">Endereço:</label>
			<input id="endereco" type="text" name="responsavel.endereco" value="${responsavel.endereco }" />
			
			<label for="cep">CEP:</label>
			<input id="cep" type="text" name="responsavel.cep" value="${responsavel.cep }" />
			
			<label for="telefone">Telefone:</label>
			<input id="telefone" type="text" name="responsavel.telefone" value="${responsavel.telefone }" />
			
			<label for="celular">Celular:</label>
			<input id="celular" type="text" name="responsavel.celular" value="${responsavel.celular }" />
			
			<label for="email">Email:</label>
			<input id="email" type="text" name="responsavel.email" value="${responsavel.email }" />
			
			<label for="dataConfirmacao">Data de Confirmação:</label>
			<input id="dataConfirmacao" type="text" name="responsavel.dataConfirmacao" value="${responsavel.dataConfirmacao }" />
			
			<tag:comboSelecionaESelecionado 
				labelSeleciona="Alunos" 
				idComboSeleciona="aluno" 
				nameSelecionado="responsavel.listaAluno.id" 
				listaSeleciona="${alunoList }" 
				labelSelecionado="Alunos Selecionados" 
				idComboSelecionado="aluno_selecionado"
				listaSelecionado="${responsavel.listaAluno }" />
						
		</fieldset>
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
	</form>
</body>
</html>