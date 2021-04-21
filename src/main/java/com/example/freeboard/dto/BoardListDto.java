package com.example.freeboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardListDto {
	private int id;
	private String nickName;
	private String title;
	private Date create_date;
	private int views;
}
