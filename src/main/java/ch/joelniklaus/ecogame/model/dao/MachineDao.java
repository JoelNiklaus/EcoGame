package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Machine;

public interface MachineDao extends CrudRepository<Machine, Long> {

}
