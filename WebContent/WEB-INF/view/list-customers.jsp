<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRM</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<br/>
<div id= "wrapper">
	<div id="header">
	
	<h1> Customer Relation Model</h1>
	</div>

</div>
<div id="container">
	<div id="content">
		<!--  Adding button for adding customer -->
			
			<input type="button" value="+Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class ="add-button"
				/> 
	
	<!--  add table here -->
	<table>
		<tr>
			<th> First Name</th>
			<th> Last Name</th>
			<th> Email</th>
			<th> Action</th>
		</tr>
		<!--  Loop over customers and print out here -->
		<c:forEach var="tempCustomer" items="${customers}">
		
			<!-- construct an "update" link with customer id -->
			<c:url var="updateLink" value="/customer/showFormForUpdate">
				<c:param name="customerId" value ="${tempCustomer.id}"></c:param>
			</c:url>
			
			<!-- construct an "delete" link with customer id -->
			<c:url var="deleteLink" value="/customer/delete">
				<c:param name="customerId" value ="${tempCustomer.id}"></c:param>
			</c:url>
			
			<tr>
				<td>${tempCustomer.firstName}</td>
				<td>${tempCustomer.lastName}</td>
				<td>${tempCustomer.email}</td>
				<!-- an "update" link with customer id -->
				<!-- defined in line42 in same page-->
				<td><a href="${updateLink}">Update</a>
				| <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete?'))) return false">Delete</a><td>
				
			
			</tr>
		
		</c:forEach>
	
	</table>
	
	</div>
</div>
</body>
</html>