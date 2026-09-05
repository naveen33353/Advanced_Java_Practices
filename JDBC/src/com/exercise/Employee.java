package com.exercise;

import java.time.LocalDate;

public class Employee {
	
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String department;
	private double salary;
	private LocalDate hireDate;
	private Boolean isActive = true;

	public Employee(int id,String firstName,String lastName,String email,String phone,String department,
			        double salary,LocalDate hireDate,Boolean isActive) {
		this.id= id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.salary = salary;
		this.hireDate = hireDate;
		this.isActive = isActive;
		
		
	}

	public Employee(String firstName, String lastName, String email,
            String phone, String department, double salary,
            LocalDate hireDate, boolean isActive) {
this.firstName = firstName;
this.lastName = lastName;
this.email = email;
this.phone = phone;
this.department = department;
this.salary = salary;
this.hireDate = hireDate;
this.isActive = isActive;
}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id= id;
	}



	public String getFirstName() {
		return firstName; 
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getlastName() {
		return lastName; 
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	  


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}



	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	 

	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Boolean getisActive() {
		return isActive;
	}
	public void setisActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	@Override
	public String toString() {
		return "First Name : " + this.firstName + 
				" Last Name : " + this.lastName +
				" Email : " + this.email +
				" Phone : " + this.phone +
				" Department : " + this.department +
				" Salary : " + this.salary +
				" Hire Date : " + this.hireDate +
				" Is active : " + this.isActive ;
	}


	}


