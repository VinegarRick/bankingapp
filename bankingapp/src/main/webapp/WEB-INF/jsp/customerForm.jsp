<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Form</title>
<style>
	.error {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
<div align="center">
<h1>Customer Form</h1>
<f:form action="saveCustomer" modelAttribute="customer">
<table border="1">

<tr>
	<td>Customer Id: </td><td> <f:input path="customerId" value="${c.customerId}"/></td>
</tr>

<tr>
	<td>Name: </td>
	<td> 
		<f:input path="customerName" value="${c.customerName}"/>
		<f:errors path="customerName" cssClass="error" />
	</td>
</tr>

<tr>
    <td>Gender: </td>
    <td>
      <f:checkbox path="customerGender" value="Male"/> Male
      <f:checkbox path="customerGender" value="Female"/> Female
      <f:checkbox path="customerGender" value="Other"/> Other
      <f:errors path="customerGender" cssClass="error" />
    </td>
</tr>

<tr>
    <td>Date of Birth: </td>
    <td>
    	<input type="date" name="customerDob" value="${c.customerDob}" />
    	<f:errors path="customerDob" cssClass="error" />
    </td>
</tr>

<tr>
	<td>Mobile Number: </td>
	<td> 
		<f:input path="customerMobileNum" value="${c.customerMobileNum}"/>
		<f:errors path="customerMobileNum" cssClass="error" />
	</td>
</tr>

<tr>
	<td>Branch Address 1: </td> 
	<td>
		<f:input path="customerAddress.addressLine1" value="${c.customerAddress.addressLine1}"/> 
		<f:errors path="customerAddress.addressLine1" cssClass="error" />
	</td>
</tr>

<tr>
	<td>Branch Address 2: </td> 
	<td>
		<f:input path="customerAddress.addressLine2" value="${c.customerAddress.addressLine2}"/> 	
	</td>
</tr>				

<tr>
	<td>City: </td> 
	<td>
		<f:input path="customerAddress.city" value="${c.customerAddress.city}"/> 
		<f:errors path="customerAddress.city" cssClass="error" />
	</td>
</tr>

<tr>
	<td>State: </td> 
	<td>
		<f:input path="customerAddress.state" value="${c.customerAddress.state}"/> 
		<f:errors path="customerAddress.state" cssClass="error" />
	</td>
</tr>				

<tr>
	<td>Country: </td> 
	<td>
		<f:input path="customerAddress.country" value="${c.customerAddress.country}"/> 
		<f:errors path="customerAddress.country" cssClass="error" />
	</td>
</tr>				

<tr>
	<td>ZIP Code: </td> 
	<td>
		<f:input path="customerAddress.zipcode" value="${c.customerAddress.zipcode}"/> 
		<f:errors path="customerAddress.zipcode" cssClass="error" />
	</td>
</tr>

<tr>
	<td>Real Id: </td>
	<td> 
		<f:input path="realId" value="${c.realId}"/>
		<f:errors path="realId" cssClass="error" />
	</td>
</tr>

<tr>
	<td>User Id: </td> 
	<td>
		<f:input path="userId" value="${c.userId}"/>
		<f:errors path="userId" cssClass="error" />
	</td>
</tr>	

<!--<f:hidden path="user.userId" />-->

<tr>
<td colspan="2" align="center"> <input type="submit" value="submit"></td>
</tr>

</table>
</f:form>

<p/>
<p/>

<c:if test="${not empty customers}">
<table border="1">
 <tr>
	<th>Customer Id</th><th>Name</th><th>Gender</th><th>Date of Birth</th><th>Mobile Number</th><th>Address</th><th>Real Id</th>
	<th>User Id</th><th>Account IDs</th>
	<th>Action</th>
</tr>
<c:forEach items="${customers}" var="c">
	<tr>
		<td>${c.getCustomerId()}</td>
		<td>${c.getCustomerName()}</td>
		<td>${c.getCustomerGender()}</td>
		<td>${c.getCustomerDob()}</td>
		<td>${c.getCustomerMobileNum()}</td>
		<td>${c.getCustomerAddress().getAddressLine1()}</td>
		<td>${c.getRealId()}</td>
		<td>${c.getUser().getUserId()}</td>
		<td>
			<c:forEach items="${c.getCustomerAccounts() }" var="account" >
					${account.getAccountId() }
			</c:forEach>
		</td>		
		<td>
			<a href="updateCustomer?customerId=${c.getCustomerId()}">Update</a>
			|
			<a href="deleteCustomer?customerId=${c.getCustomerId()}">Delete</a>
		</td>
	</tr>
</c:forEach>
</table>
</c:if>		

</div>
</body>
</html>