package com.api.model;

import java.util.List;

public class MailBodyContent {
	
	private String username;
	private List<String> mailData;
	private String message;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getMailData() {
		return mailData;
	}
	public void setMailData(List<String> mailData) {
		this.mailData = mailData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
