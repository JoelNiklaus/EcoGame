<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Conjuncture
	<a href="game/edit" class="btn btn-info pull-right" role="button">edit Game</a>
</h1>

<c:import url="../template/alerts.jsp" />

<br>
Random: ${conjuncture.random }

<c:import url="../template/footer.jsp" />