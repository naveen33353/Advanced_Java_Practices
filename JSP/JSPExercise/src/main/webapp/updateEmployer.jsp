<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Entity.Employer" %>
<%@page import="java.sql.*"%>
<%@ page import="servlet.FindEmployerById" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Employer </title>
<style>
  body {
    font-family: Arial, sans-serif;
    text-align: center;
    margin-top: 80px;
  }
  form {
    display: inline-block;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
  }
  input {
    display: block;
    margin: 10px auto;
    padding: 10px;
    width: 250px;
  }
  input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
  }
  input[type="submit"]:hover {
    background-color: #45a049;
  }
</style>
</head>
<body>
	
	<%
	 ResultSet rs = (ResultSet) request.getAttribute("employers");
	  rs.next();
	%>
	
	
	
		<h1>Enter Updated Employer Details</h1>  
	<form action="update-employer" method="post">
		<input type="number" placeholder="id" value="<%= rs.getInt(1) %>" name="id" readonly><br>
		<input type="text" placeholder="name" value="<%= rs.getString(2)  %>>" name="name" required><br>
		<input type="text" placeholder="email" value="<%= rs.getString(3)%>" name="email" required><br>
		
		<input type="submit" value="Update Employer">
	</form>
	
</body>
</html>
