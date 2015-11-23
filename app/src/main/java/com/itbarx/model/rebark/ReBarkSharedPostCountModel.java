package com.itbarx.model.rebark;

public class ReBarkSharedPostCountModel {
	private String userID;

	public ReBarkSharedPostCountModel() {
	super();
	}

	public ReBarkSharedPostCountModel(String userID) {
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
