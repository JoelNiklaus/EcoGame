package ch.joelniklaus.ecogame.controller.pojos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class GameForm {
	
	private Long id;
	
	@NotNull
	@NotEmpty(message = "Please enter a name for your game")
	private String name;
	
	@Min(5)
	@Max(25)
	private int numberOfPlayers;
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
