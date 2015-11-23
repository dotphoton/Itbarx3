package com.itbarx.model.rebark;

public class ReBarkPostShareAddModel {

	private String userID;
	private String postID;
	private String sharedText;

	public ReBarkPostShareAddModel() {
	super();
	}

	public ReBarkPostShareAddModel(String userID, String postID, String sharedText) {
	super();
	this.userID = userID;
	this.postID = postID;
	this.sharedText = sharedText;
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

	public String getSharedText() {
	return sharedText;
	}

	public void setSharedText(String sharedText) {
	this.sharedText = sharedText;
	}

}
