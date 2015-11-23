package com.itbarx.model.reply;

public class ReplyListModel {

	private String rowNum;
	private String postID;
	private String postReplyID;
	private String postSenderUserId;
	private String itBarxUserName;
	private String addedDate;
	private String postText;
	private String postURL;
	private String postPictureURL;

	public ReplyListModel() {
	super();
	}

	public ReplyListModel(String rowNum, String postID, String postReplyID, String postSenderUserId, String itBarxUserName, String addedDate, String postText, String postURL, String postPictureURL) {
	super();
	this.rowNum = rowNum;
	this.postID = postID;
	this.postReplyID = postReplyID;
	this.postSenderUserId = postSenderUserId;
	this.itBarxUserName = itBarxUserName;
	this.addedDate = addedDate;
	this.postText = postText;
	this.postURL = postURL;
	this.postPictureURL = postPictureURL;
	}

	public String getRowNum() {
	return rowNum;
	}

	public void setRowNum(String rowNum) {
	this.rowNum = rowNum;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

	public String getPostReplyID() {
	return postReplyID;
	}

	public void setPostReplyID(String postReplyID) {
	this.postReplyID = postReplyID;
	}

	public String getPostSenderUserId() {
	return postSenderUserId;
	}

	public void setPostSenderUserId(String postSenderUserId) {
	this.postSenderUserId = postSenderUserId;
	}

	public String getItBarxUserName() {
	return itBarxUserName;
	}

	public void setItBarxUserName(String itBarxUserName) {
	this.itBarxUserName = itBarxUserName;
	}

	public String getAddedDate() {
	return addedDate;
	}

	public void setAddedDate(String addedDate) {
	this.addedDate = addedDate;
	}

	public String getPostText() {
	return postText;
	}

	public void setPostText(String postText) {
	this.postText = postText;
	}

	public String getPostURL() {
	return postURL;
	}

	public void setPostURL(String postURL) {
	this.postURL = postURL;
	}

	public String getPostPictureURL() {
	return postPictureURL;
	}

	public void setPostPictureURL(String postPictureURL) {
	this.postPictureURL = postPictureURL;
	}

}
