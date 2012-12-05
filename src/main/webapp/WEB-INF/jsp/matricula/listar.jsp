<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="span12">
		<form class="form-search">
			<div class="input-append">
    			<input type="text" id="aluno" placeholder="Nome do Aluno" class="input-medium search-query">
   				<a class="btn" href="javascript:buscarMatriculas();"><i class="icon-search"></i></a>
  			</div>
		</form>
		<form id="formMatricula" method="GET">
			<h2>Matrículas</h2>
			<table id="tabelaMatricula" width="100%" class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Data</th>
						<th>Aluno</th>
						<th>Responsável</th>
						<th>Curso</th>
						<th>Tipo de Aula</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${matriculaList}" var="matricula">
						<tr onclick="consultaMatricula(${matricula.id});" class="cursor-pointer">
							<td>${matricula.id }</td>
							<td><fmt:formatDate value="${matricula.data.time}" pattern="dd/MM/yyyy" /></td>
							<td>${matricula.aluno.nome }</td>
							<td>${matricula.responsavel.nome }</td>
							<td>${matricula.curso.nome }</td>
							<td>
								<c:forEach items="${matricula.listaEsquemaAula}" var="esquemaAula">
									${esquemaAula.tipoAula.nome}<br />
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="<c:url value="/js/simpleDateFormat.js"/>"></script>
	<script type="text/javascript">
		var buscarMatriculas = function(){
			$.ajax({
				url:'<c:url value="/matriculas/busca.json"/>',
				dataType: 'json',
				data: 'nomeAluno='+$('#aluno').val(),
				cache:false,
				success: function(json){
					$('#tabelaMatricula tbody').remove();
					var tr = '';
					for (var i=0;i<json.list.length;i++){
						item = json.list[i];
						tr += '<tr>';
						tr += '<td>'+item.id+'</td>';
						tr += '<td>'+converteData(item.data.time)+'</td>';
						tr += '<td>'+item.aluno.nome+'</td>';
						tr += '<td>'+item.responsavel.nome+'</td>';
						tr += '<td>'+item.curso.nome+'</td>';
						tr += '<td>';
						for (var ii=0;ii<item.listaTipoAula.length;ii++){
							tr += item.listaTipoAula[ii].nome+'<br />';
						}
						tr +='</td>';
						tr += "</tr>";
					}
					$('#tabelaMatricula').append(tr);
				}
			});
		};
		
		var converteData = function(time){
			var date = new Date(time);
			var sdf = new JsSimpleDateFormat('dd/MM/yyyy');
			return sdf.format(date);
		};
		
		var consultaMatricula = function(idMatricula){
			$('#formMatricula').attr('action','<c:url value="/matriculas/'+idMatricula+'"/>');
			$('#formMatricula').submit();
		}
	</script>
</body>
</html>