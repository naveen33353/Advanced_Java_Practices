package com.aitrich.Exercise;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;

import com.aitrich.Exercise.DAO.DataAccesObject;
import com.aitrich.Exercise.Entity.Application;
import com.aitrich.Exercise.Entity.Company;
import com.aitrich.Exercise.Entity.JobSeeker;
import com.aitrich.Exercise.Entity.Job;



public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DataAccesObject dao = new DataAccesObject();
		
		JobSeeker jobseeker1 = new JobSeeker("Benlin","benlinbenny@gmail.com","AI-Engineer");
		JobSeeker jobseeker2 = new JobSeeker("naveen","naveen2019@gmail.com","Front-End");
		JobSeeker jobseeker3 = new JobSeeker("Akshay","Akshaykrishna@gmail.com","Devoops");
		JobSeeker jobseeker4 = new JobSeeker("Abijith","abijithps@gmail.com","Back-End");
		JobSeeker jobSeeker5 = new JobSeeker("Suraj", "suraj@gmail.comf", "Full-stack");
		
		Company company1 = new Company("Google","Delhi","Tech");
		Company company2 = new Company("MicroSoft","Pune","Development");
		Company company3 = new Company("Amazon","Gujarat","e-commerce");
	
		Job job1 = new Job("Software Engineer", "Entry level",LocalDate.now(),company1); 
		Job job2 = new Job("Devoops Engineer"," Mid-Level",LocalDate.now(),company2);

		Application app1 = new Application(jobseeker1,job1,LocalDate.now(),"ongoing");
		Application app2 = new Application(jobseeker2,job2,LocalDate.now(),"ongoing");
		Application app3 = new Application(jobseeker3,job2,LocalDate.now(),"Rejected");
		Application app4 = new Application(jobseeker4,job1,LocalDate.now(),"Rejected");
	

		boolean isRunning = true;
		int choice;
		while (isRunning) {

			System.out.println("What operation you want to do with us");
			System.out.println("choose an option ");
			System.out.println("1.JobSeeker");
			System.out.println("2.Job");
			System.out.println("3.Company");
			System.out.println("4.Application");
			System.out.println("5.Filtering");
			System.out.println("6.Exit");
		
			System.out.print("Enter your choice :");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1 -> {
				System.out.println("1.Save JobSeeker");
				System.out.println("2.Update JobSeeker");
				System.out.println("3.Delete Jobseeker");
				System.out.println("4.list all Jobseeker");
				System.out.print("Choose an option :");
				int option = sc.nextInt();
				
				switch(option) {
				case 1 -> {
//					dao.saveJobSeeker(jobSeeker5);
					System.out.println("Jobseeker saved successefully");
				}
				case 2 -> {
					dao.updateJobSeeker();
					
				}
				case 3 ->{
					dao.deleteJobSeeker();
				}
				case 4 -> {
					dao.listAllJobSeeker();
				}default -> {System.out.println("please enter a valid choice ");}
				}
						
			}
			
			case 2 -> {
				System.out.println("1.Save Job");
				System.out.println("2.Update Job");
				System.out.println("3.Delete Job");
				System.out.println("4.list all Jobs");
				System.out.print("Choose an option :");
				int option = sc.nextInt();
				
				switch(option) {
				case 1 -> {
//					dao.saveJobs(job2);
					System.out.println("Job saved successefully");
				}
				case 2 -> {
					dao.updateJob();
					
				}
				case 3 ->{
					dao.deleteJob();
				}
				case 4 -> {
					dao.listAllJobs();
				}default -> {System.out.println("please enter a valid option ");}
				}
				
			}
			case 3 -> {
				System.out.println("1.Save Company");
				System.out.println("2.Update Company");
				System.out.println("3.Delete Company");
				System.out.println("4.list all Companies");
				System.out.print("Choose an option :");
				int option = sc.nextInt();
				
				switch(option) {
				case 1 -> {
//					dao.saveCompany(company3);
					System.out.println("Company saved successefully");
				}
				case 2 -> {
					dao.updateCompany();
					
				}
				case 3 ->{
					dao.deleteCompany();
				}
				case 4 -> {
					dao.listAllCompanies();
				}default -> {System.out.println("please enter a valid option ");}
				}
			}
			case 4 -> {
				System.out.println("1.Save Application");
				System.out.println("2.Update Application");
				System.out.println("3.Delete Application");
				System.out.println("4.list all Applications");
				System.out.print("Choose an option :");
				int option = sc.nextInt();
				
				switch(option) {
				case 1 -> {
//					dao.saveApplication(app4);
					System.out.println("Application saved successefully");
				}
				case 2 -> {
					dao.updateApplication();
					
				}
				case 3 ->{
					dao.deleteApplication();
				}
				case 4 -> {
					dao.listAllApplications();
				}default -> {System.out.println("please enter a valid option ");}
				}
			}
			case 5 -> {
				System.out.println("1.Get all jobs posted by a company");
				System.out.println("2.Get all application of a jobseeker");
				System.out.println("3.Get jobseeker by job");
				System.out.print("select an option  :");
				int option = sc.nextInt();
				
				switch(option) {
				case 1 -> {
					dao.getAllJobsPostedByACompany();
					
				}
				case 2 -> {
					dao.getAllApplicationOfJobSeeker(jobSeeker5);
					
				}
				case 3 ->{
					dao.getJobSekerByJob();
				}
				default -> {System.out.println("please enter a valid option ");}
				}
			}
			case 6 -> {
				isRunning = false;
			}
			default -> {
				System.out.println("Please enter a valid option ");
			}
			}
			

		}		
	}

}
