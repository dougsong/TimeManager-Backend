package com.songzheng.domain;

public class Note {
	
	private String nid;
	
	private String note_title;
	
	private String note_lastModifiedTime;
	
	private String note_content;
	
	private User user;

	public Note() {

	}

	public Note(String nid, String note_title, String note_lastModifiedTime, String note_content, User user) {

		this.nid = nid;
		this.note_title = note_title;
		this.note_lastModifiedTime = note_lastModifiedTime;
		this.note_content = note_content;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNote_title() {
		return note_title;
	}

	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}

	public String getNote_lastModifiedTime() {
		return note_lastModifiedTime;
	}

	public void setNote_lastModifiedTime(String note_lastModifiedTime) {
		this.note_lastModifiedTime = note_lastModifiedTime;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

}
