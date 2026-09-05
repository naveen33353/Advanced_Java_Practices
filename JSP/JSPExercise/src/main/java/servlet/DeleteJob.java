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


@WebServlet("/delete-job-by-id")
public class DeleteJob extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String query = "delete from job where id = ?";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, id);
			
			pst.executeUpdate();
			
			res.sendRedirect("ListAllJobs");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
