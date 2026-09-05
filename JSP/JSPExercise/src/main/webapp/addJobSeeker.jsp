<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h1>Enter Job Seeker Details</h1>  
	
	<form action="add-jobseeker" method="post">
		
		<input type="text" placeholder="name" name="name" required><br>
		<input type="text" placeholder="email" name="email" required><br>
		<input type="text" placeholder="skill" name="skill" required><br>
		<input type="text" placeholder=" Resume path" name="resume_path" required><br>
		<input type="submit" value="Add Jobseeker">
	</form>
</body>
</html>
