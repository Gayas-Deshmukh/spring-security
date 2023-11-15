<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<h1>Hi ${username}, Welcome to Spring Security</h1>
<h4>Roles assigned are ${roles}</h4>
<br>
	<a href="/SpringSecurityBasics/admin-dash">Go to Admin Dashboard</a> &nbsp;<a href="/SpringSecurityBasics/user-dash">Go to User Dashboard</a>
<br>
<br>
	<form:form action="logout" method="POST">
		<input type="submit" value="LogOut">
	</form:form>
</body>
</html>