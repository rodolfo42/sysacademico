<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="../jsp/taglibs.jsp"%>

<c:if test="${not empty mensagem.mensagem}">
	<div class="alert alert-${mensagem.tipo} fade in">
		<a class="close" data-dismiss="alert">&times;</a> ${mensagem.mensagem}
	</div>
</c:if>