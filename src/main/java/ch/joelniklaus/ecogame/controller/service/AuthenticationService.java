package ch.joelniklaus.ecogame.controller.service;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.ForgotPasswordForm;
import ch.joelniklaus.ecogame.controller.pojos.LoginForm;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;
import ch.joelniklaus.ecogame.model.Player;

public interface AuthenticationService {
	
	/**
	 * Saves Data from SignupForm to DB.
	 *
	 * @param signupForm
	 *            the filled out form from the registration
	 * @return the SignupForm after DB transaction
	 * @throws InvalidUserException
	 *             if first name is "ESE"
	 */
	public SignupForm createProfile(SignupForm signupForm) throws InvalidUserException;
	
	/**
	 * Updates a users profile based on the signupForm submitted.
	 *
	 * @param signupForm
	 * @return
	 * @throws InvalidUserException
	 */
	public SignupForm updateProfile(SignupForm signupForm) throws InvalidUserException;
	
	/**
	 * Fetches a user from the DB based on his login credentials.
	 *
	 * @param loginForm
	 * @return
	 * @throws InvalidUserException
	 */
	public Player getPlayer(LoginForm loginForm) throws InvalidUserException;
	
	/**
	 * Fetches user from DB by email.
	 *
	 * @param forgotPasswordForm
	 *            the filled out form containing the email
	 *
	 * @return the user matching the email provided
	 * @throws InvalidUserException
	 *             if no user with this email exists.
	 */
	public Player getPlayer(ForgotPasswordForm forgotPasswordForm) throws InvalidUserException;
	
	/**
	 * Checks if email already exists in DB.
	 *
	 * @param email
	 *            email-address to check
	 * @return true if address already exists in DB, false else
	 */
	public boolean emailAlreadyExists(String email);
	
	/**
	 * Return active user
	 *
	 * @return User
	 */
	public Player getLoggedInPlayer();
	
	/**
	 * Fetches a User by id.
	 *
	 * @param id
	 * @return
	 */
	public Player getPlayer(Long id) throws InvalidUserException;

	public boolean usernameAlreadyExists(String username);
	
	public void deletePlayer(Long id);
}
