package servlet;

import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.Employer;
import connection.ConnectionManagement;


@WebServlet("/ListAllEmployers")
public class ListAllEmployers extends HttpServlet {
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Employer> list = new ArrayList<>();
		
		String query = "select * from employer";
		
		try(Connection con = ConnectionManagement.getConnection();
				PreparedStatement pst = con.prepareStatement(query)){
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Employer emp = new Employer();
				emp.setId(rs.getInt("id"));;
				emp.setName(rs.getString("companyName"));
				emp.setEmail(rs.getString("email"));
				
				list.add(emp);
				
			}
			req.setAttribute("employerList", list);
			req.getRequestDispatcher("listAllEmployer.jsp").forward(req, res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
