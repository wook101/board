package com.example.freeboard.dto;

import java.util.Date;

public class BoardListDto {
	private int id;
	private String nickName;
	private String title;
	private Date create_date;
	private int views;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public String toString() {
		return "ListInfo [id=" + id + ", nickName=" + nickName + ", title=" + title + ", create_date=" + create_date
				+ ", views=" + views + "]";
	}
	
	
}
