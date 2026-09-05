<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Entity.Job" %>
<%@page import="java.sql.*"%>
<%@ page import="servlet.FindJobById" %>

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
	 ResultSet rs = (ResultSet) request.getAttribute("jobs");
	  rs.next();
	%>
	
	
	
		<h1>Enter Updated Job Details</h1>  
	<form action="update-job" method="post">
		<input type="number" placeholder="id" value="<%= rs.getInt(1) %>" name="id" readonly><br>
		<input type="text" placeholder="title" value="<%= rs.getString(2)  %>" name="title" required><br>
		<input type="text" placeholder="description" value="<%= rs.getString(3)%>" name="description" required><br>
		<input type="number" placeholder="salary" value="<%= rs.getDouble(4)%>" name="salary" required><br>
		
		<input type="submit" value="Update Employer">
	</form>
	
</body>
</html>
