<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
<style>
	.error {
		color: red;
		font-style: italic;
		font-weight: bold;
	}
</style>
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
    <c:if test="${errors.hasFieldErrors('username') || errors.hasFieldErrors('user.username.length')}">
    	<td> 
			<f:errors path="username" cssClass="error"/>
		<td>
	</c:if>

</tr>

<tr>
<td>Password: </td><td> <f:input path="password"  value="${u.password}"/></td>
    <c:if test="${errors.hasFieldErrors('password') || errors.hasFieldErrors('user.password.length')}">
		<td> 
			<f:errors path="password" cssClass="error"/>
		<td>
	</c:if>
</tr>
<tr>
<td>Roles: </td>
<td> 
<c:forEach items="${roles}" var="r">
	<c:choose>
		<c:when test="${selectedRoles.contains(r) }">
			<f:checkbox path="roles" label ="${r.roleName}" value="${r.roleId}" checked="true"/>
		</c:when>
	<c:otherwise>
		<f:checkbox path="roles" label ="${r.roleName}" value="${r.roleId}" />
	</c:otherwise>
	</c:choose>
</c:forEach>

<tr>
<td colspan="2" align="center"> <input type="submit" value="submit"></td>
</tr>

</table>
</f:form>

<p/>
<p/>

<c:if test="${not empty users}">
<table border="1">
 <tr>
	<th>User Id</th><th>Name</th>
	<th>Roles</th>
	<th>Action</th>
</tr>
<c:forEach items="${users}" var="u">
	<tr>
		<td>${u.getUserId()}</td>
		<td>${u.getUsername()}</td>
		<td>
			<c:forEach items="${u.getRoles() }" var="role" >
					${role.getRoleName() }
			</c:forEach>
		</td>		
		<td>
			<a href="updateUser?userId=${u.getUserId()}">Update</a>
			|
			<a href="deleteUser?userId=${u.getUserId()}">Delete</a>
		</td>
	</tr>
</c:forEach>
</table>
</c:if>
</div>
</body>
</html>