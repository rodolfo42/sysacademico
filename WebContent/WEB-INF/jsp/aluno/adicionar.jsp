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
	<form action="<c:url value="/aluno/adicionar"/>" method="POST">
		<fieldset>
			<legend>Aluno</legend>
			
			<label for="nome">Nome:</label>
			<input id="nome" type="text" name="aluno.nome" />
			
			<label for="data_nasc">Data de Nascimento:</label>
			<input id="data_nasc" class="input-small" type="text" name="aluno.dataNascimento" />
			
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
						</select>
					</td>
				</tr>
			</table>
			
		</fieldset>
		<button type="submit" class="btn">Enviar</button>
		<a class="btn btn-warning direita" href="<c:url value="/matricula/adicionar"/>">
			<i class="icon-forward"></i>
			Pular Cadastro de Aluno
		</a>
	</form>
	<script type="text/javascript">
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