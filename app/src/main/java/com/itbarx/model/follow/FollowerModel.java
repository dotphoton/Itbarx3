package com.itbarx.model.follow;

public class FollowerModel {

	private String followerId;

	public FollowerModel() {
	super();
	}

	public FollowerModel(String followerId) {
	super();
	this.followerId = followerId;
	}

	public String getFollowerId() {
	return followerId;
	}

	public void setFollowerId(String followerId) {
	this.followerId = followerId;
	}

}
