<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="../jsp/taglibs.jsp" %>

<%@ attribute name="idComboSeleciona" required="true" %>
<%@ attribute name="idComboSelecionado" required="true" %>
<%@ attribute name="listaSeleciona" required="true" type="java.util.List" %>
<%@ attribute name="listaSelecionado" required="false" type="java.util.List" %>
<%@ attribute name="nameSelecionado" required="true" %>
<%@ attribute name="labelSeleciona" required="true" %>
<%@ attribute name="labelSelecionado" required="true" %>

<table>
	<tr>
		<th align="left"><label for="${idComboSeleciona }">${labelSeleciona }:</label></th>
		<th>&nbsp;</th>
		<th align="left"><label for="${idComboSelecionado }">${labelSelecionado }:</label></th>
	</tr>
	<tr>
		<td>
			<select name="listaSelecionado" id="${idComboSeleciona }" multiple="multiple">
				<c:forEach items="${listaSeleciona }" var="seleciona">
					<option value="${seleciona.id }">${seleciona.nome }</option>
				</c:forEach>
			</select>
		</td>
		<td valign="middle">
			<i class="icon-fast-backward seta-esquerda"></i>
			<i class="icon-fast-forward seta-direita"></i>
		</td>
		<td>
			<select name="${nameSelecionado }" id="${idComboSelecionado }" multiple="multiple">
				<c:if test="${!empty listaSelecionado }">
					<c:forEach items="${listaSelecionado }" var="selecionado">
						<option value="${selecionado.id }" selected="selected">${selecionado.nome }</option>
					</c:forEach>
				</c:if>
			</select>
		</td>
	</tr>
</table>

<script type="text/javascript">
	$(document).ready(function(){
		organizaCombo('${idComboSeleciona }','${idComboSelecionado }');
	});
	$('.seta-esquerda').click(function(){
		transfere('${idComboSelecionado }','${idComboSeleciona }');
	});
	$('#${idComboSelecionado }').dblclick(function(){
		$('.seta-esquerda').click();
	});
	$('.seta-direita').click(function(){
		transfere('${idComboSeleciona }','${idComboSelecionado }');
	});
	$('#${idComboSeleciona }').dblclick(function(){
		$('.seta-direita').click();
	});
</script>
