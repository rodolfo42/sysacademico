<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<div class="textCenter">
		<h1>Bem-vindo(a) ao Sistema Acadêmico Pri-si-lá</h1>
	</div>
	
	<div>
	<strong>Demonstração de loaders e error/success handlers AJAX:</strong>
		<a class="btn btn-danger erro" href="#">error</a>
		<a class="btn btn-success ok" href="#">success</a>
	</div>
	<script type="text/javascript">
	$(function(){
		$('.erro').bind('click', function(){
			$.ajax({
				url: "<c:url value="/teste/erro" />",
				type: 'GET',
				errorMessage: "Não foi possível efetuar a operação",
			});
		});
		$('.ok').bind('click', function(){
			$.ajax({
				url: "<c:url value="/teste/ok" />",
				type: 'GET',
				successMessage: "Operação efetuada com sucesso"
			});
		});
	});
	</script>
</body>
</html>