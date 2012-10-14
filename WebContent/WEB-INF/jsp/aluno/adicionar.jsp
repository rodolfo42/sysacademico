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
	<form action="<c:url value="/aluno/adicionar"/>" method="POST">
		<fieldset>
			<legend>Aluno</legend>
			
			<label for="nome">Nome:</label>
			<input id="nome" type="text" name="aluno.nome" />
			
			<label for="data_nasc">Data de Nascimento:</label>
			<input id="data_nasc" class="input-small" type="text" name="aluno.dataNascimento" />						
			
		</fieldset>
		
		<tag:comboSelecionaESelecionado 
			labelSeleciona="Responsáveis" 
			idComboSeleciona="responsavel" 
			nameSelecionado="aluno.listaResponsavel.id" 
			listaSeleciona="${responsavelList }"
			labelSelecionado="Responsáveis Selecionados" 
			idComboSelecionado="responsavel_selecionado" />
			
		<button type="submit" class="btn">Enviar</button>
		<a class="btn btn-warning direita" href="<c:url value="/matricula/adicionar"/>">
			<i class="icon-forward"></i>
			Pular Cadastro de Aluno
		</a>		
	</form>	
</body>
</html>