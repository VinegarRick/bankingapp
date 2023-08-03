<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role Form</title>
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
					<td>Role name: </td> <td><f:input path="roleName" value="${r.roleName}"/> </td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"> <input type="submit" value="submit"></td>
				</tr>
				
			</table>
		</f:form>
	</div>
</body>
</html>