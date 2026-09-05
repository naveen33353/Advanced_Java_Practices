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


@WebServlet("/ListAllJobSeeker")
public class ListAllJobSeeker extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	                 throws ServletException , IOException{
		
		String query = "select * from jobseeker";
		
		List<JobSeeker> list = new ArrayList<>();
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			
			JobSeeker jobseeker = new JobSeeker();
			
			jobseeker.setId(rs.getInt("id"));
			jobseeker.setName(rs.getString("name"));
			jobseeker.setEmail(rs.getString("email"));
			jobseeker.setSkill(rs.getString("skills"));
			jobseeker.setResume_path(rs.getString("resume"));
			
			list.add(jobseeker);
			
		}
		req.setAttribute("jobseekerList", list);
		req.getRequestDispatcher("listAllJobseeker.jsp").forward(req, res);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
