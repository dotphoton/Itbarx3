package com.itbarxproject.model.post;

public class PostWallInfoModel {

	private String searcherID;
	private String userID;

	public PostWallInfoModel() {
	super();
	}

	public PostWallInfoModel(String searcherID, String userID) {
	super();
	this.searcherID = searcherID;
	this.userID = userID;
	}

	public String getSearcherID() {
	return searcherID;
	}

	public void setSearcherID(String searcherID) {
	this.searcherID = searcherID;
	}

	public String getUserID() {
	return userID;
	}

	public void setUserID(String userID) {
	this.userID = userID;
	}

}
