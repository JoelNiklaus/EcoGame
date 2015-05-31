<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Statistics
	<a href="game/edit" class="btn btn-info pull-right" role="button">edit Game</a>
</h1>

<c:import url="../template/alerts.jsp" />

<ul class="nav nav-tabs">
  <li role="presentation"><a href="game/budget">Budget</a></li>
  <li role="presentation" class="active"><a href="game/statistics">Statistics</a></li>
</ul>

<br>
Balance: ${company.bankAccount.balance }0 CHF

<c:import url="../template/footer.jsp" />