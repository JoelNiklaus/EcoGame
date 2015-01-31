package ch.joelniklaus.ecogame.controller.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.enums.VisitAppointmentState;
import ch.joelniklaus.ecogame.controller.pojos.VisitAppointmentForm;
import ch.joelniklaus.ecogame.model.Enquiry;
import ch.joelniklaus.ecogame.model.VisitAppointment;
import ch.joelniklaus.ecogame.model.dao.VisitAppointmentDao;

@Service
public class VisitAppointmentServiceImpl implements VisitAppointmentService{

	@Autowired VisitAppointmentDao vaDao;
	@Autowired EnquiryService enquiryService;
	
	@Transactional
	public VisitAppointmentForm save(VisitAppointmentForm vaForm) {
		
		Date endDate;
		Date startDate;
		Enquiry enquiry = enquiryService.getEnquiryById(vaForm.getEnquiryId());
		try {
			endDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(vaForm.getEndDate());
			System.out.println(endDate.toString());
			startDate= new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(vaForm.getStartDate());
			VisitAppointment visitAppointment = new VisitAppointment();
//			visitAppointment.setEnquiry(enquiry);
			visitAppointment.setEndDate(endDate);
			visitAppointment.setComment(vaForm.getComment());
			visitAppointment.setStartDate(startDate);
			visitAppointment.setState(VisitAppointmentState.NEW);
			visitAppointment = vaDao.save(visitAppointment);
			vaForm.setId(visitAppointment.getId());
			
			
			List<VisitAppointment> visitAppointments = enquiry.getVisitAppointments();
			visitAppointments.add(visitAppointment);
			enquiry.setVisitAppointments(visitAppointments);
			enquiryService.save(enquiry);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vaForm;
	}

	public VisitAppointment getVisitAppointment(Long id) {
		return vaDao.findOne(id);
	}

	public void remove(Long id) {
		vaDao.delete(id);		
	}

	public void updateState(Long id, VisitAppointmentState state) {
		VisitAppointment visitAppointment = vaDao.findOne(id);
		visitAppointment.setState(state);
		vaDao.save(visitAppointment);
	}
	
	

}