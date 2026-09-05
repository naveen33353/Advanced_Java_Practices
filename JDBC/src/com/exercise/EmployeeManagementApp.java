package com.exercise;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeManagementApp {
public static void main(String[] args) {
	
	int choice ;
	boolean isRunning =true;
	
	Scanner scanner = new Scanner(System.in);
	System.out.println("Hi Welcome!!!");
   
	EmployeeDAO dao = new EmployeeDAO();
	
	while(isRunning) {
		System.out.println("Please choose an option ");
		System.out.println("1.Add Employee");
		System.out.println("2. View All Employees");
		System.out.println("3. View Employee by ID");
		System.out.println("4. Update Employee");
		System.out.println("5. Delete Employee");
		System.out.println("6. Filter by Department");
		System.out.println("7. Filter by Salary Range");
		System.out.println("8. Search by Name");
		System.out.println("9. View Active Employees");
		System.out.println("10. Exit");
		System.out.println("====================================");
		System.out.println();
		System.out.println("Enter your Choice (1-10):");
		
		choice= scanner.nextInt();
		scanner.nextLine();
		
		switch(choice) {
		case 1 ->{
			System.out.println("Enter the First Name : ");
			String fn = scanner.nextLine();
			
			System.out.println("Enter the Last Name :");
			String ls = scanner.nextLine();
			
			System.out.println("Enter the Email :");
			String email = scanner.nextLine();
			
			System.out.println("Enter phone number : ");
			String ph = scanner.nextLine();
			
			System.out.println("Enter the department :");
			String dpt = scanner.nextLine();
			
			System.out.println("Enter salary :");
			double salary = scanner.nextDouble();
			scanner.nextLine();
			
			 System.out.print("Hire Date (yyyy-mm-dd): ");
		       LocalDate hireDate = LocalDate.parse(scanner.nextLine());
			
			
			
			
			Employee emp = new Employee(fn,ls,email,ph,dpt,salary,hireDate,true);
			dao.addEmployee(emp);
		}
		
		case 2 -> {
			for(Employee emp : dao.getAllEmployees()) {
				if(emp == null) {
					System.out.println("No Employees found");
				}else {
					System.out.println(emp);
				}
				
			}
		}
			
			case 3 -> {
				System.out.println("Enter id to get the employee :");
				int id = scanner.nextInt();
				Employee emp = dao.readEmployeeById(id);
				if(id <= 0  ) {
					System.out.println("id cannot be negative");
				}else if(emp == null ) {
					System.out.println("Employee not found ");
				}else {
					System.out.println(emp);
				}
				
				
			}
			case 4 -> {
				System.out.print("Enter the id to update :");
				int id = scanner.nextInt();
				scanner.nextLine();
				
				
				Employee existing = dao.readEmployeeById(id);
				
				if(existing == null) {
					System.out.println("Employee not found !!!");
					break;
				}
				
				System.out.print("Enter  new first name :");
				existing.setFirstName(scanner.nextLine());
				
				System.out.print("Enter  new last name");
				existing.setlastName(scanner.nextLine());
				
				
				System.out.print("Enter new Email : "); 
				existing.setEmail(scanner.nextLine());
				
				System.out.print("Enter new phone :");
				existing.setPhone(scanner.nextLine());
				
				System.out.print("Enter new department :");
				existing.setDepartment(scanner.nextLine());
				
				System.out.print("Enter new salary :"); 
				existing.setSalary(scanner.nextDouble());
				
				scanner.nextLine();
				
				System.out.print("Hire Date (yyyy-mm-dd): ");
				
				String dateInput = scanner.nextLine();
			    existing.setHireDate( LocalDate.parse(dateInput));
				
			     dao.updateEmployee(existing);
				
			}
			
			case 5 -> {
				System.out.print("Enter the id to delete employee :");
				int id = scanner.nextInt();
				
				Employee existing = dao.readEmployeeById(id);
				
				if(existing == null) {
					System.out.println("Employer not found !!!");
					break;
				}
				
				dao.deleteEmployee(id);
				
			}
			
			case 6 -> {
				System.out.println("Enter department :");
				String dpt = scanner.nextLine();
				
				System.out.println(dao.getEmployeeByDepartment(dpt));
			}
			
			case 7 ->{
				System.out.println("Enter min salary :");
				double min = scanner.nextDouble();
				
				System.out.println("Enter max salary :");
				double max = scanner.nextDouble();
				
				
				System.out.println(dao.getEmployeeBySalaryRange(min, max));
				
			}
			
			case 8 -> {
				System.out.println("Enter the name to search :");
				String name = scanner.nextLine();
				
				 
					System.out.println(dao.getEmployeeByName(name));
					
				
			}
			
			case 9 -> {
			for(Employee e : dao.getActiveEmployees()) {
				System.out.println(e);
			}
			}
			
			case 10 ->{
				System.err.println("Exiting application");
				isRunning = false;
				
			}
			default ->
				System.err.println("Invalid choice !!! ");
				
			
			
		}
		
		}
		
	}
	
}

