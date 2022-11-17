package com.ssafy.park.model;

public class ParkDto {
	private String parkName;
	private String gugunName;
	private String address;
	private String phoneNum;
	private String lng;
	private String lnt;
	private String distance;
	public ParkDto() {
	}
	public ParkDto(String parkName, String gugunName, String address, String phoneNum, String lng, String lnt,String distance) {
		super();
		this.parkName = parkName;
		this.gugunName = gugunName;
		this.address = address;
		this.phoneNum = phoneNum;
		this.lng = lng;
		this.lnt = lnt;
		this.distance = distance;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLnt() {
		return lnt;
	}
	public void setLnt(String lnt) {
		this.lnt = lnt;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
