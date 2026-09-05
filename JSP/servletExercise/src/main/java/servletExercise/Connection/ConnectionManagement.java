package servletExercise.Connection;

import java.sql.Connection;
import java.sql.*;

public class ConnectionManagement {

	
		
			private static final String url = "jdbc:mysql://localhost:3306/secure_office";
			private static final String user = "root";
			private static final String pass = "Naveen@2005";
			
			public static Connection getConnection() {
				
				try {
					return DriverManager.getConnection(url,user,pass);
					
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
				
			}
		}


	
	

