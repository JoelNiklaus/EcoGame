<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<c:import url="../template/alerts.jsp" />

<div class="jumbotron">
  <h1>Welcome to the EcoGame!</h1>
  <p>You can either <a href="game/join">join</a> an existing game or <a href="game/host">host</a> your own.</p>
  <p><a class="btn btn-primary btn-lg pull-left" href="game/join" role="button">Join Game</a><a class="btn btn-primary btn-lg pull-right" href="game/host" role="button">Host Game</a><br></p>
</div>

<c:import url="../template/footer.jsp" />