<%@include file="../head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<h3>Alunos</h3>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Data de Nascimento</th>
				<th>Responsáveis</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${alunoList}" var="aluno">
				<tr>
					<td>${aluno.id }</td>
					<td>${aluno.nome }</td>
					<td>${aluno.dataNascimento }</td>
					<td>
						<c:forEach items="${aluno.listaResponsavel }" var="responsavel">
							${responsavel.nome }<br />
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>