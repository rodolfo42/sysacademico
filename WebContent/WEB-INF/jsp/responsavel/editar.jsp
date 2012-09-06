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
	<form action="<c:url value="/responsavel/${responsavel.id }"/>" method="POST">
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
			
			<table>
				<tr>
					<th align="left"><label for="aluno">Alunos:</label></th>
					<th>&nbsp;</th>
					<th align="left"><label for="aluno_selecionado">Alunos Selecionados:</label></th>
				</tr>
				<tr>
					<td>
						<select name="listaAluno" id="aluno" multiple="multiple">
							<c:forEach items="${alunoList }" var="aluno">
								<option value="${aluno.id }">${aluno.nome }</option>
							</c:forEach>
						</select>
					</td>
					<td valign="middle">
						<i class="icon-fast-backward"></i>
						<i class="icon-fast-forward"></i>
					</td>
					<td>
						<select name="responsavel.listaAluno.id" id="aluno_selecionado" multiple="multiple">
							<c:forEach items="${responsavel.listaAluno }" var="aluno">
								<option value="${aluno.id }" selected="selected">${aluno.nome }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			organizaCombo('aluno','aluno_selecionado');
		});
		
		$('.icon-fast-backward').click(function(){
			transfere('aluno_selecionado','aluno');
		});
		$('#aluno_selecionado').dblclick(function(){
			$('.icon-fast-backward').click();
		});
		
		
		$('.icon-fast-forward').click(function(){
			transfere('aluno','aluno_selecionado');
		});
		$('#aluno').dblclick(function(){
			$('.icon-fast-forward').click();
		});
	</script>
</body>
</html>