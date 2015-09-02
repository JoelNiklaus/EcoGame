package ch.joelniklaus.ecogame.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;

@Entity
public class Game extends DataBaseObject {

	private String name;
	
	private Integer maxNumberOfPlayers;
	
	private Integer year = 0;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Player> players = new LinkedList<Player>();

	@OneToMany(cascade = { CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Conjuncture> yearlyConjunctures = new ArrayList<Conjuncture>();
	
	public Game() {
		
	}
	
	public Game(GameForm gameForm) {
		addConjuncture(0, new Conjuncture());
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
		computeConjuncture();
	}
	
	private void computeConjuncture() {
		addConjuncture(year, new Conjuncture(getConjuncture(year - 1)));
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
	
	// TODO still has to be implemented or structure of conjuncture/results has to be changed
	// List<Object> = One year containing data of each company
	public List<List<Object>> getData() {
		List<List<Object>> data = new LinkedList<List<Object>>();
		List<Object> year = null;
		for (int i = 0; i < this.year; i++) {
			year = new LinkedList<Object>();
			for (Player player : this.players)
				year.add(player.getCompany().getResult(i).getBalance());
			data.add(year);
		}
		return data;
	}
}
