<%@page import="com.prisila.util.StringUtil"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<h3>Alunos</h3>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Data de Nascimento</th>
				<th>Responsáveis</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${alunoList}" var="aluno">
				<tr>
					<td>${aluno.id }</td>
					<td>${aluno.nome }</td>
					<td>${aluno.dataNascimento }</td>
					<td>
						<c:forEach items="${aluno.listaResponsavel}" var="responsavel">
							${responsavel.nome }<br />
						</c:forEach>
					</td>
					<td>
						<a href="<c:url value="/alunos/addresponsavel/${aluno.id}" />" class="btn btn-small">adicionar responsável</a>
						<a href="<c:url value="/matriculas/matricular/${aluno.id}" />" class="btn btn-success">efetuar matrícula</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>