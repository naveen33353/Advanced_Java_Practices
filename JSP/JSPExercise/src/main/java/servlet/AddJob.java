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

@WebServlet("/add-job")
public class AddJob extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		double salary = Double.parseDouble(req.getParameter("salary"));
		
		String query = "insert into job (title,description,salary) values (?,?,?)";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			
			pst.setString(1, title);
			pst.setString(2, description);
			pst.setDouble(3, salary);
			
			pst.executeUpdate();
			
			res.sendRedirect("index.jsp");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
