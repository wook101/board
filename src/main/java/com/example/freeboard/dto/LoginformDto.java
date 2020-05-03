package com.example.freeboard.dto;

public class LoginformDto {

	private String userID;
	private String password;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginformVo [userID=" + userID + ", password=" + password + "]";
	}
	
	
	
}
