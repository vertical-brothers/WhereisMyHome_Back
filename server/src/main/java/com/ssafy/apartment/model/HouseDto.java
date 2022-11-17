package com.ssafy.apartment.model;

/*
 * myhome DB - houseinfo (아파트정보 테이블)
 * 전용 DTO
 * 
 * 아파트 코드 - PK
 * 건축년도
 * 도로명
 * 도로명 본번호코드
 * 동이름
 * 동코드
 * 아파트명
 * 위도
 * 경도
 * 
 * 22.11.17 장한결
 * */

public class HouseDto {
	String aptCode;
	String buildYear;
	String roadName;
	String roadNameBonbun;
	String dong;
	String dongCode;
	String apartmentName;
	String lng;
	String lat;
	public HouseDto(String aptCode, String buildYear, String roadName, String roadNameBonbun, String dong,
			String dongCode, String apartmentName, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.dong = dong;
		this.dongCode = dongCode;
		this.apartmentName = apartmentName;
		this.lng = lng;
		this.lat = lat;
	}
	public HouseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}
	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
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
	@Override
	public String toString() {
		return "HouseDto [aptCode=" + aptCode + ", buildYear=" + buildYear + ", roadName=" + roadName
				+ ", roadNameBonbun=" + roadNameBonbun + ", dong=" + dong + ", dongCode=" + dongCode
				+ ", apartmentName=" + apartmentName + ", lng=" + lng + ", lat=" + lat + "]";
	}
}
