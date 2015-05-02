package ch.joelniklaus.ecogame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController extends ParentController {
	
	@RequestMapping(value = { "", "/", "index" })
	public String index() {
		return "redirect:game/start";
	}

	// @RequestMapping(value = "index")
	// public String index(Model model) {
	// return "redirect:game/start";
	// }

	/**
	 * Shows a 404 error page.
	 *
	 * @return
	 */
	@RequestMapping(value = "notFound")
	public String notFound(Model model) {
		return "notFound";
	}

	/**
	 *
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/security-error", method = RequestMethod.GET)
	public String securityError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("page_error",
				"You do have have permission to do that!");

		return "redirect:/";
	}
}
