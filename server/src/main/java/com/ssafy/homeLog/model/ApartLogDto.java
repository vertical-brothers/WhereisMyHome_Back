package com.ssafy.homeLog.model;

public class ApartLogDto {
	private String aptCode;
	private String apartmentName;
	private int cnt;

	public ApartLogDto() {
	}

	public ApartLogDto(String aptCode, int cnt) {
		super();
		this.aptCode = aptCode;
	}

	public ApartLogDto(String aptCode, String apartmentName, int cnt) {
		super();
		this.aptCode = aptCode;
		this.apartmentName = apartmentName;
		this.cnt = cnt;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ApartLogDto [aptCode=" + aptCode + ", apartmentName=" + apartmentName + ", cnt=" + cnt + "]";
	}

	
	
}
