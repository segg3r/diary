<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Main page</title>

<link
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700|Open+Sans:300italic,400,300,700'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/semantic/css/semantic.css" />'>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.js"></script>
<script src='<c:url value="/semantic/javascript/semantic.js" />'></script>

</head>
<body style="margin: 0px;">
	<c:if test="${not empty applicationUser }">
		<div class="ui grid">
			<div class="two wide column"></div>
			<div class="twelve wide column"><%@include file="../../topMenu.jsp"%></div>
			<div class="two wide column"></div>
		</div>

		<div class="ui grid">
			<div class="two wide column"></div>
			<div class="four wide column">
				<%@include file="../../leftMenu.jsp"%>
			</div>
			<div class="eight wide column">
				<h2 class="ui header">Themes found</h2>
				<table class="ui table segment">
					<thead>
						<tr>
							<th class="six wide">Name</th>
							<th class="six wide">Owner</th>
							<th class="two wide"></th>
							<th class="two wide"></th>
						</tr>
					</thead>
					<c:forEach items="${themesFound }" var="theme">
						<tr>
							<td><a href='<c:url value="/theme/${theme.id }"></c:url>'> ${theme.name }</a></td>
							<td>${theme.user.fullName }</td>
							<td><i class="red heart icon"></i> ${themesFoundLikes[theme] }</td>
							<td><c:if test="${not (theme.user eq applicationUser) }">
									<div class="ui tiny labeled icon button"
										onclick='window.location.href="<c:url value="/theme/subscribe/${theme.id }" />"'>
										<i class="add icon"></i>Subscribe
									</div>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="two wide column"></div>
		</div>
	</c:if>

	<script type="text/javascript">
		$('.ui.dropdown').dropdown();
	</script>
</body>
</html>