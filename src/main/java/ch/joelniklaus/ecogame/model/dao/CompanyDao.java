package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Company;

public interface CompanyDao extends CrudRepository<Company, Long> {

}
