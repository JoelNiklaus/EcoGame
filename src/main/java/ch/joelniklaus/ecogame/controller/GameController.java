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

	@RequestMapping(value = "/host")
	public String hostGame(Model model) {
		model.addAttribute("gameForm", new GameForm());
		
		return "game/host";
	}
	
	@RequestMapping(value = "/host", method = RequestMethod.POST)
	public String hostGame(Model model, @Valid GameForm gameForm, BindingResult result) {
		if (result.hasErrors())
			return "game/host";

		try {
			gameService.addGame(gameForm);
			model.addAttribute("success", "Game successfully created.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not create Game.");
		}
		return "game/edit";
	}

	@RequestMapping(value = "/edit")
	public String editGame(Model model) {
		model.addAttribute("gameForm", new GameForm());
		
		return "game/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editGame(Model model, @Valid GameForm gameForm, BindingResult result) {
		if (result.hasErrors())
			return "game/edit";

		try {
			gameService.editGame(gameForm);
			model.addAttribute("success", "Changes successfully saved.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not save changes.");
		}
		return "game/edit";
	}
	
	@RequestMapping(value = "/join")
	public String joinGame(Model model) {
		model.addAttribute("gameForm", new GameForm());
		
		return "game/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinGame(Model model, @Valid GameForm gameForm, BindingResult result) {
		if (result.hasErrors())
			return "game/join";

		try {
			gameService.editGame(gameForm);
			model.addAttribute("success", "Changes successfully saved.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not save changes.");
		}

		return "game/join";
	}

}
