package com.exercise;

import java.sql.*;

public class DatabaseManager {

	static String   dbUrl = "jdbc:mysql://localhost:3306/company_db";
	static String  user = "root";
	static String  pass  = "Naveen@2005";

public static  Connection getConnection()  {
	Connection con = null;
	 
	try {
		return DriverManager.getConnection(dbUrl,user,pass);
//		System.out.println("Database connected Successfully!!!");
	}catch(Exception e) {
//		System.out.println("Database connection Failed!!!");
		e.printStackTrace();
		return null;
	}
	
	
}


public static void close(Connection con) {
    try {
        if (con != null)
            con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
