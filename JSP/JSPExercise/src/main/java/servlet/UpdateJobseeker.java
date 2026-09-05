package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.JobSeeker;
import connection.ConnectionManagement;


@WebServlet("/update-jobseeker")
public class UpdateJobseeker extends HttpServlet {
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	int id = Integer.parseInt(req.getParameter("id"));  
	String name = req.getParameter("name");
	String email = req.getParameter("email");
	String skills = req.getParameter("skill");
	String resume = req.getParameter("resume_path");
	
	String query = "update jobseeker set name=?,email=?,skills=?,resume=? where id=?";
	
	List<JobSeeker> list = new ArrayList<>();
	
	try(Connection con = ConnectionManagement.getConnection();
			PreparedStatement pst  = con.prepareStatement(query)){
		
		pst.setString(1, name);
		pst.setString(2, email);
		pst.setString(3, skills);
		pst.setString(4, resume);
		pst.setInt(5, id);
		
		 pst.executeUpdate();
           
			res.sendRedirect("ListAllJobSeeker");
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
  

}
