package ch.joelniklaus.ecogame.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.model.BankAccount;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.BankAccountDao;
import ch.joelniklaus.ecogame.model.dao.CompanyDao;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	BankAccountDao bankAccountDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	PlayerDao playerDao;

	public static final Double STARTING_BALANCE = 1000000.00;

	@Override
	public Player initPlayer(Player player) {
		BankAccount bankAccount = new BankAccount(STARTING_BALANCE);
		bankAccountDao.save(bankAccount);
		bankAccount = new BankAccount(STARTING_BALANCE);
		bankAccountDao.save(bankAccount);
		playerDao.save(player);
		return player;
	}
}
