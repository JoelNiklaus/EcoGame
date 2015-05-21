package ch.joelniklaus.ecogame.controller.pojos;

import javax.persistence.Lob;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ch.joelniklaus.ecogame.model.Player;

public class RegisterForm {

	private Long id;
	
	@NotEmpty
	@Size(min = 3, max = 25)
	private String username;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty(message = "the passwords do not match")
	private String passwordConfirm;
	
	@Lob
	private String status;
	
	private String imageId;
	
	public RegisterForm() {
		
	}
	
	public RegisterForm(Long id, String username, String email, String password,
			String passwordConfirm, String status, String imageId) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.status = status;
		this.imageId = imageId;
	}
	
	public RegisterForm(Player player) {
		this.username = player.getUsername();
		this.email = player.getEmail();
		this.password = player.getPassword();
		this.passwordConfirm = player.getPassword();
		this.status = player.getStatus();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		checkPasswordMatch();
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getImageId() {
		return imageId;
	}
	
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	private void checkPasswordMatch() {
		if (this.password == null || this.passwordConfirm == null)
			return;
		else if (!this.password.equals(passwordConfirm))
			this.passwordConfirm = null;
	}
}
