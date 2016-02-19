package com.itbarxproject.model.follow;

//FOLLOWER LIST
public class FollowerListByFollowingIdModel {

	private String RowNum;
	private String followerID_fk;
	private String followingID_fk;
	private String followRelationStatusE;
	private String followingDate;
	private String followingPendingStatus;
	private String userID;
	private String name;
	private String itBarxUserName;
	private String PhotoContainerId;
	private String userProfilePhoto;
	private String IsYourFollower;
	private String AreYouFollowing;

	public String getRowNum() {
		return RowNum;
	}

	public FollowerListByFollowingIdModel setRowNum(String rowNum) {
		RowNum = rowNum;
		return this;
	}

	public String getFollowerID_fk() {
		return followerID_fk;
	}

	public FollowerListByFollowingIdModel setFollowerID_fk(String followerID_fk) {
		this.followerID_fk = followerID_fk;
		return this;
	}

	public String getFollowingID_fk() {
		return followingID_fk;
	}

	public FollowerListByFollowingIdModel setFollowingID_fk(String followingID_fk) {
		this.followingID_fk = followingID_fk;
		return this;
	}

	public String getFollowRelationStatusE() {
		return followRelationStatusE;
	}

	public FollowerListByFollowingIdModel setFollowRelationStatusE(String followRelationStatusE) {
		this.followRelationStatusE = followRelationStatusE;
		return this;
	}

	public String getFollowingDate() {
		return followingDate;
	}

	public FollowerListByFollowingIdModel setFollowingDate(String followingDate) {
		this.followingDate = followingDate;
		return this;
	}

	public String getFollowingPendingStatus() {
		return followingPendingStatus;
	}

	public FollowerListByFollowingIdModel setFollowingPendingStatus(String followingPendingStatus) {
		this.followingPendingStatus = followingPendingStatus;
		return this;
	}

	public String getUserID() {
		return userID;
	}

	public FollowerListByFollowingIdModel setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public String getName() {
		return name;
	}

	public FollowerListByFollowingIdModel setName(String name) {
		this.name = name;
		return this;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public FollowerListByFollowingIdModel setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
		return this;
	}

	public String getPhotoContainerId() {
		return PhotoContainerId;
	}

	public FollowerListByFollowingIdModel setPhotoContainerId(String photoContainerId) {
		PhotoContainerId = photoContainerId;
		return this;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public FollowerListByFollowingIdModel setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
		return this;
	}

	public String getIsYourFollower() {
		return IsYourFollower;
	}

	public FollowerListByFollowingIdModel setIsYourFollower(String isYourFollower) {
		IsYourFollower = isYourFollower;
		return this;
	}

	public String getAreYouFollowing() {
		return AreYouFollowing;
	}

	public FollowerListByFollowingIdModel setAreYouFollowing(String areYouFollowing) {
		AreYouFollowing = areYouFollowing;
		return this;
	}
}
