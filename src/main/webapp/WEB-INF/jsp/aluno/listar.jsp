<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ include file="../taglibs.jsp"%>
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
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${aluno.dataNascimento}" /></td>
					<td>
						<c:forEach items="${aluno.listaResponsavel}" var="responsavel">
							<div><i class="icon icon-user"></i> ${responsavel.nome}</div>
						</c:forEach>
					</td>
					<td>
						<div class="btn-group">
							<a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon icon-cog"></i>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu pull-right">
								<li><a href="javascript:void(0);"><i class="icon icon-user"></i> adicionar responsável (em breve)</a></li>
								<li><a href="<c:url value="/matriculas/matricular/${aluno.id}" />"><i class="icon icon-legal"></i> efetuar matrícula</a></li>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>