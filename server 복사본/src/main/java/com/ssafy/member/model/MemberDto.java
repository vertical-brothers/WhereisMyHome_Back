package com.ssafy.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto : 회원 정보.", description = "회원의 세정보 표시.")
public class MemberDto {

	@ApiModelProperty(value = "회원 아이디")
	private String userId;
	@ApiModelProperty(value = "회원 이름")
	private String userName;
	@ApiModelProperty(value = "회원 비밀번호")
	private String userPwd;
	@ApiModelProperty(value = "회원 이메일")
	private String userEmail;
	@ApiModelProperty(value = "회원 전화번호")
	private String userPhone;
	@ApiModelProperty(value = "회원 권한")
	private String userRole;
	@ApiModelProperty(value = "회원 탈퇴여부")
	private String userDel;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserDel() {
		return userDel;
	}
	public void setUserDel(String userDel) {
		this.userDel = userDel;
	}
	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userEmail="
				+ userEmail + ", userPhone=" + userPhone + ", userRole=" + userRole + ", userDel=" + userDel + "]";
	}
	

	

}
