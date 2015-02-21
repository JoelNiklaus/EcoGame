package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.BankAccount;

public interface BankAccountDao extends CrudRepository<BankAccount, Long> {

}
