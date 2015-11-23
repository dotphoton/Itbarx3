package com.itbarx.model.follow;

public class FollowUserModel {

	private String followerID;
	private String followingID;

	public FollowUserModel() {
	}

	public FollowUserModel(String followerID, String followingID) {
	this.followerID = followerID;
	this.followingID = followingID;
	}

	public String getFollowerID() {
	return followerID;
	}

	public void setFollowerID(String followerID) {
	this.followerID = followerID;
	}

	public String getFollowingID() {
	return followingID;
	}

	public void setFollowingID(String followingID) {
	this.followingID = followingID;
	}

}
