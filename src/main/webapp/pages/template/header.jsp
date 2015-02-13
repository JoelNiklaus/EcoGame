<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>EcoGame</title>
		
		<link rel="shortcut icon" href="/ecogame/img/favicon.ico" >
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="/ecogame/css/custom.css">
		
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
						<li><a href="hostGame"><span class="glyphicon glyphicon-search"></span> Game</a></li>
						<c:if test="${not empty loggedInUser}">

						</c:if>
					</ul>
					<c:if test="${empty loggedInUser}">
						<form class="navbar-form navbar-right" name="f"
							action="<c:url value="/j_spring_security_check"/>" method="post">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">@</div>
									<input type="email" class="form-control" id="j_username"
										name="j_username" placeholder="E-Mail">
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
						<p class="navbar-text navbar-right"><span class="glyphicon glyphicon-user"></span> Signed in as <a class="badge" href="profile">${loggedInUser.firstName }
									${loggedInUser.lastName }</a></p>
					</c:if>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
		<div class="container">