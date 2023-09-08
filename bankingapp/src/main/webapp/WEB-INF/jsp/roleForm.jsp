<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file ="header.jsp" %>
    
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
		<%@ include file ="menu.jsp" %><br><br>
		<sec:authorize access="hasAuthority(\"Admin\")">
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
		</sec:authorize>
		
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
						<sec:authorize access="hasAuthority(\"Admin\")">
							<a href="updateRole?roleId=${r.getRoleId()}">Update</a>
							|
							<a href="deleteRole?roleId=${r.getRoleId()}">Delete</a>
						</sec:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>