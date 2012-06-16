<%@include file="../tags.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form>
	<input type="text" id="aluno" />
</form>
<h3>Matricula</h3>
<table id="tabelaMatricula" width="100%" border="1">
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
<%@include file="../principal.jsp" %>
<script type="text/javascript">
	$('#aluno').keyup(function(){
		$.ajax({
			url:'<c:url value="/matricula/busca.json"/>',
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
					tr += '<td>'+item.curso+'</td>';
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