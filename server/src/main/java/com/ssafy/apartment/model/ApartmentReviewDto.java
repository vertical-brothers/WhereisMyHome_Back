package com.ssafy.apartment.model;

public class ApartmentReviewDto {
	private String id;
	private String aptCode;
	private String userId;
	private String subject;
	private String content;
	private String date;
	private String star1;
	
	public ApartmentReviewDto(String id, String aptCode, String userId, String subject, String content, String date,
			String star1) {
		super();
		this.id = id;
		this.aptCode = aptCode;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.date = date;
		this.star1 = star1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStar1() {
		return star1;
	}

	public void setStar1(String star1) {
		this.star1 = star1;
	}

	@Override
	public String toString() {
		return "ApartmentReview [id=" + id + ", aptCode=" + aptCode + ", userId=" + userId + ", subject=" + subject
				+ ", content=" + content + ", date=" + date + ", star1=" + star1 + "]";
	}
	
	
}
