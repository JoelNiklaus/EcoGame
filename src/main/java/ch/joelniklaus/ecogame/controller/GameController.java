package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.controller.service.GameService;
import ch.joelniklaus.ecogame.model.dao.GameDao;

@Controller
@RequestMapping(value = "/game")
public class GameController extends ParentController {
	
	@Autowired
	GameService gameService;
	@Autowired
	GameDao gameDao;

	@RequestMapping(value = "start")
	public String start(Model model) {
		return "game/start";
	}
	
	@RequestMapping(value = "/host")
	public String host(Model model) {
		if (gameService.loggedInUserHasAlreadyHostedGame())
			return "redirect:edit";
		
		model.addAttribute("gameForm", new GameForm());
		return "game/host";
	}

	@RequestMapping(value = "/host", method = RequestMethod.POST)
	public String host(Model model, @Valid GameForm gameForm, BindingResult result) {
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
	public String edit(Model model) {
		if (!gameService.loggedInUserHasAlreadyHostedGame())
			return "redirect:host";
		
		model.addAttribute("gameForm", gameService.getGameFormOfLoggedInUser());
		model.addAttribute("players", gameService.getPlayersOfGameOfLoggedInUser());

		return "game/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Model model, @Valid GameForm gameForm, BindingResult result) {
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

	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable Long id) {
		try {
			gameService.kickPlayer(id);
			model.addAttribute("success", "Successfully kicked player.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not kick player.");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		return edit(model);
	}

	@RequestMapping(value = "/join")
	public String join(Model model) {
		model.addAttribute("games", gameDao.findAll());

		return "game/join";
	}
	
	@RequestMapping(value = "/join/{id}")
	public String join(Model model, @PathVariable Long id) {
		try {
			gameService.joinGame(id);
			model.addAttribute("success", "Successfully joined game.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not join game.");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		return "game/join";
	}
	
	@RequestMapping(value = "/play")
	public String play(Model model) {
		model.addAttribute("games", gameDao.findAll());

		return "game/play";
	}

}
