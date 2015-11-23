package com.itbarx.model.account;

public class AccountSignUpModel {

	private String username;
	private String password;
	private String passwordConfirm;
	private String email;
	private String photo;

	public AccountSignUpModel() {
	super();
	}

	public AccountSignUpModel(String username, String password, String passwordConfirm, String email, String photo) {
	super();
	this.username = username;
	this.password = password;
	this.passwordConfirm = passwordConfirm;
	this.email = email;
	this.photo = photo;
	}

	public String getUsername() {
	return username;
	}

	public void setUsername(String username) {
	this.username = username;
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

	public String getEmail() {
	return email;
	}

	public void setEmail(String email) {
	this.email = email;
	}

	public String getPhoto() {
	return photo;
	}

	public void setPhoto(String photo) {
	this.photo = photo;
	}

}
