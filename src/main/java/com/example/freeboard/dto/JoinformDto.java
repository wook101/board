package com.example.freeboard.dto;

public class JoinformDto {
	private String userID;
	private String nickName;
	private String password;
	private String email;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "JoinformVo [userID=" + userID + ", nickName=" + nickName + ", password=" + password + ", email=" + email
				+ "]";
	}

	
	
}
