<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page import="java.time.format.DateTimeFormatter" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Transaction Form</title>
</head>
<body>
	<div align="center">
		<h1>Bank Transaction Form</h1>
		<f:form action="saveBankTransaction" modelAttribute="bankTransaction">
			<table border="1">
			
				<tr>
					<td>Transaction Id: </td> <td><f:input path="bankTransactionId" value="${bt.bankTransactionId}"/></td>
				</tr>				
			
				<tr>
					<td>To Account: </td> <td><f:input path="bankTransactionToAccount" value="${bt.bankTransactionToAccount}"/></td>
				</tr>		
				
				<tr>
					<td>From Account: </td> <td><f:input path="bankTransactionFromAccount" value="${bt.bankTransactionFromAccount}"/></td>
				</tr>	
				
				<tr>
					<td>Transaction Amount: </td> <td><f:input path="transactionAmount" value="${bt.transactionAmount}"/></td>
				</tr>
				
				<tr>
				    <td>Transaction Type: </td>
				    <td>
				        <f:select path="transactionType">
				            <f:option value="NEW_ACCOUNT" label="New Account" />
				            <f:option value="DEPOSIT" label="Deposit" />
				            <f:option value="WITHDRAWAL" label="Withdrawal" />
				            <f:option value="TRANSFER" label="Transfer" />
				        </f:select>
				    </td>
				</tr>	
				
				<tr>
				    <td>Transaction Date/Time: </td>
					<td>
					    <f:input path="bankTransactionDateTime" type="datetime-local" value="${bt.bankTransactionDateTime}" />
					</td>

				</tr>

				<tr>
				    <td>Comments: </td>
				    <td>
				        <f:input path="comments" value="${bt.comments}" />
				    </td>
				</tr>

				<tr>
					<td colspan="2" align="center"> <input type="submit" value="submit"></td>
				</tr>		
				
			</table>
				
		</f:form>
		
	<p/>
	<p/>
	
	<c:if test="${not empty bankTransactions}">
	<table border="1">
	 <tr>
		<th>Bank Transaction Id</th><th>To Account Id</th><th>From Account Id</th><th>Transaction Amount</th><th>Transaction Type</th><th>Transaction Date/Time</th><th>Comments</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${bankTransactions}" var="bt">
		<tr>
			<td>${bt.getBankTransactionId()}</td>
			<td>${bt.getBankTransactionToAccount()}</td>
			<td>${bt.getBankTransactionFromAccount()}</td>
			<td>${bt.getTransactionAmount()}</td>
			<td>${bt.getTransactionType()}</td>
			<td>${bt.getBankTransactionDateTime()}</td>
			<td>${bt.getComments()}</td>
			<td>
				<a href="updateBankTransaction?bankTransactionId=${bt.getBankTransactionId()}">Update</a>
				|
				<a href="deleteBankTransaction?bankTransactionId=${bt.getBankTransactionId()}">Delete</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>		
			
	</div>
</body>
</html>