package ch.joelniklaus.ecogame.controller.pojos;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import ch.joelniklaus.ecogame.model.User;

public class GameForm {

	private Long id;

	@NotNull
	@NotEmpty(message = "Please enter a name for your game")
	private String name;

	@Min(5)
	@Max(25)
	private int numberOfPlayers;

	private List<User> players = new LinkedList<User>();

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

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}
}
