package com.itbarx.model.like;

public class LikeModel {

	private String userID;
	private String postID;

	public LikeModel() {
	super();
	}

	public LikeModel(String userID, String postID) {
	super();
	this.userID = userID;
	this.postID = postID;
	}

	public String getUserID() {
	return userID;
	}

	public void setUserID(String userID) {
	this.userID = userID;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

}
