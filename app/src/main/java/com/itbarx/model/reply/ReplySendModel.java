package com.itbarx.model.reply;

public class ReplySendModel {

	private String postID;
	private String page;
	private String recPerPage;

	public ReplySendModel() {
	super();
	}

	public ReplySendModel(String postID, String page, String recPerPage) {
	super();
	this.postID = postID;
	this.page = page;
	this.recPerPage = recPerPage;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

	public String getPage() {
	return page;
	}

	public void setPage(String page) {
	this.page = page;
	}

	public String getRecPerPage() {
	return recPerPage;
	}

	public void setRecPerPage(String recPerPage) {
	this.recPerPage = recPerPage;
	}

}
