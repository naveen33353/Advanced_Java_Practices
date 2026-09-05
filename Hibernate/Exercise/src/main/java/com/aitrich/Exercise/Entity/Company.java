package com.aitrich.Exercise.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int companyId;
	
	
	@Column(nullable = true)
	private String companyName;
	
	private String location;
	
	private String industry;
	
	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
	private List<Job> jobs;
	
	
	
	

	public Company() {
		
	}

	public Company( String companyName, String location, String industry) {
		
		
		this.companyName = companyName;
		this.location = location;
		this.industry = industry;
	
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", location=" + location
				+ ", industry=" + industry + "]";
	}
	
	
}
