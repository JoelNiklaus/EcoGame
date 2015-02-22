package ch.joelniklaus.ecogame.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.model.BankAccount;
import ch.joelniklaus.ecogame.model.Company;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.BankAccountDao;
import ch.joelniklaus.ecogame.model.dao.CompanyDao;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;
import ch.joelniklaus.ecogame.model.dao.system.UserDao;
import ch.joelniklaus.ecogame.model.system.User;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	PlayerDao playerDao;
	@Autowired
	BankAccountDao bankAccountDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public Player initPlayer(User user) {
		BankAccount bankAccount = new BankAccount(1000000.0);
		bankAccountDao.save(bankAccount);
		Company company = new Company("Novartis", bankAccount);
		companyDao.save(company);
		Player player = new Player(user.getFirstName() + user.getLastName(), bankAccount);
		player.setCompany(company);
		playerDao.save(player);
		user.setPlayer(player);
		userDao.save(user);
		return player;
	}
}
