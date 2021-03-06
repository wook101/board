package com.example.freeboard.dto;

import java.util.Date;

public class DetailInfoDto {
	private int id;
	private int user_id;
	private String nickName;
	private String title;
	private String content;
	private Date create_date;
	private int views;
	private Integer file_hashCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Integer getFile_hashCode() {
		return file_hashCode;
	}
	public void setFile_hashCode(Integer file_hashCode) {
		this.file_hashCode = file_hashCode;
	}
	@Override
	public String toString() {
		return "DetailInfoVo [id=" + id + ", user_id=" + user_id + ", nickName=" + nickName + ", title=" + title
				+ ", content=" + content + ", create_date=" + create_date + ", views=" + views + ", file_hashCode="
				+ file_hashCode + "]";
	}
	


	
}
