<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<c:if test="${empty applicationUser }">
		<div class="ui grid">
			<div class="four wide column"></div>
			<div class="eight wide column">
				<h2 class="ui top attached header">Sign In</h2>
				<f:form method="POST" servletRelativeAction="/user/login"
					modelAttribute="credentials"
					cssClass="ui error form segment attached">

					<f:errors path="*" element="div" cssClass="ui error message" />
					<div class="field">
						<label>E-mail</label>
						<div class="ui left labeled icon input">
							<f:input path="email" name="email" />
							<i class="mail icon"></i>
							<div class="ui corner label">
								<i class="icon asterisk"></i>
							</div>
						</div>
					</div>
					<div class="field">
						<label>Password</label>
						<div class="ui left labeled icon input">
							<f:password path="password" name="password" />
							<i class="lock icon"></i>
							<div class="ui corner label">
								<i class="icon asterisk"></i>
							</div>
						</div>
					</div>
					<input type="submit" value="Login" class="ui green submit button" />
					<div class="ui right floated red button"
						onclick='window.location.href="<c:url value="/user/register" />"'>Register</div>
				</f:form>
			</div>
			<div class="four wide column"></div>
		</div>
	</c:if>

	<c:if test="${not empty applicationUser }">
		<%@include file="topMenu.jsp"%>

		<div class="ui grid" style="padding-top: 40px;">
			<div class="three wide column"></div>
			<div class="three wide column">
				<%@include file="leftMenu.jsp"%>
			</div>
			<div class="seven wide column">
				<c:if test="${not empty activeTheme }">
					<div style="margin-bottom: 55px;">
						<h2 class="ui left floated header">
							<c:out value="${activeTheme.name }"></c:out>
						</h2>
						<c:choose>
							<c:when test="${activeTheme.user eq applicationUser }">
								<div class="ui right labeled icon floated small button"
									onclick='window.location.href="<c:url value="/theme/delete/${activeTheme.id }" />"'>
									<i class="remove icon"></i>Delete theme
								</div>
							</c:when>
							<c:otherwise>
								<div class="ui right labeled icon floated small button"
									onclick='window.location.href="<c:url value="/theme/unsubscribe/${activeTheme.id }" />"'>
									<i class="remove icon"></i>Unread
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
				<c:forEach items="${notes }" var="note">
					<c:choose>
						<c:when test="${note.priority.name eq 'normal' }">
							<div class="ui top attached green primary segment">
								<h3 style="float: left; margin-bottom: 0px;">
									<c:out value="${note.name }"></c:out>
								</h3>
								<div class="ui label" style="float: right;">
									<c:out value="${note.theme.name }"></c:out>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="ui top attached red inverted primary segment">
								<h3 style="float: left; margin-bottom: 0px;">
									<c:out value="${note.name }"></c:out>
								</h3>
								<div class="ui label" style="float: right;">
									<c:out value="${note.theme.name }"></c:out>
								</div>
							</div>
						</c:otherwise>
					</c:choose>

					<div class="ui attached secondary segment">
						<c:out value="${note.description }"></c:out>
					</div>
					<div class="ui bottom attached right aligned segment">
						<c:out value="${note.published }"></c:out>
						by
						<c:out value="${note.user.firstName }"></c:out>
						<c:out value="${note.user.lastName }"></c:out>
					</div>
				</c:forEach>
			</div>
			<div class="three wide column"></div>
		</div>
	</c:if>

	<script type="text/javascript">
		$('.ui.dropdown').dropdown();
	</script>
</body>
</html>