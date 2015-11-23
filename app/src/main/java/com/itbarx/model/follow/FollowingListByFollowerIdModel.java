package com.itbarx.model.follow;

public class FollowingListByFollowerIdModel {
	private String rowNum;
	private String followerID_FK;
	private String followingID_FK;
	private String followRelationStatusE;
	private String followingDate;
	private String followingPendingStatus;
	private String userID;
	private String name;
	private String itBarxUserName;
	private String photoContainerId;
	private String userProfilePhoto;
	private String isYourFollower;
	private String areYouFollowing;

	public FollowingListByFollowerIdModel() {
	super();
	}

	public FollowingListByFollowerIdModel(String rowNum, String followerID_FK, String followingID_FK, String followRelationStatusE, String followingDate, String followingPendingStatus, String userID,
		String name, String itBarxUserName, String photoContainerId, String userProfilePhoto, String isYourFollower, String areYouFollowing) {
	super();
	this.rowNum = rowNum;
	this.followerID_FK = followerID_FK;
	this.followingID_FK = followingID_FK;
	this.followRelationStatusE = followRelationStatusE;
	this.followingDate = followingDate;
	this.followingPendingStatus = followingPendingStatus;
	this.userID = userID;
	this.name = name;
	this.itBarxUserName = itBarxUserName;
	this.photoContainerId = photoContainerId;
	this.userProfilePhoto = userProfilePhoto;
	this.isYourFollower = isYourFollower;
	this.areYouFollowing = areYouFollowing;
	}

	public String getRowNum() {
	return rowNum;
	}

	public void setRowNum(String rowNum) {
	this.rowNum = rowNum;
	}

	public String getFollowerID_FK() {
	return followerID_FK;
	}

	public void setFollowerID_FK(String followerID_FK) {
	this.followerID_FK = followerID_FK;
	}

	public String getFollowingID_FK() {
	return followingID_FK;
	}

	public void setFollowingID_FK(String followingID_FK) {
	this.followingID_FK = followingID_FK;
	}

	public String getFollowRelationStatusE() {
	return followRelationStatusE;
	}

	public void setFollowRelationStatusE(String followRelationStatusE) {
	this.followRelationStatusE = followRelationStatusE;
	}

	public String getFollowingDate() {
	return followingDate;
	}

	public void setFollowingDate(String followingDate) {
	this.followingDate = followingDate;
	}

	public String getFollowingPendingStatus() {
	return followingPendingStatus;
	}

	public void setFollowingPendingStatus(String followingPendingStatus) {
	this.followingPendingStatus = followingPendingStatus;
	}

	public String getUserID() {
	return userID;
	}

	public void setUserID(String userID) {
	this.userID = userID;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getItBarxUserName() {
	return itBarxUserName;
	}

	public void setItBarxUserName(String itBarxUserName) {
	this.itBarxUserName = itBarxUserName;
	}

	public String getPhotoContainerId() {
	return photoContainerId;
	}

	public void setPhotoContainerId(String photoContainerId) {
	this.photoContainerId = photoContainerId;
	}

	public String getUserProfilePhoto() {
	return userProfilePhoto;
	}

	public void setUserProfilePhoto(String userProfilePhoto) {
	this.userProfilePhoto = userProfilePhoto;
	}

	public String getIsYourFollower() {
	return isYourFollower;
	}

	public void setIsYourFollower(String isYourFollower) {
	this.isYourFollower = isYourFollower;
	}

	public String getAreYouFollowing() {
	return areYouFollowing;
	}

	public void setAreYouFollowing(String areYouFollowing) {
	this.areYouFollowing = areYouFollowing;
	}

}
