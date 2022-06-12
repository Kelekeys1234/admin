package com.admin.admin.vo;

public class Email {

	String email ;
	String text ;
	String subject ;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Email() {
		super();
	}
	public Email(String email, String text, String subject) {
		super();
		this.email = email;
		this.text = text;
		this.subject = subject;
	}
	
	
}
