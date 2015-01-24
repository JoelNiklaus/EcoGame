package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.SignupForm;
import ch.joelniklaus.ecogame.controller.service.LoginService;
import ch.joelniklaus.ecogame.controller.service.UpdateService;
import ch.joelniklaus.ecogame.model.Ad;
import ch.joelniklaus.ecogame.model.User;

@Controller
public class ProfileController { 
	
    @Autowired LoginService loginService;
    @Autowired UpdateService updateService;
	
    /**
     * Displays the profile view of the user with the given id.
     * 
     * @return
     */
	@RequestMapping(value="/otherProfileView", method = RequestMethod.GET)
	public ModelAndView loadOtherProfileView(@RequestParam String id) {
		ModelAndView model = new ModelAndView("otherProfileView");
		
		try{
			User otherUser = loginService.getUser(new Long(id));

			if(otherUser != null){
				model.addObject("otherUser", otherUser);
			} else {  	
				model = new ModelAndView("404");
			}	 
		}catch(NumberFormatException ex){
			model = new ModelAndView("404");
		}	
		
		User loggedInUser = loginService.getLoggedInUser();
		model.addObject("loggedInUser", loggedInUser);
		updateService.updateNumberOfUnreadItems(model);
		
		return model;
	}
    
    /**
     * Displays the profileForm which enables the user to change his profile information.
     * 
     * @return
     */
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public ModelAndView loadProfilePage()
	{
		ModelAndView model = new ModelAndView("profile");
		model.addObject("profileForm", new SignupForm());
		
		User loggedInUser = loginService.getLoggedInUser();
		
		model.addObject("loggedInUser", loggedInUser);
		updateService.updateNumberOfUnreadItems(model);
		
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
    public ModelAndView profileChange(@Valid SignupForm profileForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
    	model = loadProfilePage();
    	if (!result.hasErrors()) {
	    	try {
	    		//TODO does not work properly yet: NumberFormatException: null
	    		// save to DB
	    		loginService.updateProfile(profileForm);
				model.addObject("success", "Profile changes successfully saved");
			} catch (InvalidUserException e) {
				model.addObject("error", "Profile changes could not be saved: " + e.getMessage());
			} catch (NullPointerException e) {
				model.addObject("error", "Profile changes could not be saved: " + e.getMessage());
			}
    	} else
    		model.addObject("error", "Please enter valid data.");
    	
    	return model;
    }
}
