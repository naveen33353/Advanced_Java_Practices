package com.aitrich.Exercise.Conection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.aitrich.Exercise.Entity.Application;
import com.aitrich.Exercise.Entity.Company;
import com.aitrich.Exercise.Entity.Job;
import com.aitrich.Exercise.Entity.JobSeeker;

import jakarta.persistence.*;

public class ConnectionManagement {

	private static SessionFactory sessionfactory;
	
	static {            
		try{ 
			Configuration config = new Configuration();
				config.configure();
				config.addAnnotatedClass(Job.class);
				config.addAnnotatedClass(JobSeeker.class);
				config.addAnnotatedClass(Application.class);
				config.addAnnotatedClass(Company.class);
			
				sessionfactory = config.buildSessionFactory();
				
				}catch(Exception e) {
					e.printStackTrace(); 
				}
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionfactory;
	}
}
