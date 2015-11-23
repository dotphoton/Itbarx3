package com.itbarx.model.rebark;

public class ReBarkGetPostSharedUserListModel {

	private String rowNum;
	private String shareId;
	private String postId;
	private String sharer;
	private String sharedDate;
	private String sharerName;
	private String sharerPhoto;

	public ReBarkGetPostSharedUserListModel() {
	super();
	}

	public ReBarkGetPostSharedUserListModel(String rowNum, String shareId, String postId, String sharer, String sharedDate, String sharerName, String sharerPhoto) {
	super();
	this.rowNum = rowNum;
	this.shareId = shareId;
	this.postId = postId;
	this.sharer = sharer;
	this.sharedDate = sharedDate;
	this.sharerName = sharerName;
	this.sharerPhoto = sharerPhoto;
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

	public String getPostId() {
	return postId;
	}

	public void setPostId(String postId) {
	this.postId = postId;
	}

	public String getSharer() {
	return sharer;
	}

	public void setSharer(String sharer) {
	this.sharer = sharer;
	}

	public String getSharedDate() {
	return sharedDate;
	}

	public void setSharedDate(String sharedDate) {
	this.sharedDate = sharedDate;
	}

	public String getSharerName() {
	return sharerName;
	}

	public void setSharerName(String sharerName) {
	this.sharerName = sharerName;
	}

	public String getSharerPhoto() {
	return sharerPhoto;
	}

	public void setSharerPhoto(String sharerPhoto) {
	this.sharerPhoto = sharerPhoto;
	}

}
