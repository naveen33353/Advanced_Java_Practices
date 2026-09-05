package com.aitrich.Exercise.Entity;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "job_seeker")
public class JobSeeker {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(nullable  = false)
	private String name;
	
	@Column (unique = true)
	private String email;
	
	private String skills;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	private List<Application> applications;
	
	
	
	
	
	public JobSeeker() {
	}


	public JobSeeker( String name, String email, String skills) {
		
		this.name = name;
		this.email = email;
		this.skills = skills;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}


	@Override
	public String toString() {
		return "JobSeeker [id=" + id + ", name=" + name + ", email=" + email + ", skills=" + skills + "]";
	}
	
	
	
	

}
