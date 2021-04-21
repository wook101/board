package com.example.freeboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfoDto {
	private int id;
	private String originalFileName;
	private String contentType;
	private String size;
	private String hashCode;
}
