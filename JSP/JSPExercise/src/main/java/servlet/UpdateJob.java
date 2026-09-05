package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionManagement;


@WebServlet("/update-job")
public class UpdateJob extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		double salary = Double.parseDouble(req.getParameter("salary"));
				
		
		
		String query = "update job set title=?,description=?,salary=? where id=?";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, title);
			pst.setString(2, description);
			pst.setDouble(3, salary);
			pst.setInt(4, id);
			
			
			pst.executeUpdate();
			
			res.sendRedirect("ListAllJobs");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
