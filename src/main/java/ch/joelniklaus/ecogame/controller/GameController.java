package ch.joelniklaus.ecogame.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.joelniklaus.ecogame.controller.pojos.BudgetForm;
import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.controller.service.ChartService;
import ch.joelniklaus.ecogame.controller.service.GameService;
import ch.joelniklaus.ecogame.controller.service.PlayerService;
import ch.joelniklaus.ecogame.model.Company;
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
	@Autowired
	ChartService chartService;

	@RequestMapping(value = "/start")
	public String start(Model model) {
		if (gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:play";
		return "game/start";
	}
	
	@RequestMapping(value = "/create")
	public String create(Model model) {
		if (gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:edit";
		
		model.addAttribute("gameForm", new GameForm());
		return "game/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, @Valid GameForm gameForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return "game/create";

		try {
			gameService.createGame(gameForm);
			redirectAttributes.addFlashAttribute("success", "Game successfully created.");
			return "redirect:edit";
		} catch (Exception e) {
			model.addAttribute("error", "Could not create Game.");
		}
		return "game/create";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model) {
		if (!gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:start";
		
		model.addAttribute("gameForm", gameService.getGameFormOfLoggedInPlayer());
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
		
		model.addAttribute("gameForm", gameService.getGameFormOfLoggedInPlayer());
		model.addAttribute("game", gameService.getGameOfLoggedInPlayer());
		
		return "game/edit";
	}

	@RequestMapping(value = "/edit/leaveGame")
	public String leaveGame(Model model, RedirectAttributes redirectAttributes) {
		try {
			gameService.leaveGame();
			redirectAttributes.addFlashAttribute("success", "Successfully left game.");
			return "redirect:/game/start";
		} catch (Exception e) {
			model.addAttribute("error", "Could not leave game.");
			model.addAttribute("gameForm", gameService.getGameFormOfLoggedInPlayer());
			model.addAttribute("game", gameService.getGameOfLoggedInPlayer());
			return "game/edit";
		}
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
			model.addAttribute("gameForm", gameService.getGameFormOfLoggedInPlayer());
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
			e.printStackTrace();
			return "game/join";
		}
	}
	
	@RequestMapping(value = "/play")
	public String play(Model model) {
		if (!gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:/game/start";
		
		model.addAttribute("game", gameService.getGameOfLoggedInPlayer());

		return "game/play";
	}
	
	@RequestMapping(value = "/budget")
	public String budget(Model model) {
		if (!gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:/game/start";

		int currentYear = gameService.getGameOfLoggedInPlayer().getYear();
		Company company = authService.getLoggedInPlayer().getCompany();

		if (currentYear == 0)
			model.addAttribute("budgetForm", new BudgetForm());
		else if (company.budgetSubmitted(currentYear)) {
			model.addAttribute("budgetForm", new BudgetForm(company.getBudget(currentYear)));
			model.addAttribute("info",
					"You already submitted your budget for this year, but you can update it if you want.");
		} else {
			model.addAttribute("budgetForm", new BudgetForm(company.getBudget(currentYear - 1)));
			model.addAttribute("info",
					"Here you see your budget from the previous year. Feel free to change whatever you like. ;)");
		}
		return "game/budget";
	}

	@RequestMapping(value = "/budget", method = RequestMethod.POST)
	public String budget(Model model, @Valid BudgetForm budgetForm, BindingResult result) {
		if (!gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:/game/start";
		
		if (result.hasErrors())
			return "game/budget";
		
		try {
			gameService.saveBudget(budgetForm);
			model.addAttribute("success", "Budget successfully saved.");

			if (gameService.allBudgetsSubmitted()) {
				gameService.passYear();
				
				model.addAttribute("info",
						"All players have submitted their budgets. You can now see the results of the past year.");
			} else
				model.addAttribute(
						"info",
						"Some players have not submitted their budgets yet. Please wait for them to submit their budgets in order to see the results of the past year.");

		} catch (Exception e) {
			model.addAttribute("error", "Could not save changes.");
			e.printStackTrace();
		}

		return "game/budget";
	}
	
	@RequestMapping(value = "/results")
	public String results(Model model, RedirectAttributes redirectAttributes) {
		if (!gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:/game/start";

		List<List<Object>> data = gameService.getGameOfLoggedInPlayer().getData();
		String json = chartService.buildJsonArray(gameService.getGameOfLoggedInPlayer()
				.getPlayers(), data);
		model.addAttribute("json", json);
		
		model.addAttribute("title", "Company Performance");

		return "game/results";
	}

	@RequestMapping(value = "/conjuncture")
	public String conjuncture(Model model, RedirectAttributes redirectAttributes) {
		if (!gameService.loggedInPlayerIsAlreadyPartOfGame())
			return "redirect:/game/start";
		
		model.addAttribute(
				"conjuncture",
				gameService.getGameOfLoggedInPlayer().getConjuncture(
						gameService.getGameOfLoggedInPlayer().getYear()));

		return "game/conjuncture";
	}

}
