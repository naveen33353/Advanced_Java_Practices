<%@page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="servletExercise.Entity.Job"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Job Seekers List</title>
<style>
  body {
    font-family: Arial, sans-serif;
    text-align: center;
    padding: 20px;
  }
  table {
    margin: auto;
    border-collapse: collapse;
    width: 80%;
  }
  th, td {
    border: 1px solid #ccc;
    padding: 10px;
  }
  th {
    background-color: #f2f2f2;
  }
  a {
    color: #007bff;
    text-decoration: none;
  }
  a:hover {
    text-decoration: underline;
  }
  h2 a {
    display: inline-block;
    margin-top: 20px;
  }
</style>
</head>
<body>

 

<h1>All Job  Details</h1>
<%
List<Job> list = (List<Job>) request.getAttribute("joblist");
%>

<table>
<tr>
<th>ID</th>
<th>Title</th>
<th>Description</th>
<th>Salary</th>

</tr>

<%
if(list != null){
    for(Job job : list){
%>

<tr> 
<td><%= job.getJobId()%></td>
<td><%= job.getTitle() %></td>
<td><%= job.getDescription() %></td>
<td><%= job.getSalary() %></td>


</tr>

<%
    }
}
%>

</table>

<h2><a href="index.jsp">Go Back to Dashboard</a></h2>

</body>
</html>
