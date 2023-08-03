<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<body>
<div align="center">
<h1>User Form</h1>
<f:form action="saveUser" modelAttribute="user">
<table border="1">

<tr>
<td>User Id: </td><td> <f:input path="userId" value="${u.userId}"/></td>
</tr>

<tr>
<td>Name: </td><td> <f:input path="username" value="${u.username}"/></td>
</tr>

<tr>
<td>Email: </td><td> <f:input path="email"  value="${u.email}"/></td>
</tr>

<tr>
<td>Mobile: </td><td> <f:input path="mobile" value="${u.mobile}"/></td>
</tr>

<tr>
<td colspan="2" align="center"> <input type="submit" value="submit"></td>
</tr>

</table>
</f:form>

<p/>
<p/>
<table border="1">
 <tr>
	<th>User Id</th><th>Name</th><th>Email</th><th>Mobile</th><th>Action</th>
</tr>
<c:forEach items="${users}" var="u">
	<tr>
		<td>${u.getUserId()}</td>
		<td>${u.getUsername()}</td>
		<td>${u.getEmail()}</td>
		<td>${u.getMobile()}</td>
		<td>
			<a href="updateUser?userId=${u.getUserId()}">Update</a>
			|
			<a href="deleteUser?userId=${u.getUserId()}">Delete</a>
		</td>
	</tr>
</c:forEach>
</table>
</div>
</body>
</html>