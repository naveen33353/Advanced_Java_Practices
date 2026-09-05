package servletExercise.Entity;

public class JobSeeker {
private int jobSeekerId;
private String name;
private String email;
private String skills;
private String resume;



public JobSeeker() {
}

public JobSeeker(int jobSeekerId, String name, String email, String skills, String resume) {
	this.jobSeekerId = jobSeekerId;
	this.name = name;
	this.email = email;
	this.skills = skills;
	this.resume = resume;
}

public int getJobSeekerId() {
	return jobSeekerId;
}

public void setJobSeekerId(int jobSeekerId) {
	this.jobSeekerId = jobSeekerId;
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

public String getResume() {
	return resume;
}

public void setResume(String resume) {
	this.resume = resume;
}

@Override
public String toString() {
	return "JobSeeker [jobSeekerId=" + jobSeekerId + ", name=" + name + ", email=" + email + ", skills=" + skills
			+ ", resume=" + resume + "]";
}



}
