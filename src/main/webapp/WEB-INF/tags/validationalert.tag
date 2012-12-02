<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="../jsp/taglibs.jsp"%>
<c:if test="${not empty errors}">
	<div class="alert alert-block alert-error">
		<h4>Corrija os seguintes erros</h4>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li>${error.message}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>