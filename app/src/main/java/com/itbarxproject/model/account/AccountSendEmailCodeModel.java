package com.itbarxproject.model.account;

public class AccountSendEmailCodeModel {

	private String emailCode;
	private String password;
	private String passwordConfirm;

	public AccountSendEmailCodeModel() {
	super();
	}

	public AccountSendEmailCodeModel(String emailCode, String password, String passwordConfirm) {
	super();
	this.emailCode = emailCode;
	this.password = password;
	this.passwordConfirm = passwordConfirm;
	}

	public String getEmailCode() {
	return emailCode;
	}

	public void setEmailCode(String emailCode) {
	this.emailCode = emailCode;
	}

	public String getPassword() {
	return password;
	}

	public void setPassword(String password) {
	this.password = password;
	}

	public String getPasswordConfirm() {
	return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
	this.passwordConfirm = passwordConfirm;
	}

}
