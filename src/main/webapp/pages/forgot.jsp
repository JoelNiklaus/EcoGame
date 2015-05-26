<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>Forgot Password</h1>

<c:import url="template/alerts.jsp" />

<form:form method="post" modelAttribute="forgotPasswordForm" action="forgot"
	id="forgotPasswordForm" autocomplete="on">
	<spring:bind path="email">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-2" for="email">E-Mail</label>
			<div class="col-sm-7">
				<form:input path="email" class="form-control" id="email" type="email" maxlength="45" placeholder="E-Mail" autofocus="true"/>
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="email" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
		<div class="form-group">
			<div class="col-sm-3">
				<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Send Password</button>
			</div>
		</div>
</form:form>

<c:import url="template/footer.jsp" />