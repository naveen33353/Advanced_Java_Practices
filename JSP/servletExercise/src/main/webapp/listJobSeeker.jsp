<%@page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="servletExercise.Entity.JobSeeker"%>
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

 <%
  ResultSet rs = (ResultSet) request.getAttribute("jobseeker");
%>

<h1>All Job Seeker Details</h1>
<%
List<JobSeeker> list = (List<JobSeeker>) request.getAttribute("jobseekerList");
%>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Skill</th>
<th>Resume</th>
</tr>

<%
if(list != null){
    for(JobSeeker js : list){
%>

<tr> 
<td><%= js.getJobSeekerId()%></td>
<td><%= js.getName() %></td>
<td><%= js.getEmail() %></td>
<td><%= js.getSkills() %></td>
<td><%= js.getResume() %></td>

</tr>

<%
    }
}
%>

</table>

<h2><a href="index.jsp">Go Back to Dashboard</a></h2>

</body>
</html>
