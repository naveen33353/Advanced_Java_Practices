package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagement {

    private static final String url = "jdbc:mysql://localhost:3306/jobportal_db";
    private static final String user = "root";
    private static final String pass = "Naveen@2005";

    public static Connection getConnection() {

        Connection conn = null;

        try {

           
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            conn = DriverManager.getConnection(url, user, pass);

            System.out.println("Database connected successfully");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }

        return conn;
    }
}
