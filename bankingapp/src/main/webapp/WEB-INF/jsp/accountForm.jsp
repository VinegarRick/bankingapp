<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Form</title>
</head>
<body>
	<div align="center">
		<h1>Account Form</h1>
		<f:form action="saveAccount" modelAttribute="account">
			<table border="1">
				<tr>
					<td>Account Id: </td> <td><f:input path="accountId" value="${a.accountId}"/></td>
				</tr>
				
				<tr>
					<td>Account Holder: </td> <td><f:input path="accountHolder" value="${a.accountHolder}"/></td>
				</tr>				
				
				<tr>
				    <td>Account Type: </td>
				    <td>
				        <f:select path="accountType">
				            <f:option value="SAVINGS" label="Savings" />
				            <f:option value="CHECKING" label="Checking" />
				            <f:option value="LOAN" label="Loan" />
				        </f:select>
				    </td>
				</tr>

				
				<!--<tr>
					<td>Today's Date: </td> <td><f:input path="accountDateOpen" value="${a.accountDateOpen}"/></td>
				</tr>-->
				
				<tr>
				    <td>Today's Date: </td>
				    <td><input type="date" name="accountDateOpen" value="${a.accountDateOpen}" /></td>
				</tr>
				
				<tr>
					<td>Branch Id: </td> <td><f:input path="accountBranch.branchId"/></td>
				</tr>		
				
				<tr>
					<td>Customer Id: </td> <td><f:input path="accountCustomer.customerId"/></td>
				</tr>				
				
				    <f:hidden path="accountBranch.branchId" />
    				<f:hidden path="accountCustomer.customerId" />
				
				
				<tr>
					<td colspan="2" align="center"> <input type="submit" value="submit"></td>
				</tr>
				
				
			</table>
		
		</f:form>
		
		<p/>
		<p/>
		
	<c:if test="${not empty accounts}">
	<table border="1">
	 <tr>
		<th>Account Id</th><th>Account Holder</th><th>Account Type</th><th>Creation Date</th><th>Branch</th><th>Customer Id</th><th>Balance</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${accounts}" var="a">
		<tr>
			<td>${a.getAccountId()}</td>
			<td>${a.getAccountHolder()}</td>
			<td>${a.getAccountType()}</td>
			<td>${a.getAccountDateOpen()}</td>
			<td>${a.getAccountBranch().getBranchName()}</td>
			<td>${a.getAccountCustomer().getCustomerId()}</td>
			<td>${a.getAccountBalance()}</td>
			<td>
				<a href="updateAccount?accountId=${a.getAccountId()}">Update</a>
				|
				<a href="deleteAccount?accountId=${a.getAccountId()}">Delete</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>		
		
	</div>
</body>
</html>