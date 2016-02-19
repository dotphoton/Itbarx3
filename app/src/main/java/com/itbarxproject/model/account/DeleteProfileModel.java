package com.itbarxproject.model.account;

public class DeleteProfileModel {
	private String userID;

	public DeleteProfileModel() {
	super();
	}

	public DeleteProfileModel(String userID) {
	super();
	this.userID = userID;
	}

	public String getUserID() {
	return userID;
	}

	public void setUserID(String userID) {
	this.userID = userID;
	}

}
