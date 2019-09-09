package com.example.freeboard.dto;

import java.util.Date;

public class ReplyListInfoVo {
	private int id;
	private String nickName;
	private String comment;
	private Date create_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "ReplyListInfoVo [id=" + id + ", nickName=" + nickName + ", comment=" + comment + ", create_date="
				+ create_date + "]";
	}
	
	
}