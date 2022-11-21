package com.ssafy.apartment.model;

public class StarDto2 {

	private int starNo;
	private String dongCode;
	private String dealYM;
	private String userId;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public int getStarNo() {
		return starNo;
	}
	public void setStarNo(int starNo) {
		this.starNo = starNo;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDealYM() {
		return dealYM;
	}
	public void setDealYM(String dealYM) {
		this.dealYM = dealYM;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "StarDto [starNo=" + starNo + ", dongCode=" + dongCode + ", dealYM=" + dealYM + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dongName=" + dongName + ", userId=" + userId + "]";
	}
}
