package com.itbarx.model.account;

public class AccountGetUserByLoginInfoModel {
	private String userID;
	private String name;
	private String itBarxUserName;
	private String locationID_fk;
	private String signUpDate;
	private String profileUpdateDate;
	private String userBio;
	private String genderEnumCode;
	private String userFollowStatus;
	private String userEmail;
	private String userPassword;
	private String userAccountStatus;
	private String userProfilePhoto;
	private String LastWallSeenDate;
	private String FavouriteUserCount;
	private String NotificationCount;
	private String userrole;
	private String mentionname;
	private String userwebsite;
	private String userPassEmailCode;
	private String userPasswordChangedDate;
	private String MediaContainerId;
	private String IsNotificationActive;
	private String PhotoContainerId;
	private String LocationId;
	private String LocationName;
	private String CountryId;

	public AccountGetUserByLoginInfoModel() {
	}

	public AccountGetUserByLoginInfoModel(String userID, String name, String itBarxUserName, String locationID_fk, String signUpDate, String profileUpdateDate, String userBio, String genderEnumCode, String userFollowStatus, String userEmail, String userPassword, String userAccountStatus, String userProfilePhoto, String lastWallSeenDate, String favouriteUserCount, String notificationCount, String userrole, String mentionname, String userwebsite, String userPassEmailCode, String userPasswordChangedDate, String mediaContainerId, String isNotificationActive, String photoContainerId, String locationId, String locationName, String countryId) {
		this.userID = userID;
		this.name = name;
		this.itBarxUserName = itBarxUserName;
		this.locationID_fk = locationID_fk;
		this.signUpDate = signUpDate;
		this.profileUpdateDate = profileUpdateDate;
		this.userBio = userBio;
		this.genderEnumCode = genderEnumCode;
		this.userFollowStatus = userFollowStatus;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAccountStatus = userAccountStatus;
		this.userProfilePhoto = userProfilePhoto;
		LastWallSeenDate = lastWallSeenDate;
		FavouriteUserCount = favouriteUserCount;
		NotificationCount = notificationCount;
		this.userrole = userrole;
		this.mentionname = mentionname;
		this.userwebsite = userwebsite;
		this.userPassEmailCode = userPassEmailCode;
		this.userPasswordChangedDate = userPasswordChangedDate;
		MediaContainerId = mediaContainerId;
		IsNotificationActive = isNotificationActive;
		PhotoContainerId = photoContainerId;
		LocationId = locationId;
		LocationName = locationName;
		CountryId = countryId;
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

	public String getLocationID_fk() {
		return locationID_fk;
	}

	public void setLocationID_fk(String locationID_fk) {
		this.locationID_fk = locationID_fk;
	}

	public String getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}

	public String getProfileUpdateDate() {
		return profileUpdateDate;
	}

	public void setProfileUpdateDate(String profileUpdateDate) {
		this.profileUpdateDate = profileUpdateDate;
	}

	public String getUserBio() {
		return userBio;
	}

	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	public String getGenderEnumCode() {
		return genderEnumCode;
	}

	public void setGenderEnumCode(String genderEnumCode) {
		this.genderEnumCode = genderEnumCode;
	}

	public String getUserFollowStatus() {
		return userFollowStatus;
	}

	public void setUserFollowStatus(String userFollowStatus) {
		this.userFollowStatus = userFollowStatus;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAccountStatus() {
		return userAccountStatus;
	}

	public void setUserAccountStatus(String userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}

	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}

	public String getLastWallSeenDate() {
		return LastWallSeenDate;
	}

	public void setLastWallSeenDate(String lastWallSeenDate) {
		LastWallSeenDate = lastWallSeenDate;
	}

	public String getFavouriteUserCount() {
		return FavouriteUserCount;
	}

	public void setFavouriteUserCount(String favouriteUserCount) {
		FavouriteUserCount = favouriteUserCount;
	}

	public String getNotificationCount() {
		return NotificationCount;
	}

	public void setNotificationCount(String notificationCount) {
		NotificationCount = notificationCount;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getMentionname() {
		return mentionname;
	}

	public void setMentionname(String mentionname) {
		this.mentionname = mentionname;
	}

	public String getUserwebsite() {
		return userwebsite;
	}

	public void setUserwebsite(String userwebsite) {
		this.userwebsite = userwebsite;
	}

	public String getUserPassEmailCode() {
		return userPassEmailCode;
	}

	public void setUserPassEmailCode(String userPassEmailCode) {
		this.userPassEmailCode = userPassEmailCode;
	}

	public String getUserPasswordChangedDate() {
		return userPasswordChangedDate;
	}

	public void setUserPasswordChangedDate(String userPasswordChangedDate) {
		this.userPasswordChangedDate = userPasswordChangedDate;
	}

	public String getMediaContainerId() {
		return MediaContainerId;
	}

	public void setMediaContainerId(String mediaContainerId) {
		MediaContainerId = mediaContainerId;
	}

	public String getIsNotificationActive() {
		return IsNotificationActive;
	}

	public void setIsNotificationActive(String isNotificationActive) {
		IsNotificationActive = isNotificationActive;
	}

	public String getPhotoContainerId() {
		return PhotoContainerId;
	}

	public void setPhotoContainerId(String photoContainerId) {
		PhotoContainerId = photoContainerId;
	}

	public String getLocationId() {
		return LocationId;
	}

	public void setLocationId(String locationId) {
		LocationId = locationId;
	}

	public String getLocationName() {
		return LocationName;
	}

	public void setLocationName(String locationName) {
		LocationName = locationName;
	}

	public String getCountryId() {
		return CountryId;
	}

	public void setCountryId(String countryId) {
		CountryId = countryId;
	}
}
