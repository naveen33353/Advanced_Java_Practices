package servletExercise.Servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import servletExercise.Entity.JobSeeker;

@WebServlet("/list-all-jobseeker")
public class ListAllJobSeeker extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, javax.servlet.ServletException {

        Connection conn = null;
        List<JobSeeker> list = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jobportal_db",
                    "root",
                    "Naveen@2005");

            PreparedStatement pst = conn.prepareStatement("select * from jobseeker");

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                JobSeeker js = new JobSeeker();

                js.setJobSeekerId(rs.getInt("id"));
                js.setName(rs.getString("name"));
                js.setEmail(rs.getString("email"));
                js.setSkills(rs.getString("skill"));
                js.setResume(rs.getString("resume_path"));

                list.add(js);
            }

            req.setAttribute("jobseekerList", list);

            req.getRequestDispatcher("listJobSeeker.jsp").forward(req, resp);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
