<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<spring:bind path="name">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="name">Name</label>
			<div class="col-sm-9">
				<form:input path="name" class="form-control" id="name" type="text" maxlength="45" placeholder="Name" autofocus="true"/>
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="name" class="help-block" element="span" />
				<c:out value="${nameExists}"></c:out>
			</div>
		</div>
	</spring:bind>
	
	<spring:bind path="maxNumberOfPlayers">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="maxNumberOfPlayers">Number of Players</label>
			<div class="col-sm-9">
				<form:input path="maxNumberOfPlayers" class="form-control" id="maxNumberOfPlayers" type="number" placeholder="Number of Players" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="maxNumberOfPlayers" class="help-block" element="span" />
				<c:out value="${maxNumberOfPlayersExists}"></c:out>
			</div>
		</div>
	</spring:bind>