package com.itbarxproject.model.rebark;


//rebark eden kullanıcı sayısı - post bazlı
public class ReBarkSendSharedUserCountModel {

	private String postID;

	public ReBarkSendSharedUserCountModel() {
	super();
	}

	public ReBarkSendSharedUserCountModel(String postID) {
	super();
	this.postID = postID;
	}

	public String getPostID() {
	return postID;
	}

	public void setPostID(String postID) {
	this.postID = postID;
	}

}
