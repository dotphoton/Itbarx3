package com.itbarx.model.rebark;

public class ReBarkGetSharedPostListModel {

	private String rowNum;
	private String shareId;
	private String sharedPostId;
	private String sharedDate;
	private String sharedText;
	private String sharedPostLikeCount;
	private String sharedPostReplyCount;
	private String sharedPostShareCount;
	private String sharedPostCoummentCount;
	private String realOwner;
	private String realOwnerName;
	private String postText;
	private String postURL;

	public ReBarkGetSharedPostListModel() {
	super();
	}

	public ReBarkGetSharedPostListModel(String rowNum, String shareId, String sharedPostId, String sharedDate, String sharedText, String sharedPostLikeCount, String sharedPostReplyCount,
		String sharedPostShareCount, String sharedPostCoummentCount, String realOwner, String realOwnerName, String postText, String postURL) {
	super();
	this.rowNum = rowNum;
	this.shareId = shareId;
	this.sharedPostId = sharedPostId;
	this.sharedDate = sharedDate;
	this.sharedText = sharedText;
	this.sharedPostLikeCount = sharedPostLikeCount;
	this.sharedPostReplyCount = sharedPostReplyCount;
	this.sharedPostShareCount = sharedPostShareCount;
	this.sharedPostCoummentCount = sharedPostCoummentCount;
	this.realOwner = realOwner;
	this.realOwnerName = realOwnerName;
	this.postText = postText;
	this.postURL = postURL;
	}

	public String getRowNum() {
	return rowNum;
	}

	public void setRowNum(String rowNum) {
	this.rowNum = rowNum;
	}

	public String getShareId() {
	return shareId;
	}

	public void setShareId(String shareId) {
	this.shareId = shareId;
	}

	public String getSharedPostId() {
	return sharedPostId;
	}

	public void setSharedPostId(String sharedPostId) {
	this.sharedPostId = sharedPostId;
	}

	public String getSharedDate() {
	return sharedDate;
	}

	public void setSharedDate(String sharedDate) {
	this.sharedDate = sharedDate;
	}

	public String getSharedText() {
	return sharedText;
	}

	public void setSharedText(String sharedText) {
	this.sharedText = sharedText;
	}

	public String getSharedPostLikeCount() {
	return sharedPostLikeCount;
	}

	public void setSharedPostLikeCount(String sharedPostLikeCount) {
	this.sharedPostLikeCount = sharedPostLikeCount;
	}

	public String getSharedPostReplyCount() {
	return sharedPostReplyCount;
	}

	public void setSharedPostReplyCount(String sharedPostReplyCount) {
	this.sharedPostReplyCount = sharedPostReplyCount;
	}

	public String getSharedPostShareCount() {
	return sharedPostShareCount;
	}

	public void setSharedPostShareCount(String sharedPostShareCount) {
	this.sharedPostShareCount = sharedPostShareCount;
	}

	public String getSharedPostCoummentCount() {
	return sharedPostCoummentCount;
	}

	public void setSharedPostCoummentCount(String sharedPostCoummentCount) {
	this.sharedPostCoummentCount = sharedPostCoummentCount;
	}

	public String getRealOwner() {
	return realOwner;
	}

	public void setRealOwner(String realOwner) {
	this.realOwner = realOwner;
	}

	public String getRealOwnerName() {
	return realOwnerName;
	}

	public void setRealOwnerName(String realOwnerName) {
	this.realOwnerName = realOwnerName;
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

}
