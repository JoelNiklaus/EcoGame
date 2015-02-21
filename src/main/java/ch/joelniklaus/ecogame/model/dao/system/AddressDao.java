package ch.joelniklaus.ecogame.model.dao.system;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.system.Address;

public interface AddressDao extends CrudRepository<Address, Long> {
	
}
