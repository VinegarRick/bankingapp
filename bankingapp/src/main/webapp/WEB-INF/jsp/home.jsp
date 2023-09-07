<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<div align="center">
<h1>Home</h1>
<%@ include file ="menu.jsp" %>

<br>Logged-in User: ${pageContext.request.userPrincipal.name} 
<%-- <br>User Principal: ${pageContext.request.userPrincipal} --%>


<br><strong>Authorities: <sec:authentication property="principal.authorities"/> </strong>

<br><a href="logout" >logout</a>

<!--
<sec:authorize access="!isAuthenticated()">
<a href="login">login</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<sec:authorize access="hasAuthority(\"HR\") && hasAuthority(\"User\")">
<br><a href="userForm" >User Form</a>
</sec:authorize>

<sec:authorize access="hasAuthority(\"User\")">
<br><a href="userForm" >User Form</a>
</sec:authorize>

<sec:authorize access="hasAuthority(\"ADMIN\") && hasAuthority(\"User\") && hasAuthority(\"DBA\")">
<br><a href="${pageContext.request.contextPath}/userForm" >User Form</a>
<br><a href="${pageContext.request.contextPath}/roleForm" >Role Form</a>
</sec:authorize>

</sec:authorize>
-->
</div>
</body>
</html>