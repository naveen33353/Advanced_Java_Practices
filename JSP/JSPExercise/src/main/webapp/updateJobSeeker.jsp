<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Entity.JobSeeker" %>
<%@page import="java.sql.*"%>
<%@ page import="servlet.FindJobseekerById" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Job Seeker</title>
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
	 ResultSet rs = (ResultSet) request.getAttribute("jobseeker");
	  rs.next();
	%>
	
	
	
		<h1>Enter Updated Job Seeker Details</h1>  
	<form action="update-jobseeker" method="post">
		<input type="number" placeholder="id" value="<%= rs.getInt(1) %>" name="id" readonly><br>
		<input type="text" placeholder="name" value="<%=rs.getString(2)  %>>" name="name" required><br>
		<input type="text" placeholder="email" value="<%= rs.getString(3)%>" name="email" required><br>
		<input type="text" placeholder="skill" value="<%= rs.getString(4) %>" name="skill" required><br>
		<input type="text" placeholder=" Resume path" value="<%= rs.getString(5) %>" name="resume_path" required><br>
		<input type="submit" value="Update Jobseeker">
	</form>
	
</body>
</html>
