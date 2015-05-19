package ch.joelniklaus.ecogame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.controller.service.GameService;
import ch.joelniklaus.ecogame.controller.service.PlayerService;
import ch.joelniklaus.ecogame.model.dao.GameDao;

@Controller
@RequestMapping(value = "/game")
public class GameController extends ParentController {

	@Autowired
	GameService gameService;
	@Autowired
	GameDao gameDao;
	@Autowired
	PlayerService playerService;
	
	@RequestMapping(value = "/start")
	public String start(Model model) {
		if (gameService.loggedInPlayerHasAlreadyHostedGame()
				|| gameService.loggedInPlayerHasAlreadyJoinedGame())
			return "redirect:play";
		return "game/start";
	}

	@RequestMapping(value = "/host")
	public String host(Model model) {
		if (gameService.loggedInPlayerHasAlreadyHostedGame())
			return "redirect:edit";

		model.addAttribute("gameForm", new GameForm());
		return "game/host";
	}
	
	@RequestMapping(value = "/host", method = RequestMethod.POST)
	public String host(Model model, @Valid GameForm gameForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return "game/host";
		
		try {
			gameService.addGame(gameForm);
			redirectAttributes.addFlashAttribute("success", "Game successfully created.");
			return "redirect:edit";
		} catch (Exception e) {
			model.addAttribute("error", "Could not create Game.");
		}
		return "game/host";
	}

	@RequestMapping(value = "/edit")
	public String edit(Model model) {
		if (!gameService.loggedInPlayerHasAlreadyHostedGame())
			return "redirect:host";

		model.addAttribute("gameForm", gameService.getGameFormOfLoggedInUser());
		model.addAttribute("game", gameService.getGameOfLoggedInPlayer());
		
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
	
	@RequestMapping(value = "/edit/kickPlayer/{id}")
	public String kickPlayer(Model model, @PathVariable Long id) {
		try {
			gameService.kickPlayer(id);
			model.addAttribute("success", "Successfully kicked player.");
		} catch (Exception e) {
			model.addAttribute("error", "Could not kick player.");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		model.addAttribute("gameForm", gameService.getGameFormOfLoggedInUser());
		model.addAttribute("game", gameService.getGameOfLoggedInPlayer());

		return "game/edit";
	}
	
	@RequestMapping(value = "/edit/delete/{id}")
	public String deleteGame(Model model, @PathVariable Long id,
			RedirectAttributes redirectAttributes) {
		try {
			gameService.deleteGame(id);
			redirectAttributes.addFlashAttribute("success", "Successfully deleted game.");
			return "redirect:/game/start";
		} catch (Exception e) {
			model.addAttribute("error", "Could not delete game.");
			model.addAttribute("gameForm", gameService.getGameFormOfLoggedInUser());
			model.addAttribute("game", gameService.getGameOfLoggedInPlayer());

			return "game/edit";
		}
	}
	
	@RequestMapping(value = "/join")
	public String join(Model model) {
		model.addAttribute("games", gameService.getJoinableGames());
		
		return "game/join";
	}

	@RequestMapping(value = "/join/{id}")
	public String join(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			gameService.joinGame(id);
			redirectAttributes.addFlashAttribute("success", "Successfully joined game.");
			return "redirect:/game/play";
		} catch (Exception e) {
			model.addAttribute("error", "Could not join game.");
			return "game/join";
		}
	}

	@RequestMapping(value = "/play")
	public String play(Model model, RedirectAttributes redirectAttributes) {
		if (!gameService.loggedInPlayerHasAlreadyHostedGame()
				&& !gameService.loggedInPlayerHasAlreadyJoinedGame()) {
			System.out.println("test");
			return "redirect:/game/start";
		}

		model.addAttribute("game", gameService.getGameOfLoggedInPlayer());
		
		return "game/play";
	}
	
}
