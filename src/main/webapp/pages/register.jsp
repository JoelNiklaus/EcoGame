<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

	<h1>Create new account</h1>

<c:import url="template/alerts.jsp" />

<form:form class="form-horizontal" role="form" method="post" modelAttribute="registerForm" action="register"
	id="registerForm" autocomplete="on">
	
	<spring:bind path="email">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="email">E-Mail</label>
			<div class="col-sm-9">
				<form:input path="email" class="form-control" id="email" type="email" maxlength="45" placeholder="E-Mail" autofocus="true"/>
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="email" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="username">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="username">User Name</label>
			<div class="col-sm-9">
				<form:input path="username" class="form-control" id="username" type="text" maxlength="45" placeholder="User Name" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="username" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="password">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="password">Password</label>
			<div class="col-sm-9">
				<form:password path="password" class="form-control" id="password" maxlength="45" placeholder="Password" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="password" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="passwordConfirm">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="passwordConfirm">Confirm Password</label>
			<div class="col-sm-9">
				<form:password path="passwordConfirm" class="form-control" id="passwordConfirm" maxlength="45" placeholder="Confirm Password" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="passwordConfirm" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
		<div class="form-group">			
			<div class="col-sm-3">
			<br>
				<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-new-window"></span> Register</button>
				<button class="btn btn-warning" type="reset"><span class="glyphicon glyphicon-remove"></span> Reset</button>
				<button type="button" onclick="window.location.href='register'"
								class="btn btn-info"><span class="glyphicon glyphicon-log-in"></span> Login</button>
			</div>
		</div>
</form:form>
<%-- <c:if test="${page_error != null }">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<h4>Error!</h4>
		${page_error}
	</div>
</c:if> --%>
<c:import url="template/footer.jsp" />
