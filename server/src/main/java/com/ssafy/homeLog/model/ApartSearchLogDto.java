package com.ssafy.homeLog.model;

public class ApartSearchLogDto {
	String log_id;
	String log_date;
	String category;
	String keyword;
	public ApartSearchLogDto(String log_id, String log_date, String category, String keyword) {
		super();
		this.log_id = log_id;
		this.log_date = log_date;
		this.category = category;
		this.keyword = keyword;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getLog_date() {
		return log_date;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "ApartSearchLogDto [log_id=" + log_id + ", log_date=" + log_date + ", category=" + category
				+ ", keyword=" + keyword + "]";
	}
	
	
}
