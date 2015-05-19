package ch.joelniklaus.ecogame.controller.pojos;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ch.joelniklaus.ecogame.model.Player;

public class SignupForm {
	
	private Long id;

	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Enter your username")
	private String username;

	@NotNull(message = "E-Mail already exists")
	@Email(message = "Please enter a valid E-Mail address")
	@NotEmpty(message = "Please enter your E-Mail address")
	private String email;

	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-]*", message = "Password must be at least 3 characters long")
	private String password;

	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-]*", message = "Password must be at least 3 characters long")
	private String passwordConfirm;

	@Lob
	@NotNull
	@Pattern(regexp = "[a-zA-ZäöüÄÖÜ.,;:0-9()\\s]+", message = "Please write something about you")
	private String status;

	private String imageId;

	public SignupForm() {

	}

	public SignupForm(Long id, String username, String email, String password,
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

	public SignupForm(Player player) {
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
}
