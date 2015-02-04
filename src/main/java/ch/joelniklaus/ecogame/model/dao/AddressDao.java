package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Address;

public interface AddressDao extends CrudRepository<Address, Long> {
	
}
