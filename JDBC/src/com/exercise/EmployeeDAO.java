package com.exercise;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAO {

	
	
//	---------------Add Employees--------------------------
	
	public void addEmployee(Employee employee) {
		String insertQuery = "INSERT INTO EMPLOYEES( first_name, last_name, email, phone, department, salary,hire_date)"
				+ "           VALUES (?,?,?,?,?,?,?)";
		try(Connection con = DatabaseManager.getConnection();
		    PreparedStatement pst = con.prepareStatement(insertQuery)){
			
			pst.setString(1, employee.getFirstName());
			pst.setString(2, employee.getlastName());
			pst.setString(3, employee.getEmail());
			pst.setString(4, employee.getPhone());
			pst.setString(5, employee.getDepartment());
			pst.setDouble(6, employee.getSalary() );
			pst.setDate (7, Date.valueOf( employee.getHireDate()));
//			pst.setBoolean(8, employee.getisActive());
			
			
			pst.executeUpdate();
			System.err.println("Employee added Successfullt!!!!");
			
		}catch(Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
	
	
//	----------------Read employee by id--------------------------
	
	
	public Employee readEmployeeById(int id) {
		String readQuery = "SELECT * FROM EMPLOYEES WHERE id = ? ";
		Employee emp = null;
		try(Connection con = DatabaseManager.getConnection();
		    PreparedStatement pst = con.prepareStatement(readQuery)){
			pst.setInt(1,id); 
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				emp = mapResultSetToEmployee(rs);
			}
	}catch(SQLException e) {
		e.printStackTrace();
		
	}
		return emp;
	}
	
	
//	--------------- Read all Employee--------------------------
	
	public List<Employee> getAllEmployees() {
		
		List<Employee> employee = new ArrayList<>();
		String query = "SELECT * FROM EMPLOYEES";
		Employee emp = null;
		
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery(query);){
			
			while(rs.next()) {
				employee.add(mapResultSetToEmployee(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	
//	------------------ Update Employee ------------------------------------
	
	public Employee updateEmployee(Employee employee) {
		String updateQuery = "UPDATE EMPLOYEES SET first_name=?, last_name=?, email=?, phone=?,"
				+ "            department = ?, salary=?, hire_date=?, is_active=? WHERE id=?";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement ps = con.prepareStatement(updateQuery)){
			
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getlastName());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPhone());
			ps.setString(5, employee.getDepartment());
			ps.setDouble(6, employee.getSalary());
			ps.setDate(7, Date.valueOf(employee.getHireDate()));
			ps.setBoolean(8, employee.getisActive());
			ps.setInt(9, employee.getId());
			
			ps.executeUpdate();
			System.out.println("Employee updated Successfully!!!");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
		
	}
	
//	-----------------Delete Employee--------------------------------
	
	public void deleteEmployee(int id) {
		String deleteQuery = "DELETE  FROM EMPLOYEES WHERE id = ?";
		
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement ps = con.prepareStatement(deleteQuery)){
			
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Employee deleted Successfully!!!");

		} catch (SQLException e) {
				e.printStackTrace();
		}
		
	}
	
	
	
//	------------------mapResultSetToEmployee--------------------------------
	
	public Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("id"),
				           rs.getString("first_name"),
				           rs.getString("last_name"),
				           rs.getString("email"),
				           rs.getString("phone"),
				           rs.getString("department"),
				           rs.getDouble("salary"),
				           rs.getDate("hire_date").toLocalDate(),
				           rs.getBoolean("is_active")
				           );
	}
	
//	-----------------get Employee By Department-------------------
	
	public Employee getEmployeeByDepartment(String department){
		
		Employee emp = null;
		String query = "SELECT * FROM EMPLOYEES WHERE department = ?";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement ps =  con.prepareStatement(query)){
			
			
			ps.setString(1, department);
		    ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				emp = mapResultSetToEmployee(rs);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return emp;
		
	}
	
	
//	---------------------- get employee by salary range ---------------------
	 public Employee getEmployeeBySalaryRange(double minSalary, double maxSalary) {
		 Employee emp = null;
		 String query = "SELECT * FROM  EMPLOYEES WHERE salary  BETWEEN ? AND ?";
		 try(Connection con = DatabaseManager.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			 
			 ps.setDouble(1, minSalary);
			 ps.setDouble(2, maxSalary);
			 
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 emp = mapResultSetToEmployee(rs); 
				 
			 }
			 
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 return emp;
	 }
	
//	---------------------- get active employees ----------------------
	 
	 public List<Employee>  getActiveEmployees() {
		 
		  List<Employee> emp = new ArrayList<>();
		 
		
		 String query = "SELECT * FROM EMPLOYEES WHERE is_active = true";
		 
		 try(Connection con = DatabaseManager.getConnection();
				 PreparedStatement ps = con.prepareStatement(query)){
			 
			 
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 emp.add(mapResultSetToEmployee(rs));
			 }
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 
		 return emp;
	 }
	 
	 
//	 ------------------ Search by employee name -----------------------------
	 
	 public Employee getEmployeeByName(String searchTerm){
		 
		Employee emp = null;
		 
		 String query = "SELECT * FROM EMPLOYEES WHERE first_name LIKE ? OR last_name LIKE ?";
		 
		 try(Connection con = DatabaseManager.getConnection();
				 PreparedStatement ps = con.prepareStatement(query)){
			 
			 ps.setString(1,"%" + searchTerm + "%");
			 ps.setString(2,"%" + searchTerm + "%");
			 ResultSet rs = ps.executeQuery();
			 
			 if(rs.next()) {
				emp = mapResultSetToEmployee(rs);
			 }else {
				 System.out.println("Something Went wrong!!!");
			 }
			 
		 } catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		return emp;
		 
	 }
	
	 
//	 -------------------- get employee after hire date ---------------
	 
	 public List<Employee> getEmployeesHiredAfter(LocalDate date) {
		 
		 List<Employee> employee = new ArrayList<>();
		 String query = "SELCET * FROM EMPLOYEES WHERE hire_date > ? ";
		 
		 try(Connection con = DatabaseManager.getConnection();
				 PreparedStatement ps = con.prepareStatement(query)){
			 
			 ps.setDate(1, Date.valueOf(date));
			 
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 employee.add(mapResultSetToEmployee(rs));
			 }
			 
		 } catch (SQLException e) {
			
			e.printStackTrace();
		}
		return employee;
		 
	 }
}
	
	
	


