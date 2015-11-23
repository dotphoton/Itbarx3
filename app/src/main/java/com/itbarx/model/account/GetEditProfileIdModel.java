package com.itbarx.model.account;

public class GetEditProfileIdModel {
	private String userID;

	public GetEditProfileIdModel() {
	super();
	}

	public GetEditProfileIdModel(String userID) {
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
