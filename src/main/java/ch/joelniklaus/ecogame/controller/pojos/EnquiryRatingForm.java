package ch.joelniklaus.ecogame.controller.pojos;

import ch.joelniklaus.ecogame.model.Enquiry;

public class EnquiryRatingForm {

	private int rating;
	private Enquiry enquiry;
	private long enquiryId;
	
	private String enquiryRatingComment;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Enquiry getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}

	public long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(long enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getEnquiryRatingComment() {
		return enquiryRatingComment;
	}

	public void setEnquiryRatingComment(String enquiryRatingComment) {
		this.enquiryRatingComment = enquiryRatingComment;
	}
	
}
