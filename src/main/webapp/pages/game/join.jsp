<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Join a game</h1>

<c:import url="../template/alerts.jsp" />

<c:if test="${empty games}">
	<div class="jumbotron">
  <h3>No games by other players hosted yet!</h3>
  <p><a class="btn btn-primary btn-lg" href="game/host" role="button">Host</a> your own game.</p>
</div>
</c:if>

<div class="row">
	<c:forEach items="${games}" var="game">
		<div class="col-sm-4 col-md-3">
			<div class="panel panel-primary">
			  <div class="panel-heading">
			    <h3 class="panel-title">${game.name} <button onclick="location.href='game/join/${game.id}'" class="btn btn-xs btn-default pull-right">Join Game</button></h3>   
			  </div>
			  <div class="panel-body">
			    Panel content
			  </div>
			  <div class="panel-footer">
			    Hoster: ${game.hoster.name}, Maximum number of Players: ${game.maxNumberOfPlayers}
			  </div>
			</div>
		</div>
	</c:forEach>
</div>

<c:import url="../template/footer.jsp" />