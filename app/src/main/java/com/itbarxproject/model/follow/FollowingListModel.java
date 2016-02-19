package com.itbarxproject.model.follow;

public class FollowingListModel {

	private String searcherId;
	private String followerId;
	private String page;
	private String recPerPage;

	public FollowingListModel() {
	super();
	}

	// WILL BE USED BY SENDPENDINGLIST
	public FollowingListModel(String followerId, String page, String recPerPage) {
	super();
	this.followerId = followerId;
	this.page = page;
	this.recPerPage = recPerPage;
	}

	// WILL BE USED BY FOLLOWINGLIST
	public FollowingListModel(String searcherId, String followerId, String page, String recPerPage) {
	super();
	this.searcherId = searcherId;
	this.followerId = followerId;
	this.page = page;
	this.recPerPage = recPerPage;
	}

	public String getSearcherId() {
	return searcherId;
	}

	public void setSearcherId(String searcherId) {
	this.searcherId = searcherId;
	}

	public String getFollowerId() {
	return followerId;
	}

	public void setFollowerId(String followerId) {
	this.followerId = followerId;
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
