package servletExercise.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servletExercise.Entity.Job;
import servletExercise.Entity.JobSeeker;
 
public class ListAllJobs extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, javax.servlet.ServletException {

        Connection conn = null;
        List<Job> list = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jobportal_db",
                    "root",
                    "Naveen@2005");

            PreparedStatement pst = conn.prepareStatement("select * from job");

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                Job job = new Job();

                job.setJobId(rs.getInt("id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(getServletInfo());
                job.setSalary(rs.getDouble("salary"));
             

                list.add(job);
            }

            req.setAttribute("joblist", list);

            req.getRequestDispatcher("listAllJobs.jsp").forward(req, resp);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
