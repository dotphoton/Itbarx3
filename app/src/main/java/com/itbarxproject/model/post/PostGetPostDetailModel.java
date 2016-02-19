package com.itbarxproject.model.post;

public class PostGetPostDetailModel {

	private String userID;
	private String itBarxUserName;
	private String PostID;
	private String AddedDate;
	private String PostSpeechToText;
	private String PostLikeCount;
	private String PostReplyCount;
	private String PostRebarkCount;
	private String PostPictureURL;
	private String PostURL;

	public String getUserID() {
		return userID;
	}

	public PostGetPostDetailModel setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public PostGetPostDetailModel setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
		return this;
	}

	public String getPostID() {
		return PostID;
	}

	public PostGetPostDetailModel setPostID(String postID) {
		PostID = postID;
		return this;
	}

	public String getAddedDate() {
		return AddedDate;
	}

	public PostGetPostDetailModel setAddedDate(String addedDate) {
		AddedDate = addedDate;
		return this;
	}

	public String getPostSpeechToText() {
		return PostSpeechToText;
	}

	public PostGetPostDetailModel setPostSpeechToText(String postSpeechToText) {
		PostSpeechToText = postSpeechToText;
		return this;
	}

	public String getPostLikeCount() {
		return PostLikeCount;
	}

	public PostGetPostDetailModel setPostLikeCount(String postLikeCount) {
		PostLikeCount = postLikeCount;
		return this;
	}

	public String getPostReplyCount() {
		return PostReplyCount;
	}

	public PostGetPostDetailModel setPostReplyCount(String postReplyCount) {
		PostReplyCount = postReplyCount;
		return this;
	}

	public String getPostRebarkCount() {
		return PostRebarkCount;
	}

	public PostGetPostDetailModel setPostRebarkCount(String postRebarkCount) {
		PostRebarkCount = postRebarkCount;
		return this;
	}

	public String getPostURL() {
		return PostURL;
	}

	public PostGetPostDetailModel setPostURL(String postURL) {
		PostURL = postURL;
		return this;
	}

	public String getPostPictureURL() {
		return PostPictureURL;
	}

	public PostGetPostDetailModel setPostPictureURL(String postPictureURL) {
		PostPictureURL = postPictureURL;
		return this;
	}
}
