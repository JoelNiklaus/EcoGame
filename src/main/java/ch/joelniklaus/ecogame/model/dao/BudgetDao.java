package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Budget;

public interface BudgetDao extends CrudRepository<Budget, Long> {

}
