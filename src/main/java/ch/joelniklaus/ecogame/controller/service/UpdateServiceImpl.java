package ch.joelniklaus.ecogame.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import ch.joelniklaus.ecogame.model.Enquiry;
import ch.joelniklaus.ecogame.model.Notification;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.EnquiryDao;
import ch.joelniklaus.ecogame.model.dao.NotificationDao;


@Service
public class UpdateServiceImpl implements UpdateService {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	EnquiryDao enquiryDao;
	
	@Autowired
	NotificationDao notificationDao;
	
	public void updateNumberOfUnreadItems(ModelAndView model) {
		User user = loginService.getLoggedInUser();
		
		if(user != null)
		{
			Iterable<Enquiry> allEnquiries = enquiryDao.findAllByReceiverId(user.getId());
			long numOfUnreadE = 0;
				
			for(Enquiry e : allEnquiries)
				if(e.isUnread())
					numOfUnreadE++;
				
			model.addObject("numUnreadEnquiries", numOfUnreadE);
			
			Iterable<Notification> allNotifications = notificationDao.findAllByUserId(user.getId());
			long numOfUnreadN = 0;
				
			for(Notification e : allNotifications)
				if(e.isUnread())
					numOfUnreadN++;
				
			model.addObject("numUnreadNotifications", numOfUnreadN);
			
		}
		else
		{
			model.addObject("numUnreadEnquiries", 0);
			model.addObject("numUnreadNotifications", 0);
		}
		
	}
}
