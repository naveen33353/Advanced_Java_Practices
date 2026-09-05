package Entity;

public class JobSeeker {

	private int id;
	private String name;
	private String email;
	private String skill;
	private String resume_path;
	public JobSeeker() {
	}
	public JobSeeker(int id, String name, String email, String skill, String resume_path) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.skill = skill;
		this.resume_path = resume_path;
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
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getResume_path() {
		return resume_path;
	}
	public void setResume_path(String resume_path) {
		this.resume_path = resume_path;
	}
	@Override
	public String toString() {
		return "JobSeeker [id=" + id + ", name=" + name + ", email=" + email + ", skill=" + skill + ", resume_path="
				+ resume_path + "]";
	}
	
	
}
