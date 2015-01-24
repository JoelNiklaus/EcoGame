package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Enquiry;

public interface EnquiryDao extends CrudRepository<Enquiry,Long> {
	
	Iterable<Enquiry> findAllByReceiverId(Long receiverId);
	
}
