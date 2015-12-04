package com.itbarx.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeUsersByPostIdModel {

	@SerializedName("RowNum") @Expose private String RowNum;
	@SerializedName("PostId") @Expose private String PostId;
	@SerializedName("AddDate") @Expose private String AddDate;
	@SerializedName("userID") @Expose private String userID;
	@SerializedName("name") @Expose private String name;
	@SerializedName("itBarxUserName") @Expose private String itBarxUserName;
	@SerializedName("locationID_fk") @Expose private String locationIDFk;
	@SerializedName("signUpDate") @Expose private String signUpDate;
	@SerializedName("profileUpdateDate") @Expose private String profileUpdateDate;
	@SerializedName("userBio") @Expose private String userBio;
	@SerializedName("genderEnumCode") @Expose private String genderEnumCode;
	@SerializedName("userFollowStatus") @Expose private String userFollowStatus;
	@SerializedName("userEmail") @Expose private String userEmail;
	@SerializedName("userPassword") @Expose private String userPassword;
	@SerializedName("userAccountStatus") @Expose private String userAccountStatus;
	@SerializedName("userProfilePhoto") @Expose private String userProfilePhoto;
	@SerializedName("LastWallSeenDate") @Expose private String LastWallSeenDate;
	@SerializedName("FavouriteUserCount") @Expose private String FavouriteUserCount;
	@SerializedName("NotificationCount") @Expose private String NotificationCount;
	@SerializedName("userrole") @Expose private String userrole;
	@SerializedName("mentionname") @Expose private String mentionname;
	@SerializedName("userwebsite") @Expose private String userwebsite;
	@SerializedName("userPassEmailCode") @Expose private String userPassEmailCode;
	@SerializedName("userPasswordChangedDate") @Expose private String userPasswordChangedDate;
	@SerializedName("MediaContainerId") @Expose private String MediaContainerId;
	@SerializedName("IsNotificationActive") @Expose private String IsNotificationActive;
	@SerializedName("PhotoContainerId") @Expose private String PhotoContainerId;

	/**
	 * @return The RowNum
	 */
	public String getRowNum() {
		return RowNum;
	}

	/**
	 * @param RowNum The RowNum
	 */
	public void setRowNum(String RowNum) {
		this.RowNum = RowNum;
	}

	/**
	 * @return The PostId
	 */
	public String getPostId() {
		return PostId;
	}

	/**
	 * @param PostId The PostId
	 */
	public void setPostId(String PostId) {
		this.PostId = PostId;
	}

	/**
	 * @return The AddDate
	 */
	public String getAddDate() {
		return AddDate;
	}

	/**
	 * @param AddDate The AddDate
	 */
	public void setAddDate(String AddDate) {
		this.AddDate = AddDate;
	}

	/**
	 * @return The userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID The userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The itBarxUserName
	 */
	public String getItBarxUserName() {
		return itBarxUserName;
	}

	/**
	 * @param itBarxUserName The itBarxUserName
	 */
	public void setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
	}

	/**
	 * @return The locationIDFk
	 */
	public String getLocationIDFk() {
		return locationIDFk;
	}

	/**
	 * @param locationIDFk The locationID_fk
	 */
	public void setLocationIDFk(String locationIDFk) {
		this.locationIDFk = locationIDFk;
	}

	/**
	 * @return The signUpDate
	 */
	public String getSignUpDate() {
		return signUpDate;
	}

	/**
	 * @param signUpDate The signUpDate
	 */
	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}

	/**
	 * @return The profileUpdateDate
	 */
	public String getProfileUpdateDate() {
		return profileUpdateDate;
	}

	/**
	 * @param profileUpdateDate The profileUpdateDate
	 */
	public void setProfileUpdateDate(String profileUpdateDate) {
		this.profileUpdateDate = profileUpdateDate;
	}

	/**
	 * @return The userBio
	 */
	public String getUserBio() {
		return userBio;
	}

	/**
	 * @param userBio The userBio
	 */
	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	/**
	 * @return The genderEnumCode
	 */
	public String getGenderEnumCode() {
		return genderEnumCode;
	}

	/**
	 * @param genderEnumCode The genderEnumCode
	 */
	public void setGenderEnumCode(String genderEnumCode) {
		this.genderEnumCode = genderEnumCode;
	}

	/**
	 * @return The userFollowStatus
	 */
	public String getUserFollowStatus() {
		return userFollowStatus;
	}

	/**
	 * @param userFollowStatus The userFollowStatus
	 */
	public void setUserFollowStatus(String userFollowStatus) {
		this.userFollowStatus = userFollowStatus;
	}

	/**
	 * @return The userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail The userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return The userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword The userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return The userAccountStatus
	 */
	public String getUserAccountStatus() {
		return userAccountStatus;
	}

	/**
	 * @param userAccountStatus The userAccountStatus
	 */
	public void setUserAccountStatus(String userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}

	/**
	 * @return The userProfilePhoto
	 */
	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	/**
	 * @param userProfilePhoto The userProfilePhoto
	 */
	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}

	/**
	 * @return The LastWallSeenDate
	 */
	public String getLastWallSeenDate() {
		return LastWallSeenDate;
	}

	/**
	 * @param LastWallSeenDate The LastWallSeenDate
	 */
	public void setLastWallSeenDate(String LastWallSeenDate) {
		this.LastWallSeenDate = LastWallSeenDate;
	}

	/**
	 * @return The FavouriteUserCount
	 */
	public String getFavouriteUserCount() {
		return FavouriteUserCount;
	}

	/**
	 * @param FavouriteUserCount The FavouriteUserCount
	 */
	public void setFavouriteUserCount(String FavouriteUserCount) {
		this.FavouriteUserCount = FavouriteUserCount;
	}

	/**
	 * @return The NotificationCount
	 */
	public String getNotificationCount() {
		return NotificationCount;
	}

	/**
	 * @param NotificationCount The NotificationCount
	 */
	public void setNotificationCount(String NotificationCount) {
		this.NotificationCount = NotificationCount;
	}

	/**
	 * @return The userrole
	 */
	public String getUserrole() {
		return userrole;
	}

	/**
	 * @param userrole The userrole
	 */
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	/**
	 * @return The mentionname
	 */
	public String getMentionname() {
		return mentionname;
	}

	/**
	 * @param mentionname The mentionname
	 */
	public void setMentionname(String mentionname) {
		this.mentionname = mentionname;
	}

	/**
	 * @return The userwebsite
	 */
	public String getUserwebsite() {
		return userwebsite;
	}

	/**
	 * @param userwebsite The userwebsite
	 */
	public void setUserwebsite(String userwebsite) {
		this.userwebsite = userwebsite;
	}

	/**
	 * @return The userPassEmailCode
	 */
	public String getUserPassEmailCode() {
		return userPassEmailCode;
	}

	/**
	 * @param userPassEmailCode The userPassEmailCode
	 */
	public void setUserPassEmailCode(String userPassEmailCode) {
		this.userPassEmailCode = userPassEmailCode;
	}

	/**
	 * @return The userPasswordChangedDate
	 */
	public String getUserPasswordChangedDate() {
		return userPasswordChangedDate;
	}

	/**
	 * @param userPasswordChangedDate The userPasswordChangedDate
	 */
	public void setUserPasswordChangedDate(String userPasswordChangedDate) {
		this.userPasswordChangedDate = userPasswordChangedDate;
	}

	/**
	 * @return The MediaContainerId
	 */
	public String getMediaContainerId() {
		return MediaContainerId;
	}

	/**
	 * @param MediaContainerId The MediaContainerId
	 */
	public void setMediaContainerId(String MediaContainerId) {
		this.MediaContainerId = MediaContainerId;
	}

	/**
	 * @return The IsNotificationActive
	 */
	public String getIsNotificationActive() {
		return IsNotificationActive;
	}

	/**
	 * @param IsNotificationActive The IsNotificationActive
	 */
	public void setIsNotificationActive(String IsNotificationActive) {
		this.IsNotificationActive = IsNotificationActive;
	}

	/**
	 * @return The PhotoContainerId
	 */
	public String getPhotoContainerId() {
		return PhotoContainerId;
	}

	/**
	 * @param PhotoContainerId The PhotoContainerId
	 */
	public void setPhotoContainerId(String PhotoContainerId) {
		this.PhotoContainerId = PhotoContainerId;
	}

}