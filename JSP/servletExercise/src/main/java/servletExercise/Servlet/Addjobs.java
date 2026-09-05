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


@WebServlet ("/add-job")
public class Addjobs extends HttpServlet{ 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			 throws ServletException, IOException {
		
		String name =req.getParameter("name");
		 String title = req.getParameter("title");
		String skills=req.getParameter("description");
		double salary=Double.parseDouble(req.getParameter("salary"));

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal_db?user=root&password=Naveen@2005");

			PreparedStatement pstmt = conn.prepareStatement("insert into job(title,description,salary) values(?,?,?)");
			
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			
			pstmt.setDouble(3, salary);

			pstmt.executeUpdate();

			res.sendRedirect("index.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}
}
