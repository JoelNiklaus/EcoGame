package ch.joelniklaus.ecogame.controller.pojos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import ch.joelniklaus.ecogame.model.Game;

public class GameForm {
	
	private Long id;
	
	@NotNull
	@NotEmpty(message = "Please enter a name for your game")
	private String name;
	
	@Min(5)
	@Max(25)
	private int maxNumberOfPlayers;

	public GameForm() {

	}

	public GameForm(Game game) {
		this.setName(game.getName());
		this.setMaxNumberOfPlayers(game.getMaxNumberOfPlayers());
	}
	
	public int getMaxNumberOfPlayers() {
		return maxNumberOfPlayers;
	}

	public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
		this.maxNumberOfPlayers = maxNumberOfPlayers;
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
