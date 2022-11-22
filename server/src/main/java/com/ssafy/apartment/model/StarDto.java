package com.ssafy.apartment.model;

public class StarDto {

	private int starNo;	
	private String userId;
	private String aptCode;
	private String roadName;	
	private String dong;
	private String apartmentName;
	private String lng;
	private String lat;
	
	public StarDto(int starNo, String userId, String aptCode, String roadName, String dong, String apartmentName,
			String lng, String lat) {
		super();
		this.starNo = starNo;
		this.userId = userId;
		this.aptCode = aptCode;
		this.roadName = roadName;
		this.dong = dong;
		this.apartmentName = apartmentName;
		this.lng = lng;
		this.lat = lat;
	}
	
	public int getStarNo() {
		return starNo;
	}

	public void setStarNo(int starNo) {
		this.starNo = starNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public StarDto() {
	}

	@Override
	public String toString() {
		return "StarDto [starNo=" + starNo + ", userId=" + userId + ", aptCode=" + aptCode + ", roadName=" + roadName
				+ ", dong=" + dong + ", apartmentName=" + apartmentName + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
	
}
