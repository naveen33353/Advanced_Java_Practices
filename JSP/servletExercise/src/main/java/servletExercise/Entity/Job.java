package servletExercise.Entity;

public class Job {
private int jobId;
private String title;
private String description;
private double salary;



public Job() {
}
public Job(int jobId, String title, String description, double salary) {
	this.jobId = jobId;
	this.title = title;
	this.description = description;
	this.salary = salary;
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
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
@Override
public String toString() {
	return "Job [jobId=" + jobId + ", title=" + title + ", description=" + description + ", salary=" + salary + "]";
}


}
