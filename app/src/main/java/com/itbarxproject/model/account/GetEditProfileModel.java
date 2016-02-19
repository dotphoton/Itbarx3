package com.itbarxproject.model.account;

public class GetEditProfileModel {

	public String userID;
	public String Name;
	public String mentionname;
	public String LocationId;
	public String LocationName;
	public String userwebsite;
	public String userBio;
	public String userPassword;
	public String IsNotificationActive;
	public String userProfilePhoto;
	public String itBarxUserName;

	public GetEditProfileModel() {

	}

	public GetEditProfileModel(String userID, String name, String mentionname, String locationId, String locationName, String userwebsite, String userBio, String userPassword, String isNotificationActive, String userProfilePhoto, String itBarxUserName) {
		this.userID = userID;
		Name = name;
		this.mentionname = mentionname;
		LocationId = locationId;
		LocationName = locationName;
		this.userwebsite = userwebsite;
		this.userBio = userBio;
		this.userPassword = userPassword;
		IsNotificationActive = isNotificationActive;
		this.userProfilePhoto = userProfilePhoto;
		this.itBarxUserName = itBarxUserName;
	}

	public String getUserID() {
		return userID;
	}

	public GetEditProfileModel setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public String getName() {
		return Name;
	}

	public GetEditProfileModel setName(String name) {
		Name = name;
		return this;
	}

	public String getMentionname() {
		return mentionname;
	}

	public GetEditProfileModel setMentionname(String mentionname) {
		this.mentionname = mentionname;
		return this;
	}

	public String getLocationId() {
		return LocationId;
	}

	public GetEditProfileModel setLocationId(String locationId) {
		LocationId = locationId;
		return this;
	}

	public String getLocationName() {
		return LocationName;
	}

	public GetEditProfileModel setLocationName(String locationName) {
		LocationName = locationName;
		return this;
	}

	public String getUserwebsite() {
		return userwebsite;
	}

	public GetEditProfileModel setUserwebsite(String userwebsite) {
		this.userwebsite = userwebsite;
		return this;
	}

	public String getUserBio() {
		return userBio;
	}

	public GetEditProfileModel setUserBio(String userBio) {
		this.userBio = userBio;
		return this;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public GetEditProfileModel setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	public String getIsNotificationActive() {
		return IsNotificationActive;
	}

	public GetEditProfileModel setIsNotificationActive(String isNotificationActive) {
		IsNotificationActive = isNotificationActive;
		return this;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public GetEditProfileModel setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
		return this;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public GetEditProfileModel setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
		return this;
	}
}
