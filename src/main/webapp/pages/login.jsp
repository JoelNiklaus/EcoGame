<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

<c:import url="template/alerts.jsp" />

<div class="col-md-12">
	<div class="modal-dialog" style="margin-bottom: 0">
		<div class="modal-content">
			<div class="panel-heading">
				<h1>Sign In</h1>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" name="login" action="<c:url value="/j_spring_security_check"/>"
					method="post">
					
					<div class="form-group">
						<label class="col-sm-3 control-label">User Name</label>
						<div class="col-sm-9">
							<input class="form-control" type="text" placeholder="User Name" id="j_username"
								name="j_username" autofocus="true">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Password</label>
						<div class="col-sm-9">
							<input class="form-control" placeholder="Password" type="password" id="j_password"
								name="j_password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<div class="checkbox">
								<label> <input type="checkbox"
									name="_spring_security_remember_me"> Remember me
								</label>
							</div>
						</div>
					</div>
					
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							Failed to login.
							<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
						                  Reason: <c:out
									value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</c:if>
						</div>
					</c:if>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span> Login</button>
							<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
							<button type="button" onclick="window.location.href='register'"
								class="btn btn-info"><span class="glyphicon glyphicon-new-window"></span> Register</button>
							<a href="forgot">Forgot Password?</a>
							<%-- <input type="hidden" id="${_csrf.parameterName}" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> --%>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<c:import url="template/footer.jsp" />
