package com.itbarx.model.rebark;

public class ReBarkDeleteModel {

	private String shareID;
	private String postID;

	public ReBarkDeleteModel() {
	super();
	}

	public ReBarkDeleteModel(String shareID, String postID) {
	super();
	this.shareID = shareID;
	this.postID = postID;
	}

	public String getShareID() {
	return shareID;
	}

	public void setShareID(String shareID) {
	this.shareID = shareID;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

}
