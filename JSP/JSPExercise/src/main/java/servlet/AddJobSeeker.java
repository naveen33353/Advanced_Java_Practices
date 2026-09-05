package servlet;


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

import connection.ConnectionManagement;





@WebServlet("/add-jobseeker")
public class AddJobSeeker extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	              throws ServletException, IOException{
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String skill = req.getParameter("skill");
		String resume = req.getParameter("resume_path");
		
		
		String query = "insert into jobseeker(name,email,skills,resume) values(?,?,?,?)";
		
		try (Connection conn = ConnectionManagement.getConnection();
				PreparedStatement pst = conn.prepareStatement(query)){
			
			
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, skill);
			pst.setString(4, resume);
			
			
			pst.executeUpdate();
			
			res.sendRedirect("index.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			
		
			
		}
	}
}
