package ch.joelniklaus.ecogame.controller.pojos;

import javax.validation.constraints.NotNull;

public class LoginForm {
	
	private Long id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	public LoginForm() {

	}

	public LoginForm(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
