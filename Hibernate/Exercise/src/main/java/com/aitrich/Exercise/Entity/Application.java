package com.aitrich.Exercise.Entity;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicationId;
	
	
	@ManyToOne
	@JoinColumn(name = "job_seeker_id",nullable = false)
	private JobSeeker jobSeeker;
	
	
	@ManyToOne
	@JoinColumn(name = "job_id",nullable = false)
	private Job job;
	
	private LocalDate applicationDate;
	
	private String status;
	
	
	
	
	public Application() {
		
	}

	public Application( JobSeeker jobSeeker, Job job, LocalDate applicationDate,
			String status) {
		
//		this.applicationId = applicationId;
		this.jobSeeker = jobSeeker;
		this.job = job;
		this.applicationDate = applicationDate;
		this.status = status;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobseeker) {
		this.jobSeeker = jobseeker;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job jobs) {
		this.job = jobs;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", jobseeker=" + jobSeeker + ", jobs=" + job
				+ ", applicationDate=" + applicationDate + ", status=" + status + "]";
	}
	
	
	

}
