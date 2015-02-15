package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.controller.service.GameService;

@Controller
@RequestMapping(value = "/game")
public class GameController extends ParentController {
	
	@Autowired
	GameService gameService;
	
	@RequestMapping(value = "/hostGame")
	public String hostGame(Model model) {
		model.addAttribute("gameForm", new GameForm());

		return "hostGame";
	}

	@RequestMapping(value = "/hostGame", method = RequestMethod.POST)
	public String hostGame(Model model, @Valid GameForm gameForm, BindingResult result) {
		if (result.hasErrors())
			return "hostGame";
		
		try {
			gameService.addGame(gameForm);
			model.addAttribute("success", "Game successfully created.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not create Game.");
		}
		return "editGame";
	}
	
	@RequestMapping(value = "/editGame")
	public String editGame(Model model) {
		model.addAttribute("gameForm", new GameForm());

		return "editGame";
	}
	
	@RequestMapping(value = "/editGame", method = RequestMethod.POST)
	public String editGame(Model model, @Valid GameForm gameForm, BindingResult result) {
		if (result.hasErrors())
			return "editGame";
		
		try {
			gameService.editGame(gameForm);
			model.addAttribute("success", "Changes successfully saved.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not save changes.");
		}
		
		model.addAttribute("loggedInUser", authService.getLoggedInUser());
		
		return "editGame";
	}
	
}
