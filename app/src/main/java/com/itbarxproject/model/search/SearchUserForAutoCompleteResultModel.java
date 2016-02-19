package com.itbarxproject.model.search;

public class SearchUserForAutoCompleteResultModel {

	private String userID;
	private String name;
	private String itBarxUserName;
	private String userBio;
	private String userFollowStatus;
	private String userProfilePhoto;
	private String isYourFollower;
	private String areYouFollowing;

	public SearchUserForAutoCompleteResultModel() {
	super();
	}

	public SearchUserForAutoCompleteResultModel(String userID, String name, String itBarxUserName, String userBio, String userFollowStatus, String userProfilePhoto, String isYourFollower,
		String areYouFollowing) {
	super();
	this.userID = userID;
	this.name = name;
	this.itBarxUserName = itBarxUserName;
	this.userBio = userBio;
	this.userFollowStatus = userFollowStatus;
	this.userProfilePhoto = userProfilePhoto;
	this.isYourFollower = isYourFollower;
	this.areYouFollowing = areYouFollowing;
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

	public String getUserBio() {
	return userBio;
	}

	public void setUserBio(String userBio) {
	this.userBio = userBio;
	}

	public String getUserFollowStatus() {
	return userFollowStatus;
	}

	public void setUserFollowStatus(String userFollowStatus) {
	this.userFollowStatus = userFollowStatus;
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
