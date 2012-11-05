<%@ include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<form action="<c:url value="/alunos/adicionar"/>" method="POST">
		<fieldset>
			<legend>Aluno</legend>

			<label for="nome">Nome:</label> <input id="nome" type="text" name="aluno.nome" /> <label for="data_nasc">Data
				de Nascimento:</label> <input id="data_nasc" class="input-small" type="text" name="aluno.dataNascimento" />

		</fieldset>

		<tag:comboSelecionaESelecionado labelSeleciona="Responsáveis" idComboSeleciona="responsavel"
			nameSelecionado="aluno.listaResponsavel.id" listaSeleciona="${responsavelList }"
			labelSelecionado="Responsáveis Selecionados" idComboSelecionado="responsavel_selecionado" />

		<button type="submit" class="btn">Enviar</button>
		<a class="btn btn-warning direita" href="<c:url value="/matriculas/adicionar"/>"> <i class="icon-forward"></i>
			Pular Cadastro de Aluno </a>
	</form>
</body>