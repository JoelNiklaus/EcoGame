package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.ForgotPasswordForm;
import ch.joelniklaus.ecogame.controller.pojos.LoginForm;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;
import ch.joelniklaus.ecogame.controller.service.AuthenticationService;
import ch.joelniklaus.ecogame.controller.service.MailService;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.UserDao;

@Controller
public class AuthenticationController extends ParentController {

	@Autowired
	AuthenticationService authService;
	@Autowired
	MailService mailService;
	@Autowired
	UserDao userRepositry;
	
	@Autowired
	@Qualifier("authMgr")
	private AuthenticationManager authMgr;

	/**
	 * Creates a model for registering a new user.
	 *
	 * @return register model
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("signupForm", new SignupForm());
		return model;
	}

	/**
	 * Submits registration and returns login model
	 *
	 * @param signupForm
	 *            completed registration form
	 * @param result
	 * @param redirectAttributes
	 *
	 * @return login model, registration model if signup form invalid
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid SignupForm signupForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("login");

		try {

			if (authService.emailAlreadyExists(signupForm.getEmail()))
				throw new InvalidUserException("Email already exists");
			
			if (!result.hasErrors())
				try {
					authService.createProfile(signupForm);

					model.addObject(new LoginForm());
				} catch (InvalidUserException e) {
					model = new ModelAndView("register");
					model.addObject("page_error", e.getMessage());
				}
			else
				model = new ModelAndView("register");
			model.addObject("loggedInUser", authService.getLoggedInUser());
		} catch (InvalidUserException ex) {
			model = new ModelAndView("register");
			signupForm.setEmail("");
			model.addObject("signupForm", signupForm);
			model.addObject("emailExists", "This email address is already in use");
		}

		// perform login authentication

		try {
			User user = userRepositry.findByEmail(signupForm.getEmail());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					user, signupForm.getPassword(), user.getAuthorities());
			authMgr.authenticate(auth);
			
			if (auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				return new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			System.out.println("Problem authenticating user" + signupForm.getEmail());
		}
		
		return model;
	}

	/**
	 * Redirects the login to spring security
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	/**
	 * Redirects accessdenied to spring security
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}

	/**
	 * Redirects logout to spring security
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}

	/**
	 * Displays an E-Mail input field for the user who forgot his password.
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView forgot() {
		ModelAndView model = new ModelAndView("forgot");
		model.addObject("forgotPasswordForm", new ForgotPasswordForm());
		return model;
	}

	/**
	 * Sends users password to the entered E-Mail address.
	 *
	 * @param forgotPasswordForm
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ModelAndView forgot(@Valid ForgotPasswordForm forgotPasswordForm, BindingResult result) {
		if (result.hasErrors())
			return new ModelAndView("forgot");

		ModelAndView model = new ModelAndView("forgot");
		try {
			User user = authService.getUser(forgotPasswordForm);

			// compose E-Mail
			String email = user.getEmail();
			String password = user.getPassword();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String subject = "Sending Password";
			String message = "Dear " + firstName + " " + lastName + "\n\n"
					+ "You requested your password: " + password
					+ "\n\nYours sincerely,\nJoel Niklaus";

			try {
				mailService.sendMail(email, subject, message);

				model.addObject("success", "Password successfully delivered");
			} catch (EmailException e) {
				model.addObject("error", "Password could not be sent: " + e.getMessage());
				e.printStackTrace();
			}

		} catch (InvalidUserException e) {
			model.addObject("error", "No User with this E-Mail found: " + e.getMessage());
		}
		return model;
	}

}
