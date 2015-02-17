package ch.joelniklaus.ecogame.controller.pojos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgotPasswordForm {
	
	private Long id;
	
	@NotNull
	@Email(message = "Please enter a valid E-Mail address")
	@NotEmpty(message = "Please enter your E-Mail address")
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
