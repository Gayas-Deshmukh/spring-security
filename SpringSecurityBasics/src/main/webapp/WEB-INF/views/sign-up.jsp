<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
<h1>Sign Up Here !</h1>

	<form:form action="process-sign" method="POST" modelAttribute="signup">
		UserName : <form:input path="username"/>
		<br><br>
		Password : <form:password path="password"/>
		<br><br>
		<input type="submit" value="SignUp">
	</form:form>
</body>
</html>