<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<link
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700|Open+Sans:300italic,400,300,700'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/semantic/css/semantic.css" />'>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.js"></script>
<script src='<c:url value="/semantic/javascript/semantic.js" />'></script>

</head>
<body>
	<div class="ui grid">
		<div class="four wide column"></div>
		<div class="eight wide column">
			<h2 class="ui top attached header">Sign up</h2>
			<f:form method="POST" servletRelativeAction="/user/register.do"
				modelAttribute="user" class="ui error form segment attached">
				<f:errors path="*" element="div" cssClass="ui error message" />
				<div class="field">
					<label>E-mail</label>
					<div class="ui left labeled icon input">
						<f:input path="email" />
						<i class="mail icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>
				<div class="field">
					<label>First name</label>
					<div class="ui left labeled icon input">
						<f:input path="firstName" />
						<i class="user icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>
				<div class="field">
					<label>Last name</label>
					<div class="ui left labeled icon input">
						<f:input path="lastName" />
						<i class="user icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>
				<div class="field">
					<label>Password</label>
					<div class="ui left labeled icon input">
						<f:password path="password" />
						<i class="lock icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>
				<div class="field">
					<label>Confirm password</label>
					<div class="ui left labeled icon input">
						<f:password path="confirmPassword" />
						<i class="lock icon"></i>
						<div class="ui corner label">
							<i class="icon asterisk"></i>
						</div>
					</div>
				</div>
				<input type="submit" value="Register" class="ui green submit button" />
				<div class="ui right floated labeled icon button"
					onclick='window.location.href="<c:url value="/" />"'>
					<i class="left arrow icon"></i>Back
				</div>
			</f:form>
		</div>
		<div class="four wide column"></div>
	</div>
</body>
</html>