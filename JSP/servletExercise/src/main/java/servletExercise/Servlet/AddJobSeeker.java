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


@WebServlet("/add-jobseeker")
public class AddJobSeeker extends HttpServlet{
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) 
				 throws ServletException, IOException {
			
			String name =req.getParameter("name");
			 String email = req.getParameter("email");
			String skills=req.getParameter("skill");
			String resume=req.getParameter("resume_path");

			Connection conn = null;

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal_db?user=root&password=Naveen@2005");

				PreparedStatement pstmt = conn.prepareStatement("insert into jobseeker(name,email,skill,resume_path) values(?,?,?,?)");
				
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setString(3, skills);
				pstmt.setString(4, resume);

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

