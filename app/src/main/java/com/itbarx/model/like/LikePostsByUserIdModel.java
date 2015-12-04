package com.itbarx.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LikePostsByUserIdModel {

	@SerializedName("RowNum")
	@Expose
	private Long RowNum;
	@SerializedName("PostID")
	@Expose
	private String PostID;
	@SerializedName("PostSenderUserId")
	@Expose
	private String PostSenderUserId;
	@SerializedName("AddedDate")
	@Expose
	private String AddedDate;
	@SerializedName("PostSpeechToText")
	@Expose
	private String PostSpeechToText;
	@SerializedName("PostText")
	@Expose
	private String PostText;
	@SerializedName("PostSenderIP")
	@Expose
	private String PostSenderIP;
	@SerializedName("PostTypeE")
	@Expose
	private String PostTypeE;
	@SerializedName("IsDeleted")
	@Expose
	private String IsDeleted;
	@SerializedName("PostLikeCount")
	@Expose
	private String PostLikeCount;
	@SerializedName("PostReplyCount")
	@Expose
	private String PostReplyCount;
	@SerializedName("PostShareCount")
	@Expose
	private String PostShareCount;
	@SerializedName("PostComplainCount")
	@Expose
	private String PostComplainCount;
	@SerializedName("PostCommentCount")
	@Expose
	private String PostCommentCount;
	@SerializedName("IsAdultContent")
	@Expose
	private String IsAdultContent;
	@SerializedName("PostURL")
	@Expose
	private String PostURL;
	@SerializedName("PostPictureURL")
	@Expose
	private String PostPictureURL;
	@SerializedName("PostVoiceUrl")
	@Expose
	private String PostVoiceUrl;
	@SerializedName("AddedTimeZoneId")
	@Expose
	private String AddedTimeZoneId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public LikePostsByUserIdModel() {
	}

	/**
	 *
	 * @param PostVoiceUrl
	 * @param PostComplainCount
	 * @param PostReplyCount
	 * @param AddedTimeZoneId
	 * @param PostPictureURL
	 * @param IsAdultContent
	 * @param RowNum
	 * @param PostCommentCount
	 * @param PostText
	 * @param PostSenderIP
	 * @param PostShareCount
	 * @param PostID
	 * @param PostSenderUserId
	 * @param PostTypeE
	 * @param PostSpeechToText
	 * @param PostLikeCount
	 * @param PostURL
	 * @param IsDeleted
	 * @param AddedDate
	 */
	public LikePostsByUserIdModel(Long RowNum, String PostID, String PostSenderUserId, String AddedDate, String PostSpeechToText, String PostText, String PostSenderIP, String PostTypeE, String IsDeleted, String PostLikeCount, String PostReplyCount, String PostShareCount, String PostComplainCount, String PostCommentCount, String IsAdultContent, String PostURL, String PostPictureURL, String PostVoiceUrl, String AddedTimeZoneId) {
		this.RowNum = RowNum;
		this.PostID = PostID;
		this.PostSenderUserId = PostSenderUserId;
		this.AddedDate = AddedDate;
		this.PostSpeechToText = PostSpeechToText;
		this.PostText = PostText;
		this.PostSenderIP = PostSenderIP;
		this.PostTypeE = PostTypeE;
		this.IsDeleted = IsDeleted;
		this.PostLikeCount = PostLikeCount;
		this.PostReplyCount = PostReplyCount;
		this.PostShareCount = PostShareCount;
		this.PostComplainCount = PostComplainCount;
		this.PostCommentCount = PostCommentCount;
		this.IsAdultContent = IsAdultContent;
		this.PostURL = PostURL;
		this.PostPictureURL = PostPictureURL;
		this.PostVoiceUrl = PostVoiceUrl;
		this.AddedTimeZoneId = AddedTimeZoneId;
	}

	/**
	 *
	 * @return
	 * The RowNum
	 */
	public Long getRowNum() {
		return RowNum;
	}

	/**
	 *
	 * @param RowNum
	 * The RowNum
	 */
	public void setRowNum(Long RowNum) {
		this.RowNum = RowNum;
	}

	/**
	 *
	 * @return
	 * The PostID
	 */
	public String getPostID() {
		return PostID;
	}

	/**
	 *
	 * @param PostID
	 * The PostID
	 */
	public void setPostID(String PostID) {
		this.PostID = PostID;
	}

	/**
	 *
	 * @return
	 * The PostSenderUserId
	 */
	public String getPostSenderUserId() {
		return PostSenderUserId;
	}

	/**
	 *
	 * @param PostSenderUserId
	 * The PostSenderUserId
	 */
	public void setPostSenderUserId(String PostSenderUserId) {
		this.PostSenderUserId = PostSenderUserId;
	}

	/**
	 *
	 * @return
	 * The AddedDate
	 */
	public String getAddedDate() {
		return AddedDate;
	}

	/**
	 *
	 * @param AddedDate
	 * The AddedDate
	 */
	public void setAddedDate(String AddedDate) {
		this.AddedDate = AddedDate;
	}

	/**
	 *
	 * @return
	 * The PostSpeechToText
	 */
	public String getPostSpeechToText() {
		return PostSpeechToText;
	}

	/**
	 *
	 * @param PostSpeechToText
	 * The PostSpeechToText
	 */
	public void setPostSpeechToText(String PostSpeechToText) {
		this.PostSpeechToText = PostSpeechToText;
	}

	/**
	 *
	 * @return
	 * The PostText
	 */
	public String getPostText() {
		return PostText;
	}

	/**
	 *
	 * @param PostText
	 * The PostText
	 */
	public void setPostText(String PostText) {
		this.PostText = PostText;
	}

	/**
	 *
	 * @return
	 * The PostSenderIP
	 */
	public String getPostSenderIP() {
		return PostSenderIP;
	}

	/**
	 *
	 * @param PostSenderIP
	 * The PostSenderIP
	 */
	public void setPostSenderIP(String PostSenderIP) {
		this.PostSenderIP = PostSenderIP;
	}

	/**
	 *
	 * @return
	 * The PostTypeE
	 */
	public String getPostTypeE() {
		return PostTypeE;
	}

	/**
	 *
	 * @param PostTypeE
	 * The PostTypeE
	 */
	public void setPostTypeE(String PostTypeE) {
		this.PostTypeE = PostTypeE;
	}

	/**
	 *
	 * @return
	 * The IsDeleted
	 */
	public String getIsDeleted() {
		return IsDeleted;
	}

	/**
	 *
	 * @param IsDeleted
	 * The IsDeleted
	 */
	public void setIsDeleted(String IsDeleted) {
		this.IsDeleted = IsDeleted;
	}

	/**
	 *
	 * @return
	 * The PostLikeCount
	 */
	public String getPostLikeCount() {
		return PostLikeCount;
	}

	/**
	 *
	 * @param PostLikeCount
	 * The PostLikeCount
	 */
	public void setPostLikeCount(String PostLikeCount) {
		this.PostLikeCount = PostLikeCount;
	}

	/**
	 *
	 * @return
	 * The PostReplyCount
	 */
	public String getPostReplyCount() {
		return PostReplyCount;
	}

	/**
	 *
	 * @param PostReplyCount
	 * The PostReplyCount
	 */
	public void setPostReplyCount(String PostReplyCount) {
		this.PostReplyCount = PostReplyCount;
	}

	/**
	 *
	 * @return
	 * The PostShareCount
	 */
	public String getPostShareCount() {
		return PostShareCount;
	}

	/**
	 *
	 * @param PostShareCount
	 * The PostShareCount
	 */
	public void setPostShareCount(String PostShareCount) {
		this.PostShareCount = PostShareCount;
	}

	/**
	 *
	 * @return
	 * The PostComplainCount
	 */
	public String getPostComplainCount() {
		return PostComplainCount;
	}

	/**
	 *
	 * @param PostComplainCount
	 * The PostComplainCount
	 */
	public void setPostComplainCount(String PostComplainCount) {
		this.PostComplainCount = PostComplainCount;
	}

	/**
	 *
	 * @return
	 * The PostCommentCount
	 */
	public String getPostCommentCount() {
		return PostCommentCount;
	}

	/**
	 *
	 * @param PostCommentCount
	 * The PostCommentCount
	 */
	public void setPostCommentCount(String PostCommentCount) {
		this.PostCommentCount = PostCommentCount;
	}

	/**
	 *
	 * @return
	 * The IsAdultContent
	 */
	public String getIsAdultContent() {
		return IsAdultContent;
	}

	/**
	 *
	 * @param IsAdultContent
	 * The IsAdultContent
	 */
	public void setIsAdultContent(String IsAdultContent) {
		this.IsAdultContent = IsAdultContent;
	}

	/**
	 *
	 * @return
	 * The PostURL
	 */
	public String getPostURL() {
		return PostURL;
	}

	/**
	 *
	 * @param PostURL
	 * The PostURL
	 */
	public void setPostURL(String PostURL) {
		this.PostURL = PostURL;
	}

	/**
	 *
	 * @return
	 * The PostPictureURL
	 */
	public String getPostPictureURL() {
		return PostPictureURL;
	}

	/**
	 *
	 * @param PostPictureURL
	 * The PostPictureURL
	 */
	public void setPostPictureURL(String PostPictureURL) {
		this.PostPictureURL = PostPictureURL;
	}

	/**
	 *
	 * @return
	 * The PostVoiceUrl
	 */
	public String getPostVoiceUrl() {
		return PostVoiceUrl;
	}

	/**
	 *
	 * @param PostVoiceUrl
	 * The PostVoiceUrl
	 */
	public void setPostVoiceUrl(String PostVoiceUrl) {
		this.PostVoiceUrl = PostVoiceUrl;
	}

	/**
	 *
	 * @return
	 * The AddedTimeZoneId
	 */
	public String getAddedTimeZoneId() {
		return AddedTimeZoneId;
	}

	/**
	 *
	 * @param AddedTimeZoneId
	 * The AddedTimeZoneId
	 */
	public void setAddedTimeZoneId(String AddedTimeZoneId) {
		this.AddedTimeZoneId = AddedTimeZoneId;
	}

}