package ch.joelniklaus.ecogame.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int maxNumberOfPlayers;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Player> players = new LinkedList<Player>();

	public Game() {

	}

	public Game(GameForm gameForm) {
		this.name = gameForm.getName();
		this.maxNumberOfPlayers = gameForm.getMaxNumberOfPlayers();
	}

	public void addPlayer(Player Player) {
		players.add(Player);
	}
	
	public void kickPlayer(Player Player) {
		players.remove(Player);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxNumberOfPlayers() {
		return maxNumberOfPlayers;
	}

	public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
		this.maxNumberOfPlayers = maxNumberOfPlayers;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
