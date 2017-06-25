package bizoncloudone.com.bean;

public class User {

	Integer id;
	String email_add;
	Boolean active;
	String last_name;
	String first_name;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail_add() {
		return email_add;
	}
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
}
