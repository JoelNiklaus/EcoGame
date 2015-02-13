package ch.joelniklaus.ecogame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends ParentController {

	@RequestMapping(value = { "", "/" })
	public String redirect() {
		return "index";
	}
	
	@RequestMapping(value = "index")
	public void index(Model model) {
	}
	
	/**
	 * Shows a 404 error page.
	 *
	 * @return
	 */
	@RequestMapping(value = "notFound")
	public void notFound(Model model) {
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
