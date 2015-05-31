package ch.joelniklaus.ecogame.controller.pojos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import ch.joelniklaus.ecogame.model.Game;

public class GameForm {
	
	private Long id;
	
	@NotEmpty
	private String name;

	@Min(5)
	@Max(25)
	private Integer maxNumberOfPlayers;

	public GameForm() {

	}

	public GameForm(Game game) {
		this.setName(game.getName());
		this.setMaxNumberOfPlayers(game.getMaxNumberOfPlayers());
	}
	
	public Integer getMaxNumberOfPlayers() {
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
