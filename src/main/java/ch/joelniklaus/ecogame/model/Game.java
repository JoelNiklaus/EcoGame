package ch.joelniklaus.ecogame.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;

@Entity
public class Game extends DataBaseObject {
	
	private String name;

	private Integer maxNumberOfPlayers;

	private Integer year = 0;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Player> players = new LinkedList<Player>();
	
	@OneToMany
	private List<Conjuncture> yearlyConjunctures = new ArrayList<Conjuncture>();

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

	public Integer getMaxNumberOfPlayers() {
		return maxNumberOfPlayers;
	}

	public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
		this.maxNumberOfPlayers = maxNumberOfPlayers;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}

	public void passYear() {
		year++;
	}
	
	public List<Conjuncture> getYearlyConjunctures() {
		return yearlyConjunctures;
	}
	
	public void setYearlyConjunctures(List<Conjuncture> yearlyConjunctures) {
		this.yearlyConjunctures = yearlyConjunctures;
	}
	
	public void addConjuncture(int year, Conjuncture conjuncture) {
		this.yearlyConjunctures.add(year, conjuncture);
	}

	public Conjuncture getConjuncture(int year) {
		return this.yearlyConjunctures.get(year);
	}
	
}
