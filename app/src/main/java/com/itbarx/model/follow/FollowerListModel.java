package com.itbarx.model.follow;

public class FollowerListModel {

	private String searcherId;
	private String followingId;
	private String page;
	private String recPerPage;

	public FollowerListModel() {
	super();
	}

	// WILL BE USED BY PENDING LIST
	public FollowerListModel(String followingId, String page, String recPerPage) {
	super();
	this.followingId = followingId;
	this.page = page;
	this.recPerPage = recPerPage;
	}

	// WILL BE USED BY FOLLOWER LIST
	public FollowerListModel(String searcherId, String followingId, String page, String recPerPage) {
	super();
	this.searcherId = searcherId;
	this.followingId = followingId;
	this.page = page;
	this.recPerPage = recPerPage;
	}

	public String getSearcherId() {
	return searcherId;
	}

	public void setSearcherId(String searcherId) {
	this.searcherId = searcherId;
	}

	public String getFollowingId() {
	return followingId;
	}

	public void setFollowingId(String followingId) {
	this.followingId = followingId;
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
