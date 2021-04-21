package com.example.freeboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DetailInfoDto {
	private int id;
	private int user_id;
	private String nickName;
	private String title;
	private String content;
	private Date create_date;
	private int views;
	private Integer file_hashCode;
}
