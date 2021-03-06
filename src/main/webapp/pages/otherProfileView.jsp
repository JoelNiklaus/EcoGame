<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<div class="jumbotron">
<h1>${otherUser.username}</h1>
<div class="row">
	<div class="col-sm-4">
		<c:if test="${not empty otherUser.profileImage}">
			<img width="300px" class="gallery" src="/ecogame/img/pictures/${otherUser.profileImage.fileName}" />
		</c:if>
		<c:if test="${empty otherUser.profileImage}">
			<img width="300px" class="gallery" src="/ecogame/img/Profile_Placeholder.jpg" />
		</c:if>
	</div>
	<div class="col-sm-4">
		<p>
			<a href="mailto:${otherUser.email }">${otherUser.email }</a><br>
		</p>
	</div>
	<div class="col-sm-4">
		<p>${otherUser.status }</p>
	</div>
</div>
</div>
<c:import url="template/footer.jsp" />