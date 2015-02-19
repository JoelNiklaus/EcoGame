package ch.joelniklaus.ecogame.service;

import org.junit.Before;
import org.junit.Test;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;
import ch.joelniklaus.ecogame.controller.service.AuthenticationService;
import ch.joelniklaus.ecogame.controller.service.AuthenticationServiceImpl;

public class LoginServiceTest {
	
	private AuthenticationService loginServiceImpl;
	
	@Before
	public void doSetup() {
		loginServiceImpl = new AuthenticationServiceImpl();
	}
	
	@Test(expected = InvalidUserException.class)
	public void testInvalidUserException() {
		SignupForm signupForm = new SignupForm();
		signupForm.setLastName("formLast");
		signupForm.setFirstName("ESE");
		signupForm.setEmail("form@test.com");
		
		loginServiceImpl.createProfile(signupForm);
	}
	
}