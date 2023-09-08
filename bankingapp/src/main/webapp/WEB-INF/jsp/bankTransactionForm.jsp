<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file ="header.jsp" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Transaction Form</title>
<style>
	.error {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div align="center">
		<h1>Bank Transaction Form</h1>
		<%@ include file ="menu.jsp" %><br><br>
		<f:form action="saveBankTransaction" modelAttribute="bankTransaction">
			<table border="1">
			
				<tr>
					<td>Transaction Id: </td> <td><f:input path="bankTransactionId" value="${bt.bankTransactionId}"/></td>
				</tr>				
			
				<tr>
					<td>To Account: </td> 
					<td>
						<f:input path="bankTransactionToAccount" value="${bt.bankTransactionToAccount}"/>
						<f:errors path="bankTransactionToAccount" cssClass="error" />
					</td>
				</tr>		
				
				<tr>
					<td>From Account: </td> 
					<td>
						<f:input path="bankTransactionFromAccount" value="${bt.bankTransactionFromAccount}"/>
						<f:errors path="bankTransactionFromAccount" cssClass="error" />
					</td>
				</tr>	
				
				<tr>
					<td>Transaction Amount: </td>
					<td>
						<f:input path="transactionAmount" value="${bt.transactionAmount}"/>
						<f:errors path="transactionAmount" cssClass="error" />
					</td>
				</tr>
				
				<tr>
				    <td>Transaction Type: </td>
				    <td>
				        <f:select path="transactionType">
				        	<f:option value="" label="Choose transaction type" />
				            <f:option value="NEW_ACCOUNT" label="New Account" />
				            <f:option value="DEPOSIT" label="Deposit" />
				            <f:option value="WITHDRAWAL" label="Withdrawal" />
				            <f:option value="TRANSFER" label="Transfer" />
				        </f:select>
				        <f:errors path="transactionType" cssClass="error" />
				    </td>
				</tr>	
				
				<tr>
				    <td>Transaction Date/Time: </td>
					<td>
					    <f:input path="bankTransactionDateTime" type="datetime-local" value="${bt.bankTransactionDateTime}" />
					    <f:errors path="bankTransactionDateTime" cssClass="error" />
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
				<sec:authorize access="hasAuthority(\"Admin\")">
					<a href="updateBankTransaction?bankTransactionId=${bt.getBankTransactionId()}">Update</a>
					|
					<a href="deleteBankTransaction?bankTransactionId=${bt.getBankTransactionId()}">Delete</a>
				</sec:authorize>
			</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>		
			
	</div>
</body>
</html>