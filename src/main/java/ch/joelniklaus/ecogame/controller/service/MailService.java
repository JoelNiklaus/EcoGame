package ch.joelniklaus.ecogame.controller.service;

import org.apache.commons.mail.EmailException;

public interface MailService {
	
	/**
	 * Send an email using mailtrap.io
	 *
	 * @param email
	 * @param subject
	 * @param message
	 * @throws EmailException
	 */
	public void sendMail(String email, String subject, String message) throws EmailException;
}
