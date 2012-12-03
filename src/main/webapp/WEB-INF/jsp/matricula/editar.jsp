<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	
	<form id="formEditarMatricula" action="<c:url value="/matriculas/${matricula.id }"/>" method="POST">
		<input type="hidden" name="_method" value="PUT">
		<fieldset>
			<legend>Matrícula</legend>

			<label for="data">Data:</label>
			<input id="data" disabled="disabled" type="text" name="matricula.data" value="<fmt:formatDate value="${matricula.data.time}" pattern="dd/MM/yyyy" />" /> 
			
			<label for="aluno">Aluno:</label> 
			<select name="matricula.aluno.id" id="aluno" disabled="disabled">
				<option value="${matricula.aluno.id }">${matricula.aluno.nome }</option>
			</select> 
			
			<label for="responsavel">Responsável:</label> 
			<select name="matricula.responsavel.id" id="responsavel">
				<option value="0">Selecionar</option>
				<c:forEach items="${responsavelListPorAluno }" var="responsavel">
					<option value="${responsavel.id }">${responsavel.nome }</option>
				</c:forEach>
			</select> 
			
			<label for="curso">Curso:</label> 
			<select name="matricula.curso.id" id="curso" disabled="disabled">
				<option value="${matricula.curso.id }">${matricula.curso.nome }</option>
			</select>

		</fieldset>
		<c:if test="${matricula.ativo }">
			<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
			<a rel="tooltip" href="javascript:inativarMatricula();" title="Inativar esta matrícula" class="btn btn-danger">Inativar</a>
		</c:if>
		<c:if test="${!matricula.ativo}">
			<a rel="tooltip" href="javascript:ativarMatricula();" title="Inativar esta matrícula" class="btn btn-success">Ativar</a>
		</c:if>
	</form>
	<script type="text/javascript">
		$(function(){
			$('#responsavel').marcarSelecionado(${matricula.responsavel.id});
		});
		
		var inativarMatricula = function(){
			var form = $('#formEditarMatricula');
			form.attr('action','<c:url value="/matriculas/inativar/${matricula.id}"/>');
			form.submit();
		}
		
		var ativarMatricula = function(){
			var form = $('#formEditarMatricula');
			form.attr('action','<c:url value="/matriculas/ativar/${matricula.id}"/>');
			form.submit();
		}
		
		$('#formEditarMatricula').submit(function(){
			$(':disabled').prop('disabled',false);
		});
		
	</script>
</body>
</html>