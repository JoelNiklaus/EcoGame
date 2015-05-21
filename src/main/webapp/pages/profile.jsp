<%@ page language="java" pageEncoding="UTF-8"
  	contentType="text/html;charset=utf-8"%>  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  


<c:import url="template/header.jsp" />


<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<link href="/ecogame/css/dropzone.css" type="text/css" rel="stylesheet" /> 
<h1>My Profile <a href="otherProfileView/${loggedInUser.id}" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-user"></span> View Profile</a></h1>

  
<c:import url="template/alerts.jsp" />
 	
<form:form class="form-horizontal" role="form" method="post" modelAttribute="profileForm" action="profile"
	id="profileForm" autocomplete="on">
	
	<spring:bind path="email">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="email">E-Mail</label>
			<div class="col-sm-9">
				<form:input path="email" class="form-control" id="email" type="email" maxlength="45" placeholder="E-Mail" autofocus="true"/>
				<form:errors path="email" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="username">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="username">User Name</label>
			<div class="col-sm-9">
				<form:input path="username" class="form-control" id="username" type="text" maxlength="45" placeholder="User Name" />
				<form:errors path="username" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="password">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="password">Password</label>
			<div class="col-sm-9">
				<form:password path="password" class="form-control" id="password" maxlength="45" placeholder="Password" />
				<form:errors path="password" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="passwordConfirm">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="passwordConfirm">Confirm Password</label>
			<div class="col-sm-9">
				<form:password path="passwordConfirm" class="form-control" id="passwordConfirm" maxlength="45" placeholder="Confirm Password" />
				<form:errors path="passwordConfirm" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="status">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="description">Status</label>
			<div class="col-sm-9">
				<form:textarea path="status" class="form-control" id="status" type="text" maxlength="1000" placeholder="Status"/>
				<form:errors path="status" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
		<div class="form-group">
			<label class="control-label col-sm-3" for="imageId">Profile Image</label>
			<div class="col-sm-6">
		 		<form:input class="form-control" path="imageId" type="hidden" name="file" id="file" value="${loggedInUser.profileImage.id}" />	
				<div class="dropzone col-sm-6" id="file-dropzone">
					<div class="dz-message" data-dz-message>
						<span>Click or Drag and Drop to this field to upload images</span>
					</div>
				</div>
			</div>
			<script src="/ecogame/js/dropzone.min.js"></script>
 			<script src="/ecogame/js/ddupload.js"></script>
			
			<div class="col-sm-3">
			<br>
				<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-floppy-save"></span> Save</button>
				<button class="btn btn-warning" type="reset"><span class="glyphicon glyphicon-remove"></span> Reset</button>
				<a onclick="location.href='profile/delete/${loggedInUser.id}'" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</a>
			</div>
		</div>
 </form:form>
 
<c:import url="template/footer.jsp" />