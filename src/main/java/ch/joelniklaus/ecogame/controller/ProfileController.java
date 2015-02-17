package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String loadOtherProfileView(Model model, @PathVariable Long id) {
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
	public ModelAndView loadProfilePage() {
		ModelAndView model = new ModelAndView("profile");
		model.addObject("profileForm", new SignupForm());

		return model;
	}
	
	/**
	 * Saves the changes made to the profile to the database.
	 *
	 * @param profileForm
	 * @param result
	 * @param redirectAttributes
	 * @return the profileForm for further changes.
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView profileChange(@Valid SignupForm profileForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView model;
		model = loadProfilePage();
		if (!result.hasErrors())
			try {
				// TODO does not work properly yet: NumberFormatException: null
				// save to DB
				authService.updateProfile(profileForm);
				model.addObject("success", "Profile changes successfully saved");
			} catch (InvalidUserException e) {
				model.addObject("error", "Profile changes could not be saved: " + e.getMessage());
			} catch (NullPointerException e) {
				model.addObject("error", "Profile changes could not be saved: " + e.getMessage());
			}
		else
			model.addObject("error", "Please enter valid data.");
		
		return model;
	}
}
