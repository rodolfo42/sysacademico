<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="/professores/adicionar"/>" id="formAdicionarProfessor" method="POST">
		<fieldset>
			<legend>Professor</legend>

			<label for="nome">Nome:</label> <input id="nome" type="text" name="professor.nome" /> <label for="tel">Tel:</label>
			<input id="tel" class="input-small" type="text" name="professor.telefone" /> <label for="cel">Cel:</label> <input
				id="cel" class="input-small" type="text" name="professor.celular" /> <label for="email">Email:</label> <input
				id="email" type="text" name="professor.email" />

			<tag:comboSelecionaESelecionado labelSeleciona="Cursos" idComboSeleciona="curso"
				nameSelecionado="professor.listaCurso.id" listaSeleciona="${cursoList }" labelSelecionado="Cursos selecionados"
				idComboSelecionado="curso_selecionado" />

			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#segunda">Segunda-Feira</a>
				</li>
				<li><a href="#terca">Ter�a-Feira</a>
				</li>
				<li><a href="#quarta">Quarta-feira</a>
				</li>
				<li><a href="#quinta">Quinta-Feira</a>
				</li>
				<li><a href="#sexta">Sexta-Feira</a>
				</li>
				<li><a href="#sabado">S�bado</a>
				</li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="segunda">
					<div class="container">
						<div class="row">
							<div class="span3">
								<a class="pointer mfAddSwitch pull-right" rel="FieldsContainer" title="Adicionar mais um hor�rio"><img
									alt="add" src="<c:url value="/img/plus16x16.png"/>" />
								</a>
							</div>
						</div>
						<div class="row">
							<div id="FieldsContainer" class="mfContainer" data-max-fields="10">
								<div class="controls controls-row mfTemplate">
									<div class="span3">
										<i class="icon-time"></i> <input type="text" id="horaInicio1"
											name="professor.listaHorarioProfessor[].horaInicioTexto" class="span1" /> �s <input type="text"
											id="horaFim1" name="professor.listaHorarioProfessor[].horaFimTexto" class="span1" /> <a
											class="pointer mfRemoveSwitch pull-right" title="Remover um hor�rio"> <img alt="add"
											src="<c:url value="/img/minus16x16.png"/>" /> </a>
									</div>
									<input type="hidden" name="professor.listaHorarioProfessor[].diaDaSemana" value="SEGUNDAFEIRA" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="terca">
					<div class="container">
						<div class="row">
							<div class="span3">
								<a class="pointer mfAddSwitch pull-right" rel="FieldsContainer" title="Adicionar mais um hor�rio"><img
									alt="add" src="<c:url value="/img/plus16x16.png"/>" />
								</a>
							</div>
						</div>
						<div class="row">
							<div id="FieldsContainer" class="mfContainer" data-max-fields="10">
								<div class="controls controls-row mfTemplate">
									<div class="span3">
										<i class="icon-time"></i> <input type="text" id="horaInicio1"
											name="professor.listaHorarioProfessor[].horaInicioTexto" class="span1" /> �s <input type="text"
											id="horaFim1" name="professor.listaHorarioProfessor[].horaFimTexto" class="span1" /> <a
											class="pointer mfRemoveSwitch pull-right" title="Remover um hor�rio"> <img alt="add"
											src="<c:url value="/img/minus16x16.png"/>" /> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="quarta">
					<div class="container">
						<div class="row">
							<div class="span3">
								<a class="pointer mfAddSwitch pull-right" rel="FieldsContainer" title="Adicionar mais um hor�rio"><img
									alt="add" src="<c:url value="/img/plus16x16.png"/>" />
								</a>
							</div>
						</div>
						<div class="row">
							<div id="FieldsContainer" class="mfContainer" data-max-fields="10">
								<div class="controls controls-row mfTemplate">
									<div class="span3">
										<i class="icon-time"></i> <input type="text" id="horaInicio1"
											name="professor.listaHorarioProfessor[].horaInicioTexto" class="span1" /> �s <input type="text"
											id="horaFim1" name="professor.listaHorarioProfessor[].horaFimTexto" class="span1" /> <a
											class="pointer mfRemoveSwitch pull-right" title="Remover um hor�rio"> <img alt="add"
											src="<c:url value="/img/minus16x16.png"/>" /> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="quinta">
					<div class="container">
						<div class="row">
							<div class="span3">
								<a class="pointer mfAddSwitch pull-right" rel="FieldsContainer" title="Adicionar mais um hor�rio"><img
									alt="add" src="<c:url value="/img/plus16x16.png"/>" />
								</a>
							</div>
						</div>
						<div class="row">
							<div id="FieldsContainer" class="mfContainer" data-max-fields="10">
								<div class="controls controls-row mfTemplate">
									<div class="span3">
										<i class="icon-time"></i> <input type="text" id="horaInicio1"
											name="professor.listaHorarioProfessor[].horaInicioTexto" class="span1" /> �s <input type="text"
											id="horaFim1" name="professor.listaHorarioProfessor[].horaFimTexto" class="span1" /> <a
											class="pointer mfRemoveSwitch pull-right" title="Remover um hor�rio"> <img alt="add"
											src="<c:url value="/img/minus16x16.png"/>" /> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="sexta">
					<div class="container">
						<div class="row">
							<div class="span3">
								<a class="pointer mfAddSwitch pull-right" rel="FieldsContainer" title="Adicionar mais um hor�rio"><img
									alt="add" src="<c:url value="/img/plus16x16.png"/>" />
								</a>
							</div>
						</div>
						<div class="row">
							<div id="FieldsContainer" class="mfContainer" data-max-fields="10">
								<div class="controls controls-row mfTemplate">
									<div class="span3">
										<i class="icon-time"></i> <input type="text" id="horaInicio1"
											name="professor.listaHorarioProfessor[].horaInicioTexto" class="span1" /> �s <input type="text"
											id="horaFim1" name="professor.listaHorarioProfessor[].horaFimTexto" class="span1" /> <a
											class="pointer mfRemoveSwitch pull-right" title="Remover um hor�rio"> <img alt="add"
											src="<c:url value="/img/minus16x16.png"/>" /> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="sabado">
					<div class="container">
						<div class="row">
							<div class="span3">
								<a class="pointer mfAddSwitch pull-right" rel="FieldsContainer" title="Adicionar mais um hor�rio"><img
									alt="add" src="<c:url value="/img/plus16x16.png"/>" />
								</a>
							</div>
						</div>
						<div class="row">
							<div id="FieldsContainer" class="mfContainer" data-max-fields="10">
								<div class="controls controls-row mfTemplate">
									<div class="span3">
										<i class="icon-time"></i> <input type="text" id="horaInicio1"
											name="professor.listaHorarioProfessor[].horaInicioTexto" class="span1" /> �s <input type="text"
											id="horaFim1" name="professor.listaHorarioProfessor[].horaFimTexto" class="span1" /> <a
											class="pointer mfRemoveSwitch pull-right" title="Remover um hor�rio"> <img alt="add"
											src="<c:url value="/img/minus16x16.png"/>" /> </a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</fieldset>
		<br />
		<button type="submit" class="btn">Enviar</button>
	</form>
	<script type="text/javascript">
		$('#myTab a').click(function(){
			$(this).tab('show');			
		});
		$('.mfAddSwitch').tooltip();
	</script>
</body>
</html>