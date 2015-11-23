package com.itbarx.model.post;

public class PostAddPostModel {

	private String postSpeechText;
	private String postSenderUserId;
	private String postSenderIp;
	private String videoBytes;
	private String postAddedTimeZoneId;

	public PostAddPostModel() {
	super();
	}

	public PostAddPostModel(String postSpeechText, String postSenderUserId, String postSenderIp, String videoBytes) {
	super();
	this.postSpeechText = postSpeechText;
	this.postSenderUserId = postSenderUserId;
	this.postSenderIp = postSenderIp;
	this.videoBytes = videoBytes;
	}

	public String getPostSpeechText() {
	return postSpeechText;
	}

	public void setPostSpeechText(String postSpeechText) {
	this.postSpeechText = postSpeechText;
	}

	public String getPostSenderUserId() {
	return postSenderUserId;
	}

	public void setPostSenderUserId(String postSenderUserId) {
	this.postSenderUserId = postSenderUserId;
	}

	public String getPostSenderIp() {
	return postSenderIp;
	}

	public void setPostSenderIp(String postSenderIp) {
	this.postSenderIp = postSenderIp;
	}

	public String getVideoBytes() {
	return videoBytes;
	}

	public void setVideoBytes(String videoBytes) {
	this.videoBytes = videoBytes;
	}

	public String getPostAddedTimeZoneId() {
		return postAddedTimeZoneId;
	}

	public void setPostAddedTimeZoneId(String postAddedTimeZoneId) {
		this.postAddedTimeZoneId = postAddedTimeZoneId;
	}
}
