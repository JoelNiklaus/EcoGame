package ch.joelniklaus.ecogame.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.joelniklaus.ecogame.controller.pojos.RegisterForm;
import ch.joelniklaus.ecogame.controller.service.AuthenticationService;
import ch.joelniklaus.ecogame.controller.service.AuthenticationServiceImpl;

public class LoginServiceTest {
	
	private AuthenticationService authService;
	
	@Before
	public void doSetup() {
		authService = new AuthenticationServiceImpl();
	}
	
	@Test
	public void testUserSave() {
		RegisterForm signupForm = new RegisterForm();
		signupForm.setUsername("Max");
		signupForm.setEmail("max@muster.ch");
		
		RegisterForm resultForm = authService.createProfile(signupForm);

		assertEquals("Max", resultForm.getUsername());
		assertEquals("max@muster.ch", resultForm.getEmail());
	}
	
}