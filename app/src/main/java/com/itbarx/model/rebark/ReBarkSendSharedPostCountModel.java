package com.itbarx.model.rebark;


//rebark edilen post sayısı - user bazli
public class ReBarkSendSharedPostCountModel {
	private String userID;

	public ReBarkSendSharedPostCountModel() {
	super();
	}

	public ReBarkSendSharedPostCountModel(String userID) {
	super();
	this.userID = userID;
	}

	public String getUserID() {
	return userID;
	}

	public void setUserID(String userID) {
	this.userID = userID;
	}

}
