package ch.joelniklaus.ecogame.controller.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidAdException;
import ch.joelniklaus.ecogame.controller.pojos.EnquiryForm;
import ch.joelniklaus.ecogame.controller.pojos.EnquiryRatingForm;
import ch.joelniklaus.ecogame.model.Ad;
import ch.joelniklaus.ecogame.model.Enquiry;
import ch.joelniklaus.ecogame.model.EnquiryComparator;
import ch.joelniklaus.ecogame.model.EnquiryComparatorRating;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.AdDao;
import ch.joelniklaus.ecogame.model.dao.EnquiryDao;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired LoginService loginService;
	@Autowired AdDao adDao;
	@Autowired EnquiryDao enquiryDao;


	@Transactional 
	public EnquiryForm submit(EnquiryForm enquiryForm) {
		Enquiry enquiry = new Enquiry();
		
		Ad ad = adDao.findOne(enquiryForm.getAdId());
		User sender = loginService.getLoggedInUser();

		Set<Enquiry> enquiries = ad.getEnquiries();
		
		/* fetch ad from DB, according to submitted Ad-ID. Throw exception if no ad found */
		enquiryForm.setAd(ad);
		if(enquiryForm.getAd() == null)
			throw new InvalidAdException("This ad doesn't exist");
			
		/* Update enquiry FORM: set sender to logged in user, receiver to ad placer ID */
		enquiryForm.setTimestamp(new Timestamp(System.currentTimeMillis()));
		enquiryForm.setSenderId(loginService.getLoggedInUser().getId());
		enquiryForm.setReceiverId(enquiryForm.getAd().getPlacerId());
		
		/* Fill in enquiry from completed enquiry form */
		enquiry.setTimestamp(enquiryForm.getTimestamp());
		enquiry.setAdId(enquiryForm.getAdId());
		enquiry.setSender(sender);
		enquiry.setSenderId(enquiryForm.getSenderId());
		enquiry.setReceiverId(enquiryForm.getReceiverId());
		enquiry.setMessageText(enquiryForm.getMessageText());
		enquiry.setUnread(true);
		

		enquiryDao.save(enquiry);
		enquiries.add(enquiry);
		ad.setEnquiries(enquiries);
		adDao.save(ad);
		
		return enquiryForm;
	}

	
	
	@Transactional
	public Iterable<Enquiry> findSentEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getSenderId().equals(loginService.getLoggedInUser().getId()))
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
				
		}
		
		Collections.sort(results, new EnquiryComparator());
		
		return (Iterable<Enquiry>)results;
	}

	
	
	@Transactional
	public Iterable<Enquiry> findNewReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = findUnratedEnquiriesByUser();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(!e.isUnread())
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		
		return (Iterable<Enquiry>)results;
	}
	
	@Transactional
	public Iterable<Enquiry> findRatedReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if((e.getReceiverId().equals(loginService.getLoggedInUser().getId())) && e.getRating() != 0)
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		
		return (Iterable<Enquiry>)results;
	}



	public EnquiryRatingForm submitRating(EnquiryRatingForm form) {
		
		Enquiry enquiry = enquiryDao.findOne(form.getEnquiryId());
		form.setEnquiry(enquiry);
		
		if(enquiry == null)
				System.out.println("Service: no enquiry added!");
		else {
			enquiry.setRating(form.getRating());
			enquiry.setEnquiryRatingComment(form.getEnquiryRatingComment());
			enquiryDao.save(enquiry);
		}
		
		return form;
	}
    
	@Transactional
	public Enquiry removeEnquiry(Enquiry enquiry) {
		Ad ad = adDao.findOne(enquiry.getAdId());
		try{
			Set<Enquiry> enquiries = ad.getEnquiries();
			
			// The set is sometimes to slow in population. This loop uses the lower level iterator
			for (Iterator<Enquiry> it = enquiries.iterator(); it.hasNext();) {
			    Enquiry tmpEnquiry = it.next();
			    
				if(tmpEnquiry.getEnquiryId().equals(enquiry.getEnquiryId()))
					enquiries.remove(tmpEnquiry);
			}

			ad.setEnquiries(enquiries);
			adDao.save(ad);
			enquiryDao.delete(enquiry);
		} catch(Exception e) {
			System.out.println(e);
		}
		return enquiry;
	}


	@Transactional
	public Iterable<Enquiry> findUnreadEnquiries() {
		Iterable<Enquiry> allEnquiries = findUnratedEnquiriesByUser();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.isUnread())
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		markEnquiriesAsRead(results);
		
		return (Iterable<Enquiry>)results;
	}
	
	private Iterable<Enquiry> findUnratedEnquiriesByUser()
	{
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if((e.getReceiverId().equals(loginService.getLoggedInUser().getId())) && e.getRating() == 0)
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		
		return (Iterable<Enquiry>)results;
	}
	
	@Transactional
	private void markEnquiriesAsRead(Iterable<Enquiry> enquiries)
	{
		for(Enquiry e : enquiries)
		{
			e.setUnread(false);
			enquiryDao.save(e);
		}	
	}



	public Enquiry getEnquiryById(Long id) {
		
		return enquiryDao.findOne(id);
	}



	public Enquiry save(Enquiry enquiry) {
		return enquiryDao.save(enquiry);
	}

}
