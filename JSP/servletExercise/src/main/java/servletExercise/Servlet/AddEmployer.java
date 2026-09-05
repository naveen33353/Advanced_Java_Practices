package servletExercise.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servletExercise.Connection.ConnectionManagement;

@WebServlet("/add-employer")
public class AddEmployer extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) 
              throws ServletException, IOException{
	
	String companyName = req.getParameter("companyName");
	String email = req.getParameter("email");
	
	
	String query = "insert into employer(companyName,email) values(?,?)";
	
	try (Connection conn = ConnectionManagement.getConnection();
			PreparedStatement pst = conn.prepareStatement(query)){
		
		
		pst.setString(1, companyName);
		pst.setString(2, email);
		
		int i = pst.executeUpdate();
		
		if(i > 0) {
			System.out.println("success");
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
		
		
	}catch(Exception e) {
		e.printStackTrace();
		
	
		
	}
}
}
