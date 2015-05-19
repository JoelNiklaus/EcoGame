<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>EcoGame</title>
		
		<!-- Set localhost/ecogame as base url -->
		<c:set var="req" value="${pageContext.request}" />
		<c:set var="url">${req.requestURL}</c:set>
		<c:set var="uri" value="${req.requestURI}" />
		<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
		
		<link rel="shortcut icon" href="img/favicon.ico" >
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/custom.css">
		
		<!-- jQuery at the beginning, otherwise e.g. sliders won't work -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script>
			$(document).ready(
					function() {
						$('a[href="'+ this.location.pathname.replace('/ecogame/', '') + '"]').parent().addClass('active');
						});
		</script>
		
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index"><span class="glyphicon glyphicon-home"></span> EcoGame</a>
				</div>
	
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="navbar">
					<ul class="nav navbar-nav">
						<c:if test="${not empty loggedInUser}">
							<li><a href="game/play">Play</a></li>
						</c:if>
					</ul>
					<c:if test="${empty loggedInUser}">
						<form class="navbar-form navbar-right" name="login"
							action="<c:url value="/j_spring_security_check"/>" method="post">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">@</div>
									<input type="email" class="form-control" id="j_username"
										name="j_username" placeholder="E-Mail" autofocus="true">
								</div>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="j_password"
									name="j_password" placeholder="Password">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" name="_spring_security_remember_me"> Remember me
								</label>
							</div>
							<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span> Login</button>
							<button type="button" onclick="window.location.href='register'"
								class="btn btn-info"><span class="glyphicon glyphicon-new-window"></span> Register</button>
						</form>
					</c:if>
					<c:if test="${not empty loggedInUser}">
						<form class="navbar-form navbar-right" action="logout" method="get">
							<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-log-out"></span> Logout</button>
						</form>
						<p class="navbar-text navbar-right"><a class="navbar-link" href="profile"><span class="glyphicon glyphicon-user"></span></a> Signed in as <a class="navbar-link" href="profile">${loggedInUser.username}</a></p>
					</c:if>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
		<div class="container">