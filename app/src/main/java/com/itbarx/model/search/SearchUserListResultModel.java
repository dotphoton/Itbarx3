package com.itbarx.model.search;

public class SearchUserListResultModel {

	private String RowNum;
	private String userID;
	private String name;
	private String itBarxUserName;
	private String userBio;
	private String userFollowStatus;
	private String userProfilePhoto;
	private String IsYourFollower;
	private String AreYouFollowing;

	public SearchUserListResultModel() {

	}

	public SearchUserListResultModel(String rowNum, String userID, String name, String itBarxUserName, String userBio, String userFollowStatus, String userProfilePhoto, String isYourFollower, String areYouFollowing) {
		RowNum = rowNum;
		this.userID = userID;
		this.name = name;
		this.itBarxUserName = itBarxUserName;
		this.userBio = userBio;
		this.userFollowStatus = userFollowStatus;
		this.userProfilePhoto = userProfilePhoto;
		IsYourFollower = isYourFollower;
		AreYouFollowing = areYouFollowing;
	}

	public String getRowNum() {
		return RowNum;
	}

	public SearchUserListResultModel setRowNum(String rowNum) {
		RowNum = rowNum;
		return this;
	}

	public String getUserID() {
		return userID;
	}

	public SearchUserListResultModel setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public String getName() {
		return name;
	}

	public SearchUserListResultModel setName(String name) {
		this.name = name;
		return this;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public SearchUserListResultModel setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
		return this;
	}

	public String getUserBio() {
		return userBio;
	}

	public SearchUserListResultModel setUserBio(String userBio) {
		this.userBio = userBio;
		return this;
	}

	public String getUserFollowStatus() {
		return userFollowStatus;
	}

	public SearchUserListResultModel setUserFollowStatus(String userFollowStatus) {
		this.userFollowStatus = userFollowStatus;
		return this;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public SearchUserListResultModel setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
		return this;
	}

	public String getIsYourFollower() {
		return IsYourFollower;
	}

	public SearchUserListResultModel setIsYourFollower(String isYourFollower) {
		IsYourFollower = isYourFollower;
		return this;
	}

	public String getAreYouFollowing() {
		return AreYouFollowing;
	}

	public SearchUserListResultModel setAreYouFollowing(String areYouFollowing) {
		AreYouFollowing = areYouFollowing;
		return this;
	}
}
