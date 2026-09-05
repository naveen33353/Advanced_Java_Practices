
<head><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jobportal system Dashboard</title>
<style>
  body {
   background: linear-gradient(181deg, rgba(75, 0, 130, 1) 0%, rgba(138, 43, 226, 1) 100%);
    font-family: Arial, sans-serif;
    text-align: center;
    margin-top: 100px;
  }
  h1 {
     font-weight: 500;
  color: white;
  font-size: 52px;
  }
  
  
  button {
   
  height: 55px;
  width: 300px;
  background: white;
  border-radius: 8px;
  border: none;
  padding: 0 1.5rem;
  font-size: 20px;
  color: #8a2be2;
  outline: none;
  }
</style>
</head>
<body>
	<h1>Hi User, Welcome</h1>
	<h2><a href="jobseeker.jsp"><button>Job Seeker Management</button></a></h2>
	
	<h2><a href="job.jsp"><button>Job management</button></a></h2>
	<h2><a href="employer.jsp"><button>Employer management</button></a></h2>
	
</body>
</html>


