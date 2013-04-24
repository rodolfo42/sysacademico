<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<form action="<c:url value="/aula/${aula.id}"/>" id="formAula" method="POST">
		<fieldset>
			<legend>Aula</legend>			
			
			<label for="professor">Professor:</label>
			<select id="professor" name="aula.professor.id">
				<option value="0">Selecionar</option>
				<c:forEach items="${professorList }" var="professor">
					<option value="${professor.id }">${professor.nome }</option>
				</c:forEach>
			</select>
			
			<label for="sala">Sala:</label>
			<select id="sala" name="aula.sala.id">
				<option value="0">Selecionar</option>
				<c:forEach items="${salaList }" var="sala">
					<option value="${sala.id }">${sala.descricao }</option>
				</c:forEach>
			</select>
			
			<label for="data">Data:</label>
			<input type="text" class="input-small" id="data" value="<fmt:formatDate value="${aula.timestamp.time }" pattern="dd/MM/YYYY" />" />
			
			<label for="timestamp">Hora:</label>
			<input type="text" class="input-mini" id="hora" value="<fmt:formatDate value="${aula.timestamp.time }" pattern="HH:mm" />" />
			
			<input type="hidden" name="aula.timestampTexto" id="timestampTexto" />
			
			<div class="clearfix">
				<c:forEach items="${tipoAulas }" var="tipoAula">
					<label class="radio inline">
						<c:if test="${aula.tipoAula == tipoAula}">
							<input type="radio" name="aula.tipoAula" value="${tipoAula}" checked>
						</c:if>
						<c:if test="${aula.tipoAula != tipoAula}">
							<input type="radio" name="aula.tipoAula" value="${tipoAula}">
						</c:if>
						${tipoAula.nome}
					</label>
				</c:forEach>
			</div>

		</fieldset>
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
	</form>
	<script type="text/javascript">
		$(function(){
			var arrumaHorario = function(){
				$('#timestampTexto').val(
					$('#data').val().concat(' ').concat($('#hora').val())
				);
			};
			
			$('#professor').marcarSelecionado('${aula.professor.id}');
			$('#sala').marcarSelecionado('${aula.sala.id}');
			
			$('#formAula').submit(function(){
				arrumaHorario();
			});
		});
		
	</script>
</body>
</html>