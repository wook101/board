package com.example.freeboard.dto;

public class FileInfoVo {
	private int id;
	private String originalFileName;
	private String contentType;
	private String size;
	private String hashCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	@Override
	public String toString() {
		return "FileInfoVo [id=" + id + ", originalFileName=" + originalFileName + ", contentType=" + contentType
				+ ", size=" + size + ", hashCode=" + hashCode + "]";
	}
	
	
}
