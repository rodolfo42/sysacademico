<%@ include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/datepicker/css/datepicker.css" />" />
<script type="text/javascript" src="<c:url value="/bootstrap/datepicker/js/bootstrap-datepicker.js" />"></script>
<script type="text/javascript">
	$(function() {
		$("#AlunoDataNascimento").datepicker({
			format : "dd/mm/yyyy"
		});
	});
</script>
</head>
<body>
	<form action="<c:url value="/alunos/cadastrar"/>" method="POST" class="form-horizontal">
		<h2>Cadastrar novo aluno</h2>
		<div class="control-group">
			<div class="control-label">Nome completo</div>
			<div class="controls">
				<input type="text" name="novoAluno.nome" maxlength="150" placeholder="Informe o nome completo do aluno"
					class="input-xlarge" />
			</div>
		</div>

		<div class="control-group">
			<div class="control-label">Data de nascimento</div>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="novoAluno.dataNascimento" id="AlunoDataNascimento" class="input-small"
						placeholder="dd/mm/yyyy" />
					<div class="add-on">
						<i class="icon icon-calendar"></i>
					</div>
				</div>
			</div>
		</div>

		<h4>Responsável</h4>

		<div class="accordion" id="accResponsavel">
			<div class="accordion-group">
				<div class="accordion-heading">
					<span class="accordion-toggle">
						<i class="icon icon-plus-sign"></i>
						<a data-toggle="collapse" data-parent="#accResponsavel" href="#responsavelNovo">
							O responsável é o próprio aluno ou um novo responsável
						</a>
					</span>
				</div>
				<div id="responsavelNovo" class="accordion-body collapse in">
					<div class="accordion-inner">
						<div style="width: 60px; float: left;">
							<input type="text" class="input-block-level" maxlength="5" />
						</div>
						<div style="margin: 0 5px; float: left;">-</div>
						<div style="width: 40px; float: left;">
							<input type="text" class="input-block-level" maxlength="3" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="accordion-group">
				<div class="accordion-heading">
					<span class="accordion-toggle">
						<i class="icon icon-search"></i>
						<a data-toggle="collapse" data-parent="#accResponsavel" href="#responsavelExistente">
							O responsável já está cadastrado no sistema
						</a>
					</span>
				</div>
				<div id="responsavelExistente" class="accordion-body collapse in">
					<div class="accordion-inner">
						<span class="muted">chosen.js - pegando o resposável existente</span>
					</div>
				</div>
			</div>
		</div>

		<div class="form-actions"></div>
	</form>
</body>
</html>