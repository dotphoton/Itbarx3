package com.itbarx.model.like;

public class LikeCountUserModel {

	private String userID;

	public LikeCountUserModel() {
	super();
	}

	public LikeCountUserModel(String userID) {
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
