package com.ssafy.homeLog.model;

public class ApartSearchLogResultDto {
	String keyword;
	String category;
	String searchCount;
	public ApartSearchLogResultDto(String keyword, String category, String searchCount) {
		super();
		this.keyword = keyword;
		this.category = category;
		this.searchCount = searchCount;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(String searchCount) {
		this.searchCount = searchCount;
	}
	
	
	
	@Override
	public String toString() {
		return "ApartSearchLogResultDto [keyword=" + keyword + ", category=" + category + ", searchCount=" + searchCount
				+ "]";
	}
}
