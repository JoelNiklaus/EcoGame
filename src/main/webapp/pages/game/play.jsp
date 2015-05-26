<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Play
<c:if test="${game.hoster.id eq loggedInPlayer.id}">
	<a href="game/edit" class="btn btn-info pull-right" role="button">edit</a>
</c:if>
</h1>

<c:import url="../template/alerts.jsp" />

<c:import url="../template/footer.jsp" />