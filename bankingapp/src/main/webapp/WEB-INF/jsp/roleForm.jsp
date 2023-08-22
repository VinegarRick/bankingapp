<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role Form</title>
<style>
	.error {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div align="center">
		<h1>Role Form</h1>
		<f:form action="saveRole" modelAttribute="role">
			<table border="1">
				<tr>
					<td>Role id: </td> <td><f:input path="roleId" value="${r.roleId}"/></td>
				</tr>
				
				<tr>
					<td>Role name: </td> 
						<td><f:input path="roleName" value="${r.roleName}"/> 
						<f:errors path="roleName" cssClass="error" />
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"> <input type="submit" value="submit"></td>
				</tr>
				
			</table>
		</f:form>
		
		<p/>
		<p/>
		
		<c:if test="${not empty roles}">
		<table border="1">
			<tr>
				<th>Role Id</th><th>Role Name</th>
				<th>Users</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${roles}" var="r">
				<tr>
					<td>${r.getRoleId()}</td>
					<td>${r.getRoleName()}</td>
					<td>
						<c:forEach items="${r.getUsers() }" var="user" >
								${user.getUsername() }
						</c:forEach>
					</td>		
					<td>
						<a href="updateRole?roleId=${r.getRoleId()}">Update</a>
						|
						<a href="deleteRole?roleId=${r.getRoleId()}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>