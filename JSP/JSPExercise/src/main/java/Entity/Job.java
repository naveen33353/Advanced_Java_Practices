package Entity;

public class Job {
private int id;
private String title;
private String description;
private double salary;

public Job() {
}

public Job(int id, String title, String description, double salary) {
	this.id = id;
	this.title = title;
	this.description = description;
	this.salary = salary;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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
	return "Job [id=" + id + ", title=" + title + ", description=" + description + ", salary=" + salary + "]";
}



}
