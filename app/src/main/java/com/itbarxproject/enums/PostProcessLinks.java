package com.itbarxproject.enums;

public enum PostProcessLinks {

	GET_TIMELINE("post/gettimeline"), GET_WALL("post/getwall"), GET_POPULAR("post/getpopular"), GET_NEW_POST("post/getnews"), GET_WALL_INFO("post/getwallinfo"), GET_POST_DETAIL(
		"post/getpostdetail"), ADD_POST("post/add");

	private final String link;

	private PostProcessLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {

	return this.link;
	}

}
