<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>My Custom login page</h1>
	<c:if test="${param.error != null}">
		<i style="color: red">Invalid username or password</i>
	</c:if>
	<c:if test="${param.logout != null}">
		<i style="color:green">You are successfully logout</i>
	</c:if>
	<br>
	<form:form method="POST">
		Username : <input type="text" name="username"/>
		<br><br>
		Password : <input type="password" name="password"/>
		<br><br>
		<input type="submit" value="Login">
	</form:form>
	
</body>
</html>