package com.itbarxproject.model.account;

public class EditProfileModel {

	private String userId;
	private String name;
	private String userName;
	private String location;
	private String webSite;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String oldPhotoUrl;
	private String newPhotoBase64String;
	private String isNotificationActive;
	private String userBio;

	public EditProfileModel() {

	}

	public EditProfileModel(String name, String userName, String location, String webSite, String oldPassword, String newPassword,
		String confirmPassword, String oldPhotoUrl, String userId, String newPhotoBase64String, String isNotificationActive, String userBio) {
	super();
	this.name = name;
	this.userName = userName;
	this.location = location;
	this.webSite = webSite;
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
	this.confirmPassword = confirmPassword;
	this.oldPhotoUrl = oldPhotoUrl;
	this.userId = userId;
	this.newPhotoBase64String = newPhotoBase64String;
	this.isNotificationActive = isNotificationActive;
	this.userBio = userBio;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getUserName() {
	return userName;
	}

	public void setUserName(String userName) {
	this.userName = userName;
	}

	public String getLocation() {
	return location;
	}

	public void setLocation(String location) {
	this.location = location;
	}

	public String getWebSite() {
	return webSite;
	}

	public void setWebSite(String webSite) {
	this.webSite = webSite;
	}

	public String getOldPassword() {
	return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
	return newPassword;
	}

	public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
	return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
	}

	public String getOldPhotoUrl() {
	return oldPhotoUrl;
	}

	public void setOldPhotoUrl(String oldPhotoUrl) {
	this.oldPhotoUrl = oldPhotoUrl;
	}

	public String getUserId() {
	return userId;
	}

	public void setUserId(String userId) {
	this.userId = userId;
	}

	public String getNewPhotoBase64String() {
	return newPhotoBase64String;
	}

	public void setNewPhotoBase64String(String newPhotoBase64String) {
	this.newPhotoBase64String = newPhotoBase64String;
	}

	public String getIsNotificationActive() {
	return isNotificationActive;
	}

	public void setIsNotificationActive(String isNotificationActive) {
	this.isNotificationActive = isNotificationActive;
	}

	public String getUserBio() {
	return userBio;
	}

	public void setUserBio(String userBio) {
	this.userBio = userBio;
	}

}
