package ch.joelniklaus.ecogame.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import ch.joelniklaus.ecogame.controller.enums.VisitAppointmentState;

@Entity
public class VisitAppointment {

	@Id
    @GeneratedValue
	private Long id;
	
	private String Comment;
	
	@DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
	private Date startDate;

	@DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
	private Date endDate;
	
	@Enumerated
	private VisitAppointmentState state;
	
	public VisitAppointment() {
		state = VisitAppointmentState.NEW;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public VisitAppointmentState getState() {
		return state;
	}

	public void setState(VisitAppointmentState state) {
		this.state = state;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}
	
	
}
