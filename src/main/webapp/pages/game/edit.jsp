<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Edit your hosted game</h1>

<c:import url="../template/alerts.jsp" />

<form:form class="form-horizontal" role="form" method="post" modelAttribute="gameForm" action="game/edit">

	<spring:bind path="name">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="name">Name</label>
			<div class="col-sm-9">
				<form:input path="name" class="form-control" id="name" type="text" maxlength="45" placeholder="Name" />
				<form:errors path="name" class="help-block" element="span" />
				<c:out value="${nameExists}"></c:out>
			</div>
		</div>
	</spring:bind>
	
	<spring:bind path="numberOfPlayers">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="numberOfPlayers">Number of Players</label>
			<div class="col-sm-9">
				<form:input path="numberOfPlayers" class="form-control" id="numberOfPlayers" type="number" placeholder="Number of Players" />
				<form:errors path="numberOfPlayers" class="help-block" element="span" />
				<c:out value="${numberOfPlayersExists}"></c:out>
			</div>
		</div>
	</spring:bind>

	<div class="col-sm-3">
		<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Host new Game</button>
		<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
	</div>
</form:form>

<c:import url="../template/footer.jsp" />