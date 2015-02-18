package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;

@Controller
public class ProfileController extends ParentController {

	/**
	 * Displays the profile view of the user with the given id.
	 *
	 * @return
	 */
	@RequestMapping(value = "/otherProfileView/{id}", method = RequestMethod.GET)
	public String otherProfileView(Model model, @PathVariable Long id) {
		model.addAttribute("otherUser", authService.getUser(id));

		// try {
		//
		//
		// if (otherUser != null)
		// model.addAttribute("otherUser", otherUser);
		// else
		// return "notFound";
		// } catch (NumberFormatException e) {
		// System.out.println(e.getMessage());
		// return "notFound";
		// }
		
		return "otherProfileView";
	}

	/**
	 * Displays the profileForm which enables the user to change his profile information.
	 *
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model) {
		model.addAttribute("profileForm", new SignupForm(authService.getLoggedInUser()));
		
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
			@ModelAttribute("profileForm") @Valid SignupForm profileForm, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("error", "Please enter valid data.");
			model.addAttribute("profileForm", profileForm);
			return "profile";
		}

		try {
			authService.updateProfile(profileForm);
			model.addAttribute("success", "Profile changes successfully saved");
		} catch (InvalidUserException e) {
			model.addAttribute("error", "Profile changes could not be saved: " + e.getMessage());
		} catch (NullPointerException e) {
			model.addAttribute("error", "Profile changes could not be saved: " + e.getMessage());
		}
		
		return "profile";
	}
}
