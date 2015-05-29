<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Edit your hosted game</h1>

<c:import url="../template/alerts.jsp" />

<div class="row">
	<h2>Game</h2>
	<c:set var="submitted" value="${pageContext.request.method == 'POST'}" />
	<form:form class="form-horizontal" role="form" method="post" modelAttribute="gameForm" action="game/edit">
	
		<c:import url="../formInputs/gameFormInputs.jsp" />
	
		<div class="col-sm-12">
			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Save</button>
			<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
			<a onclick="location.href='game/edit/delete/${game.id}'" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</a>
		</div>
	</form:form>
</div>
<br>
<div class="row">
	<h2>Players</h2>
	<ul class="list-group">
		<c:forEach items="${game.players}" var="player">
			<li class="list-group-item"><a href="otherProfileView/${player.id}">${player.username}</a>
			<a onclick="location.href='game/edit/kickPlayer/${player.id}'" class="btn btn-xs btn-danger pull-right">Kick Player</a></li>
		</c:forEach>
	</ul>
</div>

<c:import url="../template/footer.jsp" />