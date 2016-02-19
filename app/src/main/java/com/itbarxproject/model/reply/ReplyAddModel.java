package com.itbarxproject.model.reply;

public class ReplyAddModel {

	private String postID;
	private String postSenderUserId;
	private String postSpeechToText;
	private String postText;
	private String postSenderIp;
	private String postReplyByte;

	public ReplyAddModel() {
	super();
	}

	public ReplyAddModel(String postID, String postSenderUserId, String postSpeechToText, String postText, String postSenderIp, String postReplyByte) {
	super();
	this.postID = postID;
	this.postSenderUserId = postSenderUserId;
	this.postSpeechToText = postSpeechToText;
	this.postText = postText;
	this.postSenderIp = postSenderIp;
	this.postReplyByte = postReplyByte;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

	public String getPostSenderUserId() {
	return postSenderUserId;
	}

	public void setPostSenderUserId(String postSenderUserId) {
	this.postSenderUserId = postSenderUserId;
	}

	public String getPostSpeechToText() {
	return postSpeechToText;
	}

	public void setPostSpeechToText(String postSpeechToText) {
	this.postSpeechToText = postSpeechToText;
	}

	public String getPostText() {
	return postText;
	}

	public void setPostText(String postText) {
	this.postText = postText;
	}

	public String getPostSenderIp() {
	return postSenderIp;
	}

	public void setPostSenderIp(String postSenderIp) {
	this.postSenderIp = postSenderIp;
	}

	public String getPostReplyByte() {
	return postReplyByte;
	}

	public void setPostReplyByte(String postReplyByte) {
	this.postReplyByte = postReplyByte;
	}

}
