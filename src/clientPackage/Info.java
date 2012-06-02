package clientPackage;

public class Info {

	private String username;
	private String password;
	private String email;
	private String nationality;
	private String date;
	
	public Info(String username, String password, String email, String nationality, String date){
		this.username = username;
		this.password = password;
		this.email = email;
		this.nationality = nationality;
		this.date = date;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
