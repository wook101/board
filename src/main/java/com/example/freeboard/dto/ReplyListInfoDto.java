package com.example.freeboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReplyListInfoDto {
	private int id;
	private int user_id;
	private String nickName;
	private String comment;
	private Date create_date;
}
