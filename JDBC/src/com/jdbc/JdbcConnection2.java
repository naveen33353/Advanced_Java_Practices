package com.jdbc;

import java.sql.*;


public class JdbcConnection2 {
public static void main(String[] args) {
	Connection con  = null;
	String dbUrl = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String pass = "Naveen@2005";


	try {
		con = DriverManager.getConnection(dbUrl,user,pass);
//		Statement stmt = con.createStatement(); 
		String selectQuery = "select * from t1";
//		String insertQuery = "insert into t1(id, name, age) values (02,'akshay',21)";
//		String updateQuery = "update t1 set age = 10 where id = 1";
//		String deleteQuery = "delete from t1 where age = 10";
		
//		--------------insertion-----------------------
		
//		int rowsInserted = stmt.executeUpdate(insertQuery);
//		System.out.println(rowsInserted+" rows effected");
//		

		
//		--------------Updation-------------------------
//		
//	    int rowsUpdated = stmt.executeUpdate(updateQuery);
//		System.out.println(rowsUpdated +" rows effected");
//		
		
//		--------------Deletion-----------------------
//		
//		int rowsDeleted = stmt.executeUpdate(deleteQuery);
//		System.out.println(rowsDeleted +" rows deleted" );
		
		 
//		ResulSet rs2 = stmt.executeUpdate(query1);
		
		
		PreparedStatement pst = con.prepareStatement(selectQuery);
		
//		--------------Selection-----------------------
		
		ResultSet rs = pst.executeQuery(selectQuery);
		
		
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println("ID : "+id+" Name : "+name+" Age :"+age);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			if(con != null) {
				con.close();
			}
			}catch(Exception e) {
				e.printStackTrace();
		}
		
	}
}

	


}

