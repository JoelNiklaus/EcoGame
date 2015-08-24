<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Budget
	<a href="game/edit" class="btn btn-info pull-right" role="button">edit Game</a>
</h1>

<c:import url="../template/alerts.jsp" />

<br>

<form:form class="form-horizontal" role="form" method="post" modelAttribute="budgetForm" action="game/budget"
	id="budgetForm" autocomplete="on">

	<c:set var="name" value="resourceQuality" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productPrice" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productionVolume" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>

	<c:set var="name" value="machines" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productionHalls" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="resourceWareHouses" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productWareHouses" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="marketing" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="dividend" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="research" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="specialFaculties" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productionPersonnel" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productionPersonnelTraining" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="productionPersonnelWage" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
		<c:set var="name" value="representative" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="representativeTraining" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	
	<c:set var="name" value="representativeWage" />
	<spring:bind path="${name}">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="${name}">${name}</label>
			<div class="col-sm-9">
				<form:input path="${name}" class="form-control" id="${name}" type="text" maxlength="45" placeholder="${name}" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="${name}" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>

		<div class="form-group">			
			<div class="col-sm-3">
			<br>
				<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-save"></span> adopt Budget</button>
				<button class="btn btn-warning" type="reset"><span class="glyphicon glyphicon-remove"></span> Reset</button>
			</div>
		</div>
</form:form>

<c:import url="../template/footer.jsp" />