<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a Employer</title>
</head>
<body>
 <h1>Enter Employer Details</h1>  
	
	<form action="add-employer" method="post">
		
		<input type="text" placeholder="name" name="companyName" required><br>
		<input type="text" placeholder="email" name="email" required><br>
		
		<input type="submit" value="Add Employer">
	</form>
</body>
</html>