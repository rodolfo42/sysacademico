<%@ include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<form action="<c:url value="/alunos/${aluno.id }"/>" method="POST">
		<fieldset>

			<legend>Aluno</legend>

			<label for="nome">Nome:</label> <input id="nome" type="text" name="aluno.nome" value="${aluno.nome }" /> <label
				for="data_nasc">Data de Nascimento:</label> <input id="data_nasc" type="text" name="aluno.dataNascimento"
				value="${aluno.dataNascimento }" />

			<tag:comboSelecionaESelecionado labelSeleciona="Responsáveis" idComboSeleciona="responsavel"
				nameSelecionado="aluno.listaResponsavel.id" listaSeleciona="${responsavelList }"
				labelSelecionado="Responsáveis Selecionados" idComboSelecionado="responsavel_selecionado"
				listaSelecionado="${aluno.listaResponsavel }" />

			<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
			<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
		</fieldset>
	</form>
</body>