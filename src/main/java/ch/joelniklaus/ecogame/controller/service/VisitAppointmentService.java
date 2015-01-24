package ch.joelniklaus.ecogame.controller.service;

import ch.joelniklaus.ecogame.controller.enums.VisitAppointmentState;
import ch.joelniklaus.ecogame.controller.pojos.VisitAppointmentForm;
import ch.joelniklaus.ecogame.model.VisitAppointment;


public interface VisitAppointmentService {
	
	/**
	 * 
	 * @param vaForm to save
	 * @return vaForm with id
	 */
	public VisitAppointmentForm save(VisitAppointmentForm vaForm);
	
	/**
	 * 
	 * @param visitAppointment
	 */
	public VisitAppointment getVisitAppointment(Long id);
	
	/**
	 * 
	 * @param id
	 */
	public void remove(Long id);
	
	/**
	 * 
	 * @param id
	 * @param state
	 */
	public void updateState(Long id, VisitAppointmentState state);
}
