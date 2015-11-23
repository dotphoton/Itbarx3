package com.itbarx.model.like;

public class LikeCountPostModel {

	private String postID;

	public LikeCountPostModel() {
	super();
	}

	public LikeCountPostModel(String postID) {
	super();
	this.postID = postID;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

}
