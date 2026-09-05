package servletExercise.Entity;

public class Employer {
private int employerId;
private String companyName;
private String email;


public Employer() {
}

public Employer(int employerId, String companyName, String email) {
	this.employerId = employerId;
	this.companyName = companyName;
	this.email = email;
}

public int getEmployerId() {
	return employerId;
}
public void setEmployerId(int employerId) {
	this.employerId = employerId;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "Employer [employerId=" + employerId + ", companyName=" + companyName + ", email=" + email + "]";
}



}
