<%@page import="com.prisila.modelo.entidade.Aluno"%>
<%@page import="com.prisila.util.StringUtil"%>
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
		<tag:validationalert />
		<div class="control-group">
			<div class="control-label">Nome completo</div>
			<div class="controls">
				<input type="text" name="novoAluno.nome" id="nomeNovoAluno" maxlength="150" placeholder="Informe o nome completo do aluno"
					value="${novoAluno.nome}" 
					class="input-xlarge" />
			</div>
		</div>

		<div class="control-group">
			<div class="control-label">Data de nascimento</div>
			<div class="controls">
				<div class="input-append">
					<input type="text" name="novoAluno.dataNascimento" id="AlunoDataNascimento" class="input-small"
						value="<fmt:formatDate pattern="dd/MM/yyyy" value="${novoAluno.dataNascimento}" />"  
						placeholder="dd/mm/yyyy" />
					<div class="add-on">
						<i class="icon icon-calendar"></i>
					</div>
				</div>
			</div>
		</div>

		<h4>O responsável ..</h4>
		<div class="accordion" id="accResponsavel">
			<div class="accordion-group">
				<div class="accordion-heading">
					<span class="accordion-toggle">
						<i class="icon icon-plus-sign"></i>
						<a data-toggle="collapse" data-parent="#accResponsavel" href="#responsavelNovo" data-responsavel="0">
							... é o próprio aluno <strong>OU</strong> um novo responsável?
						</a>
					</span>
				</div>
				<div id="responsavelNovo" class="accordion-body collapse in">
					<div class="accordion-inner" style="overflow: hidden;">
			<div class="container-fluid">
				<div class="row">
					<div class="span6">
						<div class="control-group">
							<div class="control-label">CPF</div>
							<div class="controls">
								<input type="text" class="restrict-cpf input-small"
									value="${novoResponsavel.cpf }" 
									name="novoResponsavel.cpf" placeholder="só números" maxlength="11" />
							</div>
						</div>
						<div class="control-group">
							<div class="control-label">Nome</div>
							<div class="controls">
								<div class="input-append">
									<input type="text" class="input-xlarge" id="nomeResponsavel"
										value="${novoResponsavel.nome }"
										name="novoResponsavel.nome" placeholder="nome completo" maxlength="150" />
									<a href="javascript:void(0);" id="copiarNomeAluno" class="btn" data-toggle="button"><i class="icon icon-pencil"></i> copiar nome do aluno</a>
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<div class="control-label">E-mail</div>
							<div class="controls">
								<input type="text" class="input-large" 
									value="${novoResponsavel.email }"
									name="novoResponsavel.email" placeholder="email@email.com" maxlength="150" />
							</div>
						</div>
					</div>
					<div class="span6">	
						<div class="control-group">
							<div class="control-label">CEP</div>
							<div class="controls">
								<div class="input-append">
									<input type="text" class="input-mini restrict-number" style="font-size: 12px;"
										value="${novoResponsavel.cep }"  
										name="novoResponsavel.cep" placeholder="" maxlength="8" />
									<a rel="tooltip" title="em breve, será possível consultar o endereço a partir do CEP" 
										href="javascript:void(0);" class="btn disabled"><i class="icon icon-search"></i> (em breve)</a>
								</div>
							</div>
						</div>
					
					
						<div class="control-group">
							<div class="control-label">Endereço</div>
							<div class="controls">
								<input type="text" class="input-xlarge"
									value="${novoResponsavel.endereco }" 
									name="novoResponsavel.endereco" placeholder="Avenida Qualquer, 999 apto 99" maxlength="180" />
							</div>
						</div>
					
						<div class="control-group">
							<div class="control-label">Telefone</div>
							<div class="controls">
								<input type="text" class="input-medium restrict-number"
									value="${novoResponsavel.telefone }"  
									name="novoResponsavel.telefone" placeholder="DDD + Telefone" maxlength="11" />
							</div>
						</div>
						
						<div class="control-group">
							<div class="control-label">Celular</div>
							<div class="controls">
								<input type="text" class="input-medium restrict-number"
									value="${novoResponsavel.celular }" 
									name="novoResponsavel.celular" placeholder="DDD + Celular" maxlength="11" />
							</div>
						</div>
					</div>
				</div>
			</div><!-- /container-fluid -->
					</div>
				</div>
			</div>
			
			<div class="accordion-group">
				<div class="accordion-heading">
					<span class="accordion-toggle">
						<i class="icon icon-search"></i>
						<a data-toggle="collapse" data-parent="#accResponsavel" href="#responsavelExistente" data-responsavel="1">
							... já está cadastrado no sistema?
						</a>
					</span>
				</div>
				<div id="responsavelExistente" class="accordion-body collapse">
					<div class="accordion-inner">
						<span class="muted">chosen.js - pegando o resposável existente</span>
					</div>
				</div>
			</div>
		</div>
		
		<input type="hidden" name="isResponsavelExistente" id="inputOpcaoResponsavel" value="0" />
		
		<script type="text/javascript">
		$(function(){
			
			$('[data-responsavel]').bind('click', function(e) {
				var value = $(e.target).data('responsavel');
				$('#inputOpcaoResponsavel').val(value);
			});
			
			var _bindNome = false;
			var syncNome = function(nome) {
				$('#nomeResponsavel, #nomeNovoAluno').val( nome );
			};
			$("#nomeNovoAluno, #nomeResponsavel").bind('input keypress', function(e){
				if(_bindNome) {
					setTimeout(function(){
						syncNome($(e.target).val());
					}, 1);
				}
			});
			$("#copiarNomeAluno").bind('click', function(e) {
				_bindNome = !_bindNome;
				if(_bindNome) syncNome($("#nomeNovoAluno").val());
			});
		});
		</script>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Cadastrar</button>
			<a href="<c:url value="/alunos/listar" />" class="btn btn-small">voltar</a>
		</div>
	</form>
</body>
</html>