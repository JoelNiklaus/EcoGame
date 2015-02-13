package ch.joelniklaus.ecogame.controller.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Override
	public void sendMail(String email, String subject, String message) throws EmailException {
		Email simpleEmail = new SimpleEmail();
		simpleEmail.setHostName("mailtrap.io");
		simpleEmail.setSmtpPort(465);
		simpleEmail
				.setAuthenticator(new DefaultAuthenticator("25490b88e52ba93b3", "5f10b93f61b80f"));
		simpleEmail.setSSLOnConnect(false);
		simpleEmail.setFrom("demo@mailtrap.io");
		simpleEmail.setSubject(subject);
		simpleEmail.setMsg(message);
		simpleEmail.addTo(email);
		simpleEmail.send();
	}
	
}
