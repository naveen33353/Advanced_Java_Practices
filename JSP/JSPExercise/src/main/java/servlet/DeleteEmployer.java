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


@WebServlet("/delete-employer-by-id")
public class DeleteEmployer extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String query = "delete from employer where id = ?";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			pst.setInt(1, id);
			
			pst.executeUpdate();
			
			res.sendRedirect("ListAllEmployers");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
