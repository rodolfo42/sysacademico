<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="span12">
		<form class="form-search" id="search" method="GET" action="<c:url value="/matriculas/busca.json" />">
			<div class="input-append">
    			<input type="text" id="aluno" name="aluno.nome" placeholder="Nome do Aluno" class="input-medium search-query">
   				<button type="submit" class="btn"><i class="icon-search"></i></button>
  			</div>
		</form>
		<form id="formMatricula" method="GET">
			<h2>Matrículas</h2>
			<table id="tabelaMatricula" width="100%" class="table">
				<thead>
					<tr>
						<th>Data</th>
						<th>Aluno</th>
						<th>Responsável</th>
						<th>Curso</th>
						<th>Tipo de Aula</th>
						<th>Status</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${matriculaList}" var="matricula">
						<tr>
							<td><fmt:formatDate value="${matricula.data.time}" pattern="dd/MM/yyyy" /></td>
							<td>${matricula.aluno.nome }</td>
							<td>${matricula.responsavel.nome }</td>
							<td>${matricula.curso.nome }</td>
							<td>
								<c:forEach items="${matricula.listaEsquemaAula}" var="esquemaAula">
									<div class="conteudo_popover">
										<label>
											<b>Professor:</b>
											${esquemaAula.professor.nome }
										</label>
										<label>
											<b>Sala:</b>
											${esquemaAula.sala.descricao }
										</label>
										<label>
											<b>Dia:</b>
											${esquemaAula.diaDaSemana.nome }
										</label>
										<label>
											<b>Horário:</b>
											${esquemaAula.horaTexto }
										</label>
									</div>
									<a href="#" rel="popover">${esquemaAula.tipoAula.nome}</a><br />
								</c:forEach>
							</td>
							<td>
								<c:if test="${matricula.ativo }">
									<span class="label label-success">Ativa</span>
								</c:if>
								<c:if test="${!matricula.ativo }">
									<span class="label label-important">Inativa</span>
								</c:if>
							</td>
							<td>
								<a class="btn" rel="tooltip" title="Editar matrícula" href="javascript:editaMatricula(${matricula.id});"><i class="icon-edit"></i></a>
								<a class="btn" rel="tooltip" title="Listar aulas da matrícula" href="javascript:listaAulas(${matricula.id});"><i class="icon-list"></i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="<c:url value="/js/simpleDateFormat.js"/>"></script>
	<script type="text/javascript">
		$(function(){
			$('.conteudo_popover').hide();
			$('[rel="popover"]').popover({
				title: '<i class="icon-music"></i>&nbsp;<b>Dados da Aula</b>',
				html: true,
				trigger: 'hover',
				content: function(){
					return $('[rel="popover"]').parent().find('.conteudo_popover').html();
				}
			});
		});
		
		var converteData = function(time){
			var date = new Date(time);
			var sdf = new JsSimpleDateFormat('dd/MM/yyyy');
			return sdf.format(date);
		};
		
		var editaMatricula = function(idMatricula){
			$('#formMatricula').attr('action','<c:url value="/matriculas/'+idMatricula+'"/>');
			$('#formMatricula').submit();
		};
		
		var listaAulas = function(idMatricula){
			$('#formMatricula').attr('action','<c:url value="/aula/listar/'+idMatricula+'"/>');
			$('#formMatricula').attr('method','GET');
			$('#formMatricula').submit();
		};
	</script>
</body>
</html>