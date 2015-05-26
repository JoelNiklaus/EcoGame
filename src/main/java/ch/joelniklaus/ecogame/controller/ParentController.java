package ch.joelniklaus.ecogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import ch.joelniklaus.ecogame.controller.service.AuthenticationService;
import ch.joelniklaus.ecogame.model.Player;

/**
 * Has to be extended by every Controller.
 */
@Controller
public class ParentController {
	
	@Autowired
	AuthenticationService authService;
	
	/**
	 * Adds the loggedInPlayer as a model attribute before the view is rendered.
	 */
	@ModelAttribute("loggedInPlayer")
	public Player getLoggedInPlayer() {
		return authService.getLoggedInPlayer();
	}
}
