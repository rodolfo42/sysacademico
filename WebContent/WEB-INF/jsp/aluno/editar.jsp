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
	<form action="<c:url value="/aluno/${aluno.id }"/>" method="POST">
		<fieldset>
		
		<legend>Aluno</legend>
		
		<label for="nome">Nome:</label>
		<input id="nome" type="text" name="aluno.nome" value="${aluno.nome }" />
		
		<label for="data_nasc">Data de Nascimento:</label>
		<input id="data_nasc" type="text" name="aluno.dataNascimento" value="${aluno.dataNascimento }" />
		
		<tag:comboSelecionaESelecionado 
			labelSeleciona="Responsáveis" 
			idComboSeleciona="responsavel" 
			nameSelecionado="aluno.listaResponsavel.id" 
			listaSeleciona="${responsavelList }"
			labelSelecionado="Responsáveis Selecionados" 
			idComboSelecionado="responsavel_selecionado"
			listaSelecionado="${aluno.listaResponsavel }" />
		
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
		</fieldset>
	</form>
</body>
</html>