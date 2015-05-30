<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Create a new game</h1>

<c:import url="../template/alerts.jsp" />

<form:form class="form-horizontal" role="form" method="post" modelAttribute="gameForm" action="game/create">

	<c:import url="../formInputs/gameFormInputs.jsp" />

	<div class="col-sm-3">
		<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Create new Game</button>
		<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
	</div>
</form:form>

<c:import url="../template/footer.jsp" />