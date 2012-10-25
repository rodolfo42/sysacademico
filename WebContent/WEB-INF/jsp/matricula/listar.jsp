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
<div class="span12">
<form class="well form-search">
	<label for="aluno">Aluno:</label>
	<input type="text" id="aluno" />
</form>
<h2>Matrículas</h2>
<table id="tabelaMatricula" width="100%" class="table table-striped">
	<thead>
		<tr>
			<th>Id</th>
			<th>Data</th>
			<th>Aluno</th>
			<th>Responsável</th>
			<th>Tipo de Matrícula</th>
			<th>Curso</th>
			<th>Tipo de Aula</th>
		</tr>
	</thead>
<tbody>
<c:forEach items="${matriculaList}" var="matricula">
	<tr>
		<td>${matricula.id }</td>
		<td>${matricula.data }</td>
		<td>${matricula.aluno.nome }</td>
		<td>${matricula.responsavel.nome }</td>
		<td>${matricula.tipoMatricula.nome }</td>
		<td>${matricula.curso.nome }</td>
		<td>
			<c:forEach items="${matricula.listaTipoAula}" var="tipoAula">
				${tipoAula.nome}<br />
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>
<script type="text/javascript">
	$('#aluno').keyup(function(){
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
					tr += '<td>'+item.data+'</td>';
					tr += '<td>'+item.aluno.nome+'</td>';
					tr += '<td>'+item.responsavel.nome+'</td>';
					tr += '<td>'+item.tipoMatricula.nome+'</td>';
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
	});
</script>
</body>
</html>