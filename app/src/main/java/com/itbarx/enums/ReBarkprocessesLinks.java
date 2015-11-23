package com.itbarx.enums;

public enum ReBarkprocessesLinks {

	SHARE_POST_ADD("postshare/add"), DELETE("postshare/delete"),	GET_SHARED_POST("postshare/getsharedposts"), GET_POSTSHARED_USER("postshare/getsharedusers"), 
	USER_COUNT("postshare/sharedusercount"), POST_COUNT_SHARED_BY_USER("postshare/sharedpostcount");

	private String link;

	private ReBarkprocessesLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {
	return this.link;
	}

}
