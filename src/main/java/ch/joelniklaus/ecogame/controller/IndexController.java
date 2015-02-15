package ch.joelniklaus.ecogame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController extends ParentController {

	@RequestMapping(value = "/security-error", method = RequestMethod.GET)
	public String securityError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("page_error",
				"You do have have permission to do that!");

		return "redirect:/";
	}
}
