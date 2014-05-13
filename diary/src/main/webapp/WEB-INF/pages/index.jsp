<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
</head>
<body>
	<c:out value="${applicationUser.firstName }" />

	<c:if test="${empty applicationUser }">
		<f:form method="POST" servletRelativeAction="/user/login"
			modelAttribute="credentials">
			<f:errors path="*" />
		Email: <f:input path="email" name="email" />
		Password: <f:password path="password" name="password" />
			<input type="submit" value="Login" />
		</f:form>
	</c:if>

	<c:if test="${empty applicationUser }">
		<a href="<c:url value="/user/register" />">Register</a>
	</c:if>

	<c:if test="${not empty applicationUser }">
		<f:form method="POST" servletRelativeAction="/note/add"
			modelAttribute="note">
			<f:hidden path="user" value="${applicationUser.id }" />
			Name: <f:input path="name" />
			Description: <f:input path="description" />
			Priority: <f:select path="priority">
						<f:options items="${priorities }" itemValue="id" itemLabel="name" />
					</f:select>
			<input type="submit" value="Create" />
		</f:form>
	</c:if>
</body>
</html>