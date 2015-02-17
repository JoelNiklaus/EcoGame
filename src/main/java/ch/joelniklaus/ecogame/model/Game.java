package ch.joelniklaus.ecogame.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int numberOfPlayers;

	@OneToOne
	private User hoster;

	@OneToMany(fetch = FetchType.EAGER)
	private List<User> players = new LinkedList<User>();

	public void addPlayer(User user) {
		players.add(user);
	}
	
	public void kickPlayer(User user) {
		players.remove(user);
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getHoster() {
		return hoster;
	}
	
	public void setHoster(User hoster) {
		this.hoster = hoster;
	}
	
}
