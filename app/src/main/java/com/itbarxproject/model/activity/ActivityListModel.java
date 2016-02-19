package com.itbarxproject.model.activity;

public class ActivityListModel {

	private String ActivityText;
	private String RowNum;
	private String ActivityId;
	private String MakerUserId;
	private String ActivityType;
	private String PostId;
	private String OwnerUserId;
	private String AddDate;
	private String itBarxUserName;
	private String userProfilePhoto;

	public ActivityListModel() {
	super();
	}

	public ActivityListModel(String userProfilePhoto, String rowNum, String activityId, String makerUserId, String activityType, String postId, String ownerUserId, String addDate, String itBarxUserName, String activityText) {
		this.userProfilePhoto = userProfilePhoto;
		RowNum = rowNum;
		ActivityId = activityId;
		MakerUserId = makerUserId;
		ActivityType = activityType;
		PostId = postId;
		OwnerUserId = ownerUserId;
		AddDate = addDate;
		this.itBarxUserName = itBarxUserName;
		ActivityText = activityText;
	}

	public String getActivityText() {
		return ActivityText;
	}

	public void setActivityText(String activityText) {
		ActivityText = activityText;
	}

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getActivityId() {
		return ActivityId;
	}

	public void setActivityId(String activityId) {
		ActivityId = activityId;
	}

	public String getMakerUserId() {
		return MakerUserId;
	}

	public void setMakerUserId(String makerUserId) {
		MakerUserId = makerUserId;
	}

	public String getActivityType() {
		return ActivityType;
	}

	public void setActivityType(String activityType) {
		ActivityType = activityType;
	}

	public String getPostId() {
		return PostId;
	}

	public void setPostId(String postId) {
		PostId = postId;
	}

	public String getOwnerUserId() {
		return OwnerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		OwnerUserId = ownerUserId;
	}

	public String getAddDate() {
		return AddDate;
	}

	public void setAddDate(String addDate) {
		AddDate = addDate;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public void setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}
}
