<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Budget
	<a href="game/edit" class="btn btn-info pull-right" role="button">edit Game</a>
</h1>

<c:import url="../template/alerts.jsp" />

<ul class="nav nav-tabs">
  <li role="presentation" class="active"><a href="game/budget">Budget</a></li>
  <li role="presentation"><a href="game/statistics">Statistics</a></li>
</ul>

<br>

<form:form class="form-horizontal" role="form" method="post" modelAttribute="budgetForm" action="budget"
	id="budgetForm" autocomplete="on">

	<spring:bind path="numberOfResourcesToBuy">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="numberOfResourcesToBuy">Purchase</label>
			<div class="col-sm-9">
				<form:input path="numberOfResourcesToBuy" class="form-control" id="numberOfResourcesToBuy" type="text" maxlength="45" placeholder="How many resources do you want to buy" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="numberOfResourcesToBuy" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="numberOfProductsToProduce">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="numberOfProductsToProduce">Production</label>
			<div class="col-sm-9">
				<form:input path="numberOfProductsToProduce" class="form-control" id="numberOfProductsToProduce" type="text" maxlength="45" placeholder="How many products do you want to produce" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="numberOfProductsToProduce" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="numberOfProductsToSell">
		<div class="form-group has-feedback ${status.error ? 'has-error' : ''} ${submitted && !status.error ? 'has-success' : ''}">
			<label class="control-label col-sm-3" for="numberOfProductsToSell">Sale</label>
			<div class="col-sm-9">
				<form:input path="numberOfProductsToSell" class="form-control" id="numberOfProductsToSell" type="text" maxlength="45" placeholder="How many products do you want to sell" />
				<span class="${status.error ? 'glyphicon glyphicon-remove' : ''} ${submitted && !status.error ? 'glyphicon glyphicon-ok' : ''} form-control-feedback"></span>
				<form:errors path="numberOfProductsToSell" class="help-block" element="span" />
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