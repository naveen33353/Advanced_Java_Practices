package com.aitrich.Exercise.Entity;

import java.time.LocalDate;
import java.util.List;

import com.aitrich.Exercise.Entity.Application;


import jakarta.persistence.*;



@Entity
@Table(name = "jobs")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	
	private String title;
	
	@Column(length = 1000)
	private String description;
	
	private LocalDate postedDate;
	
	
	@ManyToOne 
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToMany(mappedBy = "job")
	private List<Application> applications;
	
	
	
	

	public Job() {
	}

	public Job( String title, String description, LocalDate postedDate, Company company
			) {
		
		this.title = title;
		this.description = description;
		this.postedDate = postedDate;
		this.company = company;
		
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", title=" + title + ", description=" + description + ", postedDate="
				+ postedDate + ", company=" + company + ", applications=" + applications + "]";
	}
	
	
	
	
	
	
	
	

} 
