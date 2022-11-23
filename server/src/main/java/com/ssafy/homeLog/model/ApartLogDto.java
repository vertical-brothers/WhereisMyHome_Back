package com.ssafy.homeLog.model;

public class ApartLogDto {
	private String aptCode;
	private int cnt;

	public ApartLogDto() {
	}

	public ApartLogDto(String aptCode, int cnt) {
		super();
		this.aptCode = aptCode;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ApartLogDto [aptCode=" + aptCode + ", cnt=" + cnt + "]";
	}
	
}
