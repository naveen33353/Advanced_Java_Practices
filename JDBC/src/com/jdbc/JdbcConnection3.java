package com.jdbc;

import java.sql.*;


public class JdbcConnection3 {
public static void main(String[] args) {
//	Connection con  = null;
	String dbUrl = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String pass = "Naveen@2005";
	String selectQuery = "select * from t1";
	try(Connection con = DriverManager.getConnection(dbUrl,user,pass);
			PreparedStatement pst = con.prepareStatement(selectQuery))
	{
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println("ID : "+id+" Name : "+name+" Age :"+age);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}

	

}
}
