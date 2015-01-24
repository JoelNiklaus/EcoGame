package ch.joelniklaus.ecogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.joelniklaus.ecogame.controller.service.LoginService;
import ch.joelniklaus.ecogame.controller.service.NotificationService;
import ch.joelniklaus.ecogame.controller.service.UpdateService;
import ch.joelniklaus.ecogame.model.Notification;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.AdDao;
import ch.joelniklaus.ecogame.model.dao.NotificationDao;

@Controller
public class NotificationController {
	@Autowired LoginService loginService;
	@Autowired NotificationService notificationService;
	@Autowired AdDao adDao;
	@Autowired NotificationDao notificationDao;
	@Autowired UpdateService updateService;
	@Autowired AdController adController;

	/**
	 * Creates a model displaying user's notifications
	 * 
	 * @return notification model
	 */
	@RequestMapping("/notifications")
	public ModelAndView showNotifications() {
		ModelAndView model = new ModelAndView("notifications");

		User user = loginService.getLoggedInUser();
		
		model.addObject("notifications", notificationService.findNotifications(user));
		model.addObject("unreadNotifications", notificationService.findUnreadNotifications());
		
		notificationService.markAllNotificationsAsRead(user);
		model.addObject("loggedInUser", user);
		updateService.updateNumberOfUnreadItems(model);
		return model;
	}

	/**
	 * Removes notification with given id from DB, returns notification model
	 * 
	 * @param id	id of notification to be deleted
	 * @return		notification model, 404 if id invalid
	 */
	@RequestMapping(value = "/removeNotification", method = RequestMethod.GET)
	public ModelAndView removenotification(@RequestParam String id) {
		ModelAndView model = showNotifications();

		try {
			long notificationId = Long.parseLong(id);
			Notification notification = notificationDao.findOne(notificationId);

			User user = loginService.getLoggedInUser();
			updateService.updateNumberOfUnreadItems(model);
			notificationService.removeNotification(notification);
			model = showNotifications();
			model.addObject("message", "notification successfully removed.");
			model.addObject("notifications", notificationService.findNotifications(user)); 
		} catch(NumberFormatException ex){
			model = new ModelAndView("404");
		} catch(IllegalArgumentException e) {
			model = new ModelAndView("404");
		}
		return model;
	}

	/**
	 * 
	 * 
	 * @param adId
	 * @param notificationId
	 * @return
	 */
	@RequestMapping(value = "/openNotification", method = RequestMethod.GET)
	public ModelAndView openNotification(@RequestParam String adId, @RequestParam String notificationId)
	{
		ModelAndView model = adController.displaySingleAd(adId);
		Long notifId;
		
		try
		{
			notifId = Long.parseLong(notificationId);
			Notification notification = notificationDao.findOne(notifId);
			
			if(notification != null)
				notificationDao.delete(notification);
			else
				throw new IllegalArgumentException();
			
		}
		catch(NumberFormatException ex)
		{
			model = new ModelAndView("404");
		}
		catch(IllegalArgumentException ex)
		{
			model = new ModelAndView("404");
		}
		
		return model;
	}
	
	/**
	 * Deletes all notifications of the logged in user.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clearNotifications")
	public ModelAndView clearNotifications()
	{
		ModelAndView model;
		
		Iterable<Notification> allNotificationsByUser = notificationDao.findAllByUserId(loginService.getLoggedInUser().getId());
		for(Notification n : allNotificationsByUser)
			notificationDao.delete(n);
			
		model = showNotifications();
		
		return model;
	}
	
	
}