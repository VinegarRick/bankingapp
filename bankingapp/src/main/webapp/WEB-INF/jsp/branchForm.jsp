<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file ="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Form</title>
<style>
	.error {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div align="center">
		<h1>Branch Form</h1>
		<%@ include file ="menu.jsp" %><br><br>
		
		<sec:authorize access="hasAuthority(\"Admin\")">
		
		<f:form action="saveBranch" modelAttribute="branch">
			<table border="1">
				<tr>
					<td>Branch Id: </td> <td><f:input path="branchId" value="${b.branchId}"/></td>
				</tr>
				
				<tr>
					<td>Branch Name: </td> 
					<td>
						<f:input path="branchName" value="${b.branchName}"/> 
						<f:errors path="branchName" cssClass="error" />
					</td>
				</tr>
				
				<tr>
					<td>Branch Address 1: </td> 
					<td>
						<f:input path="branchAddress.addressLine1" value="${b.branchAddress.addressLine1}"/> 
						<f:errors path="branchAddress.addressLine1" cssClass="error" />
					</td>
				</tr>
				
				<tr>
					<td>Branch Address 2: </td> 
					<td>
						<f:input path="branchAddress.addressLine2" value="${b.branchAddress.addressLine2}"/> 	
					</td>
				</tr>				
				
				<tr>
					<td>City: </td> 
					<td>
						<f:input path="branchAddress.city" value="${b.branchAddress.city}"/> 
						<f:errors path="branchAddress.city" cssClass="error" />
					</td>
				</tr>
				
				<tr>
					<td>State: </td> 
					<td>
						<f:input path="branchAddress.state" value="${b.branchAddress.state}"/> 
						<f:errors path="branchAddress.state" cssClass="error" />
					</td>
				</tr>				
				
				<tr>
					<td>Country: </td> 
					<td>
						<f:input path="branchAddress.country" value="${b.branchAddress.country}"/> 
						<f:errors path="branchAddress.country" cssClass="error" />
					</td>
				</tr>				
				
				<tr>
					<td>ZIP Code: </td> 
					<td>
						<f:input path="branchAddress.zipcode" value="${b.branchAddress.zipcode}"/> 
						<f:errors path="branchAddress.zipcode" cssClass="error" />
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
		
		<c:if test="${not empty branches}">
		<table border="1">
			<tr>
				<th>Branch Id</th><th>Branch Name</th><th>Branch Address 1</th><th>Branch Address 2</th><th>City</th><th>State</th><th>Country</th><th>ZIP Code</th>
				<th>Account IDs</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${branches}" var="b">
				<tr>
					<td>${b.getBranchId()}</td>
					<td>${b.getBranchName()}</td>
					<td>${b.getBranchAddress().getAddressLine1()}</td>
					<td>${b.getBranchAddress().getAddressLine2()}</td>
					<td>${b.getBranchAddress().getCity()}</td>
					<td>${b.getBranchAddress().getState()}</td>
					<td>${b.getBranchAddress().getCountry()}</td>
					<td>${b.getBranchAddress().getZipcode()}</td>
					<td>
						<c:forEach items="${b.getBranchAccount() }" var="account" >
								${account.getAccountId() }
						</c:forEach>
					</td>		
					<td>
						<sec:authorize access="hasAuthority(\"Admin\")">
							<a href="updateBranch?branchId=${b.getBranchId()}">Update</a>
							|
							<a href="deleteBranch?branchId=${b.getBranchId()}">Delete</a>
						</sec:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>