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

import Entity.Employer;
import Entity.Job;
import connection.ConnectionManagement;


@WebServlet("/ListAllJobs")
public class ListAllJobs extends HttpServlet {
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Job> list = new ArrayList<>();
		
		String query = "select * from job";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Job job = new Job();
				job.setId(rs.getInt("id"));;
				job.setTitle(rs.getString("title"));
				job.setDescription(rs.getString("description"));
				job.setSalary(rs.getDouble("salary"));
				
				list.add(job);
				
			}
			req.setAttribute("jobList", list);
			req.getRequestDispatcher("listAllJobs.jsp").forward(req, res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
