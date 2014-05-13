<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<f:form method="POST" servletRelativeAction="/user/register.do" modelAttribute="user">
		<f:errors path="*" />
		Email: <f:input path="email" />
		First name: <f:input path="firstName" />
		Last name: <f:input path="lastName" />
		Password: <f:password path="password" />
		Confirm password: <f:password path="confirmPassword" />
		<input type="submit" value="Register" />
	</f:form>
</body>
</html>