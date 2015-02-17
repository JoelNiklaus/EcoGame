package ch.joelniklaus.ecogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.joelniklaus.ecogame.controller.service.GameService;

@Controller
public class IndexController extends ParentController {
	
	@Autowired
	GameService gameService;

	@RequestMapping(value = { "", "/" })
	public String redirect() {
		return "redirect:index";
	}
	
	@RequestMapping(value = "index")
	public String index(Model model) {
		if (gameService.loggedInUserHasAlreadyHostedGame()
				|| gameService.loggedInUserHasAlreadyJoinedGame())
			return "redirect:game/play";
		return "redirect:game/start";
	}
	
	/**
	 * Shows a 404 error page.
	 *
	 * @return
	 */
	@RequestMapping(value = "notFound")
	public String notFound(Model model) {
		return "notFound";
	}

	// /**
	// *
	// * @param redirectAttributes
	// * @return
	// */
	// @RequestMapping(value = "/security-error", method = RequestMethod.GET)
	// public String securityError(RedirectAttributes redirectAttributes) {
	// redirectAttributes.addFlashAttribute("page_error",
	// "You do have have permission to do that!");
	//
	// return "redirect:/";
	// }
}
