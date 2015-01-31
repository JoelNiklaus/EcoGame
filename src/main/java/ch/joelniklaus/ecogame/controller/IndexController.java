package ch.joelniklaus.ecogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.joelniklaus.ecogame.controller.service.LoginService;
import ch.joelniklaus.ecogame.controller.service.UpdateService;

@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	UpdateService updateService;
	
	@RequestMapping(value = { "", "/" })
	public String redirect() {
		return "redirect:index";
	}

	@RequestMapping(value = "index")
	public void index(Model model) {
		model.addAttribute("loggedInUser", loginService.getLoggedInUser());
		model.addAttribute("message", "blabla");
	}

	/**
	 * Shows a 404 error page.
	 *
	 * @return
	 */
	@RequestMapping(value = "notFound")
	public void notFound() {

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