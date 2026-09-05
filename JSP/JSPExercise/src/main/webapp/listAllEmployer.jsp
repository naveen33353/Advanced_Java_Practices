<%@page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="Entity.Employer"%>
<%@ page import="servlet.ListAllEmployers" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Employers List</title>
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

 

<h1>All Employers Details</h1>
<%
List<Employer> list = (List<Employer>) request.getAttribute("employerList");
%>

<table>
<tr>
<th>ID</th>
<th>Company Name</th>
<th>Email</th>
</tr>

<%
if(list != null){
    for(Employer emp : list){
%>

<tr> 
<td><%= emp.getId()%></td>
<td><%= emp.getName() %></td>
<td><%= emp.getEmail() %></td>

<td><a href="find-employer-by-id?id=<%= emp.getId()%>">Update</a></td>
<td><a href="delete-employer-by-id?id=<%= emp.getId() %>">Delete</a></td>

</tr>

<%
    }
}
%>

</table>

<h2><a href="index.jsp">Go Back to Dashboard</a></h2>

</body>
</html>
