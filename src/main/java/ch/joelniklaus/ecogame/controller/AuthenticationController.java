package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.ForgotPasswordForm;
import ch.joelniklaus.ecogame.controller.pojos.RegisterForm;
import ch.joelniklaus.ecogame.controller.service.AuthenticationService;
import ch.joelniklaus.ecogame.controller.service.MailService;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;

@Controller
public class AuthenticationController extends ParentController {

	@Autowired
	AuthenticationService authService;
	@Autowired
	MailService mailService;
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	@Qualifier("authMgr")
	private AuthenticationManager authMgr;

	/**
	 * Creates a model for registering a new user.
	 *
	 * @return register model
	 */
	@RequestMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}

	/**
	 * Submits registration and returns login model
	 *
	 * @param registerForm
	 *            completed registration form
	 * @param result
	 * @param redirectAttributes
	 *
	 * @return login model, registration model if signup form invalid
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, @Valid RegisterForm registerForm, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("registerForm", registerForm);
			return "register";
		}

		if (authService.emailAlreadyExists(registerForm.getEmail())) {
			result.rejectValue("email", "error.email", "This email already exists.");
			return "register";
		}
		
		if (authService.usernameAlreadyExists(registerForm.getUsername())) {
			result.rejectValue("username", "error.username", "This username already exists.");
			return "register";
		}
		
		try {
			authService.createProfile(registerForm);
		} catch (InvalidUserException e) {
			model.addAttribute("page_error", e.getMessage());
		}
		
		model.addAttribute("loggedInUser", authService.getLoggedInPlayer());
		
		// perform login authentication

		try {
			Player player = playerDao.findByEmail(registerForm.getEmail());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					player.getUsername(), registerForm.getPassword(), player.getAuthorities());
			authMgr.authenticate(auth);
			
			if (auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				return "redirect:/";
			}
		} catch (Exception e) {
			System.out.println("Problem authenticating user " + registerForm.getUsername());
			System.out.println(e.getMessage());
			
		}
		return "register";
	}

	/**
	 * Redirects the login to spring security
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	/**
	 * Redirects accessdenied to spring security
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(Model model) {
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
	public String logout(Model model) {
		return "logout";
	}

	/**
	 * Displays an E-Mail input field for the user who forgot his password.
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(Model model) {
		model.addAttribute("forgotPasswordForm", new ForgotPasswordForm());
		return "forgot";
	}

	/**
	 * Sends users password to the entered E-Mail address.
	 *
	 * @param forgotPasswordForm
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgot(Model model, @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result) {
		if (result.hasErrors())
			return "forgot";

		try {
			Player player = authService.getPlayer(forgotPasswordForm);

			// compose E-Mail
			String email = player.getEmail();
			String username = player.getUsername();
			String password = player.getPassword();
			String subject = "Sending Password";
			String message = "Howdy " + username + "\n\n" + "You requested your password: "
					+ password + "\n\nYours sincerely,\nJoel Niklaus";

			try {
				mailService.sendMail(email, subject, message);

				model.addAttribute("success", "Password successfully delivered");
			} catch (EmailException e) {
				model.addAttribute("error", "Password could not be sent: " + e.getMessage());
				e.printStackTrace();
			}

		} catch (InvalidUserException e) {
			model.addAttribute("error", e.getMessage());
		}
		return "forgot";
	}
	
	/**
	 * Displays the profile view of the user with the given id.
	 *
	 * @return
	 */
	@RequestMapping(value = "/otherProfileView/{id}", method = RequestMethod.GET)
	public String otherProfileView(Model model, @PathVariable Long id) {
		model.addAttribute("otherUser", authService.getPlayer(id));

		return "otherProfileView";
	}
	
	/**
	 * Displays the profileForm which enables the user to change his profile information.
	 *
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model) {
		model.addAttribute("profileForm", new RegisterForm(authService.getLoggedInPlayer()));

		return "profile";
	}
	
	/**
	 * Saves the changes made to the profile to the database.
	 *
	 * @param profileForm
	 * @param result
	 * @return the profileForm for further changes.
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profile(Model model,
			@ModelAttribute("profileForm") @Valid RegisterForm profileForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("error", "Please enter valid data.");
			model.addAttribute("profileForm", profileForm);
			return "profile";
		}
		
		try {
			authService.updateProfile(profileForm);
			redirectAttributes.addFlashAttribute("success", "Profile changes successfully saved");
			return "redirect:/profile";
		} catch (InvalidUserException e) {
			model.addAttribute("error", "Profile changes could not be saved: " + e.getMessage());
		} catch (NullPointerException e) {
			model.addAttribute("error", "Profile changes could not be saved: " + e.getMessage());
		}

		return "profile";
	}

	@RequestMapping(value = "/profile/delete/{id}")
	public String deletePlayer(Model model, @PathVariable Long id,
			RedirectAttributes redirectAttributes) {
		try {
			authService.deletePlayer(id);
			redirectAttributes.addFlashAttribute("success", "Successfully deleted account.");
			return "redirect:/logout";
		} catch (Exception e) {
			model.addAttribute("error", "Could not delete account.");
			
			return "profile";
		}
	}

}
