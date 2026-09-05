package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionManagement;


@WebServlet("/find-job-by-id")
public class FindJobById extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String query = "select * from job where id=?";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			 
			req.setAttribute("jobs", rs);
			req.getRequestDispatcher("updateJob.jsp").forward(req, res);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
