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
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-2" for="email">E-Mail</label>
			<div class="col-sm-7">
				<form:input path="email" class="form-control" id="email" type="email" maxlength="45" placeholder="E-Mail" />
				<form:errors path="email" class="help-block" element="span" />
			</div>
			<div class="col-sm-3">
				<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Send Password</button>
			</div>
		</div>
	</spring:bind>
	
</form:form>

<c:import url="template/footer.jsp" />