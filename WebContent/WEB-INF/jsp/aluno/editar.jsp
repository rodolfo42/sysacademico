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
	<form action="<c:url value="/aluno/${aluno.id }"/>" method="POST">
		<fieldset>
		
		<legend>Aluno</legend>
		
		<label for="nome">Nome:</label>
		<input id="nome" type="text" name="aluno.nome" value="${aluno.nome }" />
		
		<label for="data_nasc">Data de Nascimento:</label>
		<input id="data_nasc" type="text" name="aluno.dataNascimento" value="${aluno.dataNascimento }" />
		
		<table>
			<tr>
				<th align="left"><label for="aluno">Responsáveis:</label></th>
				<th>&nbsp;</th>
				<th align="left"><label for="aluno_selecionado">Responsáveis Selecionados:</label></th>
			</tr>
			<tr>
				<td>
					<select name="listaResponsavel" id="responsavel" multiple="multiple">
						<c:forEach items="${responsavelList }" var="responsavel">
							<option value="${responsavel.id }">${responsavel.nome }</option>
						</c:forEach>
					</select>
				</td>
				<td valign="middle">
					<i class="icon-fast-backward"></i>
					<i class="icon-fast-forward"></i>
				</td>
				<td>
					<select name="aluno.listaResponsavel.id" id="responsavel_selecionado" multiple="multiple">
						<c:forEach items="${aluno.listaResponsavel }" var="responsavel">
							<option value="${responsavel.id }" selected="selected">${responsavel.nome }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
		</fieldset>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			organizaCombo('responsavel','responsavel_selecionado');
		});
		
		$('.icon-fast-backward').click(function(){
			transfere('responsavel_selecionado','responsavel');
		});
		$('#responsavel_selecionado').dblclick(function(){
			$('.icon-fast-backward').click();
		});
		
		
		$('.icon-fast-forward').click(function(){
			transfere('responsavel','responsavel_selecionado');
		});
		$('#responsavel').dblclick(function(){
			$('.icon-fast-forward').click();
		});
	</script>
</body>
</html>