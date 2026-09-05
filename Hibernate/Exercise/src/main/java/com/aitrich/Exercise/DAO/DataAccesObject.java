package com.aitrich.Exercise.DAO;

import com.aitrich.Exercise.Entity.Company;
import com.aitrich.Exercise.Entity.JobSeeker;



import com.aitrich.Exercise.Entity.Application;
import com.aitrich.Exercise.Entity.Job;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.build.AllowSysOut;

import com.aitrich.Exercise.Conection.ConnectionManagement;



public class DataAccesObject {
	
	
	 Scanner sc = new Scanner(System.in);
//	save job seeker
	public void saveJobSeeker(JobSeeker jobseeker) { 
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		
		session.persist(jobseeker); 
		
		tx.commit();
		session.close();
	}
	
	
//	update JobSeeker
	public void updateJobSeeker() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("Which Job seeker you want to updaye (id) : ");
		int id = sc.nextInt();
		sc.nextLine();
		
		JobSeeker jobseeker = session.get(JobSeeker.class, id);
		
		if(jobseeker == null) {
			System.out.println("jobseeker not exist !!!");
			
		}else {
			System.out.print("Enter the name : ");
			String name = sc.nextLine();
			
			System.out.print("Enter the email :");
			String email = sc.nextLine();
			
			System.out.print("Enter the skills :");
			String skills = sc.nextLine();
			
			jobseeker.setName(name);
			jobseeker.setEmail(email);
			jobseeker.setSkills(skills);
			
//			session.merge(jobseeker);
			
		     System.out.println("JobSeeker updated successfully");
			
		}
		tx.commit();
		session.close();
		
	}
	
//	list all jobseeker 
	public void listAllJobSeeker() {
		Session session =ConnectionManagement.getSessionFactory().openSession();
		
		
		List<JobSeeker> jobseeker = session.createQuery(
				"from JobSeeker",JobSeeker.class).getResultList();
			   
			  
		if(jobseeker.isEmpty()) {
			System.out.println("No JobSeeker found");
		}
		else {
			
			for(JobSeeker js : jobseeker) {
				System.out.println("id : "+js.getId()+
				           " name : "+js.getName()+
				           " email : "+js.getEmail()+ 
				           " Skills : "+js.getSkills());
			}
			
		}
	}
	
//	Delete Job seeker
	public void deleteJobSeeker() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter the job seeker id to delete a jobseeker : ");
		int id = sc.nextInt();
		
		JobSeeker jobseeker = session.get(JobSeeker.class, id);
		
		if(jobseeker == null) {
			System.out.println("please eneter a valid id");
		}else {
			session.remove(jobseeker);
		}
		
		
		
		tx.commit();
		session.close();
	}
	
	
	
//	save Company
	
	public void saveCompany(Company company) {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(company);
		
		tx.commit();
		session.close();
	}
	
//	update Company
	public void updateCompany() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("Which Company you want to update (id) : ");
		int id = sc.nextInt();
		sc.nextLine();
		
		
		Company company = session.get(Company.class, id);
		
		if(company == null) {
			System.out.println("Company not found !!!");
			
		}else {
			System.out.print("Enter the company name : ");
			String name = sc.nextLine();
			
			System.out.print("Enter the indusrty :");
			String indusrty = sc.nextLine();
			
			System.out.print("Enter the Location :");
			String location = sc.nextLine();
			
			company.setCompanyName(name);
			company.setIndustry(indusrty);
			company.setLocation(location);
			
			
//			session.merge(jobseeker);
			
		     System.out.println("Company updated successfully");
			
		}
		tx.commit();
		session.close();
		
	}
	
//	list all Companies
	 
	public void listAllCompanies() {
		Session session =ConnectionManagement.getSessionFactory().openSession();
		
		
		List<Company> company = session.createQuery(
				"from Company",Company.class).getResultList();
			   
			  
		if(company.isEmpty()) {
			System.out.println("No Companies found");
		}
		else {
			
			for(Company c : company) {
				System.out.println("id : "+c.getCompanyId()+
				           " name : "+c.getCompanyName()+
				           " indusrty : "+c.getIndustry()+ 
				           " Location : "+c.getLocation());
			}
			
		}
	}
	
//	Delete Company
	public void deleteCompany() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter the Companyid to delete a company : ");
		int id = sc.nextInt();
		
		Company company = session.get(Company.class, id);
		
		if(company == null) {
			System.out.println("company not found !!");
		}else {
			session.remove(company);
		}
		
		
		
		tx.commit();
		session.close();
	}
	
	
	
//	save jobs
	
	public void saveJobs(Job job) {
	    Session session = ConnectionManagement
	                        .getSessionFactory()
	                        .openSession();
	    Transaction tx = session.beginTransaction();

	    Company managedCompany = session.get(
	        Company.class,
	        job.getCompany().getCompanyId()
	    );

	    if (managedCompany == null) {
	        throw new RuntimeException("Company not found");
	    }

	    job.setCompany(managedCompany);
	    session.persist(job);

	    tx.commit();
	    session.close();
	}
	
//	update job
	
public void updateJob(){
		
		Session session = ConnectionManagement.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter job id to update a job : ");
		int jobId = sc.nextInt();
		
	   Job jobs = session.get(Job.class,jobId);
	   if(jobs==null) {
		   System.out.println("No job exist");
	   }else {
		   
		 
		  
		  System.out.println("enter the tilte : ");
		  String title = sc.nextLine();
		  
		  System.out.println("Enter the description : ");
		  String description = sc.nextLine();
		  
		  
		  jobs.setTitle(title);
		  jobs.setDescription(description);
		  
		  
		   session.merge(jobs);
		   
		   System.out.println("job updated succssesfully");
	   }
	 
		 
		
		tx.commit();
		session.close();
		
		
	}

//list all jobs
public void listAllJobs() {
	Session session =ConnectionManagement.getSessionFactory().openSession();
	
	
	List<Job> job = session.createQuery(
			"from Job",Job.class).getResultList();
		   
		  
	if(job.isEmpty()) {
		System.out.println("No jobs found");
	}
	else {
		
		for(Job j : job) {
			System.out.println("Job id : "+j.getJobId()+
			           " Description : "+j.getDescription()+
			           " title : "+j.getTitle()+ 
			           " posted date : "+j.getPostedDate()+
			           " company name : "+ j.getCompany().getCompanyName());
		}
		
	}
}

//  delete job

public void deleteJob() {
	Session session  = ConnectionManagement.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	
	System.out.print("Enter  jobId to delete a job : ");
	int jobId = sc.nextInt();
	
	Job jobs = session.get(Job.class,jobId);
	if(jobs == null) {
		System.out.println("Job not found");
	}else {
		session.remove(jobs);
		System.out.println("job removed successfully");
	}
	
	
	tx.commit();
	session.close();
}
	

//	save application 
	
	public void saveApplication(Application application) {
	    Session session = ConnectionManagement
	                        .getSessionFactory()
	                        .openSession();
	    Transaction tx = session.beginTransaction();

	    Job managedJob = session.get(
	        Job.class,
	        application.getJob().getJobId()
	    );

	    JobSeeker managedSeeker = session.get(
	        JobSeeker.class,
	        application.getJobSeeker().getId()
	    );

	    if (managedJob == null || managedSeeker == null) {
	        throw new RuntimeException(
	            "Job or JobSeeker not found in DB"
	        );
	    }

	    application.setJob(managedJob);
	    application.setJobSeeker(managedSeeker);

	    session.persist(application);

	    tx.commit();
	    session.close();
	}
	
//	update application
	
	public void updateApplication() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter the application id to update application : ");
		int applicationId = sc.nextInt();
		
		Application application = session.get(Application.class,applicationId);
		
		
		if(application == null) {
			System.out.println("No application found");
		}else {
			System.out.println("Enter the status : ");
			String status = sc.nextLine();
			
			application.setStatus(status);
			session.merge(application);
			System.out.println("Application updated !!!");
			
		}
		
		
		tx.commit();
		session.close();
	}
	
//	delete application
	
	public void deleteApplication() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		System.out.print("Enter the application id to delete application : ");
		int applicationId = sc.nextInt();
		
		Application application = session.get(Application.class, applicationId);
		
		
		if(application == null) {
			System.out.println("No application found");
		}else {
			session.remove(application);
			System.out.println("Application removed  successfully!!!");
		}
		
		tx.commit();
		session.close();
	}
	

	//list all applications
	public void listAllApplications() {
		Session session =ConnectionManagement.getSessionFactory().openSession();
		
		
		List<Application> app = session.createQuery(
				"from Application",Application.class).getResultList();
			   
			  
		if(app.isEmpty()) {
			System.out.println("No application found");
		}
		else {
			
			for(Application a  : app) {
				System.out.println("Application id : "+ a.getApplicationId()+
				           " Date : "+a.getApplicationDate()+
				           " status : "+a.getStatus());
			}
			
		}
	}

	
//	
	public List<Job> getAllJobsPostedByACompany() {
		Session session = ConnectionManagement.getSessionFactory().openSession();
		
		System.out.print("Enter company id :");
		int companyId = sc.nextInt();
		
		List<Job> jobs = session.createQuery(
				"FROM Job j WHERE j.company.companyId = :cid", Job.class)
			    .setParameter("cid", companyId)
			    .getResultList();

//		System.out.println("Job title : "+job.getTitle()+
//		                   " Job decription : "+job.getDescription()+
//		                   " job postedDate : "+job.getPostedDate()+
//		                   " Company name : " + job.getCompany());
		
		 for (Job job : jobs) {
		        System.out.println(
		            job.getTitle() + " | " +
		            job.getDescription() + " | " +
		            job.getPostedDate()
		        );
		    }
		session.close();
		
		return jobs;
		
	}
	
	
	public List<Application> getAllApplicationOfJobSeeker(JobSeeker jobseekerId) {
		
		Session session = ConnectionManagement.getSessionFactory().openSession();
		
		List<Application> applications= null;
		
		 applications = session.createQuery("From Application a where a.jobSeeker.id = :jsId",
				                     Application.class)
				            .setParameter("jsId", jobseekerId.getId())
				            .getResultList();
		 
		 
		 for (Application application : applications) {
		        System.out.println(
		            application.getJobSeeker() + " | " +
		            		application.getJob() + " | " +
		            		application.getStatus() + " | "+
		            		application.getApplicationDate()
		        );
		    }
		 session.close();
		
		return applications;
	}
	
	
	public List<JobSeeker> getJobSekerByJob(){
		
		List<JobSeeker> jobseekers = null ;
		
		Session session =  ConnectionManagement.getSessionFactory().openSession();
		
		System.out.print("Enter job id : ");
		int jobId = sc.nextInt();
		
		jobseekers = session.createQuery(
				"select a.jobSeeker from Application a where a.job.id = :jobId",
				JobSeeker.class)
				.setParameter("jobId", jobId)
				.getResultList();
		
		 
		 for (JobSeeker jobseeker : jobseekers) {
		        System.out.println(
		        		jobseeker.getName() + " | " +
		        				jobseeker.getEmail() + " | " +
		        				jobseeker.getSkills() 
		        );
		    }
		
		session.close();
		return jobseekers;
		
	}
	
}
	
	
	
	
	
	
	